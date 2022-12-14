package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.sep.model.Employee;
import com.example.sep.viewModel.loginVM.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ActivityLogin extends AppCompatActivity {

    /*----- SHARED PREFERNCES -----*/
    public static SharedPreferences sharedPref; // needs to be static for the same preferences to be accessed form other classes
    public static final String NAME = "name";
    public static final String ROLE = "role";
    public static final String DEPARTMENT = "department";

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
        getUserFromStorage();

        /*--------- listeners ---------*/
        login.setOnClickListener(v -> {
            if (!isFieldEmpty(String.valueOf(etPassword.getText()))) {
                tiPassword.setError("Password required");
            } if (!isFieldEmpty(String.valueOf(etUsername.getText()))) {
                tiUsername.setError("Name required");
            } else {
                tiPassword.setError(null);
                tiUsername.setError(null);
                loginVM.login(String.valueOf(etUsername.getText()).trim(), String.valueOf(etPassword.getText()));
            }
        });

        loginVM.setLoginListener((loggedIn, errorMessage, user) -> {
            if (loggedIn) login(user);
            else {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                if (errorMessage.equals("Wrong password")) tiPassword.setError("Wrong password");
                else tiUsername.setError("Wrong name");
            }
        });

        // removes error messages
        etPassword.setOnKeyListener((view1, i, keyEvent) -> {
            if(isFieldEmpty(String.valueOf(etPassword.getText()))) tiPassword.setError(null);
            return false;
        });
        etUsername.setOnKeyListener((view1, i, keyEvent) -> {
            if(isFieldEmpty(String.valueOf(etUsername.getText()))) tiUsername.setError(null);
            return false;
        });
    }

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void startActivityAfterLogin(String name, String department, String role) {
        // intent to BaseActivity with name, role and department as extras
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra(NAME, name);
        intent.putExtra(DEPARTMENT, department);
        intent.putExtra(ROLE, role);
        startActivity(intent);
    }

    private void getUserFromStorage() {
        // if the user is already logged in (saved in shared preferences), the user will skip the login screen and go straight to the baseActivity
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        Employee userFromStorage = new Employee(null, null, null, null);
        if (sharedPref.contains(NAME) && sharedPref.contains(ROLE) && sharedPref.contains(DEPARTMENT)) {
            userFromStorage.setDepartment(sharedPref.getString(DEPARTMENT, null));
            userFromStorage.setName(sharedPref.getString(NAME, null));
            userFromStorage.setRole(sharedPref.getString(ROLE, null));
            startActivityAfterLogin(userFromStorage.getName(), userFromStorage.getDepartment(), userFromStorage.getRole());
        }
    }

    private void login(Employee user) {
        // save the logged in user into local storage so no need to relog in every time the app starts
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        prefsEditor.putString(NAME, user.getName());
        prefsEditor.putString(DEPARTMENT, user.getDepartment());
        prefsEditor.putString(ROLE, user.getRole());
        prefsEditor.apply();

        startActivityAfterLogin(user.getName(), user.getDepartment(), user.getRole());
    }
}