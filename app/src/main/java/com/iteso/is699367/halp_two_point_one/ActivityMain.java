package com.iteso.is699367.halp_two_point_one;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.iteso.is699367.halp_two_point_one.Constants.Constants;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ActivityMain extends AppCompatActivity  implements
        NavigationView.OnNavigationItemSelectedListener,
        PreferenceFragmentCompat.OnPreferenceStartScreenCallback{
    private DrawerLayout drawer;

    DatabaseReference firebaseDatabase;

    TextView username, userEmail;
    ImageView userPic;

    String personName;
    String personGivenName;
    String personFamilyName;
    String personEmail;
    String personId;
    Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(loadPreferences() == "Norm") {
            getApplication().setTheme(R.style.AppTheme);
        }
        else {
            getApplication().setTheme(R.style.AppThemeInverse);
        }


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        username = header.findViewById(R.id.nav_header_username);
        userEmail = header.findViewById(R.id.nav_header_email);
        userPic = header.findViewById(R.id.nav_header_user_pic);

        getUserData();

        username.setText(personName);
        userEmail.setText(personEmail);
        Picasso.get().load(personPhoto).into(userPic);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        onPostCreate(savedInstanceState, null);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_agenda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AgendaFragment()).commit();
                break;
            case R.id.nav_assignments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AssigmentFragment()).commit();
                break;
            case R.id.nav_flashcards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FlashcardsFragment()).commit();
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(ActivityMain.this, ActivitySettings.class);
                startActivity(intent);
                this.setTheme(R.style.AppThemeInverse);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen pref) {
        return false;
    }

    public void getUserData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null) {
            personName = account.getDisplayName();
            personGivenName = account.getGivenName();
            personFamilyName = account.getFamilyName();
            personEmail = account.getEmail();
            personId = account.getId();
            personPhoto = account.getPhotoUrl();

        }
    }

    public String loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        String appTheme = sharedPreferences.getString(Constants.KEY_CHANGE_COLOR, null);
        return appTheme;
    }
}
