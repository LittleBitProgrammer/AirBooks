package it.corelab.airbooks.section.intro.activity

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
import it.corelab.airbooks.section.intro.handler.OnBoardingAdapter
import it.corelab.airbooks.section.intro.interfaces.*
import it.corelab.airbooks.section.intro.pages.host.OnBoardingViewPagerHost
import it.corelab.airbooks.widget.RoundedImageView

class IntroActivity : AppCompatActivity(), IntroMethod, AnimationPageTwo, AnimationPageThree, AnimationPageFour, PageTwoInitialization, PageThreeInitialization, PageFourInitialization {

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

    //VARIABLES OF THE THIRD PAGE
    private lateinit var bookSpot: ImageView
    private lateinit var textSpot: TextView
    private lateinit var starOne: ImageView
    private lateinit var starTwo: ImageView
    private lateinit var starThree: ImageView
    private lateinit var starFour: ImageView
    private lateinit var starFive: ImageView
    private lateinit var userImage: RoundedImageView

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


        OnBoardingViewPagerHost().setContentView(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pager = findViewById<ViewPager>(R.id.ONBOARDING_VIEWPAGER_BASE)
        indicator01 = findViewById(R.id.INDICATOR_ONE)
        indicator02 = findViewById(R.id.INDICATOR_TWO)
        indicator03 = findViewById(R.id.INDICATOR_THREE)
        indicator04 = findViewById(R.id.INDICATOR_FOUR)
        indicators = arrayOf(indicator01, indicator02, indicator03, indicator04)

        updateIndicator(pagePosition)


        // Set Adapter on ViewPager
        pager.adapter = OnBoardingAdapter(supportFragmentManager)

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
                            initializePageTwoVariables()
                            animatePageTwo()
                        }

                    }

                    2->{

                        if (!isAnimatedPageThree){
                            isAnimatedPageThree = true
                            initializePageThreeVariables()
                            animatePageThree()
                        }

                    }

                    3->{

                        if (!isAnimatedPageFour){
                            isAnimatedPageFour = true
                            initializePageFourVariables()
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
        animateSecondShelfBook()
        animateFirstShelfBook()
        animateFourthShelfBook()
        animateThirdShelfBook()
        animateSixthShelfBook()
        animateFifthShelfBook()
    }

    override fun animatePageThree() {
        Log.i("TAG", "nt")
        animateSpotBook()
        animateSpotText()
        animateRating()
        animateUserImage()
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
        animateImageY(teacup,0F,100,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Tea cup animated")
    }

    override fun animatePlusTea() {
        animateTextY(plusUnderTea,0F,300,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Plus under tea cup animated")
    }

    override fun animateSofa() {
        animateImageY(sofa,0F,450,550,OvershootInterpolator(0.6F))
        Log.i(ANIMATION, "Sofa cup animated")
    }

    override fun animatePlusSofa() {
        animateTextY(plusUnderSofa,0F,650,550,OvershootInterpolator(0.6F))
        Log.i(ANIMATION, "Plus under sofa cup animated")
    }

    override fun animateBookOne() {
        animateImageX(bookOne,0F,1351,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book one animated")
    }

    override fun animateBookTwo() {
        animateImageX(bookTwo,0F,1150,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book two animated")
    }

    override fun animateBookThree() {
        animateImageX(bookThree,0F,950,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Book three animated")
    }

    override fun animateEqual() {
        animateTextY(equalSign,0F,1450,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Equal sign animated")
    }

    override fun animateEmojiFace() {
        animateImageY(emojiFace,0F,1650,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Emoji face animated")
    }

    override fun animateEmojiHeart() {
        animateImageY(emojiHeart,0F,1650,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Emoji heart animated")
    }


    //ANIMATION PAGE TWO INTERFACE

    override fun animateFirstShelfBook() {
        animateImage(firstBook,0F,0F,750,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "First book animated")
    }

    override fun animateSecondShelfBook() {
        animateImage(fourthBook,0F,0F,650,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Second book animated")
    }

    override fun animateThirdShelfBook() {
        animateImage(secondBook,0F,0F,950,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Third book animated")
    }

    override fun animateFourthShelfBook() {
        animateImage(fifthBook,0F,0F,850,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Fourth book animated")
    }

    override fun animateFifthShelfBook() {
        animateImage(thirdBook,0F,0F,1251,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Fifth book animated")
    }

    override fun animateSixthShelfBook() {
        animateImage(sixthBook,0F,0F,1150,550,OvershootInterpolator(0.8F))
        Log.i(ANIMATION, "Sixth book animated")
    }

    override fun animateShelfOne() {
        animateImageY(shelfOne,0F,450,650,OvershootInterpolator(1F))
        Log.i(ANIMATION, "Shelf book animated")
    }

    override fun animateShelfTwo() {
        animateImageY(shelfTwo,0F,350,650,OvershootInterpolator(1F))
        Log.i(ANIMATION, "First book two animated")
    }

    override fun animateShelfThree() {
        animateImageY(shelfThree,0F,250,650,OvershootInterpolator(1F))
        Log.i(ANIMATION, "Shelf book three animated")
    }


    //ANIMATION PAGE THREE
    override fun animateSpotBook() {
        animateImageY(bookSpot,0F,250,550,OvershootInterpolator(0.7F))
        Log.i(ANIMATION, "Spot book animated")
    }

    override fun animateSpotText() {
        animateTextY(textSpot,0F,500,550,OvershootInterpolator(0.7F))
        Log.i(ANIMATION, "Spot text animated")
    }

    override fun animateRating() {
        animateStarOne()
        animateStarTwo()
        animateStarThree()
        animateStarOneFour()
        animateStarOneFive()

        Log.i(ANIMATION, "RatingBar animated")
    }

    override fun animateUserImage() {
        animateImageY(userImage,0F,1000,550,OvershootInterpolator(0.81F))
        Log.i(ANIMATION, "User image animated")
    }

    override fun animateStarOne() {
        animateImageY(starOne,0F,750,550,OvershootInterpolator(0.5F))
        Log.i(ANIMATION, "Star One animated")
    }

    override fun animateStarTwo() {
        animateImageY(starTwo,0F,750,550,OvershootInterpolator(0.5F))
        Log.i(ANIMATION, "Star Two animated")
    }

    override fun animateStarThree() {
        animateImageY(starThree,0F,750,550,OvershootInterpolator(0.5F))
        Log.i(ANIMATION, "Star Three animated")
    }

    override fun animateStarOneFour() {
        animateImageY(starFour,0F,750,550,OvershootInterpolator(0.5F))
        Log.i(ANIMATION, "Star Four animated")
    }

    override fun animateStarOneFive() {
        animateImageY(starFive,0F,750,550,OvershootInterpolator(0.51F))
        Log.i(ANIMATION, "Star Five animated")
    }

    //PAGE THREE INITIALIZATION
    override fun initializePageThreeVariables() {

        bookSpot = findViewById(R.id.BOKK_INTRO_PAGE_THREE)
        textSpot = findViewById(R.id.SPOT_BOOK_INTRO)
        starOne = findViewById(R.id.STAR_ONE_INTRO)
        starTwo = findViewById(R.id.STAR_TWO_INTRO)
        starThree = findViewById(R.id.STAR_THREE_INTRO)
        starFour = findViewById(R.id.STAR_FOUR_INTRO)
        starFive = findViewById(R.id.STAR_FIVE_INTRO)
        userImage = findViewById(R.id.ROUNDED_IMAGE_INTRO)
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
