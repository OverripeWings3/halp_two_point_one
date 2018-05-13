package com.iteso.is699367.halp_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.iteso.is699367.halp_3.Constants.Constants;

import java.util.ArrayList;

/**
 * Created by sjacobus on 24/04/18.
 */


public class SettingsFragment extends PreferenceFragment{

    private Preference logOutPref;
    private Preference invColorPref;
    private Preference normColorPref;
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        logOutPref = findPreference(Constants.KEY_LOG_OUT);
        invColorPref = findPreference(Constants.KEY_CHANGE_COLOR_INVR);
        normColorPref = findPreference(Constants.KEY_CHANGE_COLOR_NORM);
        mAuth = FirebaseAuth.getInstance();

        logOutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                signOut();
                return false;
            }
        });

        /*invColorPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                getActivity().getApplication().setTheme(R.style.AppThemeInverse);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.KEY_CHANGE_COLOR, Constants.KEY_CHANGE_COLOR_INVR);
                editor.clear();
                editor.commit();
                Log.i("COLOR INV", "YOU'VE CHANGED COLOR TO INV ********************");
                Log.i("COLOR INV", sharedPreferences.getString(Constants.KEY_CHANGE_COLOR, null));
                return false;
            }
        });

        normColorPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                getActivity().getApplication().setTheme(R.style.AppTheme);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.KEY_CHANGE_COLOR, Constants.KEY_CHANGE_COLOR_NORM);
                editor.clear();
                editor.commit();
                Log.i("COLOR NORM", "YOU'VE CHANGED COLOR TO NORM ********************");
                Log.i("COLOR NORM", sharedPreferences.getString(Constants.KEY_CHANGE_COLOR, null));
                return false;
            }
        });*/

    }



    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }
}
