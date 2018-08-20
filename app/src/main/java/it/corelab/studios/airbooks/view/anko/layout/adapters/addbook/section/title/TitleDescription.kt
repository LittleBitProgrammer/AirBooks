package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.title

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import android.text.InputFilter



class TitleDescription : AnkoComponent<ViewGroup> {

    lateinit var description: EditText
    lateinit var remainingsText: TextView

    private val txwatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            remainingsText.text = "${500-s.length}/500"
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        linearLayout {
            orientation = LinearLayout.VERTICAL

            isFocusable = true
            isFocusableInTouchMode = true
            lparams(width = matchParent, height = wrapContent)
            editText {
                hint = "Type a title"
                hintTextColor = Color.parseColor("#BABABF")
                textSize = 20F
                textColor = Color.parseColor("#565B66")
                background = null
            }.lparams(width = matchParent, height = wrapContent)

            description = editText {
                hint = "Type a description"
                hintTextColor = Color.parseColor("#AAAAAA")
                textSize = 14F
                background = null
                val maxLength = 500
                filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
                this.addTextChangedListener(txwatcher)
            }.lparams(width = matchParent, height = wrapContent)

            remainingsText = textView {
                text = "500/500"
                maxLines = 1
                textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END

            }.lparams(width = matchParent, height = wrapContent, weight = 1F){
                marginEnd = dip(12)
            }
        }
    }
}