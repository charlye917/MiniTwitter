package com.charlye934.minitwitter.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.Login.presenter.ui.LoginFragment
import com.charlye934.minitwitter.Login.presenter.ui.SignUpFragment
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.home.HomeActivity

class LoginActivity : AppCompatActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState == null){
            supportActionBar!!.hide()
            changeFragmen(LoginFragment(), LoginFragment.TAG)
        }
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

    override fun onBackPressed() {
        when(supportFragmentManager.fragments.last()){
            is LoginFragment -> {finish()}
            is SignUpFragment -> { super.onBackPressed() }
        }
    }
}