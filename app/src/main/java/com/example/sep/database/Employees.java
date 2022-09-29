package com.example.sep.database;
/*
Mockup database
 */

import com.example.sep.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class Employees {

    // Administration department
    Employee SCSO = new Employee("Janet", "Senior Customer Service Officer", "password");
    Employee CS1 = new Employee("Sarah", "Customer Service", "password");
    Employee CS2 = new Employee("Sam", "Customer Service", "password");
    Employee CS3 = new Employee("Judy", "Customer Service", "password");
    Employee CS4 = new Employee("Carine", "Customer Service", "password");
    Employee HRM = new Employee("Simon", "Senior HR Manager", "password");
    Employee HRA = new Employee("Maria", "HR Assistant", "password");
    Employee AD = new Employee("Mike", "Administration department manager", "password");
    Employee MO = new Employee("David", "Marketing officer", "password");
    Employee MA = new Employee("Emma", "Marketing assistant", "password");

    // Financial department
    Employee FM = new Employee("Alice", "Financial manager", "password");
    Employee Acc1 = new Employee("Fredrik", "Accountant", "password");
    Employee Acc2 = new Employee("Sophia", "Accountant", "password");

    // Production department
    Employee PM = new Employee("Jack", "Production manager", "password");
    Employee Photo1 = new Employee("Tobias", "Photographer", "password");
    Employee Photo2 = new Employee("Magdalena", "Photographer", "password");
    Employee Audio1 = new Employee("Antony", "Audio specialist", "password");
    Employee Audio2 = new Employee("Adam", "Audio specialist", "password");
    Employee Graphic1 = new Employee("Julia", "Graphic designer", "password");
    Employee Graphic2 = new Employee("Raymond", "Graphic designer", "password");
    Employee Deco1 = new Employee("Magy", "Decorating Architect", "password");
    Employee Deco2 = new Employee("Angelina", "Decorating specialist", "password");
    Employee Network1 = new Employee("Christian", "Network engineer", "password");
    Employee Network2 = new Employee("Nicolas", "Network engineer", "password");
    Employee Network3 = new Employee("Michael", "Technician", "password");
    Employee Network4 = new Employee("Robert", "Technician", "password");

    // Services department
    Employee SM = new Employee("Natalie", "Services department manager", "password");
    Employee Chef = new Employee("Helen", "Top chef", "password");
    Employee SW = new Employee("Kate", "Senior waitress", "password");

    // top management
    Employee VP = new Employee("Charlie", "Vice president", "password");
    Employee Sec1 = new Employee("Jennifer", "Secretary", "password");
    Employee Sec2 = new Employee("Jennifer", "Secretary", "password");

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(SCSO);
        employees.add(CS1);
        employees.add(CS2);
        employees.add(CS3);
        employees.add(CS4);
        employees.add(HRM);
        employees.add(HRA);
        employees.add(AD);
        employees.add(MO);
        employees.add(MA);
        employees.add(FM);
        employees.add(Acc1);
        employees.add(Acc2);
        employees.add(PM);
        employees.add(Photo1);
        employees.add(Photo2);
        employees.add(Audio1);
        employees.add(Audio2);
        employees.add(Graphic1);
        employees.add(Graphic2);
        employees.add(Deco1);
        employees.add(Deco2);
        employees.add(Network1);
        employees.add(Network2);
        employees.add(Network3);
        employees.add(Network4);
        employees.add(SM);
        employees.add(Chef);
        employees.add(SW);
        employees.add(VP);
        employees.add(Sec1);
        employees.add(Sec2);
        return employees;
    }
}
