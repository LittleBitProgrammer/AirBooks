package it.corelab.studios.airbooks.view.fragment.book.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import it.corelab.studios.airbooks.view.widget.CustomNested
import it.corelab.studios.airbooks.view.widget.DiagonalView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.general.main.isSectionVisible
import it.corelab.studios.airbooks.model.general.main.setupActionBar
import it.corelab.studios.airbooks.view.anko.layout.book.detail.BookDetailView
import mehdi.sakout.fancybuttons.FancyButton
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.ctx

class DetailBook: Fragment(), OnReselectedDelegate {

    private lateinit var mainUI: BookDetailView

    private lateinit var viewUI: View

    private lateinit var diagonalView: DiagonalView
    private lateinit var customNestedHome: CustomNested
    private lateinit var customNestedExplore: CustomNested
    private lateinit var button: FancyButton
    private lateinit var buttonPreference: FancyButton
    private lateinit var linearBottom: LinearLayout

    private var firstColor:String? = null
    private var secondColor:String? = null
    private var coverUrl:String? = null
    private var bookTitle:String? = null
    private var bookAuthor:String? = null
    private var bookGenre: String? = null
    private var bookId: String? = null
    private var bookReaders: Int? = null
    private var bookLovers: Int? = null
    private var counterReview: Int? = null
    private var starCount: Double? = null
    private var bookDescription: String? = null
    private var tagsList: ArrayList<String>? = null
    private var isComingFromHome: Boolean? = null
    private var isComingFromExplore: Boolean? = null
    private var isSaved: Boolean? = null

    private lateinit var linearCard: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstColor = arguments?.getString("firstColor")
        secondColor = arguments?.getString("secondColor")
        coverUrl = arguments?.getString("coverUrl")
        bookTitle = arguments?.getString("bookTitle")
        bookAuthor= arguments?.getString("bookAuthor")
        bookGenre= arguments?.getString("bookGenre")
        bookId= arguments?.getString("bookId")
        bookReaders= arguments?.getInt("bookReaders")
        bookLovers= arguments?.getInt("bookLovers")
        counterReview = arguments?.getInt("countNumb")
        starCount = arguments?.getDouble("star")
        bookDescription  = arguments?.getString("bookDescription")
        tagsList = arguments?.getStringArrayList("tags")
        isComingFromHome= arguments?.getBoolean("comingHome")
        isComingFromExplore= arguments?.getBoolean("comingExplore")
        isSaved= arguments?.getBoolean("isSaved")

        diagonalView= activity?.findViewById(R.id.diagonal_main)!!
        customNestedHome = activity?.findViewById(R.id.nested_home)!!
        customNestedExplore = activity?.findViewById(R.id.nested_explore)!!
        button = activity?.findViewById(R.id.color_button_read_now)!!
        buttonPreference = activity?.findViewById(R.id.preferred_button)!!
        linearBottom = activity?.findViewById(R.id.linearMain)!!
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = BookDetailView(coverUrl,bookTitle,bookAuthor,bookGenre,bookReaders,bookLovers,bookDescription,firstColor,starCount,tagsList)
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        linearCard = mainUI.linearGenre

        if (isComingFromHome!!) {
            customNestedHome.animateToOriginal(diagonalView)
            customNestedHome.scrollTo(0, 0)
        } else if (isComingFromExplore!!) {
            customNestedExplore.animateToOriginal(diagonalView)
            customNestedExplore.scrollTo(0, 0)
        }

        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("firstColor", arguments!!.getString("firstColor"))?.apply()
        sharedPreferences?.edit()?.putString("secondColor", arguments!!.getString("secondColor"))?.apply()

        val colors = intArrayOf(Color.parseColor("#${arguments?.getString("firstColor")}"), Color.parseColor("#${arguments?.getString("secondColor")}"))

        val gd = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
        gd.cornerRadius = 0f

        val gd2 = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
        gd.cornerRadius = 0f

        diagonalView.background = gd
        linearCard.background = gd2
        button.backgroundColor = Color.parseColor("#$firstColor")
        buttonPreference.backgroundColor = Color.parseColor("#$secondColor")

        if (!isSaved!!) {
            buttonPreference.setIconResource(R.drawable.ic_bookmark_border)
            buttonPreference.setOnClickListener {
                buttonPreference.setIconResource(R.drawable.ic_bookmark)
                isSaved = true
            }
        } else {
            buttonPreference.setIconResource(R.drawable.ic_bookmark)
            buttonPreference.setOnClickListener {
                buttonPreference.setIconResource(R.drawable.ic_bookmark_border)
                isSaved = false
            }
        }


        return viewUI
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isSectionVisible()) setupActionBar()
        if (linearBottom.visibility == View.INVISIBLE){
            linearBottom.visibility = View.VISIBLE
            linearBottom.isEnabled = true
        }
    }


    private fun setupActionBar() = setupActionBar(bookTitle, 4, counterReview.toString(), bookId)

    override fun onReselected() = setupActionBar()
}