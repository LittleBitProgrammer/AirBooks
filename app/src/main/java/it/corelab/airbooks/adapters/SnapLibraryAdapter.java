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

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

public class SnapLibraryAdapter  extends RecyclerView.Adapter<SnapLibraryAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapLibraryAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public SnapLibraryAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.library_book_item, parent, false);

        return new SnapLibraryAdapter.ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final SnapLibraryAdapter.ReyclerViewHolder holder, int position) {
        Item item = items.get(position);
        int resto = position % 3;

        holder.image.setImageResource(item.getDrawable());

        if (resto == 0){
            holder.scaffale.setVisibility(View.VISIBLE);
        }else{
            holder.scaffale.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private ImageView scaffale;


        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.cardImage_library);
            scaffale = v.findViewById(R.id.scaffale_id);
        }
    }
}
