package com.example.sep.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.database.Employees;
import com.example.sep.model.Employee;

public class LoginViewModel extends ViewModel {


    private LoginInterface listener;
    private MutableLiveData<Employee> employee = new MutableLiveData<>();

    public MutableLiveData<Employee> getEmployee() { return employee; }

    public void setLoginListener(LoginInterface listener) {
        // Assign the listener implementing events interface that will receive the events
        this.listener = listener;
    }

    public void login(String name, String password) {
        Employees cEmployees = new Employees();
        cEmployees.initEmployees();
        Employee loggedInEmployee = cEmployees.getEmployeeFromDb(name);

        if (loggedInEmployee.getName().equals(name)) {
            if (loggedInEmployee.getPassword().equals(password)) {
                listener.onLoggedIn(true, null, loggedInEmployee);
            } else listener.onLoggedIn(false, "Wrong password", null);
        } else {
            listener.onLoggedIn(false, "Employee " + name + " does not exist", null);
        }

    }

    public void deleteUser() {
        employee.postValue(null);
    }


}
