package it.corelab.airbooks.intro.Pages.Layout

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import it.corelab.airbooks.R
import org.jetbrains.anko.*
import android.support.v4.content.res.ResourcesCompat
import it.corelab.airbooks.intro.Handler.OnboardingFragment


class OnboardingOne_Laoyout : AnkoComponent<OnboardingFragment> {



    override fun createView(ui: AnkoContext<OnboardingFragment>) = with(ui) {

        /*
        *
        * XLARGE CONFIGURATION / TABLET - VERSION
        *
         */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.XLARGE ){

            /*
        *
        * External linear layout this include other
        * two linear layout that divide in two equally
        * parts the screen
        *
         */

            return@with linearLayout {

                id = Ids.LINEAR_LAYOUT_TOTAL_EXTERN
                orientation = LinearLayout.VERTICAL

                /*
                *
                * This is the TOP Linear layout
                * that include other three linear layout
                *
                 */

                linearLayout {
                    id = Ids.LINEAR_LAYOUT_TOP
                    orientation = LinearLayout.HORIZONTAL

                    //this.setBackgroundColor(resources.getColor(R.color.biografyDark))


                    //*****************************************************************************
                    //      Here we initialize the three linear layout in two equally space
                    //                             Center = bigger
                    //*****************************************************************************

                    //_______________________________________________________________________________

                    /*
                    *
                    * this is the LEFT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_ONE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.comicsDark))

                    }.lparams(width = dip(0),height = matchParent, weight = 1F)

                    /*
                    *
                    * this is the CENTER linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_TWO
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                        imageView(R.drawable.book2) {
                            id = Ids.TOP_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = -600F

                            val anims4 = AnimatorSet()
                            val sY4 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y,  -600f, 0f)
                            sY4.startDelay = 550
                            anims4.duration = 350
                            anims4.play(sY4)
                            anims4.interpolator = OvershootInterpolator(1.75f)

                            anims4.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            topMargin = dip(12)
                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book3) {
                            id = Ids.DOWN_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = 600F

                            val anims5 = AnimatorSet()
                            val sY5 = ObjectAnimator.ofFloat( this@imageView, View.TRANSLATION_Y,  600f, 0f)
                            sY5.startDelay = 700
                            anims5.duration = 350
                            anims5.play(sY5)
                            anims5.interpolator = OvershootInterpolator(1.75f)

                            anims5.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1.2F)

                    /*
                    *
                    * this is the RIGHT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_THREE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                        imageView(R.drawable.book5) {
                            id = Ids.TOP_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.x = 400.0F
                            this@imageView.y = -400.0F

                            val anims6 = AnimatorSet()
                            val sX6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)

                            sY6.startDelay = 850
                            sX6.startDelay = 850
                            anims6.duration = 350
                            anims6.playTogether(sX6, sY6)
                            anims6.interpolator = OvershootInterpolator(1.75f)

                            anims6.start()

                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(-60)
                            marginEnd = dip(-15)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book6) {
                            id = Ids.CENTER_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F

                            val anims7 = AnimatorSet()
                            val sX7 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            sX7.startDelay = 1000
                            anims7.duration = 350
                            anims7.play(sX7)
                            anims7.interpolator = OvershootInterpolator(1.75f)

                            anims7.start()

                        }.lparams(width = dip(128), height = dip(162)){
                            topMargin = dip(4)
                            marginStart = dip(2)
                            marginEnd = dip(-15)
                        }

                        imageView(R.drawable.book1) {
                            id = Ids.DOWN_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F
                            this@imageView.y = 400.0F

                            val anims8 = AnimatorSet()
                            val sX8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, 400f, 0f)
                            sX8.startDelay = 1150
                            sY8.startDelay = 1150
                            anims8.duration = 350
                            anims8.playTogether(sX8, sY8)
                            anims8.interpolator = OvershootInterpolator(1.75f)

                            anims8.start()

                        }.lparams(width = dip(128), height = dip(158)){

                            topMargin = dip(4)
                            marginStart = dip(0)
                            marginEnd = dip(-14)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1F)

                }.lparams(width = matchParent, height = dip(0), weight = 1.4F){
                    topMargin = dip(25)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

                /*
                *
                * This is the BOTTOM linear layout
                * that include some text information
                *
                 */

                linearLayout{

                    id = Ids.LINEAR_LAYOUT_BOTTOM
                    orientation = LinearLayout.VERTICAL

                    //elevation = dip(106).toFloat()
                    //this.outlineProvider = null

                    textView{
                        id = Ids.ONBOARDING_TEXT_TITLE
                        text = resources.getText(R.string.onboarding_one_title)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                        textSize = sp(8).toFloat()
                    }.lparams(width = matchParent, height = wrapContent){

                        topMargin = dip(20)
                        marginStart = dip(15)
                        marginEnd = dip(15)

                    }

                    textView{
                        id = Ids.ONBOARDING_TEXT_DESCRIPTION
                        text = resources.getText(R.string.onboarding_one_description)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                        textSize = sp(5).toFloat()

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(8)
                        marginStart = dip(15)
                        marginEnd = dip(15)
                    }

                }.lparams(width = matchParent, height = dip(0), weight = 1F)
            }
        }

        /*
        *
        * LARGE CONFIGURATION - VERSION
        *
         */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.LARGE ){

            /*
    *
    * External linear layout this include other
    * two linear layout that divide in two equally
    * parts the screen
    *
     */

            return@with linearLayout {

                id = Ids.LINEAR_LAYOUT_TOTAL_EXTERN
                orientation = LinearLayout.VERTICAL

                /*
                *
                * This is the TOP Linear layout
                * that include other three linear layout
                *
                 */

                linearLayout {
                    id = Ids.LINEAR_LAYOUT_TOP
                    orientation = LinearLayout.HORIZONTAL

                    //this.setBackgroundColor(resources.getColor(R.color.biografyDark))


                    //*****************************************************************************
                    //      Here we initialize the three linear layout in two equally space
                    //                             Center = bigger
                    //*****************************************************************************

                    //_______________________________________________________________________________

                    /*
                    *
                    * this is the LEFT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_ONE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.comicsDark))

                    }.lparams(width = dip(0),height = matchParent, weight = 1F)

                    /*
                    *
                    * this is the CENTER linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_TWO
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                        imageView(R.drawable.book2) {
                            id = Ids.TOP_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = -600F

                            val anims4 = AnimatorSet()
                            val sY4 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y,  -600f, 0f)
                            sY4.startDelay = 550
                            anims4.duration = 350
                            anims4.play(sY4)
                            anims4.interpolator = OvershootInterpolator(1.75f)

                            anims4.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            topMargin = dip(12)
                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book3) {
                            id = Ids.DOWN_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = 600F

                            val anims5 = AnimatorSet()
                            val sY5 = ObjectAnimator.ofFloat( this@imageView, View.TRANSLATION_Y,  600f, 0f)
                            sY5.startDelay = 700
                            anims5.duration = 350
                            anims5.play(sY5)
                            anims5.interpolator = OvershootInterpolator(1.75f)

                            anims5.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1.2F)

                    /*
                    *
                    * this is the RIGHT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_THREE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                        imageView(R.drawable.book5) {
                            id = Ids.TOP_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.x = 400.0F
                            this@imageView.y = -400.0F

                            val anims6 = AnimatorSet()
                            val sX6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)

                            sY6.startDelay = 850
                            sX6.startDelay = 850
                            anims6.duration = 350
                            anims6.playTogether(sX6, sY6)
                            anims6.interpolator = OvershootInterpolator(1.75f)

                            anims6.start()

                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(-60)
                            marginEnd = dip(-15)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book6) {
                            id = Ids.CENTER_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F

                            val anims7 = AnimatorSet()
                            val sX7 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            sX7.startDelay = 1000
                            anims7.duration = 350
                            anims7.play(sX7)
                            anims7.interpolator = OvershootInterpolator(1.75f)

                            anims7.start()

                        }.lparams(width = dip(128), height = dip(162)){
                            topMargin = dip(4)
                            marginStart = dip(2)
                            marginEnd = dip(-15)
                        }

                        imageView(R.drawable.book1) {
                            id = Ids.DOWN_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F
                            this@imageView.y = 400.0F

                            val anims8 = AnimatorSet()
                            val sX8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, 400f, 0f)
                            sX8.startDelay = 1150
                            sY8.startDelay = 1150
                            anims8.duration = 350
                            anims8.playTogether(sX8, sY8)
                            anims8.interpolator = OvershootInterpolator(1.75f)

                            anims8.start()

                        }.lparams(width = dip(128), height = dip(158)){

                            topMargin = dip(4)
                            marginStart = dip(0)
                            marginEnd = dip(-14)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1F)

                }.lparams(width = matchParent, height = dip(0), weight = 1.4F){
                    topMargin = dip(25)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

                /*
                *
                * This is the BOTTOM linear layout
                * that include some text information
                *
                 */

                linearLayout{

                    id = Ids.LINEAR_LAYOUT_BOTTOM
                    orientation = LinearLayout.VERTICAL

                    //elevation = dip(106).toFloat()
                    //this.outlineProvider = null

                    textView{
                        id = Ids.ONBOARDING_TEXT_TITLE
                        text = resources.getText(R.string.onboarding_one_title)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                        textSize = sp(8).toFloat()
                    }.lparams(width = matchParent, height = wrapContent){

                        topMargin = dip(20)
                        marginStart = dip(15)
                        marginEnd = dip(15)

                    }

                    textView{
                        id = Ids.ONBOARDING_TEXT_DESCRIPTION
                        text = resources.getText(R.string.onboarding_one_description)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                        textSize = sp(5).toFloat()

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(8)
                        marginStart = dip(15)
                        marginEnd = dip(15)
                    }

                }.lparams(width = matchParent, height = dip(0), weight = 1F)
            }
        }

        /*
        *
        * NORMAL CONFIGURATION - VERSION
        *
         */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.NORMAL ){

            /*
    *
    * External linear layout this include other
    * two linear layout that divide in two equally
    * parts the screen
    *
     */

            return@with linearLayout {

                id = Ids.LINEAR_LAYOUT_TOTAL_EXTERN
                orientation = LinearLayout.VERTICAL

                /*
                *
                * This is the TOP Linear layout
                * that include other three linear layout
                *
                 */

                linearLayout {
                    id = Ids.LINEAR_LAYOUT_TOP
                    orientation = LinearLayout.HORIZONTAL

                    //this.setBackgroundColor(resources.getColor(R.color.biografyDark))


                    //*****************************************************************************
                    //      Here we initialize the three linear layout in two equally space
                    //                             Center = bigger
                    //*****************************************************************************

                    //_______________________________________________________________________________

                    /*
                    *
                    * this is the LEFT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_ONE
                        orientation = LinearLayout.VERTICAL

                        imageView(R.drawable.book6) {
                            id = Ids.HIGH_LEFT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.y = -400.0F
                            this@imageView.x = -400.0F
                            val anims = AnimatorSet()
                            val sX = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, -400f, 0f)
                            val sY = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)
                            sX.startDelay = 100
                            sY.startDelay = 100
                            anims.duration = 350
                            anims.playTogether(sX, sY)
                            anims.interpolator = OvershootInterpolator(1.75f)

                            anims.start()
                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(-93)
                            marginStart = dip(-15)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        imageView(R.drawable.book5){
                            id = Ids.CENTER_LEFT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.x = -400.0F

                            val anims3 = AnimatorSet()
                            val sX3 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, -400f, 0f)
                            sX3.startDelay = 250
                            anims3.duration = 350
                            anims3.playTogether(sX3)
                            anims3.interpolator = OvershootInterpolator(1.75f)

                            anims3.start()
                        }.lparams(width = dip(128), height = dip(158)){

                            marginStart = dip(-15)
                            topMargin = dip(4)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        imageView(R.drawable.book7){
                            id = Ids.DOWN_LEFT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY


                            this@imageView.x = -400F
                            this@imageView.elevation = dip(100).toFloat()

                            val anims2 = AnimatorSet()
                            val sX2 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, -400f, 0f)
                            sX2.startDelay = 400
                            anims2.duration = 350
                            anims2.play(sX2)
                            anims2.interpolator = OvershootInterpolator(1.75f)

                            anims2.start()
                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(2)
                            bottomMargin = dip(-40)
                            marginStart = dip(-12)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        //this.setBackgroundColor(resources.getColor(R.color.comicsDark))

                    }.lparams(width = dip(0),height = matchParent, weight = 1F)

                    /*
                    *
                    * this is the CENTER linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_TWO
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                        imageView(R.drawable.book2) {
                            id = Ids.TOP_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = -600F

                            val anims4 = AnimatorSet()
                            val sY4 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y,  -600f, 0f)
                            sY4.startDelay = 550
                            anims4.duration = 350
                            anims4.play(sY4)
                            anims4.interpolator = OvershootInterpolator(1.75f)

                            anims4.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            topMargin = dip(12)
                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book3) {
                            id = Ids.DOWN_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = 600F

                            val anims5 = AnimatorSet()
                            val sY5 = ObjectAnimator.ofFloat( this@imageView, View.TRANSLATION_Y,  600f, 0f)
                            sY5.startDelay = 700
                            anims5.duration = 350
                            anims5.play(sY5)
                            anims5.interpolator = OvershootInterpolator(1.75f)

                            anims5.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1.2F)

                    /*
                    *
                    * this is the RIGHT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_THREE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                        imageView(R.drawable.book5) {
                            id = Ids.TOP_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.x = 400.0F
                            this@imageView.y = -400.0F

                            val anims6 = AnimatorSet()
                            val sX6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)

                            sY6.startDelay = 850
                            sX6.startDelay = 850
                            anims6.duration = 350
                            anims6.playTogether(sX6, sY6)
                            anims6.interpolator = OvershootInterpolator(1.75f)

                            anims6.start()

                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(-60)
                            marginEnd = dip(-15)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book6) {
                            id = Ids.CENTER_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F

                            val anims7 = AnimatorSet()
                            val sX7 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            sX7.startDelay = 1000
                            anims7.duration = 350
                            anims7.play(sX7)
                            anims7.interpolator = OvershootInterpolator(1.75f)

                            anims7.start()

                        }.lparams(width = dip(128), height = dip(162)){
                            topMargin = dip(4)
                            marginStart = dip(2)
                            marginEnd = dip(-15)
                        }

                        imageView(R.drawable.book1) {
                            id = Ids.DOWN_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F
                            this@imageView.y = 400.0F

                            val anims8 = AnimatorSet()
                            val sX8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, 400f, 0f)
                            sX8.startDelay = 1150
                            sY8.startDelay = 1150
                            anims8.duration = 350
                            anims8.playTogether(sX8, sY8)
                            anims8.interpolator = OvershootInterpolator(1.75f)

                            anims8.start()

                        }.lparams(width = dip(128), height = dip(158)){

                            topMargin = dip(4)
                            marginStart = dip(0)
                            marginEnd = dip(-15)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1F)

                }.lparams(width = matchParent, height = dip(0), weight = 1.4F){
                    topMargin = dip(25)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

                /*
                *
                * This is the BOTTOM linear layout
                * that include some text information
                *
                 */

                linearLayout{

                    id = Ids.LINEAR_LAYOUT_BOTTOM
                    orientation = LinearLayout.VERTICAL

                    //elevation = dip(106).toFloat()
                    //this.outlineProvider = null

                    textView{
                        id = Ids.ONBOARDING_TEXT_TITLE
                        text = resources.getText(R.string.onboarding_one_title)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                        textSize = sp(7).toFloat()
                    }.lparams(width = matchParent, height = wrapContent){

                        topMargin = dip(35)
                        marginStart = dip(15)
                        marginEnd = dip(15)

                    }

                    textView{
                        id = Ids.ONBOARDING_TEXT_DESCRIPTION
                        text = resources.getText(R.string.onboarding_one_description)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                        textSize = sp(5).toFloat()

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(8)
                        marginStart = dip(15)
                        marginEnd = dip(15)
                    }

                }.lparams(width = matchParent, height = dip(0), weight = 1F)
            }
        }

        /*
        *
        * SMALL CONFIGURATION - VERSION
        *
         */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.SMALL ){

            /*
    *
    * External linear layout this include other
    * two linear layout that divide in two equally
    * parts the screen
    *
     */

            return@with linearLayout {

                id = Ids.LINEAR_LAYOUT_TOTAL_EXTERN
                orientation = LinearLayout.VERTICAL

                /*
                *
                * This is the TOP Linear layout
                * that include other three linear layout
                *
                 */

                linearLayout {
                    id = Ids.LINEAR_LAYOUT_TOP
                    orientation = LinearLayout.HORIZONTAL

                    //this.setBackgroundColor(resources.getColor(R.color.biografyDark))


                    //*****************************************************************************
                    //      Here we initialize the three linear layout in two equally space
                    //                             Center = bigger
                    //*****************************************************************************

                    //_______________________________________________________________________________

                    /*
                    *
                    * this is the LEFT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_ONE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.comicsDark))

                    }.lparams(width = dip(0),height = matchParent, weight = 1F)

                    /*
                    *
                    * this is the CENTER linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_TWO
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                        imageView(R.drawable.book2) {
                            id = Ids.TOP_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = -600F

                            val anims4 = AnimatorSet()
                            val sY4 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y,  -600f, 0f)
                            sY4.startDelay = 550
                            anims4.duration = 350
                            anims4.play(sY4)
                            anims4.interpolator = OvershootInterpolator(1.75f)

                            anims4.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            topMargin = dip(12)
                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book3) {
                            id = Ids.DOWN_CENTER_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.y = 600F

                            val anims5 = AnimatorSet()
                            val sY5 = ObjectAnimator.ofFloat( this@imageView, View.TRANSLATION_Y,  600f, 0f)
                            sY5.startDelay = 700
                            anims5.duration = 350
                            anims5.play(sY5)
                            anims5.interpolator = OvershootInterpolator(1.75f)

                            anims5.start()

                        }.lparams(width = matchParent, height = dip(167)){

                            marginEnd = dip(0)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1.2F)

                    /*
                    *
                    * this is the RIGHT linear layout
                    *
                     */

                    linearLayout {

                        id = Ids.LINEAR_LAYOUT_TOP_COLUMN_THREE
                        orientation = LinearLayout.VERTICAL

                        //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                        imageView(R.drawable.book5) {
                            id = Ids.TOP_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY

                            this@imageView.x = 400.0F
                            this@imageView.y = -400.0F

                            val anims6 = AnimatorSet()
                            val sX6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)

                            sY6.startDelay = 850
                            sX6.startDelay = 850
                            anims6.duration = 350
                            anims6.playTogether(sX6, sY6)
                            anims6.interpolator = OvershootInterpolator(1.75f)

                            anims6.start()

                        }.lparams(width = dip(128), height = dip(178)){

                            topMargin = dip(-60)
                            marginEnd = dip(-15)
                            marginStart = dip(0)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                        imageView(R.drawable.book6) {
                            id = Ids.CENTER_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F

                            val anims7 = AnimatorSet()
                            val sX7 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            sX7.startDelay = 1000
                            anims7.duration = 350
                            anims7.play(sX7)
                            anims7.interpolator = OvershootInterpolator(1.75f)

                            anims7.start()

                        }.lparams(width = dip(128), height = dip(162)){
                            topMargin = dip(4)
                            marginStart = dip(2)
                            marginEnd = dip(-15)
                        }

                        imageView(R.drawable.book1) {
                            id = Ids.DOWN_RIGHT_IMAGE

                            scaleType = ImageView.ScaleType.FIT_XY
                            this@imageView.x = 400.0F
                            this@imageView.y = 400.0F

                            val anims8 = AnimatorSet()
                            val sX8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                            val sY8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, 400f, 0f)
                            sX8.startDelay = 1150
                            sY8.startDelay = 1150
                            anims8.duration = 350
                            anims8.playTogether(sX8, sY8)
                            anims8.interpolator = OvershootInterpolator(1.75f)

                            anims8.start()

                        }.lparams(width = dip(128), height = dip(158)){

                            topMargin = dip(4)
                            marginStart = dip(0)
                            marginEnd = dip(-14)
                            gravity = Gravity.CENTER_HORIZONTAL

                        }

                    }.lparams(width = dip(0), height = matchParent, weight = 1F)

                }.lparams(width = matchParent, height = dip(0), weight = 1.4F){
                    topMargin = dip(25)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

                /*
                *
                * This is the BOTTOM linear layout
                * that include some text information
                *
                 */

                linearLayout{

                    id = Ids.LINEAR_LAYOUT_BOTTOM
                    orientation = LinearLayout.VERTICAL

                    //elevation = dip(106).toFloat()
                    //this.outlineProvider = null

                    textView{
                        id = Ids.ONBOARDING_TEXT_TITLE
                        text = resources.getText(R.string.onboarding_one_title)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                        textSize = sp(8).toFloat()
                    }.lparams(width = matchParent, height = wrapContent){

                        topMargin = dip(20)
                        marginStart = dip(15)
                        marginEnd = dip(15)

                    }

                    textView{
                        id = Ids.ONBOARDING_TEXT_DESCRIPTION
                        text = resources.getText(R.string.onboarding_one_description)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                        textSize = sp(5).toFloat()

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(8)
                        marginStart = dip(15)
                        marginEnd = dip(15)
                    }

                }.lparams(width = matchParent, height = dip(0), weight = 1F)
            }

        }

        /*
        *
        * DEFAULT CONFIGURATION - VERSION
        *
         */

        linearLayout {

            id = Ids.LINEAR_LAYOUT_TOTAL_EXTERN
            orientation = LinearLayout.VERTICAL

            /*
            *
            * This is the TOP Linear layout
            * that include other three linear layout
            *
             */

            linearLayout {
                id = Ids.LINEAR_LAYOUT_TOP
                orientation = LinearLayout.HORIZONTAL

                //this.setBackgroundColor(resources.getColor(R.color.biografyDark))


                //*****************************************************************************
                //      Here we initialize the three linear layout in two equally space
                //                             Center = bigger
                //*****************************************************************************

                //_______________________________________________________________________________

                /*
                *
                * this is the LEFT linear layout
                *
                 */

                linearLayout {

                    id = Ids.LINEAR_LAYOUT_TOP_COLUMN_ONE
                    orientation = LinearLayout.VERTICAL

                    //this.setBackgroundColor(resources.getColor(R.color.comicsDark))

                }.lparams(width = dip(0),height = matchParent, weight = 1F)

                /*
                *
                * this is the CENTER linear layout
                *
                 */

                linearLayout {

                    id = Ids.LINEAR_LAYOUT_TOP_COLUMN_TWO
                    orientation = LinearLayout.VERTICAL

                    //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                    imageView(R.drawable.book2) {
                        id = Ids.TOP_CENTER_IMAGE

                        scaleType = ImageView.ScaleType.FIT_XY
                        this@imageView.y = -600F

                        val anims4 = AnimatorSet()
                        val sY4 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y,  -600f, 0f)
                        sY4.startDelay = 550
                        anims4.duration = 350
                        anims4.play(sY4)
                        anims4.interpolator = OvershootInterpolator(1.75f)

                        anims4.start()

                    }.lparams(width = matchParent, height = dip(167)){

                        topMargin = dip(12)
                        marginEnd = dip(0)
                        marginStart = dip(0)
                        gravity = Gravity.CENTER_HORIZONTAL

                    }

                    imageView(R.drawable.book3) {
                        id = Ids.DOWN_CENTER_IMAGE

                        scaleType = ImageView.ScaleType.FIT_XY
                        this@imageView.y = 600F

                        val anims5 = AnimatorSet()
                        val sY5 = ObjectAnimator.ofFloat( this@imageView, View.TRANSLATION_Y,  600f, 0f)
                        sY5.startDelay = 700
                        anims5.duration = 350
                        anims5.play(sY5)
                        anims5.interpolator = OvershootInterpolator(1.75f)

                        anims5.start()

                    }.lparams(width = matchParent, height = dip(167)){

                        marginEnd = dip(0)
                        marginStart = dip(0)
                        gravity = Gravity.CENTER_HORIZONTAL

                    }

                }.lparams(width = dip(0), height = matchParent, weight = 1.2F)

                /*
                *
                * this is the RIGHT linear layout
                *
                 */

                linearLayout {

                    id = Ids.LINEAR_LAYOUT_TOP_COLUMN_THREE
                    orientation = LinearLayout.VERTICAL

                    //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                    imageView(R.drawable.book5) {
                        id = Ids.TOP_RIGHT_IMAGE

                        scaleType = ImageView.ScaleType.FIT_XY

                        this@imageView.x = 400.0F
                        this@imageView.y = -400.0F

                        val anims6 = AnimatorSet()
                        val sX6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                        val sY6 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, -400f, 0f)

                        sY6.startDelay = 850
                        sX6.startDelay = 850
                        anims6.duration = 350
                        anims6.playTogether(sX6, sY6)
                        anims6.interpolator = OvershootInterpolator(1.75f)

                        anims6.start()

                    }.lparams(width = dip(128), height = dip(178)){

                        topMargin = dip(-60)
                        marginEnd = dip(-15)
                        marginStart = dip(0)
                        gravity = Gravity.CENTER_HORIZONTAL

                    }

                    imageView(R.drawable.book6) {
                        id = Ids.CENTER_RIGHT_IMAGE

                        scaleType = ImageView.ScaleType.FIT_XY
                        this@imageView.x = 400.0F

                        val anims7 = AnimatorSet()
                        val sX7 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                        sX7.startDelay = 1000
                        anims7.duration = 350
                        anims7.play(sX7)
                        anims7.interpolator = OvershootInterpolator(1.75f)

                        anims7.start()

                    }.lparams(width = dip(128), height = dip(162)){
                        topMargin = dip(4)
                        marginStart = dip(2)
                        marginEnd = dip(-15)
                    }

                    imageView(R.drawable.book1) {
                        id = Ids.DOWN_RIGHT_IMAGE

                        scaleType = ImageView.ScaleType.FIT_XY
                        this@imageView.x = 400.0F
                        this@imageView.y = 400.0F

                        val anims8 = AnimatorSet()
                        val sX8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_X, 400f, 0f)
                        val sY8 = ObjectAnimator.ofFloat(this@imageView, View.TRANSLATION_Y, 400f, 0f)
                        sX8.startDelay = 1150
                        sY8.startDelay = 1150
                        anims8.duration = 350
                        anims8.playTogether(sX8, sY8)
                        anims8.interpolator = OvershootInterpolator(1.75f)

                        anims8.start()

                    }.lparams(width = dip(128), height = dip(158)){

                        topMargin = dip(4)
                        marginStart = dip(0)
                        marginEnd = dip(-14)
                        gravity = Gravity.CENTER_HORIZONTAL

                    }

                }.lparams(width = dip(0), height = matchParent, weight = 1F)

            }.lparams(width = matchParent, height = dip(0), weight = 1.4F){
                topMargin = dip(25)
                marginStart = dip(15)
                marginEnd = dip(15)
            }

            /*
            *
            * This is the BOTTOM linear layout
            * that include some text information
            *
             */

            linearLayout{

                id = Ids.LINEAR_LAYOUT_BOTTOM
                orientation = LinearLayout.VERTICAL

                //elevation = dip(106).toFloat()
                //this.outlineProvider = null

                textView{
                    id = Ids.ONBOARDING_TEXT_TITLE
                    text = resources.getText(R.string.onboarding_one_title)
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                    textSize = sp(8).toFloat()
                }.lparams(width = matchParent, height = wrapContent){

                    topMargin = dip(20)
                    marginStart = dip(15)
                    marginEnd = dip(15)

                }

                textView{
                    id = Ids.ONBOARDING_TEXT_DESCRIPTION
                    text = resources.getText(R.string.onboarding_one_description)
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                    textSize = sp(5).toFloat()

                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(8)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

            }.lparams(width = matchParent, height = dip(0), weight = 1F)
        }
    }

    private object Ids{

        const val LINEAR_LAYOUT_TOTAL_EXTERN = R.id.LINEAR_LAYOUT_TOTAL_EXTERN
        const val HIGH_LEFT_IMAGE = R.id.HIGH_LEFT_IMAGE
        const val DOWN_LEFT_IMAGE = R.id.DOWN_LEFT_IMAGE
        const val CENTER_LEFT_IMAGE = R.id.CENTER_LEFT_IMAGE
        const val DOWN_CENTER_IMAGE = R.id.DOWN_CENTER_IMAGE
        const val TOP_CENTER_IMAGE = R.id.TOP_CENTER_IMAGE
        const val DOWN_RIGHT_IMAGE = R.id.DOWN_RIGHT_IMAGE
        const val CENTER_RIGHT_IMAGE = R.id.CENTER_RIGHT_IMAGE
        const val TOP_RIGHT_IMAGE = R.id.TOP_RIGHT_IMAGE
        const val ONBOARDING_TEXT_DESCRIPTION = R.id.ONBOARDING_TEXT_DESCRIPTION
        const val ONBOARDING_TEXT_TITLE = R.id.ONBOARDING_TEXT_TITLE
        const val LINEAR_LAYOUT_BOTTOM = R.id.LINEAR_LAYOUT_BOTTOM
        const val LINEAR_LAYOUT_TOP = R.id.LINEAR_LAYOUT_TOP
        const val LINEAR_LAYOUT_TOP_COLUMN_ONE = R.id.LINEAR_LAYOUT_TOP_COLUMN_ONE
        const val LINEAR_LAYOUT_TOP_COLUMN_TWO = R.id.LINEAR_LAYOUT_TOP_COLUMN_TWO
        const val LINEAR_LAYOUT_TOP_COLUMN_THREE = R.id.LINEAR_LAYOUT_TOP_COLUMN_THREE

    }
}