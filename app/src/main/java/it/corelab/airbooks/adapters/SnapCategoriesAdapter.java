package it.corelab.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Item;

/**
 * Created by Roberto_Vecchio on 19/02/18.
 */

public class SnapCategoriesAdapter  extends RecyclerView.Adapter<SnapCategoriesAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapCategoriesAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapCategoriesAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.categories_home, parent, false);

        return new SnapCategoriesAdapter.ReyclerViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SnapCategoriesAdapter.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.textView.setText(item.getGenreName());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textView;



        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.cardImage_categories_home);
            textView = (TextView) v.findViewById(R.id.categories_home_id);
        }
    }
}
