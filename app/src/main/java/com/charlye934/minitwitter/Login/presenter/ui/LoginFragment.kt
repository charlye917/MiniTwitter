package com.charlye934.minitwitter.Login.presenter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charlye934.minitwitter.Login.LoginActivity
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var loginListener: LoginListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        events()
    }

    private fun events(){
        btnEnterLogin.setOnClickListener(this)
        txtGoSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btnEnterLogin -> {}
            R.id.txtGoSignUp -> { loginListener.goToSignUp() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loginListener = activity as LoginActivity
    }

    companion object{
        val TAG = this::class.java.simpleName
    }
}