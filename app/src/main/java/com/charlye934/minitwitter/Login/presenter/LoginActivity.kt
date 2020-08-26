package com.charlye934.minitwitter.Login.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.Login.presenter.ui.LoginFragment
import com.charlye934.minitwitter.Login.presenter.ui.SignUpFragment
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.HomeActivity

class LoginActivity : AppCompatActivity(), LoginListener {

    private val sharedPreferences = SharedPreferencesManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        changeFragmen(LoginFragment(), LoginFragment.TAG)
    }

    override fun saveDataSharedPrefernces(token:String, username:String, email:String, photo:String, created:String, active:Boolean){
        sharedPreferences.setSomeStringValue(Constants.PREF_TOKEN, token)
        sharedPreferences.setSomeStringValue(Constants.PREF_USERNAME, username)
        sharedPreferences.setSomeStringValue(Constants.PREF_EMAIL, email)
        sharedPreferences.setSomeStringValue(Constants.PREF_PHOTOURL, photo)
        sharedPreferences.setSomeStringValue(Constants.PREF_CREATED, created)
        sharedPreferences.setSomeBooleanValue(Constants.PREF_ACTIVE, active)
    }


    override fun goToLogin() {
        changeFragmen(LoginFragment(), LoginFragment.TAG)
    }

    override fun goToSignUp() {
        changeFragmen(SignUpFragment(), SignUpFragment.TAG)
    }

    override fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun changeFragmen(fragment: Fragment, tag:String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLogin, fragment)
            .addToBackStack(tag)
            .commit()
    }
}