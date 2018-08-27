package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import com.google.gson.reflect.TypeToken
import it.corelab.studios.airbooks.model.data.HOME.Genre
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookTagAdapter
import it.corelab.studios.airbooks.view.adapters.add.book.CategoriesDialogAdapter
import it.corelab.studios.airbooks.view.adapters.bookDetail.TagAdapter




class Categories : AnkoComponent<ViewGroup> {

    private val tagArrayList: ArrayList<String> = ArrayList()

    private lateinit var tagRecycler: RecyclerView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {

            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            recyclerView {

                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(this)
                this.layoutManager = GridLayoutManager(ctx, 2, GridLayoutManager.HORIZONTAL, false)
                onFlingListener = snapHelper
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

            editText {
                fitsSystemWindows = true
                hint = "Type some tags (press Enter to add)"
                hintTextColor = Color.parseColor("#AAAAAA")
                textSize = 12F
                background = null
                maxLines = 1
                inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                showSoftInputOnFocus = true
                isFocusableInTouchMode = true
                val maxLength = 30

                this.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                    // If the event is a key-down event on the "enter" button
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        // Perform action on key press
                        if (this@editText.text.toString() != "") tagArrayList.add(this@editText.text.toString())

                        val tagAdapter = AddBookTagAdapter(tagArrayList)
                        tagRecycler.adapter = tagAdapter

                        this@editText.text.clear()
                        return@OnKeyListener true
                    }
                    false
                })
            }.lparams(width = matchParent, height = wrapContent){
                //topMargin = dip(10)
                bottomMargin = dip(5)
                marginStart = dip(8)
                descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS
            }

            tagRecycler = recyclerView {
                fitsSystemWindows = true
                val layoutManager = FlexboxLayoutManager(ctx)
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.justifyContent = JustifyContent.FLEX_START
                this.layoutManager = layoutManager
                setHasFixedSize(true)

            }.lparams(width = matchParent, height = wrapContent){
                marginStart = dip(16)
                bottomMargin = dip(10)
            }
        }
    }
}