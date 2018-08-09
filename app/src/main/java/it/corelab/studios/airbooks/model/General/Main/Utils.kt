package it.corelab.studios.airbooks.model.General.Main

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import it.corelab.studios.airbooks.R

fun <T> T?.or(default: T): T = this ?: default
fun <T> T?.or(compute: () -> T): T = this ?: compute()

fun Fragment.isSectionVisible(): Boolean = (((view?.parent as? ViewGroup)?.parent as? ViewGroup)?.visibility == View.VISIBLE)

fun Fragment.setupActionBar(title: String, id: Int){

    val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
    val firstColor = sharedPreferences.getString("firstColor", "")
    val secondColor = sharedPreferences.getString("secondColor", "")
    val linearBottom: LinearLayout? = activity?.findViewById(R.id.linearMain)

    (activity as? AppCompatActivity)?.supportActionBar?.apply {

        this.elevation = 0F

        if (firstColor != null && secondColor != null) {

            val colors = intArrayOf(Color.parseColor("#$firstColor"), Color.parseColor("#$secondColor"))

            val gd = GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT,
                    colors)

            gd.cornerRadius = 0f

            setBackgroundDrawable(gd)
        }


        this.title = title
        when (id) {

            0 -> {
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_home)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE
                Log.i("SECTION", title)
            }

            1->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_explore)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE
                Log.i("SECTION", title)
            }

            2->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_library)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE
                Log.i("SECTION", title)
            }

            3->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_book_detail)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE
                Log.i("SECTION", title)
            }

            4->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_book_detail)

                linearBottom?.isEnabled = true
                linearBottom?.visibility = View.VISIBLE
                Log.i("SECTION", title)
            }
        }
        setDisplayShowHomeEnabled(false)
        setDisplayHomeAsUpEnabled(false)
    }

    setHasOptionsMenu(false)
}