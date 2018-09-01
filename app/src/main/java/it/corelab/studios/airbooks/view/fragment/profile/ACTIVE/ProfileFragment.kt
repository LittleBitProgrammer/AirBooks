package it.corelab.studios.airbooks.view.fragment.profile.ACTIVE

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.general.main.isSectionVisible
import it.corelab.studios.airbooks.model.general.main.setupActionBar
import it.corelab.studios.airbooks.viewmodel.ViewModelProfile
import org.jetbrains.anko.support.v4.act
import java.util.*

class ProfileFragment: Fragment(), OnReselectedDelegate, ProfileController {

    private lateinit var viewModel: ViewModelProfile

    private lateinit var name: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(act, R.layout.profile_fragment,null).apply {
                Log.d("SECTION", "onCreateViewProfile")
                if (isSectionVisible()) setupActionBar()
                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")

                viewModel = ViewModelProviders.of(activity!!).get(ViewModelProfile::class.java)

                name = findViewById(R.id.NameSurname)
                viewModel.loadProfile("http://airbooks.altervista.org/API/v2/me/", Locale.getDefault().language, "android",token!!)
                getProfile("http://airbooks.altervista.org/API/v2/me/", Locale.getDefault().language, "android",token!!)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreatedProfile")
        //if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("profile",6, null,null)

    override fun onReselected() = setupActionBar()

    override fun getProfile(url: String?, lang: String?, os: String?, token: String?) {
        getName(url, lang, os, token)
    }

    override fun getName(url: String?, lang: String?, os: String?, token: String?) {
        viewModel.getName()
                .observe(viewLifecycleOwner,android.arch.lifecycle.Observer { nameItem ->
                    if (nameItem != null){
                        name.text = nameItem.toString()
                    }
                })
    }

    override fun getProfileImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFollowers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBooksWritten() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReview() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}