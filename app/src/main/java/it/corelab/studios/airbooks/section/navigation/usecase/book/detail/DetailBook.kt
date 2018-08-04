package it.corelab.studios.airbooks.section.navigation.usecase.book.detail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import it.corelab.studios.airbooks.DiagonalView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.navigation.activity.MainActivity
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.isSectionVisible
import it.corelab.studios.airbooks.section.navigation.common.setupActionBar
import org.jetbrains.anko.support.v4.ctx

class DetailBook: Fragment(), OnReselectedDelegate {

    private var diagonalView: DiagonalView? = null
    private var firstColor:String? =  null
    private var secondColor:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.activity_book_detail,null).apply {

                diagonalView = activity?.findViewById(R.id.diagonal_main)

                firstColor = arguments?.getString("firstColor")
                secondColor = arguments?.getString("secondColor")

                val colors = intArrayOf(Color.parseColor("#${arguments?.getString("firstColor")}"), Color.parseColor("#${arguments?.getString("secondColor")}"))

                val gd = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        colors)
                gd.cornerRadius = 0f

                diagonalView?.background = gd

                (activity as? AppCompatActivity)?.supportActionBar?.apply {
                    setBackgroundDrawable(gd)
                }

                Log.d("BookDetail", "onCreateView")
                Log.d("Arguments", "$firstColor")
                Log.d("Arguments", "$secondColor")
                Log.d("Arguments", "$diagonalView")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun setupActionBar() = setupActionBar("bla",true,0)

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