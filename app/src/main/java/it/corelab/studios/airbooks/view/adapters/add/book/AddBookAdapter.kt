package it.corelab.studios.airbooks.view.adapters.add.book

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.CoverBook
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.Language
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.TitleDescription
import it.corelab.studios.airbooks.view.anko.layout.adapters.home.ContinueRead
import org.jetbrains.anko.AnkoContext



class AddBookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType){
            0->(holder as? ItemViewHolder0)?.itemView?.setOnClickListener {

                //val i = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            0-> ItemViewHolder0(CoverBook().createView(AnkoContext.create(parent.context, parent)))
            1-> ItemViewHolder1(TitleDescription().createView(AnkoContext.create(parent.context,parent)))
            2-> ItemViewHolder2(Language().createView(AnkoContext.create(parent.context,parent)))
            else-> ItemViewHolder0(ContinueRead().createView(AnkoContext.create(parent.context, parent)))
        }

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount()= 5

    inner class ItemViewHolder0(view: View) : RecyclerView.ViewHolder(view) {

        //var bookCover: ImageView = view.findViewById(R.id.CARD_VIEW_CHOOSE_BOOK_COVER)


        init {
            ButterKnife.bind(this,view)
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
}