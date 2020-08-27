package com.charlye934.minitwitter.Login.data.db

import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager

class SaveDataSharedPreference {

    private val sharedPreferences = SharedPreferencesManager()

    operator fun invoke(token:String, username:String, email:String, photo:String, created:String, active:Boolean){
        sharedPreferences.setSomeStringValue(Constants.PREF_TOKEN, token)
        sharedPreferences.setSomeStringValue(Constants.PREF_USERNAME, username)
        sharedPreferences.setSomeStringValue(Constants.PREF_EMAIL, email)
        sharedPreferences.setSomeStringValue(Constants.PREF_PHOTOURL, photo)
        sharedPreferences.setSomeStringValue(Constants.PREF_CREATED, created)
        sharedPreferences.setSomeBooleanValue(Constants.PREF_ACTIVE, active)
    }
}