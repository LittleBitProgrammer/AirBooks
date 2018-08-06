package it.corelab.studios.airbooks.view.fragment.book.detail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import it.corelab.studios.airbooks.view.widget.CustomNested
import it.corelab.studios.airbooks.view.widget.DiagonalView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import org.jetbrains.anko.support.v4.ctx

class DetailBook: Fragment(), OnReselectedDelegate {

    private var diagonalView: DiagonalView? = null
    private var customNested: CustomNested? = null
    private lateinit var firstColor:String
    private lateinit var secondColor:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.activity_book_detail,null).apply {

                customNested = activity?.findViewById(R.id.nested_home)
                diagonalView = activity?.findViewById(R.id.diagonal_main)

                customNested?.scrollTo(0,0)
                firstColor = arguments!!.getString("firstColor")
                secondColor = arguments!!.getString("secondColor")

                val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
                sharedPreferences?.edit()?.putString("firstColor", arguments!!.getString("firstColor"))?.apply()
                sharedPreferences?.edit()?.putString("secondColor", arguments!!.getString("secondColor"))?.apply()

                val colors = intArrayOf(Color.parseColor("#${arguments?.getString("firstColor")}"), Color.parseColor("#${arguments?.getString("secondColor")}"))

                val gd = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        colors)
                gd.cornerRadius = 0f

                diagonalView?.background = gd

                Log.d("BookDetail", "onCreateView")
                Log.d("Arguments", "$firstColor")
                Log.d("Arguments", "$secondColor")
                Log.d("Arguments", "$diagonalView")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("bla",true,0,firstColor,secondColor)

    override fun onReselected() = setupActionBar()

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId){
            android.R.id.home -> {
                view?.findNavController()?.navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}