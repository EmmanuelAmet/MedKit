package com.funmilola.medkit.feature.onboarding.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager2.widget.ViewPager2
import com.funmilola.medkit.LoginActivity
import com.funmilola.medkit.R
import com.funmilola.medkit.feature.onboarding.OnBoardingPagerAdapter
import com.funmilola.medkit.feature.onboarding.entity.OnBoardingPage
import com.funmilola.medkit.domain.OnBoardingPrefManager
import com.funmilola.medkit.feature.onboarding.OnBoardingActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_onbording.view.*
import setParallaxTransformation

class OnBoardingView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: OnBoardingPrefManager

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_onbording, this, true)
        setUpSlider(view)
        addingButtonsClickListeners()
        prefManager = OnBoardingPrefManager(view.context)
    }

    private fun setUpSlider(view: View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }
//BUTTON CLICK METHOD
    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
        }
        startBtn.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }

    private fun navigateToMainACtivity() {
    }
}