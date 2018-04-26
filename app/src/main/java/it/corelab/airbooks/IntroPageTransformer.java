package it.corelab.airbooks;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import it.corelab.airbooks.activity.Login;

public class IntroPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(final View page, float position){

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
            if (pagePosition == 4){
                View button = page.findViewById(R.id.color_button_next_add_book);

                final Intent intent = new Intent(page.getContext(), Login.class);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        page.getContext().startActivity(intent);
                    }
                });
            }

        }else{

            /*

            *The page is currently being scrolled / swiped. This is
            * a good place to show animations that react to the user's
            * swiping as it provides a good user experience

             */

            if (pagePosition == 0) {

                //Do animation for the first page


                View titleOne = page.findViewById(R.id.title_part_one);
                titleOne.setTranslationY(-pageWidthTimesPosition / 2f);
                titleOne.setAlpha(1.0f - absPosition);

                View description = page.findViewById(R.id.description_one);
                description.setTranslationY(-pageWidthTimesPosition / 2f);
                description.setAlpha(1.0f - absPosition);

                View cardLeft = page.findViewById(R.id.card_left_one);
                cardLeft.setTranslationY(pageWidthTimesPosition / 2f);
                cardLeft.setAlpha(1.0f - absPosition);
                cardLeft.setScaleX(1.0f - absPosition);
                cardLeft.setScaleY(1.0f - absPosition);

                View cardCenter = page.findViewById(R.id.card_center_one);
                cardCenter.setTranslationY(pageWidthTimesPosition / 2f);
                cardCenter.setAlpha(1.0f - absPosition);
                cardCenter.setScaleX(1.0f - absPosition);
                cardCenter.setScaleY(1.0f - absPosition);

                View cardRight = page.findViewById(R.id.card_right_one);
                cardRight.setTranslationY(pageWidthTimesPosition / 2f);
                cardRight.setAlpha(1.0f - absPosition);
                cardRight.setScaleX(1.0f - absPosition);
                cardRight.setScaleY(1.0f - absPosition);

            }else if (pagePosition == 1){

                //Do animation for the second page


                View title = page.findViewById(R.id.title_intro_part_two);
                title.setTranslationY(-pageWidthTimesPosition / 2f);
                title.setAlpha(1.0f -absPosition);

                View description = page.findViewById(R.id.description_two);
                description.setTranslationY(-pageWidthTimesPosition /2f);
                description.setAlpha(1.0f -absPosition);

                View topLeftCard = page.findViewById(R.id.cardView10);
                topLeftCard.setTranslationY(pageWidthTimesPosition /2f);
                topLeftCard.setAlpha(1.0f - absPosition);

                View topCenterCard = page.findViewById(R.id.card_upper_center_two);
                topCenterCard.setTranslationY(pageWidthTimesPosition /2f);
                topCenterCard.setAlpha(1.0f - absPosition);

                View topRightCard = page.findViewById(R.id.card_center_two);
                topRightCard.setTranslationY(pageWidthTimesPosition /2f);
                topRightCard.setAlpha(1.0f - absPosition);

                View topShelf = page.findViewById(R.id.imageView2);
                topShelf.setTranslationY(pageWidthTimesPosition /2f);
                topShelf.setAlpha(1.0f - absPosition);

                View bottomLeftCard = page.findViewById(R.id.card_under_left);
                bottomLeftCard.setTranslationY(pageWidthTimesPosition /2f);
                bottomLeftCard.setAlpha(1.0f - absPosition);

                View bottomCenterCard = page.findViewById(R.id.card_under_center);
                bottomCenterCard.setTranslationY(pageWidthTimesPosition /2f);
                bottomCenterCard.setAlpha(1.0f - absPosition);

                View bottomRightCad = page.findViewById(R.id.card_under_right);
                bottomRightCad.setTranslationY(pageWidthTimesPosition /2f);
                bottomRightCad.setAlpha(1.0f - absPosition);

                View bottomShelf = page.findViewById(R.id.scaffale_under);
                bottomShelf.setTranslationY(pageWidthTimesPosition /2f);
                bottomShelf.setAlpha(1.0f - absPosition);


            }else if (pagePosition == 2){

                //Do animation for the third page

                View title = page.findViewById(R.id.title_part_three);
                title.setTranslationY( -pageWidthTimesPosition / 2f);
                title.setAlpha(1.0f - absPosition);

                View description = page.findViewById(R.id.description_three);
                description.setTranslationY( -pageWidthTimesPosition /2f );
                description.setAlpha(1.0f - absPosition);

                View cellular = page.findViewById(R.id.cellular_cupcake);
                cellular.setTranslationY( pageWidthTimesPosition / 2f);
                cellular.setAlpha(1.0f - absPosition);
                cellular.setScaleX(1.0f - absPosition);
                cellular.setScaleY(1.0f - absPosition);

                View cardRight = page.findViewById(R.id.card_right_three);
                cardRight.setTranslationY( pageWidthTimesPosition /2f);
                cardRight.setAlpha(1.0f - absPosition);
                cardRight.setScaleX(1.0f - absPosition);
                cardRight.setScaleY(1.0f - absPosition);

                View cardLeft = page.findViewById(R.id.card_lef_three);
                cardLeft.setTranslationY( pageWidthTimesPosition /2f);
                cardLeft.setAlpha(1.0f - absPosition);
                cardLeft.setScaleX(1.0f - absPosition);
                cardLeft.setScaleY(1.0f - absPosition);

            }else if (pagePosition == 3){

                //Do animation for the fourth page
                View title = page.findViewById(R.id.title_intro_part_four);
                title.setTranslationY(-pageWidthTimesPosition /2f);
                title.setAlpha(1.0f - absPosition);

                View description = page.findViewById(R.id.description_four);
                description.setTranslationY(-pageWidthTimesPosition / 2f);
                description.setAlpha(1.0f - absPosition);

                View phone = page.findViewById(R.id.cellular_four);
                phone.setTranslationY(pageWidthTimesPosition /2f);
                phone.setAlpha(1.0f -absPosition);
                phone.setScaleX(1.0f - absPosition);
                phone.setScaleY(1.0f - absPosition);

                View tablet = page.findViewById(R.id.tablet_four);
                tablet.setTranslationY(pageWidthTimesPosition / 2f);
                tablet.setAlpha(1.0f - absPosition);
                tablet.setScaleY(1.0f - absPosition);
                tablet.setScaleX(1.0f -absPosition);

            }else if (pagePosition == 4){

                //Do animation for the fifth page

                //Do nothing because is the last page
                //this has an animation only in entry

            }



            // Finally, it can be useful to know the direction
            // of the user's swipe - if we're entering or exiting.
            // This is quite simple:
            if (position < 0) {
                // Create your out animation here
            } else {
                // Create your in animation here

                if (pagePosition == 1){

                    //here we are entering
                    //Do animation for the second page


                    View title = page.findViewById(R.id.title_intro_part_two);
                    title.setTranslationY(pageWidthTimesPosition / 2f);
                    title.setAlpha(1.0f  - absPosition);

                    View description = page.findViewById(R.id.description_two);
                    description.setTranslationY(pageWidthTimesPosition /2f);
                    description.setAlpha(1.0f  - absPosition);

                    View topLeftCard = page.findViewById(R.id.cardView10);
                    topLeftCard.setTranslationY(-pageWidthTimesPosition /2f);
                    topLeftCard.setAlpha(1.0f - absPosition);
                    topLeftCard.setTranslationX(pageWidthTimesPosition /2f);

                    View topCenterCard = page.findViewById(R.id.card_upper_center_two);
                    topCenterCard.setTranslationY(-pageWidthTimesPosition /2f);
                    topCenterCard.setAlpha(1.0f - absPosition);
                    topCenterCard.setTranslationX(pageWidthTimesPosition /2f);

                    View topRightCard = page.findViewById(R.id.card_center_two);
                    topRightCard.setTranslationY(-pageWidthTimesPosition /2f);
                    topRightCard.setAlpha(1.0f - absPosition);
                    topRightCard.setTranslationX(pageWidthTimesPosition /2f);

                    View topShelf = page.findViewById(R.id.imageView2);
                    topShelf.setTranslationY(-pageWidthTimesPosition /2f);
                    topShelf.setAlpha(1.0f - absPosition);
                    topShelf.setTranslationX(pageWidthTimesPosition /2f);

                    View bottomLeftCard = page.findViewById(R.id.card_under_left);
                    bottomLeftCard.setTranslationY(-pageWidthTimesPosition /2f);
                    bottomLeftCard.setAlpha(1.0f - absPosition);
                    bottomLeftCard.setTranslationX(pageWidthTimesPosition /2f);

                    View bottomCenterCard = page.findViewById(R.id.card_under_center);
                    bottomCenterCard.setTranslationY(-pageWidthTimesPosition /2f);
                    bottomCenterCard.setAlpha(1.0f - absPosition);
                    bottomCenterCard.setTranslationX(pageWidthTimesPosition /2f);

                    View bottomRightCad = page.findViewById(R.id.card_under_right);
                    bottomRightCad.setTranslationY(-pageWidthTimesPosition /2f);
                    bottomRightCad.setAlpha(1.0f - absPosition);
                    bottomRightCad.setTranslationX(pageWidthTimesPosition /2f);

                    View bottomShelf = page.findViewById(R.id.scaffale_under);
                    bottomShelf.setTranslationY(-pageWidthTimesPosition /2f);
                    bottomShelf.setAlpha(1.0f - absPosition);
                    bottomShelf.setTranslationX(pageWidthTimesPosition /2f);


                } else if (pagePosition == 2){

                    View title = page.findViewById(R.id.title_part_three);
                    title.setTranslationY( pageWidthTimesPosition / 2f);
                    title.setAlpha(1.0f - absPosition);

                    View description = page.findViewById(R.id.description_three);
                    description.setTranslationY( pageWidthTimesPosition /2f );
                    description.setAlpha(1.0f - absPosition);

                    View cellular = page.findViewById(R.id.cellular_cupcake);
                    cellular.setTranslationY( -pageWidthTimesPosition / 2f);
                    cellular.setAlpha(1.0f - absPosition);
                    cellular.setScaleX(1.0f - absPosition);
                    cellular.setScaleY(1.0f - absPosition);
                    cellular.setTranslationX(pageWidthTimesPosition /2f);

                    View cardRight = page.findViewById(R.id.card_right_three);
                    cardRight.setTranslationY( -pageWidthTimesPosition /2f);
                    cardRight.setAlpha(1.0f - absPosition);
                    cardRight.setScaleX(1.0f - absPosition);
                    cardRight.setScaleY(1.0f - absPosition);
                    cardRight.setTranslationX(pageWidthTimesPosition /2f);

                    View cardLeft = page.findViewById(R.id.card_lef_three);
                    cardLeft.setTranslationY( -pageWidthTimesPosition /2f);
                    cardLeft.setAlpha(1.0f - absPosition);
                    cardLeft.setScaleX(1.0f - absPosition);
                    cardLeft.setScaleY(1.0f - absPosition);
                    cardLeft.setTranslationX(pageWidthTimesPosition /2f);

                }else if (pagePosition == 3){

                    View title = page.findViewById(R.id.title_intro_part_four);
                    title.setTranslationY(pageWidthTimesPosition /2f);
                    title.setAlpha(1.0f - absPosition);

                    View description = page.findViewById(R.id.description_four);
                    description.setTranslationY(pageWidthTimesPosition / 2f);
                    description.setAlpha(1.0f - absPosition);

                    View phone = page.findViewById(R.id.cellular_four);
                    phone.setTranslationY(-pageWidthTimesPosition /2f);
                    phone.setAlpha(1.0f -absPosition);
                    phone.setScaleX(1.0f - absPosition);
                    phone.setScaleY(1.0f - absPosition);
                    phone.setTranslationX(pageWidthTimesPosition / 2f);

                    View tablet = page.findViewById(R.id.tablet_four);
                    tablet.setTranslationY(-pageWidthTimesPosition / 2f);
                    tablet.setAlpha(1.0f - absPosition);
                    tablet.setScaleY(1.0f - absPosition);
                    tablet.setScaleX(1.0f -absPosition);
                    tablet.setTranslationX(pageWidthTimesPosition / 2f);

                }else if (pagePosition == 4){

                    View title = page.findViewById(R.id.title_intro_part_five);
                    title.setTranslationY(pageWidthTimesPosition / 2f);
                    title.setAlpha(1.0f - absPosition);

                    View description = page.findViewById(R.id.description_five);
                    description.setTranslationY(pageWidthTimesPosition / 2f);
                    description.setAlpha(1.0f - absPosition);

                    View button = page.findViewById(R.id.color_button_next_add_book);
                    button.setTranslationY(pageWidthTimesPosition / 2f);
                    button.setAlpha(1.0f - absPosition);

                    View phone = page.findViewById(R.id.cellular_five);
                    phone.setTranslationY(-pageWidthTimesPosition / 2f);
                    phone.setAlpha(1.0f - absPosition);
                    phone.setScaleY(1.0f - absPosition);
                    phone.setScaleX(1.0f - absPosition);
                    phone.setTranslationX(pageWidthTimesPosition - absPosition);

                    View tablet = page.findViewById(R.id.tablet_five);
                    tablet.setTranslationY(-pageWidthTimesPosition / 2f);
                    tablet.setAlpha(1.0f - absPosition);
                    tablet.setScaleX(1.0f -absPosition);
                    tablet.setScaleY(1.0f - absPosition);
                    tablet.setTranslationX(pageWidthTimesPosition - absPosition);

                }
            }
        }
    }
}
