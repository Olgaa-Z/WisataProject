package com.lauwba.wisataproject

import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.akun.*

class Akun : Fragment() {

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
}
