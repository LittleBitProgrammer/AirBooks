package it.corelab.studios.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.data.model.HOME.ItemReading;
import it.corelab.studios.airbooks.object.Book;

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

public class SnapContinueReadAdapter extends RecyclerView.Adapter<SnapContinueReadAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private List<ItemReading> books;

    public SnapContinueReadAdapter(Context context, List<ItemReading> books) {
        this.layoutInflater = LayoutInflater.from(context);
        this.books = books;
    }

    @NonNull
    @Override
    public SnapContinueReadAdapter.ReyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View book = layoutInflater.inflate(R.layout.rv_continue_read, parent, false);

        return new SnapContinueReadAdapter.ReyclerViewHolder(book);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SnapContinueReadAdapter.ReyclerViewHolder holder, int position) {
        ItemReading book = books.get(position);

        Picasso.get().load(book.getCoverUrl()).into(holder.image);
        Log.i("PIPINO", "$books.size()");

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;


        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.cover_image);
        }
    }
}
