package it.corelab.airbooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Item;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<Item> {

    public CustomListViewAdapter(Context context, int textViewResourceId,
                                 List<Item> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_profile_all_reviews);

        Item items = getItem(position);

        image.setImageResource(items.getDrawable());

        return convertView;
    }

}
