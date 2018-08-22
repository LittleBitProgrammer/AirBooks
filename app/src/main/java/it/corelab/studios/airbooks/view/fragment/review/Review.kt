package it.corelab.studios.airbooks.view.fragment.review

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.API.remote.ApiUtils
import it.corelab.studios.airbooks.model.general.main.isSectionVisible
import it.corelab.studios.airbooks.model.general.main.setupActionBar
import it.corelab.studios.airbooks.model.data.REVIEW.GetReviews
import it.corelab.studios.airbooks.model.data.REVIEW.Item
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.view.adapters.review.ReviewAdapter
import it.corelab.studios.airbooks.view.anko.layout.review.ReviewView
import it.corelab.studios.airbooks.view.widget.CustomNested
import it.corelab.studios.airbooks.view.widget.DiagonalView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import java.util.*

class Review : Fragment(), OnReselectedDelegate {

    private lateinit var mainUI: ReviewView

    private lateinit var viewUI: View

    private val mAPIService = ApiUtils.getAPIService()

    private var customNestedHome: CustomNested? = null
    private var diagonalView: DiagonalView? = null

    private var bookId: String? = null
    private var bookTitle: String? = null

    private lateinit var recyclerView: RecyclerView

    private lateinit var listItem: List<Item>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = ReviewView()

        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        customNestedHome = activity?.findViewById(R.id.nested_home)
        diagonalView = activity?.findViewById(R.id.diagonal_main)

        recyclerView = mainUI.recyclerview

        bookId = arguments?.getString("bookId")
        bookTitle = arguments?.getString("bookTitle")

        customNestedHome?.animateToActionBar(diagonalView)

        val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", "")

        getReview("http://airbooks.altervista.org/API/v2/books/$bookId/reviews", Locale.getDefault().language, "android",token!!)
        return viewUI
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreatedProfile")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar(bookTitle,5, null,null)

    override fun onReselected() = setupActionBar()

    private fun getReview(url: String, lang: String, os: String, token: String){
        mAPIService!!.getReviews(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetReviews> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getReviewResponse: GetReviews) {
                
                listItem = getReviewResponse.result.items
                val customListViewAdapter = ReviewAdapter(listItem, ctx)
                recyclerView.adapter = customListViewAdapter
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }
}