package com.example.ptc.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    public AppPreferences(Context context) {
        this.pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.edit = pref.edit();
    }

    public void setImage(String imageUri) {
        edit.putString("profilePicture", imageUri);
        edit.apply();
    }

    public String getImage() {
        return pref.getString("profilePicture", "");
    }
}
