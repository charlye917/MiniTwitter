package com.charlye934.minitwitter.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import com.bumptech.glide.Glide
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.presenter.view.FavoriteFragment
import com.charlye934.minitwitter.home.presenter.view.NewTweetDialogFragment
import com.charlye934.minitwitter.home.presenter.listener.StateFragment
import com.charlye934.minitwitter.home.presenter.view.TweetListFragment
import kotlinx.android.synthetic.main.activity_home.*

private val TAG_ONE = Constants.TWEET_LIST_ALL
private val TAG_TWO = Constants.TWEET_LIST_FAVS
private const val TAG_THREE = "THREE"
private const val MAX_HISTORIC = 4

class HomeActivity : AppCompatActivity() {

    private val listState = mutableListOf<StateFragment>()
    private var currentTag = TAG_ONE
    private var oldTag = TAG_ONE
    private var currentMenuItem = R.id.navigationHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(savedInstanceState == null){
            supportActionBar!!.hide()
            init()
            goToHomeTweets()
        }
    }

    private fun init(){

        setPicturePerfil()

        btnFloatHome.setOnClickListener {
            val dialog = NewTweetDialogFragment()
            dialog.show(supportFragmentManager, "NewTweetDialgoFragment")
        }

        navigationHome.setOnNavigationItemSelectedListener{ menuItem ->
            val fragmet = supportFragmentManager.findFragmentById(R.id.frameHome)

            when(menuItem.itemId){
                R.id.navigation_home ->{
                    btnFloatHome.show()
                    changeFragment(TAG_ONE, TweetListFragment.newInstance())
                }
                R.id.navigation_favs ->{
                    btnFloatHome.hide()
                    changeFragment(TAG_TWO, FavoriteFragment.newInstance())
                }
                R.id.navigation_notification ->{
                    btnFloatHome.hide()
                    Toast.makeText(applicationContext, "notification",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun changeFragment2(tag:String, fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frameHome, fragment)
            .commit()

    }

    private fun setPicturePerfil(){
        val photoUrl = SharedPreferencesManager().getSomeStringValue(Constants.PREF_PHOTOURL)
        if(photoUrl!!.isNotEmpty()){
            Glide.with(this)
                .load(Constants.PHOTO_URL + photoUrl)
                .into(imgPerfilHome)
        }
    }

    private fun goToHomeTweets(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.frameHome,
            TweetListFragment.newInstance(),
            TAG_ONE
        )
        transaction.commit()
    }

    private fun changeFragment(tagToChange: String, fragment: Fragment){

        if(fragment == FavoriteFragment()){

        }else{
            if(currentTag != tagToChange){
                val ft = supportFragmentManager.beginTransaction()
                val currentFragment = supportFragmentManager.findFragmentByTag(currentTag)
                val fragmentToReplaceByTag = supportFragmentManager.findFragmentByTag(tagToChange)

                oldTag = currentTag
                currentTag = tagToChange

                if(fragmentToReplaceByTag != null)
                    currentFragment?.let{ ft.hide(it).show(fragmentToReplaceByTag)}
                else
                    currentFragment?.let{ ft.hide(it).add(R.id.frameHome, fragment, tagToChange)}

                ft.commit()
                addBackStack()
            }
        }
    }

    override fun onBackPressed() {
        if(listState.size >= 1)
            recoverFragment()
        else
            super.onBackPressed()
    }

    private fun recoverFragment(){
        val lastSate = listState.last()
        val ft = supportFragmentManager.beginTransaction()

        val currentFragmentByTag = supportFragmentManager.findFragmentByTag(lastSate.currentFragmentTag)
        val oldFragmentByTag = supportFragmentManager.findFragmentByTag(lastSate.oldFragment)

        if((currentFragmentByTag != null && currentFragmentByTag.isVisible) &&
            (oldFragmentByTag != null && oldFragmentByTag.isHidden)){

            ft.hide(currentFragmentByTag).show(oldFragmentByTag)
        }

        ft.commit()

        val menu = navigationHome.menu

        when(lastSate.oldFragment){
            TAG_ONE -> setMenuItem(menu.getItem(0))
            TAG_TWO -> setMenuItem(menu.getItem(1))
        }

        //Remove from Stack
        listState.removeAt(listState.size -1)

        if(listState.isEmpty()){
            currentTag = TAG_ONE
            oldTag = TAG_ONE
        }else{
            currentTag = listState.last().currentFragmentTag
            oldTag = listState.last().oldFragment
        }
    }

    private fun setMenuItem(menuItem: MenuItem){
        menuItem.isChecked = true
        currentMenuItem = menuItem.itemId
    }

    //Like youtube
    private fun addBackStack(){
        when(listState.size){
            MAX_HISTORIC ->{
                listState[1].oldFragment = TAG_ONE
                val firstState = listState[1]

                for(i in listState.indices){
                    if(listState.indices.contains(i + 1)){
                        listState[i] = listState[i + 1]
                    }
                }

                listState[0] = firstState
                listState[listState.lastIndex] = StateFragment(currentTag, oldTag)
            }
            else ->{
                listState.add(StateFragment(currentTag, oldTag))
            }
        }
    }
}