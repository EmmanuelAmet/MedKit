package com.funmilola.medkit.feature.scan

import com.funmilola.medkit.feature.mvp.BaseMvpPresenter
import com.funmilola.medkit.feature.mvp.BaseMvpView
import com.google.api.ResourceDescriptor

object ScanActivityContract {

    interface View: BaseMvpView {
        fun showSuccessScanningDialog(result: String)
        fun continueScanning()
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun qrCodeScanned(history: ResourceDescriptor.History)
        fun searchByResultBtnPressed(result:String)
        fun copyResultBtnPressed(result: String)
        fun shareResultBtnPressed(result: String)
    }
}