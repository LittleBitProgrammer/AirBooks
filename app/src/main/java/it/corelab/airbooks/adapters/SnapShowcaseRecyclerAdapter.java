package it.corelab.airbooks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Showcase;

/**
 * Created by Roberto_Vecchio on 15/02/18.
 */

public class SnapShowcaseRecyclerAdapter extends RecyclerView.Adapter<SnapShowcaseRecyclerAdapter.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Showcase> items;

    public SnapShowcaseRecyclerAdapter(Context context, ArrayList<Showcase> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapShowcaseRecyclerAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.home_showcase, parent, false);

        return new SnapShowcaseRecyclerAdapter.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final SnapShowcaseRecyclerAdapter.ReyclerViewHolder holder, int position) {
        Showcase item = items.get(position);

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

            image = (ImageView) v.findViewById(R.id.cardImage_showcase);
        }
    }
}
