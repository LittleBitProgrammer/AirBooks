package it.corelab.airbooks.Intro

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import it.corelab.airbooks.AnimationPageFour
import it.corelab.airbooks.IntroMethod
import it.corelab.airbooks.PageFourInitialization
import it.corelab.airbooks.R
import org.jetbrains.anko.setContentView
import it.corelab.airbooks.Utils.*
import org.jetbrains.anko.find

class IntroActivity : AppCompatActivity(), IntroMethod, AnimationPageFour, PageFourInitialization {

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

    //VARIABLES OF THE FOURTH PAGE
    private lateinit var teacup: ImageView
    private lateinit var sofa: ImageView
    private lateinit var bookOne: ImageView
    private lateinit var bookTwo: ImageView
    private lateinit var bookThree: ImageView
    private lateinit var emojiFace: ImageView
    private lateinit var emojiHeart: ImageView
    private lateinit var plusUnderTea: TextView
    private lateinit var plusUnderSofa: TextView
    private lateinit var equalSign: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        OnBoardingViewPager_Host().setContentView(this)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pager = findViewById<ViewPager>(R.id.ONBOARDING_VIEWPAGER_BASE)
        indicator01 = findViewById(R.id.INDICATOR_ONE)
        indicator02 = findViewById(R.id.INDICATOR_TWO)
        indicator03 = findViewById(R.id.INDICATOR_THREE)
        indicator04 = findViewById(R.id.INDICATOR_FOUR)
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

                        initializePageFourVariables()

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



    //INTRO METHOD INTERFACE
    override fun animatePageTwo() {
        Log.i("TAG", "nt")
    }

    override fun animatePageThree() {
        Log.i("TAG", "nt")
    }

    override fun animatePageFour() {
        Log.i("TAG", "nt")
    }



    //ANIMATION PAGE FOUR INTERFACE

    override fun animateTeaCup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animatePlusTea() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateSofa() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animatePlusSofa() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateBookOne() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateBookTwo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateBookThree() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateEqual() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateEmojiFace() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateEmojiHeart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    //PAGEFOURINITIALIZATION INTERFACE

    override fun initializePageFourVariables() {
        teacup = findViewById(R.id.TEACUP)
        sofa = findViewById(R.id.SOFA)
        bookOne = findViewById(R.id.LEFT_BOOK_PAGE_FOUR)
        bookTwo = findViewById(R.id.CENTER_BOOK_PAGE_FOUR)
        bookThree = findViewById(R.id.RIGHT_BOOK_PAGE_FOUR)
        emojiFace = findViewById(R.id.FACE_HEART)
        emojiHeart = findViewById(R.id.HEART_EMOJI)
        plusUnderTea = findViewById(R.id.PLUS_UNDER_TEACUP)
        plusUnderSofa = findViewById(R.id.PLUS_UNDER_SOFA)
        equalSign = findViewById(R.id.EQUAL_PAGE_FOUR)
    }
}
