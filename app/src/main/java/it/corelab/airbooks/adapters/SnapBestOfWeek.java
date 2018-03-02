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
 * Created by Roberto_Vecchio on 21/02/18.
 */

public class SnapBestOfWeek extends RecyclerView.Adapter<SnapBestOfWeek.ReyclerViewHolder>{
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapBestOfWeek(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapBestOfWeek.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.home_best_of_week, parent, false);

        return new SnapBestOfWeek.ReyclerViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SnapBestOfWeek.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.colorGenre.setImageResource(item.getGenreColor());
        holder.title.setText(item.getName());
        holder.author.setText(item.getAuthor());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView author;
        private ImageView colorGenre;



        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image_bestweek);
            colorGenre = (ImageView) v.findViewById(R.id.rettangolo_smussato_rv);
            title = (TextView) v.findViewById(R.id.title_rv_bestweek);
            author = (TextView) v.findViewById(R.id.author_bestweek);
        }
    }
}
