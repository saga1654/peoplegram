package kr.co.people_gram.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPreferenceUtil
{
    public static void putSharedPreference
            (Context context, String key, String value)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public static String getSharedPreference
            (Context context, String key)
    {
        String returnString = "";

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if(prefs.getString(key, "") != "") {
            returnString = prefs.getString(key, "");
        }

        //String returnString = prefs.getString(key, "");

        //Log.d("people_gram", "login=" + check);

        return returnString;
        //return prefs.getString(key, null);
    }
}