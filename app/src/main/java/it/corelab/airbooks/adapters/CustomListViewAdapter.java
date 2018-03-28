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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_profile_all_reviews);
        TextView textView = (TextView) convertView.findViewById(R.id.name_surname);
        final ExpandableTextView description = (ExpandableTextView) convertView.findViewById(R.id.expandable_text);

        ImageView stella1 = (ImageView) convertView.findViewById(R.id.stella_1);
        ImageView stella2 = (ImageView) convertView.findViewById(R.id.stella_2);
        ImageView stella3 = (ImageView) convertView.findViewById(R.id.stella_3);
        ImageView stella4 = (ImageView) convertView.findViewById(R.id.stella_4);
        ImageView stella5 = (ImageView) convertView.findViewById(R.id.stella_5);

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
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_piena);
                stella5.setImageResource(R.drawable.stella_piena);
                break;
            case 4:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_piena);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 3:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 2:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 1:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_vuota);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            default:
                stella1.setImageResource(R.drawable.stella_vuota);
                stella2.setImageResource(R.drawable.stella_vuota);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);

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
