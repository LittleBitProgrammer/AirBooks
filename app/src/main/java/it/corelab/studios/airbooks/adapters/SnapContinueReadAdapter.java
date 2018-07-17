package it.corelab.studios.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.object.Book;
import it.corelab.studios.airbooks.object.Item;

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

public class SnapContinueReadAdapter extends RecyclerView.Adapter<SnapContinueReadAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Book> books;

    public SnapContinueReadAdapter(Context context, ArrayList<Book> books) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.books = books;
    }

    @Override
    public SnapContinueReadAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View book = layoutInflater.inflate(R.layout.rv_continue_read, parent, false);

        return new SnapContinueReadAdapter.ReyclerViewHolder(book);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SnapContinueReadAdapter.ReyclerViewHolder holder, int position) {
        Book book = books.get(position);

        Picasso.get().load(book.getCoverUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;


        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.review_cover_image);
        }
    }
}
