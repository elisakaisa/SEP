package com.example.sep.viewModel;

import com.example.sep.model.Employee;

public interface LoginInterface {
    void onLoggedIn(boolean loggedIn, String errorMessage, Employee employee);
}
