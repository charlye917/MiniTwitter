package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_perfil.*
import java.util.jar.Manifest

class PerfilFragment : Fragment() {

    private val viewModel:HomeViewModel by activityViewModels()
    private lateinit var allPermission: PermissionListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){

        changeProfile()

        btnGuardarPerfil.setOnClickListener {
            updateProfile()
        }

        btnModifyPassPerfil.setOnClickListener {
            Toast.makeText(activity, "Click on save", Toast.LENGTH_SHORT).show()
        }

        imgViewAvatarProfile.setOnClickListener {
            checkPermission()
        }
    }

    private fun changeProfile(){
        viewModel.changeProfile().observe(viewLifecycleOwner){
            if(it != null){
                etUserNameProfile.setText(it.username)
                etEmailPerfil.setText(it.email)
                etWebSitePerfil.setText(it.website)
                etDescPerfil.setText(it.descripcion)
                if(it.photoUrl!!.isNotEmpty()){
                    Glide.with(requireActivity())
                        .load(Constants.PHOTO_URL + it.photoUrl)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(imgViewAvatarProfile)
                }
                btnGuardarPerfil.isEnabled = true
            }else{
                Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateProfile(){
        val username = etUserNameProfile.text.toString()
        val email = etEmailPerfil.text.toString()
        val description = etDescPerfil.text.toString()
        val website = etWebSitePerfil.text.toString()
        val password = etPasswordPerfil.text.toString()

        if(username.isEmpty())
            etUserNameProfile.error = "El nombre de usuario es requerido"
        else if(email.isEmpty())
            etEmailPerfil.error = "El email es requerido"
        else if(password.isEmpty())
            etPasswordPerfil.error = "El password es requerido"
        else{
            val requestUserProfile = RequestUserProfile(
                username,
                email,
                description,
                website,
                password
            )
            viewModel.updateProfile(requestUserProfile).observe(viewLifecycleOwner){
                if(it != null){
                    Toast.makeText(activity, "Enviando información al servidor", Toast.LENGTH_SHORT).show()
                    btnGuardarPerfil.isEnabled = false
                }else{
                    Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uploadPhoto(){
        viewModel.uploadPhoto("").observe(viewLifecycleOwner){
            if(it != null){

            }else{
                Toast.makeText(context, "Erro al cargar la foto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermission(){
        val dialogOnDeniedPermissionListener =
            DialogOnDeniedPermissionListener.Builder
                .withContext(context)
                .withTitle("Permisos")
                .withMessage("Los permisos solicitados son necesarios para poder seleccionar una foto de perfil")
                .withButtonText("Aceptar")
                .withIcon(R.mipmap.ic_launcher)
                .build()

        allPermission = CompositePermissionListener(
            activity as PermissionListener?,
            dialogOnDeniedPermissionListener
        )

        Dexter.withActivity(activity)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(allPermission)
            .check()
    }

    companion object{
        fun newInstances() = PerfilFragment()
    }
}