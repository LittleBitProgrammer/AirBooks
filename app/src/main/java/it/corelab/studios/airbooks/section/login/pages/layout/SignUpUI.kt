package it.corelab.studios.airbooks.section.login.pages.layout

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.login.fragment.SignUpFragment
import org.jetbrains.anko.*

class SignUpUI: AnkoComponent<SignUpFragment>{

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var nationality: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var signUpButton: Button
    lateinit var backButton: Button

    override fun createView(ui: AnkoContext<SignUpFragment>)= with(ui) {

        linearLayout {
            id = Ids.LINEAR_LAYOUT_EXTERNAL

            isFocusable = true
            isFocusableInTouchMode = true

            orientation = LinearLayout.VERTICAL
            //this@linearLayout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.scifiDark))

            linearLayout {
                id = Ids.LINEAR_LAYOUT_EDIT_TEXT

                orientation = LinearLayout.VERTICAL
                background = ContextCompat.getDrawable(ctx, R.drawable.login_edit_text)

                linearLayout {
                    id = Ids.LINEAR_PERSON

                    orientation = LinearLayout.HORIZONTAL
                    //this.setBackgroundColor(ContextCompat.getColor(ctx, R.color.scifiDark))

                    imageView {
                        id = Ids.PERSON_ICON

                        Picasso.get().load(R.drawable.person_icon).into(this)
                        scaleType = ImageView.ScaleType.FIT_CENTER

                    }.lparams(width = dip(30), height = dip(50)){
                        gravity = Gravity.CENTER
                        leftMargin = dip(4)
                    }

                    linearLayout {
                        id = Ids.LINEAR_VERTICAL_PERSON

                        orientation = LinearLayout.VERTICAL

                        firstName = editText {
                            id = Ids.EDIT_TEXT_NAME

                            backgroundColor = Color.parseColor("#00000000")
                            hint = resources.getString(R.string.first_name)
                            this.inputType = InputType.TYPE_CLASS_TEXT
                            maxLines = 1

                        }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                            rightMargin = dip(8)
                            leftMargin = dip(8)
                        }

                        view {
                            id = Ids.DIVIDER_PERSON

                            backgroundColor = Color.parseColor("#64E8E8E8")

                        }.lparams(width = matchParent, height = dip(2)){
                            rightMargin = dip(8)
                            leftMargin = dip(8)
                        }

                        lastName = editText {
                            id = Ids.EDIT_TEXT_LAST_NAME

                            backgroundColor = Color.parseColor("#00000000")
                            hint = resources.getString(R.string.last_name)
                            this.inputType = InputType.TYPE_CLASS_TEXT
                            maxLines = 1

                        }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                            rightMargin = dip(8)
                            leftMargin = dip(8)
                        }
                    }
                }.lparams(width = matchParent, height = wrapContent)

                view {
                    id = Ids.DIVIDER_UNDER_PERSON

                    backgroundColor = Color.parseColor("#64E8E8E8")

                }.lparams(width = matchParent, height = dip(2)){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                nationality = editText {
                    id = Ids.EDIT_TEXT_NATIONALITY

                    this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nationality_icon ,0,0,0)
                    compoundDrawablePadding = dip(8)
                    backgroundColor = Color.parseColor("#00000000")
                    isFocusableInTouchMode = false
                    hint = resources.getString(R.string.nationality_sign_up)
                    this.inputType = InputType.TYPE_CLASS_TEXT
                    maxLines = 1

                }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                view {
                    id = Ids.DIVIDER_UNDER_NATIONALITY

                    backgroundColor = Color.parseColor("#64E8E8E8")

                }.lparams(width = matchParent, height = dip(2)){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                email = editText {
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
                    id = Ids.DIVIDER_UNDER_EMAIL

                    backgroundColor = Color.parseColor("#64E8E8E8")

                }.lparams(width = matchParent, height = dip(2)){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                password = editText {
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

                view {
                    id = Ids.DIVIDER_UNDER_PASSWORD

                    backgroundColor = Color.parseColor("#64E8E8E8")

                }.lparams(width = matchParent, height = dip(2)){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }

                confirmPassword = editText {
                    id = Ids.EDIT_TEXT_CONFIRM_PSW

                    this.setCompoundDrawablesWithIntrinsicBounds(R.drawable.confirm_icon ,0,0,0)
                    compoundDrawablePadding = dip(8)
                    backgroundColor = Color.parseColor("#00000000")
                    hint = resources.getString(R.string.confirm_sign_up)
                    this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                    this.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
                    maxLines = 1

                }.lparams(width = matchParent, height = wrapContent,weight = 1F){
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }
            }.lparams(width = matchParent, height = dip(300)){
                rightMargin = dip(20)
                leftMargin = dip(20)
            }

            signUpButton = button {
                id = Ids.SIGN_UP_BUTTON

                background = ContextCompat.getDrawable(ctx,R.drawable.login_button_background)
                text = resources.getString(R.string.sign_up_button)
                allCaps = false
                textColor = Color.parseColor("#FFFFFF")
                textSize = sp(5).toFloat()

            }.lparams(width = matchParent, height = dip(39)){
                rightMargin = dip(20)
                leftMargin = dip(20)
                topMargin = dip(14)
            }

            backButton = button {
                id = Ids.BACK_TO_LOGIN

                backgroundColor = Color.parseColor("#00000000")
                text = resources.getString(R.string.back_to_login_sign_up)
                setAllCaps(false)
                textColor = Color.parseColor("#FFFFFF")

            }.lparams(width = wrapContent, height = wrapContent){
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    private object Ids{

        const val LINEAR_LAYOUT_EXTERNAL = R.id.LINEAR_LAYOUT_SIGN_UP
        const val LINEAR_LAYOUT_EDIT_TEXT = R.id.LINEAR_LAYOUT_EDIT_TEXT_SIGN_UP
        const val LINEAR_PERSON = R.id.LINEAR_LAYOUT_HORIZONTAL_PERSON_SIGN_UP
        const val PERSON_ICON = R.id.PERSON_IMAGE_VIEW_SIGN_UP
        const val LINEAR_VERTICAL_PERSON = R.id.LINEAR_LAYOUT_VERTICAL_PERSON_SIGN_UP
        const val EDIT_TEXT_NAME = R.id.EDIT_TEXT_FIRST_NAME_SIGN_UP
        const val EDIT_TEXT_LAST_NAME = R.id.EDIT_TEXT_LAST_NAME_SIGN_UP
        const val DIVIDER_PERSON = R.id.DIVIDER_PERSON_SIGN_UP
        const val EDIT_TEXT_NATIONALITY = R.id.EDIT_TEXT_NATIONALITY_SIGN_UP
        const val EDIT_TEXT_EMAIL = R.id.EDIT_TEXT_EMAIL_SIGN_UP
        const val EDIT_TEXT_PASSWORD = R.id.EDIT_TEXT_PASSWORD_SIGN_UP
        const val EDIT_TEXT_CONFIRM_PSW = R.id.EDIT_TEXT_CONFIRM_PASSWORD_SIGN_UP
        const val DIVIDER_UNDER_PERSON = R.id.DIVIDER_UNDER_PERSON_SIGN_UP
        const val DIVIDER_UNDER_NATIONALITY = R.id.DIVIDER_UNDER_NATIONALITY_SIGN_UP
        const val DIVIDER_UNDER_EMAIL = R.id.DIVIDER_UNDER_EMAIL_SIGN_UP
        const val DIVIDER_UNDER_PASSWORD = R.id.DIVIDER_UNDER_PASSWORD_SIGN_UP
        const val SIGN_UP_BUTTON = R.id.SIGN_UP_BUTTON
        const val BACK_TO_LOGIN = R.id.BACK_TO_LOGIN_SIGN_UP

    }
}