package it.corelab.airbooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Review;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<Review> {

    public CustomListViewAdapter(Context context, int textViewResourceId,
                                 List<Review> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_profile_all_reviews);
        TextView textView = (TextView) convertView.findViewById(R.id.name_surname);
        TextView description = (TextView) convertView.findViewById(R.id.description_all_reviews);

        ImageView stella1 = (ImageView) convertView.findViewById(R.id.stella_1);
        ImageView stella2 = (ImageView) convertView.findViewById(R.id.stella_2);
        ImageView stella3 = (ImageView) convertView.findViewById(R.id.stella_3);
        ImageView stella4 = (ImageView) convertView.findViewById(R.id.stella_4);
        ImageView stella5 = (ImageView) convertView.findViewById(R.id.stella_5);

        Review items = getItem(position);

        image.setImageResource(items.getDrawable());
        textView.setText(items.getName());
        description.setText(items.getDescription());

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

        return convertView;
    }

}
