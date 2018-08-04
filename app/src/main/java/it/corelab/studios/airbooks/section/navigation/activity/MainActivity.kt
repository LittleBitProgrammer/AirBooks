package it.corelab.studios.airbooks.section.navigation.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import java.util.ArrayList
import it.corelab.studios.airbooks.R
import kotlinx.android.synthetic.main.activity_main.*
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.or

class MainActivity : AppCompatActivity() {

    private val sectionHomeWrapper: FrameLayout by lazy { section_home_wrapper }
    private val sectionExploreWarapper: FrameLayout by lazy { section_explore_wrapper }
    private val sectionLibraryWrapper: FrameLayout by lazy { section_library_wrapper }
    private val sectionProfileWrapper: FrameLayout by lazy { section_profile_wrapper }

    private val navHomeController: NavController by lazy { findNavController(R.id.section_home) }
    private val navHomeFragment: Fragment by lazy { section_home }

    private val navExploreController: NavController by lazy { findNavController(R.id.section_explore) }
    private val navExploreFragment: Fragment by lazy { section_explore }

    private val navLibraryController: NavController by lazy { findNavController(R.id.section_library) }
    private val navLibraryFragment: Fragment by lazy { section_library }

    private val navProfileController: NavController by lazy { findNavController(R.id.section_profile) }
    private val navProfileFragment: Fragment by lazy { section_profile }

    private var currentController: NavController? = null

    private val mOnNavigationItemSelectedListener = AHBottomNavigation.OnTabSelectedListener { position, _ ->
        var returnValue = false

        when(position){

            0->{
                currentController = navHomeController

                sectionHomeWrapper.visibility = View.VISIBLE
                sectionExploreWarapper.visibility = View.INVISIBLE
                sectionLibraryWrapper.visibility = View.INVISIBLE
                sectionProfileWrapper.visibility = View.INVISIBLE

                nested_home.animateDiagonal(diagonal_main)
                returnValue = true
            }

            1->{
                currentController = navExploreController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionExploreWarapper.visibility = View.VISIBLE
                sectionLibraryWrapper.visibility = View.INVISIBLE
                sectionProfileWrapper.visibility = View.INVISIBLE

                nested_explore.animateDiagonal(diagonal_main)
                returnValue = true
            }

            2->{
                currentController = navLibraryController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionExploreWarapper.visibility = View.INVISIBLE
                sectionLibraryWrapper.visibility = View.VISIBLE
                sectionProfileWrapper.visibility = View.INVISIBLE

                nested_library.animateDiagonal(diagonal_main)
                returnValue = true
            }

            3->{
                currentController = navProfileController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionExploreWarapper.visibility = View.INVISIBLE
                sectionLibraryWrapper.visibility = View.INVISIBLE
                sectionProfileWrapper.visibility = View.VISIBLE

                nested_Profile.animateDiagonal(diagonal_main)
                returnValue = true
            }
        }

        onReselected(position)
        return@OnTabSelectedListener returnValue
    }

    //private FadeFragment currentFragment;
    private val bottomNavigationItems = ArrayList<AHBottomNavigationItem>()

    // UI
    private lateinit var bottomNavigation: AHBottomNavigation



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //HIDE STATUS BAR
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)
        setTheme(R.style.AppTheme)

        currentController = navHomeController

        initUI()

        nested_home.takeScrollVariation(diagonal_main)
        nested_explore.takeScrollVariation(diagonal_main)

    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        currentController?.navigateUp()
    }

    override fun onBackPressed() {
        currentController
                ?.let { if (it.popBackStack().not()) finish() }
                .or { finish() }
    }

    private fun onReselected(itemId: Int){
        when(itemId){

            0->{
                val fragmentClassname = (navHomeController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navHomeFragment.childFragmentManager.fragments.asReversed().forEach {
                    if (it.javaClass.simpleName == fragmentClassname && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }
                }
            }

            1->{
                val fragmentClassName = (navExploreController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navExploreFragment.childFragmentManager.fragments.asReversed().forEach {
                    if (it.javaClass.simpleName == fragmentClassName && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }
                }
            }

            2->{
                val fragmentClassName = (navLibraryController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navLibraryFragment.childFragmentManager.fragments.asReversed().forEach {
                    if (it.javaClass.simpleName == fragmentClassName && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }
                }
            }

            3->{
                val fragmentClassName = (navProfileController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navProfileFragment.childFragmentManager.fragments.asReversed().forEach {
                    if (it.javaClass.simpleName == fragmentClassName && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }
                }
            }
        }
    }


    //============================================================================================//
    //                                      methods_main                                          //
    //============================================================================================//


    /*
    init UI
     */

    private fun initUI() {

        bottomNavigation = findViewById(R.id.navigation)
        //viewPager = findViewById(R.id.view_pager);


        val item1 = AHBottomNavigationItem(R.string.tab1, R.drawable.icona_home, R.color.accent_color)

        val item2 = AHBottomNavigationItem(R.string.tab2, R.drawable.icona_esplora_piena, R.color.colorPrimary)

        val item3 = AHBottomNavigationItem(R.string.tab3, R.drawable.icona_segnalibro, R.color.inactive_color)

        val item4 = AHBottomNavigationItem(R.string.tab4, R.drawable.icona_profilo, R.color.blu_airbooks)

        bottomNavigationItems.add(item1)
        bottomNavigationItems.add(item2)
        bottomNavigationItems.add(item3)
        bottomNavigationItems.add(item4)

        bottomNavigation.addItems(bottomNavigationItems)

        bottomNavigation.isTranslucentNavigationEnabled = true

        // Change colors
        bottomNavigation.accentColor = Color.parseColor("#6E7B8C")
        bottomNavigation.inactiveColor = Color.parseColor("#C6C8CB")

        bottomNavigation.elevation = 0f
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottomNavigation.setOnTabSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is TextInputEditText) {
                if (!isPointInsideView(event.rawX, event.rawY, v)) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    v.clearFocus()
                }
            }
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    /**
     * Determines if given points are inside view
     * @param x - x coordinate of point
     * @param y - y coordinate of point
     * @param view - view object to compare
     * @return true if the points are within view bounds, false otherwise
     */
    private fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]

        // point is inside view bounds
        return x > viewX && x < viewX + view.width && y > viewY && y < viewY + view.height
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        val v = currentFocus
        if (keyCode == KeyEvent.KEYCODE_ENTER && v is EditText) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            v.postDelayed({ v.clearFocus() }, 100)
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}