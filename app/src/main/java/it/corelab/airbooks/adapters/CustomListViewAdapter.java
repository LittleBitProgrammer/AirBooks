package it.corelab.airbooks.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

import it.corelab.airbooks.widget.ExpandableTextView;
import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Review;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<Review> {

    public CustomListViewAdapter(Context context, int textViewResourceId, List<Review> objects) {
        super(context, textViewResourceId, objects);
    }


    //TODO: new branch
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_profile_all_reviews);
        TextView textView = (TextView) convertView.findViewById(R.id.name_surname);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar_listView);
        final ExpandableTextView description = (ExpandableTextView) convertView.findViewById(R.id.expandable_text);

        final Button buttonToggle = (Button) convertView.findViewById(R.id.read_all_button);

        final View parentReturn = (View) buttonToggle.getParent();  // button: the view you want to enlarge hit area

        parentReturn.post( new Runnable() {
                               public void run() {
                                   final Rect rect = new Rect();
                                   buttonToggle.getHitRect(rect);
                                   rect.top -= 250;    // increase top hit area
                                   rect.left -= 250;   // increase left hit area
                                   rect.bottom += 250; // increase bottom hit area
                                   rect.right += 250;  // increase right hit area
                                   parentReturn.setTouchDelegate(new TouchDelegate(rect, buttonToggle));
                               }
                           });

        description.setAnimationDuration(450L);

        description.setDrawingCacheEnabled(true);
        description.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        description.buildDrawingCache();

         // or set them separately
        description.setExpandInterpolator(new OvershootInterpolator(1.0f));
        description.setCollapseInterpolator(new LinearOutSlowInInterpolator());

        final Review items = getItem(position);

        image.setImageResource(items.getDrawable());
        textView.setText(items.getName());
        description.setText(items.getDescription());

        if (items.getDescription().length() < 100){
            buttonToggle.setEnabled(false);
            buttonToggle.setVisibility(View.INVISIBLE);
        }


        int vote = items.getVote();

        switch (vote){
            case 5:
                ratingBar.setRating(5);
                break;
            case 4:
                ratingBar.setRating(4);
                break;
            case 3:
                ratingBar.setRating(3);
                break;
            case 2:
                ratingBar.setRating(2);
                break;
            case 1:
                ratingBar.setRating(1);
                break;
            default:
                ratingBar.setRating(0);

        }

        // but, you can also do the checks yourself
        buttonToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (description.isExpanded())
                {
                    description.collapse();
                    buttonToggle.setText("Read all");
                }
                else
                {
                    description.expand();
                    buttonToggle.setText("Read more");
                }
            }
        });

        buttonToggle.setDrawingCacheEnabled(true);
        buttonToggle.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        buttonToggle.buildDrawingCache();

        // listen for expand / collapse events
        description.setOnExpandListener(new ExpandableTextView.OnExpandListener()
        {
            @Override
            public void onExpand(final ExpandableTextView view)
            {
                Log.d("EXPAND", "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(final ExpandableTextView view)
            {
                Log.d("COLLAPSE", "ExpandableTextView collapsed");
            }
        });

        return convertView;
    }
}
