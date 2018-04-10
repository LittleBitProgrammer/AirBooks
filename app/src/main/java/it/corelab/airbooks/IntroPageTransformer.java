package it.corelab.airbooks;

import android.support.v4.view.ViewPager;
import android.view.View;

public class IntroPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position){

        /*

        * Get the page  index from the tag. This makes
        * it possible to know which page index you're
        * currently transforming - and that can be used
        * to make some important performance improvements

         */

        int pagePosition = (int) page.getTag();

        /*

        * Here you can do all kinds of stuff, like get the
        * width of the page and perform calculations based
        * on how far the user has swiped the page

         */

        int pageWidth = page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        //time for the effects

        if (position <= -1.0f || position >= 1.0f){

            /*

            * The page is not visible. This is a good place to stop
            * any potential work / animations you may have running

             */

        }else if (position == 0.0f){

            /*

            * The page is selected. This is a good time to reset Views
            * after animations as you can't always count on the PageTransformer
            * callbacks to match up perfectly

            */
        }else{

            /*

            *The page is currently being scrolled / swiped. This is
            * a good place to show animations that react to the user's
            * swiping as it provides a good user experience

             */


            // Finally, it can be useful to know the direction
            // of the user's swipe - if we're entering or exiting.
            // This is quite simple:
            if (position < 0) {
                // Create your out animation here
            } else {
                // Create your in animation here
            }
        }
    }
}
