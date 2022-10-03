package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.sep.model.Employee;
import com.example.sep.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ActivityLogin extends AppCompatActivity {

    public static SharedPreferences sharedPref; // needs to be static for the same preferences to be accessed form other classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar in the login page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        /*----------- UI -----------*/
        TextInputLayout tiPassword = findViewById(R.id.ti_password);
        TextInputLayout tiUsername = findViewById(R.id.ti_username);
        TextInputEditText etUsername = findViewById(R.id.et_username);
        TextInputEditText etPassword = findViewById(R.id.et_password);
        Button login = findViewById(R.id.btn_login);

        /*--------- view models ---------*/
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        /* ----- USER FROM STORAGE ------*/
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        Employee userFromStorage = new Employee(null, null, null);
        if (sharedPref.contains("name")) {
            Log.i("login", sharedPref.getString("name", null));
            userFromStorage.setName(sharedPref.getString("name", null));
            userFromStorage.setRole(sharedPref.getString("role", null));
            startActivityAfterLogin(userFromStorage.getName(), userFromStorage.getRole());
        }

        /*--------- listeners ---------*/
        login.setOnClickListener(v -> {
            if (!isFieldEmpty(String.valueOf(etPassword.getText()))) {
                tiPassword.setError("Password required");
            } if (!isFieldEmpty(String.valueOf(etUsername.getText()))) {
                tiUsername.setError("Name required");
            } else {
                tiPassword.setError(null);
                tiUsername.setError(null);
                loginVM.login(String.valueOf(etUsername.getText()), String.valueOf(etPassword.getText()));
            }
        });

        loginVM.setLoginListener((loggedIn, errorMessage, user) -> {
            if (loggedIn) {

                // save the logged in user into local storage so no need to relog in every time the app starts
                SharedPreferences.Editor prefsEditor = sharedPref.edit();
                prefsEditor.putString("name", user.getName());
                prefsEditor.putString("role", user.getRole());
                prefsEditor.apply();

                startActivityAfterLogin(user.getName(), user.getRole());
            } else {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                if (errorMessage.equals("Wrong password")) tiPassword.setError("Wrong password");
                else tiUsername.setError("Wrong name");
            }
        });

        // removes error messages
        etPassword.setOnKeyListener((view1, i, keyEvent) -> {
            if(isFieldEmpty(String.valueOf(etPassword.getText()))) {
                tiPassword.setError(null);
            }
            return false;
        });
        etUsername.setOnKeyListener((view1, i, keyEvent) -> {
            if(isFieldEmpty(String.valueOf(etUsername.getText()))) {
                tiUsername.setError(null);
            }
            return false;
        });
    }

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void startActivityAfterLogin(String name, String role) {
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("role", role);
        startActivity(intent);
    }
}