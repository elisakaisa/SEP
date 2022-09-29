package com.example.sep.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.database.Employees;
import com.example.sep.model.Employee;

import java.util.List;
import java.util.stream.IntStream;

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

        List<Employee> employeesList = cEmployees.getEmployees();
        boolean nameMatch = containsName(employeesList, name);
        Employee loggedInEmployee = getEmployeeFromDb(employeesList, name);

        if (nameMatch && (loggedInEmployee != null)) {
            boolean checkPassword = checkPassword(loggedInEmployee, password);
            if (checkPassword) {
                listener.onLoggedIn(true, null, loggedInEmployee);
            } else listener.onLoggedIn(false, "Wrong password", null);
        } else {
            listener.onLoggedIn(false, "Employee " + name + " does not exist", null);
        }

    }

    public void deleteUser() {
        employee.postValue(null);
    }

    // Todo: refactor this spaghetti code
    private boolean containsName(final List<Employee> list, final String searchedName){
        return list.stream().anyMatch(o -> searchedName.equals(o.getName()));
    }

    private boolean checkPassword(Employee loggedInEmployee, String password) {
        return loggedInEmployee.getPassword().equals(password);
    }

    private Employee getEmployeeFromDb(final List<Employee> list, final String searchedName) {
        int index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getName().equals(searchedName))
                .findFirst()
                .orElse(-1);
        if (index == -1) return null;
        else return list.get(index);
    }
}
