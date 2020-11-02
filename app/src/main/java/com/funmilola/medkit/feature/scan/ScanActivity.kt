package com.funmilola.medkit.feature.scan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.funmilola.medkit.R
import com.funmilola.medkit.WelcomeActivity
import com.funmilola.medkit.feature.drugs_identified.DrugsIdentifiedActivity
import com.funmilola.medkit.feature.mvp.BaseMvpActivity
import com.funmilola.medkit.utils.Constants
import com.google.api.ResourceDescriptor
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_scan.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.text.DateFormat
import java.util.*

class ScanActivity : BaseMvpActivity<ScanActivityContract.View, ScanActivityContract.Presenter>(),
    ScanActivityContract.View, View.OnClickListener, ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private var flashState: Boolean = false
    private var dialog: AlertDialog? = null
    //private var mHistoryOrm: HistoryORM? = null

    override var mPresenter: ScanActivityContract.Presenter = ScanActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)


        initUI()
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    private fun initUI() {
        mScannerView = ZXingScannerView(this)
        frmContent.addView(mScannerView)
        btnLight.setOnClickListener(this)
        btnHistory.setOnClickListener(this)
        privacyPolicyTextView.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLight -> {
                if(flashState) {
                    v.setBackgroundResource(R.mipmap.ic_flash_on)
                    showMessage(R.string.flashlight_turned_off)
                    mScannerView?.setFlash(false)
                    flashState = false
                }else {
                    v.setBackgroundResource(R.mipmap.ic_flash_off)
                    showMessage(R.string.flashlight_turned_on)
                    mScannerView?.setFlash(true)
                    flashState = true
                }
            }
            R.id.btnHistory -> {
                //startActivity(Intent(this, HistoryActivity::class.java))
            }
            R.id.privacyPolicyTextView -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.privacyPolicy))
                startActivity(browserIntent)
            }
        }
    }

    override fun handleResult(result: Result?) {
//        var history: ResourceDescriptor.History = ResourceDescriptor.History(
//            DateFormat.getDateTimeInstance().format(Calendar.getInstance().time),
//            result?.text.toString()
//        )
//        mHistoryOrm?.add(this, history)
        //mPresenter.qrCodeScanned(history)

    }

    override fun showSuccessScanningDialog(result: String) {
        val intent = Intent(this, DrugsIdentifiedActivity::class.java)
        startActivity(intent)
    }

    override fun continueScanning() {
        dialog?.dismiss()
        mScannerView?.resumeCameraPreview(this)
    }
}