package it.corelab.airbooks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.R;

/**
 * Created by Roberto_Vecchio on 14/02/18.
 */

public class SnapExploreRecyclerAdapter extends RecyclerView.Adapter<SnapExploreRecyclerAdapter.ReyclerViewHolder>  {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapExploreRecyclerAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapExploreRecyclerAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.card_explore, parent, false);

        return new SnapExploreRecyclerAdapter.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final SnapExploreRecyclerAdapter.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.bookName.setText(item.getName());
        holder.authorName.setText(item.getAuthor());
        holder.numberReviews.setText("" + item.getNumberReviews() + " Reviews");
        holder.ratingBar.setRating(item.getVote());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView bookName;
        private TextView authorName;
        private TextView numberReviews;
        private RatingBar ratingBar;


        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.cardImage_explore);
            bookName = v.findViewById(R.id.bookName);
            authorName = v.findViewById(R.id.author_id);
            numberReviews = v.findViewById(R.id.number_reviews_explore);
            ratingBar = v.findViewById(R.id.ratingBar);
        }
    }
}
