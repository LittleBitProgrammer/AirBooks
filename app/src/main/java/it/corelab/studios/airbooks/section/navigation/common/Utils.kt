package it.corelab.studios.airbooks.section.navigation.common

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.support.v4.ctx

fun <T> T?.or(default: T): T = this ?: default
fun <T> T?.or(compute: () -> T): T = this ?: compute()

fun Fragment.isSectionVisible(): Boolean = (((view?.parent as? ViewGroup)?.parent as? ViewGroup)?.visibility == View.VISIBLE)

fun Fragment.setupActionBar(title: String, displayHome: Boolean = false, id:Int){
    (activity as? AppCompatActivity)?.supportActionBar?.apply {

        this.elevation = 0F

        when(id){
            0-> {
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_home)
            }
            1-> {
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_explore)
            }
            2->{

            }
            3->{

            }
        }


        setDisplayShowHomeEnabled(displayHome)
        setDisplayHomeAsUpEnabled(displayHome)

        setBackgroundDrawable(ContextCompat.getDrawable(ctx,R.drawable.top_bar))

    }
    setHasOptionsMenu(displayHome)
}