package com.charlye934.minitwitter.Login.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.presenter.LoginActivity
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.Login.presenter.viewmodel.LoginViewModel
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(){

    private lateinit var loginListener: LoginListener
    private val viewmodel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        events()
    }

    private fun events(){
        btnEnterLogin.setOnClickListener{sendDataLogin()}
        txtGoSignUp.setOnClickListener{loginListener.goToSignUp()}
    }

    private fun sendDataLogin(){
        val email = etMailLogin.text.toString()
        val pass = etPassLogin.text.toString()

        if(email.isEmpty())
            etMailLogin.error = "El email es requerido"
        else if(pass.isEmpty())
            etPassLogin.error = "El password es requerido"
        else
            getDataUser(email, pass)
    }

    private fun getDataUser(email:String, password: String){
        viewmodel.sendDataLogin(RequestLogin(email, password))
            .observe(viewLifecycleOwner){
                if(it.first != null){
                    loginListener.saveDataSharedPrefernces(
                        it.first!!.token,
                        it.first!!.username,
                        it.first!!.email,
                        it.first!!.photoUrl,
                        it.first!!.created,
                        it.first!!.active
                    )
                    loginListener.goToHomeActivity()
                }else{
                    Toast.makeText(context, "Algo fue mal revise sus datos", Toast.LENGTH_SHORT).show()
                }
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