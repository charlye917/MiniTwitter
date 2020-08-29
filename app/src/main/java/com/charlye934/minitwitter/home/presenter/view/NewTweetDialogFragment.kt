package com.charlye934.minitwitter.home.presenter.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.new_tweet_dialog.*


class NewTweetDialogFragment : DialogFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.new_tweet_dialog, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhoto()
        eventos()
    }

    private fun eventos(){
        btnTwitterTweetDialog.setOnClickListener { sendTweet() }
        imgViewCloseTwittDialog.setOnClickListener { closeDialog() }
    }

    private fun setPhoto(){
        val photoUrl = SharedPreferencesManager().getSomeStringValue(Constants.PREF_PHOTOURL)
        if(photoUrl!!.isNotEmpty()){
            Glide.with(this)
                .load(Constants.PHOTO_URL + photoUrl)
                .into(imgAvatarTweetDialog)
        }
    }

    private fun sendTweet(){
        val mensaje = etTextMessageTweetDialog.text.toString()

        if(mensaje.isEmpty()){
            Toast.makeText(context,"Debe escribir un texto en el mensaje",Toast.LENGTH_SHORT).show()
        }else{
            viewModel.postTweet(RequestCreateTweet(mensaje)).observe(viewLifecycleOwner){
                if(it != null) {
                    Toast.makeText(context,"El mensaje a sido posteado correctamente",Toast.LENGTH_SHORT).show()
                    dialog!!.dismiss()
                }else {
                    Toast.makeText(context,"Problemas al enviar el mensaje intentelo mas tarde",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun closeDialog(){
        val mensaje = etTextMessageTweetDialog.text.toString()

        if(mensaje.isNotEmpty())
            showDialogConfirm()
        else
            dialog!!.dismiss()
    }

    private fun showDialogConfirm(){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("¿Desea realmente elminar el tweet? El mensaje se borrara")
            .setTitle("Cancelar Tweet")
            .setPositiveButton("Eliminar", DialogInterface.OnClickListener{ dialog, _ ->
                dialog!!.dismiss()
                getDialog()!!.dismiss()
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, _ ->
                dialog.dismiss()
            }).create()

        builder.show()
    }


}