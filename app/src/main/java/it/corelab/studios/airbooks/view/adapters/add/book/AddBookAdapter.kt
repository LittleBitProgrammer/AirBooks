package it.corelab.studios.airbooks.view.adapters.add.book

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.pdf.Pdf
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories.Categories
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.cover.CoverBook
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.language.Language
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.title.TitleDescription
import org.jetbrains.anko.AnkoContext
import it.corelab.studios.airbooks.view.dialog.add.book.LanguageDialog


class AddBookAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val PICK_IMAGE = 1
    private val PICKFILE_RESULT_CODE = 2

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
                intent.action = Intent.ACTION_OPEN_DOCUMENT
                activity.startActivityForResult(intent, PICK_IMAGE)
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

        var language: TextView = view.findViewById(R.id.TEXT_VIEW_LANGUAGE)


        init {
            ButterKnife.bind(this,view)
            _languageText = language

            this.itemView.setOnClickListener {
                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val firstColor = sharedPreferences.getString("firstColor", "")

                val languageDialog = LanguageDialog(activity,firstColor)
                languageDialog.show()
            }
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
        var fileText: TextView = view.findViewById(R.id.TEXT_VIEW_CHOOSE_FILE)
        var fileImage: ImageView = view.findViewById(R.id.IMAGE_VIEW_CHOOSE_FILE)
        var extension: TextView = view.findViewById(R.id.TEXT_VIEW_EXTENSION_CHOOSE_FILE)


        init {
            ButterKnife.bind(this,view)

            _text = fileText
            _formatBackground = fileImage
            _extension = extension

            this.itemView.setOnClickListener {

                val intent1 = Intent(Intent.ACTION_GET_CONTENT)
                intent1.type = "*/*"
                val mimetypes = arrayOf("application/epub+zip", "application/pdf")
                intent1.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes)
                activity.startActivityForResult(intent1, PICKFILE_RESULT_CODE)
            }
        }
    }

    companion object {
        var image: ImageView? = null
        var _text: TextView? = null
        var _formatBackground: ImageView? = null
        var _extension: TextView? = null
        var _languageText: TextView? = null
    }

}