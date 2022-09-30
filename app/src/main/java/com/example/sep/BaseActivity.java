package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sep.viewModel.LoginViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

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

        // default fragment
        // TODO: set default fragment depending on user role
        loadFragment(new FragmentHome());
        // highlight the correct icon
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        /*-------- INTENT -----------*/
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String role = intent.getStringExtra("role");

        String s = "Welcome " + name;
        tv_username.setText(s);
        tv_role.setText(role);

        /*--------- view models ---------*/
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        /*---------- LISTENERS ----------*/
        btnLogout.setOnClickListener(v -> {

            // remove user from local storage
            ActivityLogin.sharedPref.edit().remove("name").apply();
            ActivityLogin.sharedPref.edit().remove("role").apply();

            loginVM.deleteUser();

            startActivity(new Intent(this, ActivityLogin.class));
            finish();
        });

    }

    @SuppressLint("NonConstantResourceId")
    private boolean listener(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
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