package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.example.sep.viewModel.LoginViewModel;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar in the login page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        /*----------- UI -----------*/
        Button login = findViewById(R.id.btn_login);

        /*--------- view models ---------*/
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        /*--------- listeners ---------*/
        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, BaseActivity.class);
            //TODO: add name and function as extra
            //intent.putExtra(Stops.TIME, time);
            startActivity(intent);
        });
    }
}