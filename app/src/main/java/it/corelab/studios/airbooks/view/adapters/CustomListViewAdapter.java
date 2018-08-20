package it.corelab.studios.airbooks.view.adapters;

import android.content.Context;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import it.corelab.studios.airbooks.model.data.REVIEW.Item;
import it.corelab.studios.airbooks.view.widget.ExpandableTextView;
import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.view.widget.RoundedImageView;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<Item> {


    private List<Item> objects;

    private static class ViewHolder {
        RoundedImageView roundedImageView;
    }

    public CustomListViewAdapter(Context context, List<Item> objects) {
        super(context, R.layout.custom_listview_item_all_reviews, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view

        Item reviews = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, parent, false);
            viewHolder.roundedImageView = convertView.findViewById(R.id.rounded_review);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(convertView).load(reviews.getAuthorProfilePicture()).into(viewHolder.roundedImageView);

        //GetReviews reviews = getItem(position);
        //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        //ImageView roundedImage = convertView.findViewById(R.id.image_profile_all_reviews);
        //TextView textView = convertView.findViewById(R.id.name_surname);
        final ExpandableTextView description = convertView.findViewById(R.id.expandable_text);


         // or set them separately
        description.setExpandInterpolator(new OvershootInterpolator(1.0f));
        description.setCollapseInterpolator(new LinearOutSlowInInterpolator());

        //final Reviews items = getItem(position);

        //roundedImage.setImageResource(items.getDrawable());
        //textView.setText(items.getName());
        //description.setText(items.getDescription());

        /*if (items.getDescription().length() < 100){
            buttonToggle.setEnabled(false);
            buttonToggle.setVisibility(View.INVISIBLE);
        }*/


        // but, you can also do the checks yourself
        /*buttonToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (description.isExpanded())
                {
                    description.collapse();
                }
                else
                {
                    description.expand();
                }
            }
        });*/

        // listen for expand / collapse events
        /*description.setOnExpandListener(new ExpandableTextView.OnExpandListener()
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
        });*/

        return convertView;
    }

    @Override
    public int getCount() {
        return this.objects.size();
    }
}
