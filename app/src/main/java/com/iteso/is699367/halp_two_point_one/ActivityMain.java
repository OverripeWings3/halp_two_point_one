package com.iteso.is699367.halp_two_point_one;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.iteso.is699367.halp_two_point_one.Constants.Constants;
import com.squareup.picasso.*;

public class ActivityMain extends AppCompatActivity  implements
        NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
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
        String pref = loadPreferences();
        if(pref.equals(Constants.KEY_CHANGE_COLOR_INVR)) {
            setTheme(R.style.AppThemeInverse);
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        switch (item.getItemId()) {
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
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String colorChange = sharedPreferences.getString(Constants.KEY_CHANGE_COLOR,
                Constants.KEY_CHANGE_COLOR_NORM);
        Log.i("COLOR SHIT:::::::::", colorChange);
        return colorChange;
    }
}
