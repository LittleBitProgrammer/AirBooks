package it.corelab.airbooks.section.login.pages.layout

import it.corelab.airbooks.R
import it.corelab.airbooks.section.login.fragment.LoginFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.constraint.layout.constraintLayout

class SignInLayout: AnkoComponent<LoginFragment> {
    override fun createView(ui: AnkoContext<LoginFragment>) = with(ui) {

        constraintLayout {
            id = Ids.CONSTRAINT_LAYOUT

            isFocusable = true
            isFocusableInTouchMode = true
        }
    }

    private object Ids{
        const val CONSTRAINT_LAYOUT = R.id.CONSTRAINT_LAYOUT_SIGN_IN_FRAGMENT
    }
}
