package com.funmilola.medkit.feature.scan

import android.util.Patterns
import com.funmilola.medkit.R
import com.funmilola.medkit.feature.mvp.BaseMvpPresenterImpl
import com.google.api.ResourceDescriptor

open class ScanActivityPresenter: BaseMvpPresenterImpl<ScanActivityContract.View>(),
        ScanActivityContract.Presenter {

    private val preUrl: String = "http://www.google.com/#q="

    override fun searchByResultBtnPressed(result: String) {
        var url: String = result
        if (!Patterns.WEB_URL.matcher(result).matches())
            url = preUrl + result
        mView?.continueScanning()
        mView?.searchInWWW(url)
    }

    override fun copyResultBtnPressed(result: String) {
        mView?.copyToClipboard(result)
        mView?.continueScanning()
        mView?.showMessage(R.string.text_copied)
    }

    override fun shareResultBtnPressed(result: String) {
        mView?.continueScanning()
        mView?.shareResultViewSharingIntent(result)
    }

    override fun qrCodeScanned(history: ResourceDescriptor.History) {
        //mView?.showSuccessScanningDialog(history.context)
    }
}