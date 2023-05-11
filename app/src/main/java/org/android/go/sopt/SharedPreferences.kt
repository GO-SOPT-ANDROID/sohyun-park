package org.android.go.sopt

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences {

    object MySharedPreferences {
        private const val myAccount: String = "account"
        fun setUserId(context: Context, input: String) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString("MY_ID", input)
            editor.commit()
        }

        fun getUserId(context: Context): String {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            return prefs.getString("MY_ID", "").toString()
        }

        fun setUserPw(context: Context, input: String) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString("MY_PW", input)
            editor.commit()
        }

        fun getUserPw(context: Context): String {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            return prefs.getString("MY_PW", "").toString()
        }

        fun setUserName(context: Context, input: String) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString("MY_NAME", input)
            editor.commit()
        }

        fun getUserName(context: Context): String {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            return prefs.getString("MY_NAME", "").toString()
        }

        fun setUserSpeciality(context: Context, input: String) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString("MY_SPECIALITY", input)
            editor.commit()
        }

        fun getUserSpeciality(context: Context): String {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            return prefs.getString("MY_SPECIALITY", "").toString()
        }

        fun delete(context: Context) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.clear()
            editor.commit()
        }

    }

}

