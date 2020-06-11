package com.lauwba.wisataproject.scanner

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import com.google.zxing.BarcodeFormat
import com.lauwba.wisataproject.R
import kotlinx.android.synthetic.main.activity_scanner.*

class ScannerActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )
        setContentView(R.layout.activity_scanner)

        codeScanner = CodeScanner(this, scanner_view)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE) // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                //                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show() --> awalnya

//                startActivity(Intent(this@ScannerActivity, Hasil::class.java))
//                getDetailPelapak(it.text)

                val builder = AlertDialog.Builder(this)
                builder.setCancelable(false)
//                builder.setMessage("Keluar dari Aplikasi?")
                builder.setMessage("Scan result: ${it.text}")
                builder.setPositiveButton(
                    "Tutup",
                    DialogInterface.OnClickListener { dialog, which ->
                        //if user pressed "yes", then he is allowed to exit from application
                        finish()
                    })
                builder.setNegativeButton(
                    "Scan ulang",
                    DialogInterface.OnClickListener { dialog, which ->
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel()
                    })
                val alert = builder.create()
                alert.show()
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }
}
