package it.corelab.studios.airbooks.view.fragment.book.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.view.widget.CustomNested
import it.corelab.studios.airbooks.view.widget.DiagonalView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import it.corelab.studios.airbooks.model.Gesture.GestureHelper
import kotlinx.android.synthetic.main.activity_main.view.*
import mehdi.sakout.fancybuttons.FancyButton
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class DetailBook: Fragment(), OnReselectedDelegate {

    private var diagonalView: DiagonalView? = null
    private var customNested: CustomNested? = null
    private var button: FancyButton? = null
    private var buttonPreference: FancyButton? = null
    private var firstColor:String? = null
    private var secondColor:String? = null
    private var coverUrl:String? = null
    private lateinit var coverImage: ImageView
    private lateinit var cardBook: CardView
    private lateinit var cardGenre: CardView
    internal var isSwipedCenter = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.activity_book_detail,null).apply {

                customNested = activity?.findViewById(R.id.nested_home)
                diagonalView = activity?.findViewById(R.id.diagonal_main)
                button = activity?.findViewById(R.id.color_button_read_now)
                buttonPreference = activity?.findViewById(R.id.preferred_button)
                coverImage = findViewById(R.id.image_bestweek_shared)
                cardBook = findViewById(R.id.cardView_shared)
                cardGenre = findViewById(R.id.cardView_categories_shared)

                Handler().postDelayed({

                    button?.visibility = View.VISIBLE
                    button?.isEnabled = true
                    buttonPreference?.visibility = View.VISIBLE
                    button?.isEnabled = true

                }, 200)

                customNested?.animateToOriginal(diagonalView!!)
                customNested?.scrollTo(0,0)

                firstColor = arguments?.getString("firstColor")
                secondColor = arguments?.getString("secondColor")
                coverUrl = arguments?.getString("coverUrl")
                Picasso.get().load(coverUrl).into(coverImage)

                val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
                sharedPreferences?.edit()?.putString("firstColor", arguments!!.getString("firstColor"))?.apply()
                sharedPreferences?.edit()?.putString("secondColor", arguments!!.getString("secondColor"))?.apply()

                val colors = intArrayOf(Color.parseColor("#${arguments?.getString("firstColor")}"), Color.parseColor("#${arguments?.getString("secondColor")}"))

                val gd = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        colors)
                gd.cornerRadius = 0f

                val gd2 = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        colors)
                gd.cornerRadius = 0f

                diagonalView?.background = gd
                cardGenre.background = gd2
                button?.backgroundColor = Color.parseColor("#$firstColor")
                buttonPreference!!.backgroundColor = Color.parseColor("#$secondColor")

                cardBook.setOnTouchListener(object : GestureHelper(ctx) {
                    override fun onClick() {
                        if (isSwipedCenter) {
                            tapDownAnimation()
                        } else if (!isSwipedCenter) {
                            tapTopAnimation()
                        }

                    }
                })

                Log.d("BookDetail", "onCreateView")
                Log.d("Arguments", "$firstColor")
                Log.d("Arguments", "$secondColor")
                Log.d("Arguments", "$diagonalView")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isSectionVisible()) setupActionBar()
    }

    fun tapDownAnimation() {
        Log.d("swipe bookDetail", "click to bottom")
        cardBook.animate().translationY(20f)
        cardGenre.animate().translationY(-30f)
        this.isSwipedCenter = false
    }

    fun tapTopAnimation() {
        Log.d("swipe bookDetail", "click to center")
        cardBook.animate().translationY(0f)
        cardGenre.animate().translationY(0f)
        this.isSwipedCenter = true
    }

    private fun setupActionBar() = setupActionBar("bla",false,4,firstColor,secondColor)

    override fun onReselected() = setupActionBar()

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId){
            R.id.back_to_home -> {
                toast("fsfdf")
                view?.findNavController()?.navigateUp()
                button?.isEnabled = false
                button?.visibility = View.INVISIBLE

                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}