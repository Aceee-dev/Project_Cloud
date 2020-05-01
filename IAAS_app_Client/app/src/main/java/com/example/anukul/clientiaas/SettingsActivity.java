package com.example.anukul.clientiaas;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;


public class SettingsActivity extends AppCompatPreferenceActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_settings);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Display the fragment as the main content
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    public static class SettingsFragment extends PreferenceFragment implements android.content.SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(com.example.anukul.clientiaas.R.xml.pref_notification);
        }

        public void onSharedPreferenceChanged(android.content.SharedPreferences sharedPreferences, String key) {

            if(key.equals("ip_text")){
                Common.IP=sharedPreferences.getString("ip_text","");
            }
        }

        android.content.SharedPreferences.OnSharedPreferenceChangeListener mListener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(android.content.SharedPreferences prefs, String key) {
                // listener implementation

            }
        };

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);

            //prefs.registerOnSharedPreferenceChangeListener(mListener);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

    }
}
