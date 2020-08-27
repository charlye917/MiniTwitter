package com.charlye934.minitwitter.Login.presenter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.LoginActivity
import com.charlye934.minitwitter.Login.presenter.listener.LoginListener
import com.charlye934.minitwitter.Login.presenter.viewmodel.LoginViewModel
import com.charlye934.minitwitter.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    private lateinit var loginListener: LoginListener
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        events()
    }

    private fun events(){
        btnSingUp.setOnClickListener{ checkData()}
        txtGoLogin.setOnClickListener{loginListener.goToLogin()}
    }

    private fun checkData(){
        val userName = etUserNameSignUp.text.toString()
        val email = etEmailSignUp.text.toString()
        val pass = etPassSignUp.text.toString()

        if(userName.isEmpty())
            etUserNameSignUp.error = "El nombre de usuario es requerido"
        else if(email.isEmpty())
            etEmailSignUp.error = "El email es requerido"
        else if(pass.isEmpty() || pass.length <= 4)
            etPassSignUp.error = "La contraseña es requerida y debe tener una longitud mayor a 4 caracteres"
        else
            sendDataSignUp(userName, email, pass)
    }

    private fun sendDataSignUp(userName: String, email: String, pass:String){
        val code = "UDEMYANDROID"
        viewModel.sedDataSignUp(RequestSignUp(userName, email, pass, code))
            .observe(viewLifecycleOwner){
                if(it!= null){
                    Toast.makeText(context, "Datos guaradados correctamente ", Toast.LENGTH_SHORT).show()
                    viewModel.saveDataSharePreferences(
                        it.token,
                        it.username,
                        it.email,
                        it.photoUrl,
                        it.created,
                        it.active
                    )
                    loginListener.goToHomeActivity()
                }else{
                    Toast.makeText(context, "Algo ha ido mal, revise los datos de registro ", Toast.LENGTH_SHORT).show()
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