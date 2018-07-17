package it.corelab.studios.airbooks.section.intro.handler

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.section.intro.pages.layout.OnBoardingTwoLayout
import it.corelab.studios.airbooks.section.intro.pages.layout.OnBoardingFourLayout
import it.corelab.studios.airbooks.section.intro.pages.layout.OnBoardingOneLayout
import it.corelab.studios.airbooks.section.intro.pages.layout.OnBoardingThreeLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import java.util.*

class OnBoardingFragment : Fragment(), Observer {

    override fun update(o: Observable?, arg: Any?) {
       // val root = view
    }

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        page = arguments!!.getInt(PAGE)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Select a layout according to the current page

        return when (page){
            0 -> OnBoardingOneLayout().createView(AnkoContext.create(ctx, this))
            1 -> OnBoardingTwoLayout().createView(AnkoContext.create(ctx,this))
            2 -> OnBoardingThreeLayout().createView(AnkoContext.create(ctx,this))
            else -> OnBoardingFourLayout().createView(AnkoContext.create(ctx, this))
        }

    }

    companion object {

        private const val PAGE = "page"

        fun newInstance(page: Int): OnBoardingFragment {
            val fragment = OnBoardingFragment()

            val b = Bundle()
            b.putInt(PAGE, page)

            fragment.arguments = b

            return fragment
        }
    }

}