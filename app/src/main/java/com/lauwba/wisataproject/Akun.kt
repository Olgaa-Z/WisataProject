package com.lauwba.wisataproject

import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.lauwba.wisataproject.scanner.ScannerActivity
import kotlinx.android.synthetic.main.akun.*

class Akun : Fragment() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.akun, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        namauser.text =
            activity?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.getString(Constant.NAMA, "not set")
        email.text =
            activity?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.getString(Constant.EMAIL, "not set")
        alamat.text =
            activity?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.getString(Constant.ALAMAT, "not set")
        nohp.text = activity?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
            ?.getString(Constant.NO_TELP, "not set")


        startTransaction.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                var i: Intent
                i = Intent(activity, ScannerActivity::class.java)
                activity?.startActivity(i)
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                        arrayOf(
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.CAMERA
                        ), 200
                    )
                }
            }
        }

        logout.setOnClickListener {

            context?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.edit {
                    clear()
                }
            //
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
            Intent(activity, Welcome::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1025) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                var i: Intent
                i = Intent(activity, ScannerActivity::class.java)
                activity?.startActivity(i)
            }
        }
    }
}
