package it.corelab.airbooks.Intro

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.ImageView
import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import it.corelab.airbooks.IntroMethod
import it.corelab.airbooks.R
import org.jetbrains.anko.setContentView

class IntroActivity : AppCompatActivity(), IntroMethod {

    //CLASS VARIABLES
    private var isAnimatedPageTwo :Boolean = false
    private var isAnimatedPageThree :Boolean = false
    private var isAnimatedPageFour :Boolean = false

    //VARIABLE OF THE HOST FRAGMENT
    private var pagePosition = 0
    private var indicators: Array<ImageView>? = null
    private lateinit var indicator01: ImageView
    private lateinit var indicator02: ImageView
    private lateinit var indicator03: ImageView
    private lateinit var indicator04: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        OnBoardingViewPager_Host().setContentView(this)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pager = findViewById<ViewPager>(R.id.ONBOARDING_VIEWPAGER_BASE)
        indicator01 = findViewById<ImageView>(R.id.INDICATOR_ONE)
        indicator02 = findViewById<ImageView>(R.id.INDICATOR_TWO)
        indicator03 = findViewById<ImageView>(R.id.INDICATOR_THREE)
        indicator04 = findViewById<ImageView>(R.id.INDICATOR_FOUR)
        indicators = arrayOf(indicator01, indicator02, indicator03, indicator04)

        updateIndicator(pagePosition)


        // Set Adapter on ViewPager
        pager.adapter = OnboardingAdapter(supportFragmentManager)

        // Set PageTransformer on ViewPager
        pager.setPageTransformer(false, DefaultTransformer())
        pager.offscreenPageLimit = 4

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //

            }

            override fun onPageSelected(position: Int) {
                pagePosition = position
                updateIndicator(pagePosition)
                when (pagePosition){

                    0->{

                    }

                    1->{

                        if (!isAnimatedPageTwo){
                            isAnimatedPageTwo = true
                            animatePageTwo()
                        }

                    }

                    2->{

                        if (!isAnimatedPageThree){
                            isAnimatedPageThree = true
                            animatePageThree()
                        }

                    }

                    3->{

                        if (!isAnimatedPageFour){
                            isAnimatedPageFour = true
                            animatePageFour()
                        }

                    }

                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun updateIndicator(pagePosition: Int) {
        for (i in indicators!!.indices) {
            indicators!![i].setBackgroundResource(
                    if (i == pagePosition) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }

    override fun animatePageTwo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animatePageThree() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animatePageFour() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
