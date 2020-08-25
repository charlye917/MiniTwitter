package com.charlye934.minitwitter.Login.presenter.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charlye934.minitwitter.Login.LoginActivity
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), View.OnClickListener {

    private lateinit var loginListener: LoginListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSingUp.setOnClickListener(this)
        txtGoLogin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btnSingUp -> {}
            R.id.txtGoLogin -> { loginListener.goToLogin() }
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