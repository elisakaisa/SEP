package com.example.sep.viewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class LoginViewModelTest {

    private LoginViewModel loginVM = new LoginViewModel();


    @Test
    public void successfullLogin() {
        // do not ask me how this works, it's between java and higher powers
        LoginInterface listener = (loggedIn, errorMessage, employee) -> {
            loginVM.login("Janet", "password");
            assertEquals(loginVM.getEmployee().getValue().getRole(), "Senior Customer Service Officer");
            assertTrue(loggedIn);
            assertEquals(employee.getRole(), "Senior Customer Service Officer");
            assertEquals(employee.getName(), "Janet");
            assertNull(errorMessage);
        };
    }

    @Test
    public void wrongPassword() {
        LoginInterface listener = (loggedIn, errorMessage, employee) -> {
            loginVM.login("Janet", "wrong");
            assertEquals(errorMessage, "Wrong password");
            assertNull(loginVM.getEmployee());
            assertFalse(loggedIn);
        };
    }

    @Test
    public void wrongName() {
        LoginInterface listener = (loggedIn, errorMessage, employee) -> {
            loginVM.login("Jane", "password");
            assertEquals(errorMessage, "Employee Jane does not exist");
            assertNull(loginVM.getEmployee());
            assertFalse(loggedIn);
        };
    }

}