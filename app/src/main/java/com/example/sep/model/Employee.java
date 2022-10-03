package com.example.sep.model;

public class Employee {
    String name, role, password;

    public Employee(String newName, String newRole, String newPassword) {
        // Constructor
        name = newName;
        role = newRole;
        password = newPassword;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
