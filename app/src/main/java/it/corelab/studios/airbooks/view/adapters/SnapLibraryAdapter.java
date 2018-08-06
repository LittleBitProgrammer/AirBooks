package it.corelab.studios.airbooks.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import it.corelab.studios.airbooks.R;

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

public class SnapLibraryAdapter  extends RecyclerView.Adapter<SnapLibraryAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    //private ArrayList<Book> books;

    /*public SnapLibraryAdapter(Context context, ArrayList<Book> books) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.books = books;
    }*/

    @Override
    public SnapLibraryAdapter.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View book = layoutInflater.inflate(R.layout.library_book_item, parent, false);

        return new SnapLibraryAdapter.ReyclerViewHolder(book);
    }

    @Override
    public void onBindViewHolder(final SnapLibraryAdapter.ReyclerViewHolder holder, int position) {
        //Book book = books.get(position);
        int resto = position % 3;

        //Picasso.get().load(book.getCoverUrl()).into(holder.image);

        if (resto == 0){
            holder.scaffale.setVisibility(View.VISIBLE);
        }else{
            holder.scaffale.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 1;//books.size();
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
