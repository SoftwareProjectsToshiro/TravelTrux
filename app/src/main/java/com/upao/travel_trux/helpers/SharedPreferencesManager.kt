package com.upao.travel_trux.helpers

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val PREFS_NAME = "app_prefs"
    private const val USER_KEY = "user_key"
    private const val TOKEN = "token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserData(context: Context, user: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_KEY, user)
        editor.apply()
    }

    fun getUserData(context: Context): String? {
        return getPreferences(context).getString(USER_KEY, null)
    }

    fun removeUserData(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(USER_KEY)
        editor.apply()
    }

    fun setToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(TOKEN, null)
    }

    fun removeToken(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(TOKEN)
        editor.apply()
    }
}