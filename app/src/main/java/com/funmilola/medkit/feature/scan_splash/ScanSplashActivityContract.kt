package com.funmilola.medkit.feature.scan_splash

import com.funmilola.medkit.feature.mvp.BaseMvpPresenter
import com.funmilola.medkit.feature.mvp.BaseMvpView

object ScanSplashActivityContract {

    interface View : BaseMvpView {
        fun setupPermissions()
        fun startActivity()
        fun requestPermissions()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun permissionGranted()
        fun permissionDenied()
    }
}