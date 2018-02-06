package it.corelab.airbooks;

/**
 * Created by Roberto_Vecchio on 06/02/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class CardViewReviewAdapter extends RecyclerView.Adapter<it.corelab.airbooks.CardViewReviewAdapter.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public CardViewReviewAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public it.corelab.airbooks.CardViewReviewAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.review_card, parent, false);

        return new it.corelab.airbooks.CardViewReviewAdapter.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final it.corelab.airbooks.CardViewReviewAdapter.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);

        holder.image.setImageResource(item.getDrawable());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;


        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.cardReviewImage);
        }
    }
}