package com.lauwba.wisataproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lauwba.wisataproject.video.ExoVideoPlayer
import com.lauwba.wisataproject.wisata.AddWisata
import kotlinx.android.synthetic.main.activity_inbox.*

class Inbox : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.activity_inbox, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputwisata.setOnClickListener {
            var i: Intent
            i = Intent(activity, AddWisata::class.java)
            activity?.startActivity(i)
        }

        inputvideo.setOnClickListener {
            var i: Intent
            i = Intent(activity, ExoVideoPlayer::class.java)
            activity?.startActivity(i)
        }

        mutasiadmin.setOnClickListener {
            var i: Intent
            i = Intent(activity, ListBank::class.java)
            activity?.startActivity(i)
        }
    }
}
