package it.corelab.airbooks.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.section.BookDetail;
import it.corelab.airbooks.object.Book;

/**
 * Created by Roberto_Vecchio on 21/02/18.
 */

public class SnapBestOfWeek extends RecyclerView.Adapter<SnapBestOfWeek.ReyclerViewHolder>  {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Book> books;
    private View item;


    public SnapBestOfWeek(Context context, ArrayList<Book> books) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.books = books;
    }

    @Override
    public SnapBestOfWeek.ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        item = layoutInflater.inflate(R.layout.home_best_of_week, parent, false);

        return new SnapBestOfWeek.ReyclerViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SnapBestOfWeek.ReyclerViewHolder holder, final int position) {
        final Book book = books.get(position);

        Picasso.get().load(book.getCoverUrl()).into(holder.image);
        holder.title.setText(book.getTitle());
        //holder.author.setText(item.getAuthor());
        holder.numbLovers.setText("" + book.getLovers());
        holder.numbReaders.setText("" + book.getReadings());
        holder.author.setText(book.getAuthorFirstName() + " " + book.getAuthorLastNAme());

        int genreID = Integer.parseInt(book.getGenreID());


        switch (genreID){
            case 1:
                holder.colorGenre.setImageResource(R.drawable.for_children);
                holder.lovers.setColorFilter(ContextCompat.getColor(context, R.color.forChildrenDark));
                holder.numbLovers.setTextColor(ContextCompat.getColor(context, R.color.forChildrenDark));
                holder.readers.setColorFilter(ContextCompat.getColor(context,R.color.forChildrenLight));
                holder.numbReaders.setTextColor(ContextCompat.getColor(context,R.color.forChildrenLight));
                break;
            case 2:
                holder.colorGenre.setImageResource(R.drawable.biografy);
                holder.lovers.setColorFilter(ContextCompat.getColor(context, R.color.biografyDark));
                holder.numbLovers.setTextColor(ContextCompat.getColor(context, R.color.biografyDark));
                holder.readers.setColorFilter(ContextCompat.getColor(context,R.color.biografyLight));
                holder.numbReaders.setTextColor(ContextCompat.getColor(context, R.color.biografyLight));
                break;
            case 3:
                holder.colorGenre.setImageResource(R.drawable.erotic);
                holder.lovers.setColorFilter(ContextCompat.getColor(context, R.color.eroticLight));
                holder.numbLovers.setTextColor(ContextCompat.getColor(context, R.color.eroticLight));
                holder.readers.setColorFilter(ContextCompat.getColor(context,R.color.eroticDark));
                holder.numbReaders.setTextColor(ContextCompat.getColor(context, R.color.eroticDark));
                break;
            case 4:
                holder.colorGenre.setImageResource(R.drawable.sci_fi);
                holder.lovers.setColorFilter(ContextCompat.getColor(context, R.color.scifiDark));
                holder.numbLovers.setTextColor(ContextCompat.getColor(context, R.color.scifiDark));
                holder.readers.setColorFilter(ContextCompat.getColor(context,R.color.scifiLight));
                holder.numbReaders.setTextColor(ContextCompat.getColor(context,R.color.scifiLight));
                break;
            case 5:
                holder.colorGenre.setImageResource(R.drawable.comics_manga);
                holder.lovers.setColorFilter(ContextCompat.getColor(context, R.color.comicsDark));
                holder.numbLovers.setTextColor(ContextCompat.getColor(context, R.color.comicsDark));
                holder.readers.setColorFilter(ContextCompat.getColor(context,R.color.comicsLight));
                holder.numbReaders.setTextColor(ContextCompat.getColor(context, R.color.comicsDark));
                break;
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LogConditional")
            @Override
            public void onClick(View view) {

                    Intent sharedIntent = new Intent(context, BookDetail.class);

                    //Pair[] pairs = new Pair[2];
                   //pairs[0] = new Pair<View, String>(holder.colorGenre, "genreTransition");

                    sharedIntent.putExtra("pos", book.getCoverUrl());
                    sharedIntent.putExtra("title", book.getTitle());
                    sharedIntent.putExtra("genre", Integer.parseInt(book.getGenreID()));
                    //sharedIntent.putExtra("author", book.getAuthor());
                    sharedIntent.putExtra("loversNumb", book.getLovers());
                    sharedIntent.putExtra("readersNumb", book.getReadings());
                    //sharedIntent.putExtra("colorGenre",)
                    sharedIntent.putExtra("coverImage", book.getCoverUrl());


                    context.startActivity(sharedIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        int a;

        if(books != null && !books.isEmpty()) {

            a = 6;

        }
        else {

            a = 0;

        }

        return a;
    }



    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView author;
        private ImageView colorGenre;
        private ImageView lovers;
        private ImageView readers;
        private TextView numbLovers;
        private TextView numbReaders;



        private ReyclerViewHolder(final View v) {
            super(v);

            image = v.findViewById(R.id.image_bestweek);
            colorGenre = v.findViewById(R.id.rettangolo_smussato_rv);
            title = v.findViewById(R.id.title_rv_bestweek);
            author = v.findViewById(R.id.author_bestweek);
            lovers = v.findViewById(R.id.lovers_best_week);
            readers = v.findViewById(R.id.readers_best_week);
            numbLovers = v.findViewById(R.id.numb_lovers_best_week);
            numbReaders = v.findViewById(R.id.numb_readers_best_week);
        }
    }
}
