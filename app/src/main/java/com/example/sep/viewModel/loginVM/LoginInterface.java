package com.example.sep.viewModel.loginVM;

/*
listener used to inform the LoginActivity of the result of the (normally) asynchronous fetching of login data.
In this case since everything is locally stored, it would not be necessary
 */

import com.example.sep.model.Employee;

public interface LoginInterface {
    void onLoggedIn(boolean loggedIn, String errorMessage, Employee employee);
}
