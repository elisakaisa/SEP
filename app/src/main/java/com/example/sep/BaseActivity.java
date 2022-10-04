package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sep.database.EventList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BaseActivity extends AppCompatActivity {

    public static EventList eventList; // referenced from everywhere, needs to be static

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fabAdd;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        /*------------ UI ------------*/
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::listener);
        TextView tv_username = findViewById(R.id.tv_logged_as);
        TextView tv_role = findViewById(R.id.tv_role);
        ImageButton btnLogout = findViewById(R.id.img_btn_login);
        fabAdd = findViewById(R.id.fab_add);

        /*-------- INTENT -----------*/
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        role = intent.getStringExtra("role");

        String s = "Welcome " + name;
        tv_username.setText(s);
        tv_role.setText(role);

        // default fragment
        // TODO: figure out how we can do the menu
        if (role.equals("Customer Service")) {
            fabAdd.setVisibility(View.VISIBLE);
            loadFragment(new FragmentEventList());
        } else if (role.equals("Senior Customer Service Officer")){
            loadFragment(new FragmentEventList());
        } else {
            loadFragment(new FragmentHome());
        }
        // highlight the correct icon
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        /*---------- LISTENERS ----------*/
        btnLogout.setOnClickListener(v -> {

            // remove user from local storage
            ActivityLogin.sharedPref.edit().remove("name").apply();
            ActivityLogin.sharedPref.edit().remove("role").apply();

            startActivity(new Intent(this, ActivityLogin.class));
            finish();
        });

        fabAdd.setOnClickListener(v -> {
            loadFragment(new FragmentCreateEvent());
            fabAdd.setVisibility(View.INVISIBLE);
        });

    }

    @SuppressLint("NonConstantResourceId")
    private boolean listener(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                if (role.equals("Customer Service")) {
                    fabAdd.setVisibility(View.VISIBLE);
                }
                //load home fragment
                loadFragment(new FragmentHome());
                return true;

            // add additional menus here
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}