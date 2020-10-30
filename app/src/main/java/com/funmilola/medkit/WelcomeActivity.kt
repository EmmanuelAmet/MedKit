package com.funmilola.medkit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.funmilola.medkit.feature.meet_a_doc.MeetADoctorActivity
import com.funmilola.medkit.feature.scan_splash.ScanSplashActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun onScan(view: View?){
        val intent = Intent(this, ScanSplashActivity::class.java)
        startActivity(intent)
    }

    fun onMeetDoc(view: View?){
        val intent = Intent(this, MeetADoctorActivity::class.java)
        startActivity(intent)
    }
}