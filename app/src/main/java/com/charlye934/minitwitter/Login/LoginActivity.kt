package com.charlye934.minitwitter.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.Login.presenter.view.LoginFragment
import com.charlye934.minitwitter.Login.presenter.view.SignUpFragment
import com.charlye934.minitwitter.R

class LoginActivity : AppCompatActivity(), LoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        changeFragmen(LoginFragment(), LoginFragment.TAG)
    }

    private fun changeFragmen(fragment: Fragment, tag:String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLogin, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun goToLogin() {
        changeFragmen(LoginFragment(), LoginFragment.TAG)
    }

    override fun goToSignUp() {
        changeFragmen(SignUpFragment(), SignUpFragment.TAG)
    }
}