package it.corelab.studios.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.data.model.HOME.Genre;
import it.corelab.studios.airbooks.object.Item;

/**
 * Created by Roberto_Vecchio on 19/02/18.
 */

public class SnapCategoriesAdapter  extends RecyclerView.Adapter<SnapCategoriesAdapter.RecyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Genre> items;

    public SnapCategoriesAdapter(Context context, List<Genre> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public SnapCategoriesAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.categories_home, parent, false);

        return new SnapCategoriesAdapter.RecyclerViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SnapCategoriesAdapter.RecyclerViewHolder holder, int position) {
        Genre item = items.get(position);

        int[] colors = {Color.parseColor("#" + item.getFirstColor()),Color.parseColor("#" + item.getSecondColor())};
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors);
        gd.setCornerRadius(0f);

        holder.image.setBackground(gd);
        holder.textView.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textView;



        private RecyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.cardImage_categories_home);
            textView = v.findViewById(R.id.categories_home_id);
        }
    }
}
