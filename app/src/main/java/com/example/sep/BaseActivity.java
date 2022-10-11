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
import com.example.sep.database.RecruitmentRequestList;
import com.example.sep.database.TaskList;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.database.EventList;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    /*------ SERILAIZATION -------*/
    public static EventList eventList; // referenced from everywhere, needs to be static
    public static FinancialRequestList fRequestList;
    public static TaskList taskList;
    public static RecruitmentRequestList recruitmentRequestList;
    public static final String EVENT_LIST_FILE = "eventlist.ser";
    public static final String FIN_REQUEST_FILE = "finrequestlist.ser";
    public static final String TASK_LIST_FILE = "taskList.ser";
    public static final String RES_REQUEST_FILE = "recruitmentlist.ser";

    /* -------- BOTTom NAV ---------*/
    BottomNavigationView bottomNavigationView;
    BottomNavigationView bottomNavigationViewFM;
    BottomNavigationView bottomNavigationViewFiM;

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
        bottomNavigationViewFiM = findViewById(R.id.bottom_navigation_fim);
        bottomNavigationViewFiM.setOnItemSelectedListener(this::listenerFiM);

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
            HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentHome());
        }

        // highlight the correct icon
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        /*---------- LISTENERS ----------*/
        btnLogout.setOnClickListener(v -> logOut());

    }

    private void setDefaultFragment(String role, String department) {
        // TODO add all user cases
        // TODO make bottom nav function
        // method to select the correct default fragment and visible menus based on the logged in user
        if (department.equals(Employees.ADMINISTRATION)) {
            HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
        } else if ((department.equals(Employees.FINANCIAL) & role.equals("Financial manager"))){
            bottomNavigationView.setVisibility(View.INVISIBLE);
            bottomNavigationViewFM.setVisibility(View.VISIBLE);
            bottomNavigationViewFiM.setVisibility(View.INVISIBLE);
            HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
            bottomNavigationViewFM.getMenu().getItem(0).setChecked(true);
        } else if (department.equals(Employees.SERVICE) || department.equals(Employees.PRODUCTION)){
            bottomNavigationView.setVisibility(View.INVISIBLE);
            bottomNavigationViewFM.setVisibility(View.INVISIBLE);
            bottomNavigationViewFiM.setVisibility(View.VISIBLE);
            bottomNavigationViewFiM.getMenu().getItem(0).setChecked(true); // todo: check which one should be default
            if (role.equals("Production department manager") || role.equals("Services department manager")){
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
            } else {
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentTaskListSubTeam());
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
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                return true;
        }
        return false;
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listenerFiM(MenuItem menuItem) {
        // TODO implement correct fragments
        switch (menuItem.getItemId()){
            case R.id.nav_events_fim:
                setDefaultFragment(role, department);
                return true;
            case R.id.nav_fin_requests_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                return true;
            case R.id.nav_tasks_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                return true;
            case R.id.nav_res_requests_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentRecruitmentRequestsList());
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
}