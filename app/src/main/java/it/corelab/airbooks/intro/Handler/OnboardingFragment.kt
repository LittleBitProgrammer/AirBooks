package it.corelab.airbooks.intro.Handler

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.airbooks.intro.Pages.Layout.OnBoardingTwo_Layout
import it.corelab.airbooks.intro.Pages.Layout.OnboardingFour_Layout
import it.corelab.airbooks.intro.Pages.Layout.OnboardingOne_Laoyout
import it.corelab.airbooks.intro.Pages.Layout.OnboardingThree_Layout
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