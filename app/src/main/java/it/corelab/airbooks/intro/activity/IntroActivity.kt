package it.corelab.airbooks.intro.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import it.corelab.airbooks.R
import org.jetbrains.anko.setContentView
import it.corelab.airbooks.Utils.*
import it.corelab.airbooks.Constants.*
import it.corelab.airbooks.intro.Handler.OnboardingAdapter
import it.corelab.airbooks.intro.Interface.*
import it.corelab.airbooks.intro.Pages.Host.OnBoardingViewPager_Host
import org.jetbrains.anko.find

class IntroActivity : AppCompatActivity(), IntroMethod, AnimationPageFour, AnimationPageTwo, PageFourInitialization, PageTwoInitialization {

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

    //VARIABLES OF THE SECOND PAGE
    private lateinit var shelfOne: ImageView
    private lateinit var shelfTwo: ImageView
    private lateinit var shelfThree: ImageView
    private lateinit var firstBook: ImageView
    private lateinit var secondBook: ImageView
    private lateinit var thirdBook: ImageView
    private lateinit var fourthBook: ImageView
    private lateinit var fifthBook: ImageView
    private lateinit var sixthBook: ImageView

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

                        initializePageTwoVariables()
                        
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
        Log.i(INTRO, "Created page two, animation starting")
        animateShelfThree()
        animateShelfTwo()
        animateShelfOne()
        animateBookTwo()
        animateSecondShelfBook()
        animateFirstShelfBook()
        animateFourthShelfBook()
        animateThirdShelfBook()
        animateSixthShelfBook()
        animateFifthShelfBook()
    }

    override fun animatePageThree() {
        Log.i("TAG", "nt")
    }

    override fun animatePageFour() {
        Log.i(INTRO, "created page four, animation starting...")
        animateTeaCup()
        animatePlusTea()
        animateSofa()
        animatePlusSofa()
        animateBookThree()
        animateBookTwo()
        animateBookOne()
        animateEqual()
        animateEmojiFace()
        animateEmojiHeart()
    }



    //ANIMATION PAGE FOUR INTERFACE

    override fun animateTeaCup() {
        animateImageY(teacup,2200F,0F,100,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Tea cup animated")
    }

    override fun animatePlusTea() {
        animateTextY(plusUnderTea,2200F,0F,450,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Plus under tea cup animated")
    }

    override fun animateSofa() {
        animateImageY(sofa,2200F,0F,800,850,OvershootInterpolator(0.6F))
        Log.i(ANIMATION, "Sofa cup animated")
    }

    override fun animatePlusSofa() {
        animateTextY(plusUnderSofa,2200F,0F,950,850,OvershootInterpolator(0.6F))
        Log.i(ANIMATION, "Plus under sofa cup animated")
    }

    override fun animateBookOne() {
        animateImageX(bookOne,-1000F,0F,2300,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book one animated")
    }

    override fun animateBookTwo() {
        animateImageX(bookTwo,-1000F,0F,2000,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book two animated")
    }

    override fun animateBookThree() {
        animateImageX(bookThree,-1000F,0F,1700,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book three animated")
    }

    override fun animateEqual() {
        animateTextY(equalSign,2200F,0F,2450,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Equal sign animated")
    }

    override fun animateEmojiFace() {
        animateImageY(emojiFace,2200F,0F,2500,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Emoji face animated")
    }

    override fun animateEmojiHeart() {
        animateImageY(emojiHeart,2200F,0F,2500,850,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Emoji heart animated")
    }


    //ANIMATION PAGE TWO INTERFACE

    override fun animateFirstShelfBook() {
        Log.i(ANIMATION, "First book animated")
    }

    override fun animateSecondShelfBook() {
        Log.i(ANIMATION, "Second book animated")
    }

    override fun animateThirdShelfBook() {
        Log.i(ANIMATION, "Third book animated")
    }

    override fun animateFourthShelfBook() {
        Log.i(ANIMATION, "Fourth book animated")
    }

    override fun animateFifthShelfBook() {
        Log.i(ANIMATION, "Fifth book animated")
    }

    override fun animateSixthShelfBook() {
        Log.i(ANIMATION, "Sixth book animated")
    }

    override fun animateShelfOne() {
        Log.i(ANIMATION, "Shelf book animated")
    }

    override fun animateShelfTwo() {
        Log.i(ANIMATION, "First book two animated")
    }

    override fun animateShelfThree() {
        Log.i(ANIMATION, "Shelf book three animated")
    }


    //PAGE TWO INITIALIZATION INTERFACE

    override fun initializePageTwoVariables() {

        shelfOne = findViewById(R.id.SHELF_ONE)
        shelfTwo = findViewById(R.id.SHELF_TWO)
        shelfThree = findViewById(R.id.SHELF_THREE)
        firstBook = findViewById(R.id.FIRST_FIRST_IMAGE)
        secondBook = findViewById(R.id.FIRST_SECOND_IMAGE)
        thirdBook = findViewById(R.id.FIRST_THIRD_IMAGE)
        fourthBook = findViewById(R.id.SECOND_FIRST_IMAGE)
        fifthBook = findViewById(R.id.SECOND_SECOND_IMAGE)
        sixthBook = findViewById(R.id.SECOND_THIRD_IMAGE)

    }


    //PAGE FOUR INITIALIZATION INTERFACE

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
