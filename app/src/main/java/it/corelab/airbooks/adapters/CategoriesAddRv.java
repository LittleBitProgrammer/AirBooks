package it.corelab.airbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.AddDescription;
import it.corelab.airbooks.activity.AddSection;
import it.corelab.airbooks.activity.MainActivity;
import it.corelab.airbooks.object.Item;

import static java.security.AccessController.getContext;

/**
 * Created by Roberto_Vecchio on 28/02/18.
 */

public class CategoriesAddRv extends RecyclerView.Adapter<it.corelab.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;
    private int lastPosition = -1;

    public CategoriesAddRv(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public it.corelab.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.categories_add_item, parent, false);

        return new it.corelab.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final it.corelab.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.categoriesName.setText(item.getGenreName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , AddDescription.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView categoriesName;

        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.cardImage_categories_add);
            categoriesName = (TextView) v.findViewById(R.id.nameCategories);
        }
    }
}
