package com.funmilola.medkit.feature.scan_splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.funmilola.medkit.R
import com.funmilola.medkit.feature.mvp.BaseMvpActivity
import com.funmilola.medkit.feature.scan.ScanActivity


class ScanSplashActivity: BaseMvpActivity<ScanSplashActivityContract.View, ScanSplashActivityContract.Presenter>(),
    ScanSplashActivityContract.View {

    private val RECORD_REQUEST_CODE = 101

    override var mPresenter: ScanSplashActivityContract.Presenter = ScanSplashActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_splash)
        setupPermissions()
    }

    override fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED)
            requestPermissions()
        else
            mPresenter.permissionGranted()
    }

    override fun startActivity() {
        startActivity(Intent(this, ScanActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA),
            RECORD_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    mPresenter.permissionDenied()
                } else {
                    mPresenter.permissionGranted()
                }
            }
        }
    }

}