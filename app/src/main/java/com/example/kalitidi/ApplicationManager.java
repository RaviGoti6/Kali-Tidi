package com.example.kalitidi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class ApplicationManager extends Application {

    Context _context;

    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;

    public ApplicationManager(Context applictionContext) {
        this._context = applictionContext;
        preferences = _context.getSharedPreferences("i3", MODE_PRIVATE);
        prefEditor = preferences.edit();
    }


    public static String SetSession(String user) {
        prefEditor.putString("user", user).commit();
        return user;
    }

    public static String GetSession() {
        return preferences.getString("user", "");
    }
}
