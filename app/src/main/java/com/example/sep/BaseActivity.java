package com.example.sep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sep.database.Employees;
import com.example.sep.database.FinancialRequestList;
import com.example.sep.database.RecruitmentRequestList;
import com.example.sep.database.TaskList;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.database.EventList;

import com.example.sep.viewModel.bottomNavigationVM.BottomNavigationViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    /*------ SERIALIZATION -------*/
    public static EventList eventList; // referenced from everywhere, needs to be static
    public static FinancialRequestList fRequestList;
    public static TaskList taskList;
    public static RecruitmentRequestList recruitmentRequestList;
    public static final String EVENT_LIST_FILE = "eventlist.ser";
    public static final String FIN_REQUEST_FILE = "finrequestlist.ser";
    public static final String TASK_LIST_FILE = "taskList.ser";
    public static final String RES_REQUEST_FILE = "recruitmentlist.ser";

    /* -------- BOTTOM NAV ---------*/
    private BottomNavigationView bottomNavigationViewFM;
    private BottomNavigationView bottomNavigationViewFiM;
    private BottomNavigationViewModel bottomNavVM;

    private String name, role, department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        /*----------- NAV -------------*/
        bottomNavigationViewFM = findViewById(R.id.bottom_navigation_fm);
        bottomNavigationViewFM.setOnItemSelectedListener(this::listenerFM);
        bottomNavigationViewFiM = findViewById(R.id.bottom_navigation_fim);
        bottomNavigationViewFiM.setOnItemSelectedListener(this::listenerFiM);

        /*------------ NAV VM ------------*/
        bottomNavVM =  new ViewModelProvider(this).get(BottomNavigationViewModel.class);

        /*------------ UI ------------*/
        TextView tv_username = findViewById(R.id.tv_logged_as);
        TextView tv_role = findViewById(R.id.tv_role);
        ImageButton btnLogout = findViewById(R.id.img_btn_login);

        /*-------- INTENT -----------*/
        Intent intent = getIntent();
        name = intent.getStringExtra(ActivityLogin.NAME);
        department = intent.getStringExtra(ActivityLogin.DEPARTMENT);
        role = intent.getStringExtra(ActivityLogin.ROLE);

        /*----------- ROLE & DEP ------------*/
        RoleTransfer.setRole(role);
        RoleTransfer.setDepartment(department);
        RoleTransfer.setName(name);
        String s = "Welcome " + name;
        tv_username.setText(s);
        tv_role.setText(role);

        /*-------- DEFAULT FRAGMENT ---------*/
        if ((role != null) & (department != null)) setDefaultFragment(role, department);
        else {
            Toast.makeText(this, "An error occurred, please try later", Toast.LENGTH_SHORT).show();
            logOut();
        }

        /*---------- LISTENERS ----------*/
        btnLogout.setOnClickListener(v -> logOut());

    }

    private void setDefaultFragment(String role, String department) {
        // method to select the correct default fragment and visible menus based on the logged in user
        switch (department) {
            case Employees.ADMINISTRATION:
                if (role.equals("HR Assistant") || role.equals("Senior HR Manager")) { // HR
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentRecruitmentRequestsList());
                } else { // customer Service and marketing
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
                }
                break;

            case Employees.FINANCIAL:
                if (role.equals("Financial manager")) {
                    bottomNavigationViewFM.setVisibility(View.VISIBLE);
                    bottomNavigationViewFiM.setVisibility(View.INVISIBLE);
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
                    bottomNavigationViewFM.getMenu().getItem(0).setChecked(true);
                } else { // Accountants
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                }
                break;

            case Employees.SERVICE_DEP:
            case Employees.PRODUCTION:
                bottomNavigationViewFM.setVisibility(View.INVISIBLE);

                if (role.equals("Production department manager") || role.equals("Services department manager")) { // managers
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentEventList());
                    bottomNavigationViewFiM.setVisibility(View.VISIBLE);
                    bottomNavigationViewFiM.getMenu().getItem(0).setChecked(true);
                } else { //Sub-teams
                    HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentTaskListSubTeam());
                    bottomNavigationViewFiM.setVisibility(View.INVISIBLE);
                }
                break;

            case Employees.TOP_MANAGEMENT:
                // TODO: decide which fragment is viewed
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentRecruitmentRequestsList());
                break;

            default:  // any other roles
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentHome());
                break;
        }

    }


    private void logOut() {
        // method to remove user from local storage and intent to LoginActivity
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.NAME).apply();
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.DEPARTMENT).apply();
        ActivityLogin.sharedPref.edit().remove(ActivityLogin.ROLE).apply();
        startActivity(new Intent(this, ActivityLogin.class));
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listenerFM(MenuItem menuItem) {
        // listener for the bottom nav menu of the financial manager
        switch (menuItem.getItemId()){
            case R.id.nav_events:
                setDefaultFragment(role, department);
                return true;
            case R.id.nav_financial_requests:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                return true;
        }
        return false;
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listenerFiM(MenuItem menuItem) {
        // listener for the bottom nav menu of the field managers
        switch (menuItem.getItemId()){
            case R.id.nav_events_fim:
                setDefaultFragment(role, department);
                bottomNavVM.setSelectedNavigationPage(String.valueOf(menuItem));
                return true;
            case R.id.nav_tasks_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentTaskListManager());
                bottomNavVM.setSelectedNavigationPage(String.valueOf(menuItem));
                return true;
            case R.id.nav_fin_requests_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentFinancialRequestsList());
                bottomNavVM.setSelectedNavigationPage(String.valueOf(menuItem));
                return true;
            case R.id.nav_res_requests_fim:
                HelperFunctions.loadFragment(getSupportFragmentManager(), new FragmentRecruitmentRequestsList());
                bottomNavVM.setSelectedNavigationPage("resource_requests");
                return true;
        }
        return false;
    }

}