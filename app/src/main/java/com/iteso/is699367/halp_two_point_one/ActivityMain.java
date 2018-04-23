package com.iteso.is699367.halp_two_point_one;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.net.URI;

public class ActivityMain extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    TextView username, userEmail;
    ImageView userPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        username.setText(getIntent().getStringExtra(Intent.EXTRA_USER));
        userEmail.setText(getIntent().getStringExtra(Intent.EXTRA_EMAIL));
        userPic.setImageURI(Uri.parse(getIntent().getStringExtra(Intent.EXTRA_ORIGINATING_URI)));
        userPic.

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

        //userEmail.setText(getIntent().getStringExtra(Intent.EXTRA_EMAIL));
        //userPic.setImageURI(Uri.parse(getIntent().getStringExtra(Intent.EXTRA_ORIGINATING_URI)));

        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_agenda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AgendaFragment()).commit();
                break;
            case R.id.nav_assigments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AssigmentFragment()).commit();
                break;
            case R.id.nav_flashcards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FlashcardsFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
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
}
