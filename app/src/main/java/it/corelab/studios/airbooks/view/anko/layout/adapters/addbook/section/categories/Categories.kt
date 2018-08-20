package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import it.corelab.studios.airbooks.view.adapters.home.CategoriesAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import com.google.gson.reflect.TypeToken
import it.corelab.studios.airbooks.model.data.HOME.Genre
import it.corelab.studios.airbooks.view.adapters.add.book.CategoriesDialogAdapter


class Categories : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {

            lparams(matchParent, wrapContent)
            recyclerView {

                //val snapHelper: LinearSnapHelper = LinearSnapHelper()
                //snapHelper.attachToRecyclerView(this)
                this.layoutManager = GridLayoutManager(ctx, 2, GridLayoutManager.HORIZONTAL, false)
                //onFlingListener = snapHelper
                this.setHasFixedSize(true)

                val sharedPreferences = ctx.getSharedPreferences(ctx.packageName, Context.MODE_PRIVATE)
                val json: String = sharedPreferences.getString("categories","")!!
                val type = object : TypeToken<List<Genre>>() {

                }.type
                val categories: List<Genre> = Gson().fromJson(json,type)

                val snapCategoriesAdapter = CategoriesDialogAdapter(categories)
                adapter = snapCategoriesAdapter
            }.lparams(width = matchParent, height = wrapContent){

                topMargin = dip(10)
                marginStart = dip(16)

            }
        }
    }
}