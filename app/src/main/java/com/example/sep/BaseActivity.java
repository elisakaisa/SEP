package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.sep.database.Employees;
import com.example.sep.database.FinancialRequestList;
import com.example.sep.database.TaskList;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.database.EventList;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    /*------ SERILAIZATION -------*/
    public static EventList eventList; // referenced from everywhere, needs to be static
    public static FinancialRequestList fRequestList;

    public static String EVENT_LIST_FILE = "eventlist.ser";
    public static String FIN_REQUEST_FILE = "finrequestlist.ser";
    public static String TASK_LIST_FILE = "taskList.ser";

    BottomNavigationView bottomNavigationView;
    BottomNavigationView bottomNavigationViewFM;
    public static TaskList taskList;

    String name;
    String role;
    String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        /*----------- NAV -------------*/
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::listener);
        bottomNavigationViewFM = findViewById(R.id.bottom_navigation_fm);
        bottomNavigationViewFM.setOnItemSelectedListener(this::listenerFM);

        /*------------ UI ------------*/
        TextView tv_username = findViewById(R.id.tv_logged_as);
        TextView tv_role = findViewById(R.id.tv_role);
        ImageButton btnLogout = findViewById(R.id.img_btn_login);

        /*-------- INTENT -----------*/
        Intent intent = getIntent();
        name = intent.getStringExtra(ActivityLogin.NAME);
        department = intent.getStringExtra(ActivityLogin.DEPARTMENT);
        role = intent.getStringExtra(ActivityLogin.ROLE);

        RoleTransfer.setRole(role);
        RoleTransfer.setDepartment(department);
        RoleTransfer.setName(name);

        String s = "Welcome " + name;
        tv_username.setText(s);
        tv_role.setText(role);

        // default fragment
        // TODO: figure out how we can do the menu
        if ((role != null) & (department != null)) {
            setDefaultFragment(role, department);
        } else {
            loadFragment(new FragmentHome());
        }

        // highlight the correct icon
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        /*---------- LISTENERS ----------*/
        btnLogout.setOnClickListener(v -> logOut());

    }

    private void setDefaultFragment(String role, String department) {
        // method to select the correct default fragment and visible menus based on the logged in user
        if (department.equals(Employees.ADMINISTRATION)) {
            loadFragment(new FragmentEventList());
        } else if ((department.equals(Employees.FINANCIAL) & role.equals("Financial manager"))){
            bottomNavigationView.setVisibility(View.INVISIBLE);
            bottomNavigationViewFM.setVisibility(View.VISIBLE);
            loadFragment(new FragmentEventList());
            bottomNavigationViewFM.getMenu().getItem(0).setChecked(true);
        } else if (department.equals(Employees.SERVICE) || department.equals(Employees.PRODUCTION)){
            if (role.equals("Production department manager") || role.equals("Services department manager")){
                loadFragment(new FragmentEventList());
            } else {
                loadFragment(new FragmentTaskListSubTeam());
            }
        }

    }


    private void logOut() {
        // remove user from local storage
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.NAME).apply();
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.DEPARTMENT).apply();
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.ROLE).apply();

        startActivity(new Intent(this, ActivityLogin.class));
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listenerFM(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_events:
                setDefaultFragment(role, department);
                return true;
            case R.id.nav_financial_requests:
                // TODO: if financial manager, load financial request rv
                loadFragment(new FragmentFinancialRequestsList());
                return true;
        }
        return false;
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listener(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                //load home fragment
                setDefaultFragment(role, department);

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