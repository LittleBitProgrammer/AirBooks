package it.corelab.airbooks.Intro

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import java.util.*

class OnboardingFragment : Fragment(), Observer {
    override fun update(o: Observable?, arg: Any?) {
        val root = view
    }

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        page = arguments!!.getInt(PAGE)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Select a layout according to the current page

        return when (page){
            0 -> OnboardingOne_Laoyout().createView(AnkoContext.create(ctx, this))
            1 -> OnBoardingTwo_Layout().createView(AnkoContext.create(ctx,this))
            2 -> OnboardingThree_Layout().createView(AnkoContext.create(ctx,this))
            else -> OnboardingFour_Layout().createView(AnkoContext.create(ctx, this))
        }

    }

    companion object {

        private val PAGE = "page"

        fun newInstance(page: Int): OnboardingFragment {
            val fragment = OnboardingFragment()

            val b = Bundle()
            b.putInt(PAGE, page)

            fragment.arguments = b

            return fragment
        }
    }

}