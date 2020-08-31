package com.charlye934.minitwitter.common

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SharedPreferencesManager {

    @Suppress("DEPRECATION")
    companion object{
        val APP_SETTINGS_FILE = "APP_SETTINGS"

        @RequiresApi(Build.VERSION_CODES.M)
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val context = MyApp.getContext()

        private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            APP_SETTINGS_FILE,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setSomeStringValue(dataLabel: String, dataValue: String){
        sharedPreferences
            .edit()
            .putString(dataLabel, dataValue)
            .apply()
    }

    fun setSomeBooleanValue(dataLabel: String, dataValue: Boolean){
        sharedPreferences
            .edit()
            .putBoolean(dataLabel,dataValue)
            .apply()
    }

    fun getSomeStringValue(dataLabel: String): String?{
        return sharedPreferences.getString(dataLabel,null)
    }

    fun getSomeBooleanVlaue(dataLabel: String): Boolean?{
        return sharedPreferences.getBoolean(dataLabel, false)
    }
}