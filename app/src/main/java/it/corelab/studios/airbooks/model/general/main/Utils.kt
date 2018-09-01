package it.corelab.studios.airbooks.model.general.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ShareCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.dialog.add.book.CustomDialogClass
import org.jetbrains.anko.support.v4.act

fun <T> T?.or(default: T): T = this ?: default
fun <T> T?.or(compute: () -> T): T = this ?: compute()

fun Fragment.isSectionVisible(): Boolean = (((view?.parent as? ViewGroup)?.parent as? ViewGroup)?.visibility == View.VISIBLE)

fun Fragment.setupActionBar(title: String?, id: Int, string: String?, bookId: String?) {

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

                this.customView.findViewById<ImageButton>(R.id.add_book_home).setOnClickListener {
                    val customDialog = CustomDialogClass(act, R.layout.custom_dialog_book, firstColor)
                    customDialog.show()
                }
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
                this.customView.findViewById<ImageButton>(R.id.back_to_home).setOnClickListener {
                    view?.findNavController()?.navigateUp()
                }
                this.customView.findViewById<TextView>(R.id.number_comments).text = string
                this.customView.findViewById<ImageButton>(R.id.reviewButton).setOnClickListener {

                    view?.findNavController()?.navigate(R.id.action_detailBook_to_review_view, Bundle().apply {

                        putString("bookTitle",title)
                        putString("bookId", bookId)

                    })
                }
                this.customView.findViewById<ImageButton>(R.id.share_button).setOnClickListener {
                    val shareIntent = ShareCompat.IntentBuilder.from(activity)
                            .setText("Condividi da Airbooks")
                            .setType("text/plain")
                            .createChooserIntent()
                            .apply {
                                // https://android-developers.googleblog.com/2012/02/share-with-intents.html
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    // If we're on Lollipop, we can open the intent as a document
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                                } else {
                                    // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                                }
                            }
                    startActivity(shareIntent)
                }

                linearBottom?.isEnabled = true
                linearBottom?.visibility = View.VISIBLE
                Log.i("SECTION", title)
            }

            5->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_all_review)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE

                this.customView.findViewById<TextView>(R.id.action_bar_title_all_review).text = title
                this.customView.findViewById<ImageButton>(R.id.back_from_review).setOnClickListener {
                    view?.findNavController()?.navigateUp()
                }
            }
            6->{
                this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                this.setCustomView(R.layout.actionbar_profile)

                linearBottom?.isEnabled = false
                linearBottom?.visibility = View.INVISIBLE
            }
        }
        setDisplayShowHomeEnabled(false)
        setDisplayHomeAsUpEnabled(false)
    }

    setHasOptionsMenu(false)
}