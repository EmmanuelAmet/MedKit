package com.funmilola.medkit.feature.scan_splash

import com.funmilola.medkit.feature.mvp.BaseMvpPresenterImpl

open class ScanSplashActivityPresenter: BaseMvpPresenterImpl<ScanSplashActivityContract.View>(),
        ScanSplashActivityContract.Presenter {

    override fun permissionGranted() {
        mView?.startActivity()
    }

    override fun permissionDenied() {
        mView?.setupPermissions()
    }


}