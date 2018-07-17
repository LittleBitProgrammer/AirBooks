package it.corelab.studios.airbooks.section.login.pages.layout

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.login.fragment.RecoverPasswordFragment
import org.jetbrains.anko.*

class RecoverPasswordUI: AnkoComponent<RecoverPasswordFragment> {

    lateinit var email: EditText
    lateinit var backButton: Button
    lateinit var recoverButton: Button

    override fun createView(ui: AnkoContext<RecoverPasswordFragment>) = with(ui) {

        linearLayout {
            id = Ids.LINEAR_LAYOUT

            isFocusable = true
            isFocusableInTouchMode = true

            orientation = LinearLayout.VERTICAL
            //this@linearLayout.setBackgroundColor(ContextCompat.getColor(ctx,R.color.scifiDark))

            linearLayout {
                id = Ids.LINEAR_LAYOUT_EDIT_TEXT

                orientation = LinearLayout.VERTICAL
                background = ContextCompat.getDrawable(ctx, R.drawable.login_edit_text)

                email = editText{
                    id = Ids.RECOVER_PASSWORD_EDIT_TEXT

                    this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon ,0,0,0)
                    compoundDrawablePadding = dip(8)
                    backgroundColor = Color.parseColor("#00000000")
                    hint = resources.getString(R.string.email_edit_text_sign_in)
                    this.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    maxLines = 1

                }.lparams(width = matchParent, height = matchParent){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

            }.lparams(width = matchParent, height = dip(55)){
                rightMargin = dip(20)
                leftMargin = dip(20)
            }

            recoverButton = button {
                id = Ids.RECOVER_BUTTON

                background = ContextCompat.getDrawable(ctx,R.drawable.login_button_background)
                text = resources.getString(R.string.recover)
                textColor = Color.parseColor("#FFFFFF")
                textSize = sp(5).toFloat()
                allCaps = false

            }.lparams(width = matchParent, height = dip(39)){
                rightMargin = dip(20)
                leftMargin = dip(20)
                topMargin = dip(12)
            }

            backButton = button {
                id = Ids.BACK_TO_LOGIN

                backgroundColor = Color.parseColor("#00000000")
                text = resources.getString(R.string.back_to_login_recover_password)
                setAllCaps(false)
                textColor = Color.parseColor("#FFFFFF")

            }.lparams(width = wrapContent, height = wrapContent){
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    private object Ids{
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_RECOVER_PASSWORD
        const val LINEAR_LAYOUT_EDIT_TEXT = R.id.LINEAR_LAYOUT_EDIT_TEXT_RECOVER_PASSWORD
        const val RECOVER_PASSWORD_EDIT_TEXT = R.id.RECOVER_PASSWORD_EDIT_TEXT
        const val RECOVER_BUTTON = R.id.RECOVER_BUTTON
        const val BACK_TO_LOGIN = R.id.BACK_TO_LOGIN_RECOVER_PASSWORD
    }
}