package it.corelab.airbooks.adapters;

/**
 * Created by Roberto_Vecchio on 06/02/18.
 */

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import it.corelab.airbooks.activity.ReviewDetail;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.R;


public class CardViewReviewAdapter extends RecyclerView.Adapter<CardViewReviewAdapter.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public CardViewReviewAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CardViewReviewAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.review_card, parent, false);

        return new CardViewReviewAdapter.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewReviewAdapter.ReyclerViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());
        holder.textView.setText(item.getReview());
        holder.categories.setImageResource(item.getGenreColor());
        holder.author.setText(item.getAuthor());
        holder.title.setText(item.getName());
        holder.ratingBar.setRating(item.getVote());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewSharedIntent = new Intent(context, ReviewDetail.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(holder.image,"imageCoverTransition");

                reviewSharedIntent.putExtra("image",item.getDrawable());
                reviewSharedIntent.putExtra("title",item.getName());
                reviewSharedIntent.putExtra("author",item.getAuthor());
                reviewSharedIntent.putExtra("review",item.getReview());

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
                context.startActivity(reviewSharedIntent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private ImageView categories;
        private TextView textView;
        private TextView author;
        private TextView title;
        private RatingBar ratingBar;



        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.review_cover_image);
            categories = v.findViewById(R.id.genre_color_continue_read);
            textView = v.findViewById(R.id.firstTextReview);
            author = v.findViewById(R.id.author_continue_read);
            title = v.findViewById(R.id.title_continue_read);
            ratingBar = v.findViewById(R.id.ratingBar_profile);
        }
    }
}