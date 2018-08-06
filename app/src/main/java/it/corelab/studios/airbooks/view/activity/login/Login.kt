package it.corelab.studios.airbooks.view.activity.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.login.LoginFragment
import it.corelab.studios.airbooks.view.anko.layout.login.host.LoginHost
import org.jetbrains.anko.setContentView


class Login : AppCompatActivity() {

    private var fragmentManager: android.support.v4.app.FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginHost().setContentView(this)

        //==========================
        //      hide status bar
        //==========================

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        fragmentManager = supportFragmentManager

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, LoginFragment(), "Login_FRagment")
                    .commit()
        }

    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
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
}
