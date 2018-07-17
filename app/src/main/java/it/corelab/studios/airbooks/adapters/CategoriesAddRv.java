package it.corelab.studios.airbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.section.AddDescription;
import it.corelab.studios.airbooks.object.Item;


/**
 * Created by Roberto_Vecchio on 28/02/18.
 */

public class CategoriesAddRv extends RecyclerView.Adapter<it.corelab.studios.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private String uri;
    private String title;
    private ArrayList<Item> items;
    private int lastPosition = -1;

    public CategoriesAddRv(Context context, ArrayList<Item> items, String uri, String title) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
        this.uri = uri;
        this.title = title;
    }

    @Override
    public it.corelab.studios.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.categories_add_item, parent, false);

        return new it.corelab.studios.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final it.corelab.studios.airbooks.adapters.CategoriesAddRv.ReyclerViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.categoriesName.setText(item.getGenreName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , AddDescription.class);
                intent.putExtra("image", uri);
                intent.putExtra("categories", item.getDrawable());
                intent.putExtra("nameCat", item.getGenreName());
                intent.putExtra("title", title);
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

            image = v.findViewById(R.id.cardImage_categories_add);
            categoriesName = v.findViewById(R.id.nameCategories);
        }
    }
}
