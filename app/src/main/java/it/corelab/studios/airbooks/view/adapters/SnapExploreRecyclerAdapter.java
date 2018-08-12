package it.corelab.studios.airbooks.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import it.corelab.studios.airbooks.R;

/**
 * Created by Roberto_Vecchio on 14/02/18.
 */

public class SnapExploreRecyclerAdapter extends RecyclerView.Adapter<SnapExploreRecyclerAdapter.ReyclerViewHolder>  {
    private LayoutInflater layoutInflater;
    private Context context;
    //private ArrayList<Book> books;

    /*public SnapExploreRecyclerAdapter(Context context, ArrayList<Book> books) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.books = books;
    }*/

    @Override
    public SnapExploreRecyclerAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View book = layoutInflater.inflate(R.layout.card_explore, parent, false);

        return new SnapExploreRecyclerAdapter.ReyclerViewHolder(book);
    }

    @Override
    public void onBindViewHolder(final SnapExploreRecyclerAdapter.ReyclerViewHolder holder, int position) {
        //Book book = books.get(position);

        //Picasso.get().load(book.getCoverUrl()).into(holder.image);
        //holder.bookName.setText(book.getTitle());
        //holder.authorName.setText(book.getAuthorFirstName() + " " + book.getAuthorLastNAme());
        //holder.numberReviews.setText("93 Reviews");
        //holder.ratingBar.setRating((float)book.getAvarageRating());

    }

    @Override
    public int getItemCount() {
        return 1;// books.size();
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
