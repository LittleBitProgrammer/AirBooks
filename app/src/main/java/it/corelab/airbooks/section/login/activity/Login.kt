package it.corelab.airbooks.section.login.activity

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton

import it.corelab.airbooks.R
import it.corelab.airbooks.fragment.Login_Fragment


class Login : AppCompatActivity() {

    private var fragmentManager: android.support.v4.app.FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //==========================
        //      hide status bar
        //==========================

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        fragmentManager = supportFragmentManager
        leftArrow = findViewById(R.id.left_arrow_login)

        setOffLeftArrow()

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.frameContainer, Login_Fragment(), "Login_FRagment")
                    .commit()
        }

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
        }
        return super.dispatchTouchEvent(event)
    }


    private fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]

        // point is inside view bounds
        return x > viewX && x < viewX + view.width && y > viewY && y < viewY + view.height
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    fun setOffLeftArrow() {
        leftArrow.isEnabled = false
        leftArrow.visibility = View.INVISIBLE
    }

    companion object {
        var leftArrow: ImageButton
    }
}
