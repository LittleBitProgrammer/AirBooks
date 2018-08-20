package it.corelab.studios.airbooks.view.anko.layout.login.pages

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.login.LoginFragment
import org.jetbrains.anko.*

class SignInLayout: AnkoComponent<LoginFragment> {

    lateinit var forgotButton: Button
    lateinit var loginButton: Button
    lateinit var signUpButton: Button
    lateinit var facebookButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    override fun createView(ui: AnkoContext<LoginFragment>) = with(ui) {

        linearLayout {
            id = Ids.LINEAR_LAYOUT_EXTERNAL

            isFocusable = true
            isFocusableInTouchMode = true

            orientation = LinearLayout.VERTICAL

            //this@linearLayout.setBackgroundColor(ContextCompat.getColor(ctx,R.color.scifiDark))

            linearLayout {
                Ids.LINEAR_LAYOUT_EDIT_TEXT

                orientation = LinearLayout.VERTICAL
                background = ContextCompat.getDrawable(ctx,R.drawable.login_edit_text)

                emailEditText = editText{
                    id = Ids.EDIT_TEXT_EMAIL

                    this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon ,0,0,0)
                    compoundDrawablePadding = dip(8)
                    backgroundColor = Color.parseColor("#00000000")
                    hint = resources.getString(R.string.email_edit_text_sign_in)
                    this.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    maxLines = 1

                }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                view {
                    id = Ids.DIVIDER

                    backgroundColor = Color.parseColor("#64E8E8E8")

                }.lparams(width = matchParent, height = dip(2)){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                passwordEditText = editText{
                    id = Ids.EDIT_TEXT_PASSWORD

                    this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon ,0,0,0)
                    compoundDrawablePadding = dip(8)
                    backgroundColor = Color.parseColor("#00000000")
                    hint = resources.getString(R.string.password_edit_text_sign_in)
                    this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                    this.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
                    maxLines = 1

                }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

            }.lparams(width = matchParent, height = dip(110)){
                rightMargin = dip(20)
                leftMargin = dip(20)
            }

            loginButton = button {
                id = Ids.LOGIN_BUTTON_SIGN_IN

                background = ContextCompat.getDrawable(ctx,R.drawable.login_button_background)
                text = resources.getString(R.string.login_button_sign_in)
                textColor = Color.parseColor("#FFFFFF")
                textSize = sp(5).toFloat()

            }.lparams(width = matchParent, height = dip(39)){
                rightMargin = dip(20)
                leftMargin = dip(20)
                topMargin = dip(14)
            }

            forgotButton = button {
                Ids.FORGOT_PSW

                backgroundColor = Color.parseColor("#00000000")
                text = resources.getString(R.string.forgot_password_sign_in)
                isAllCaps = false
                textColor = Color.parseColor("#FFFFFF")

            }.lparams(width = wrapContent, height = wrapContent){
                gravity = Gravity.CENTER_HORIZONTAL
            }

            linearLayout {
                id = Ids.LINEAR_LAYOUT_BOTTOM

                //this.setBackgroundColor(ContextCompat.getColor(ctx,R.color.scifiDark))
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.BOTTOM

                linearLayout {
                    id = Ids.OR_LOG_IN_WITH_SIGN_IN

                    //this.setBackgroundColor(ContextCompat.getColor(ctx,R.color.scifiDark))

                    orientation = LinearLayout.HORIZONTAL

                    view {
                        id = Ids.DIVIDER_LEFT

                        backgroundColor = Color.parseColor("#FFFFFF")

                    }.lparams(width = dip(20), height = dip(1), weight = 1F){
                        rightMargin = dip(8)
                        leftMargin = dip(8)
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView(R.string.text_or_log_in_sign_in) {
                        id = Ids.TEXT_OR_LOG_IN

                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        gravity = Gravity.CENTER_VERTICAL
                        textColor = Color.parseColor("#FFFFFF")

                    }.lparams(width = wrapContent, height = wrapContent, weight = 1F){
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    view {
                        id = Ids.DIVIDER_RIGHT

                        backgroundColor = Color.parseColor("#FFFFFF")

                    }.lparams(width = dip(20), height = dip(1), weight = 1F){
                        rightMargin = dip(8)
                        leftMargin = dip(8)
                        gravity = Gravity.CENTER_VERTICAL
                    }

                }.lparams(width = matchParent, height = wrapContent)

                facebookButton = button {
                    id = Ids.BUTTON_FACEBOOK

                    //this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.facebook_letter_logo ,0,0,0)
                    compoundDrawablePadding = dip(18)
                    background = ContextCompat.getDrawable(ctx,R.drawable.login_button_background)
                    text = resources.getString(R.string.facebook)
                    isAllCaps = false
                    textColor = Color.parseColor("#ffffff")
                    textSize = sp(6).toFloat()
                    typeface = ResourcesCompat.getFont(ctx,R.font.facebook)

                }.lparams(width = matchParent, height = dip(39)){
                    rightMargin = dip(20)
                    leftMargin = dip(20)
                    topMargin = dip(12)
                }

                signUpButton = button(R.string.no_account_sign_in) {
                    Ids.NO_ACCOUNT_BUTTON

                    backgroundColor = Color.parseColor("#00000000")
                    isAllCaps = false
                    textColor = Color.parseColor("#FFFFFF")

                }.lparams(width = wrapContent, height = wrapContent){
                    gravity = Gravity.CENTER_HORIZONTAL
                }

            }.lparams(width = matchParent, height = dip(100),weight = 1F)
        }
    }

    private object Ids{

        const val LINEAR_LAYOUT_EXTERNAL = R.id.LINEAR_LAYOUT_EXTERNAL_SIGN_IN_FRAGMENT
        const val LINEAR_LAYOUT_EDIT_TEXT = R.id.LINEAR_LAYOUT_EDIT_TEXT_SIGN_IN
        const val EDIT_TEXT_EMAIL = R.id.EDIT_TEXT_EMAIL_SIGN_IN
        const val EDIT_TEXT_PASSWORD = R.id.EDIT_TEXT_PASSWORD_SIGN_IN
        const val DIVIDER = R.id.DIVIDER_SIGN_IN
        const val LOGIN_BUTTON_SIGN_IN = R.id.LOGIN_BUTTON_SIGN_IN
        const val FORGOT_PSW = R.id.FORGOT_PASSWORD_BUTTON_SIGN
        const val LINEAR_LAYOUT_BOTTOM = R.id.LINEAR_LAYOUT_BOTTOM_SIGN_IN
        const val OR_LOG_IN_WITH_SIGN_IN = R.id.OR_LOG_IN_WITH_SIGN_IN
        const val DIVIDER_LEFT = R.id.DIVIDER_LEFT_SIGN_IN
        const val DIVIDER_RIGHT = R.id.DIVIDER_RIGHT_SIGN_IN
        const val TEXT_OR_LOG_IN = R.id.TEXT_VIEW_OR_LOG_IN_SIGN_IN
        const val BUTTON_FACEBOOK = R.id.BUTTON_FACEBOOK_SIGN_IN
        const val NO_ACCOUNT_BUTTON = R.id.NO_ACCOUNT_BUTTON_SIGN_IN

    }
}
