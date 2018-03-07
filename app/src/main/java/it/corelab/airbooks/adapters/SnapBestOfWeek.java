package it.corelab.airbooks.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.AddDescription;
import it.corelab.airbooks.activity.BookDetail;
import it.corelab.airbooks.activity.MainActivity;
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
    public void onBindViewHolder(final SnapBestOfWeek.ReyclerViewHolder holder, final int position) {
        final Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.colorGenre.setImageResource(item.getGenreColor());
        holder.title.setText(item.getName());
        holder.author.setText(item.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent sharedIntent = new Intent(context, BookDetail.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(holder.colorGenre, "genreTransition");
                    pairs[1] = new Pair<View, String>(holder.image, "imageTransition");

                    sharedIntent.putExtra("pos", item.getDrawable());
                    sharedIntent.putExtra("title", item.getName());
                    sharedIntent.putExtra("genre", item.getGenreColor());
                    sharedIntent.putExtra("author", item.getAuthor());

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
                    context.startActivity(sharedIntent, options.toBundle());
            }
        });


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
