package it.corelab.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import it.corelab.airbooks.object.Item;

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

public class SnapContinueReadAdapter extends RecyclerView.Adapter<SnapContinueReadAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapContinueReadAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapContinueReadAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.rv_continue_read, parent, false);

        return new SnapContinueReadAdapter.ReyclerViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SnapContinueReadAdapter.ReyclerViewHolder holder, int position) {
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

            image = (ImageView) v.findViewById(R.id.cardReviewImage_comtinue_read);
        }
    }
}
