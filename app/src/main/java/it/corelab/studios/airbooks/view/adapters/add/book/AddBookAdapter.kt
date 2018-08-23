package it.corelab.studios.airbooks.view.adapters.add.book

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.AddSection.PICK_IMAGE
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.pdf.Pdf
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories.Categories
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.cover.CoverBook
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.language.Language
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.title.TitleDescription
import org.jetbrains.anko.AnkoContext


class AddBookAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType){

            0-> {
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            0-> ItemViewHolder0(CoverBook().createView(AnkoContext.create(parent.context, parent)))
            1-> ItemViewHolder1(TitleDescription().createView(AnkoContext.create(parent.context,parent)))
            2-> ItemViewHolder2(Language().createView(AnkoContext.create(parent.context,parent)))
            3-> ItemViewHolder3(Categories().createView(AnkoContext.create(parent.context,parent)))
            else-> ItemViewHolder4(Pdf().createView(AnkoContext.create(parent.context, parent)))
        }

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount()= 5

    inner class ItemViewHolder0(view: View) : RecyclerView.ViewHolder(view) {

        var addCover: CardView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)
        var imageCover: ImageView = view.findViewById(R.id.BOOK_COVER_CHOOSE_BOOK_COVER)

        init {
            ButterKnife.bind(this,view)

            image = imageCover
            this.itemView.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            }
        }
    }

    inner class ItemViewHolder1(view: View) : RecyclerView.ViewHolder(view) {

        //var bookCover: ImageView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)

        init {
            ButterKnife.bind(this,view)
        }
    }

    inner class ItemViewHolder2(view: View) : RecyclerView.ViewHolder(view) {

        //var bookCover: ImageView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)


        init {
            ButterKnife.bind(this,view)
        }
    }

    inner class ItemViewHolder3(view: View) : RecyclerView.ViewHolder(view) {

        //var bookCover: ImageView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)


        init {
            ButterKnife.bind(this,view)
        }
    }

    inner class ItemViewHolder4(view: View) : RecyclerView.ViewHolder(view) {

        //var bookCover: ImageView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)


        init {
            ButterKnife.bind(this,view)
        }
    }

    companion object {
        var image: ImageView? = null
    }

}