package com.example.sep.database;
/*
Mockup database
 */

import com.example.sep.model.Employee;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Employees {

    ArrayList<Employee> dbEmployees = new ArrayList<>();

    // departments
    public static final String ADMINISTRATION = "Administration";
    public static final String FINANCIAL = "Financial";
    public static final String PRODUCTION = "Production";
    public static final String SERVICE_DEP = "Service";
    public static final String TOP_MANAGEMENT = "Top management";

    // Sub-teams
    public static final String DECOR = "Decorating Architect";
    public static final String PHOTO = "Photographer";
    public static final String MUSIC = "Audio specialist";
    public static final String DESIGN = "Graphic designer";
    public static final String DATA = "Network Engineer";
    public static final String FOOD = "Top chef";
    public static final String SERVICE = "Senior waitress";


    // Administration department
    Employee SCSO = new Employee("Janet", ADMINISTRATION, "Senior Customer Service Officer", "password");
    Employee CS1 = new Employee("Sarah", ADMINISTRATION,"Customer Service", "password");
    Employee CS2 = new Employee("Sam", ADMINISTRATION,"Customer Service", "password");
    Employee CS3 = new Employee("Judy", ADMINISTRATION,"Customer Service", "password");
    Employee CS4 = new Employee("Carine", ADMINISTRATION,"Customer Service", "password");
    Employee HRM = new Employee("Simon", ADMINISTRATION,"Senior HR Manager", "password");
    Employee HRA = new Employee("Maria", ADMINISTRATION,"HR Assistant", "password");
    Employee AD = new Employee("Mike", ADMINISTRATION,"Administration department manager", "password");
    Employee MO = new Employee("David", ADMINISTRATION,"Marketing officer", "password");
    Employee MA = new Employee("Emma", ADMINISTRATION,"Marketing assistant", "password");

    // Financial department
    Employee FM = new Employee("Alice", FINANCIAL,"Financial manager", "password");
    Employee Acc1 = new Employee("Fredrik", FINANCIAL,"Accountant", "password");
    Employee Acc2 = new Employee("Sophia", FINANCIAL,"Accountant", "password");

    // Production department
    Employee PM = new Employee("Jack", PRODUCTION, "Production department manager", "password");
    Employee Photo1 = new Employee("Tobias", PRODUCTION,PHOTO, "password");
    Employee Photo2 = new Employee("Magdalena", PRODUCTION,PHOTO, "password");
    Employee Audio1 = new Employee("Antony", PRODUCTION,MUSIC, "password");
    Employee Audio2 = new Employee("Adam", PRODUCTION,MUSIC, "password");
    Employee Graphic1 = new Employee("Julia", PRODUCTION,DESIGN, "password");
    Employee Graphic2 = new Employee("Raymond", PRODUCTION,DESIGN, "password");
    Employee Deco1 = new Employee("Magy", PRODUCTION,DECOR, "password");
    Employee Deco2 = new Employee("Angelina", PRODUCTION,"Decorating specialist", "password");
    Employee Network1 = new Employee("Christian", PRODUCTION,DATA, "password");
    Employee Network2 = new Employee("Nicolas", PRODUCTION,DATA, "password");
    Employee Network3 = new Employee("Michael", PRODUCTION,"Technician", "password");
    Employee Network4 = new Employee("Robert", PRODUCTION,"Technician", "password");

    // Services department
    Employee SM = new Employee("Natalie", SERVICE_DEP,"Services department manager", "password");
    Employee Chef = new Employee("Helen", SERVICE_DEP,FOOD, "password");
    Employee SW = new Employee("Kate", SERVICE_DEP,SERVICE, "password");

    // top management
    Employee VP = new Employee("Charlie", TOP_MANAGEMENT, "Vice president", "password");
    Employee Sec1 = new Employee("Jennifer", TOP_MANAGEMENT,"Secretary", "password");
    Employee Sec2 = new Employee("Cameron", TOP_MANAGEMENT,"Secretary", "password");

    public void initEmployees() {
        dbEmployees.add(SCSO);
        dbEmployees.add(CS1);
        dbEmployees.add(CS2);
        dbEmployees.add(CS3);
        dbEmployees.add(CS4);
        dbEmployees.add(HRM);
        dbEmployees.add(HRA);
        dbEmployees.add(AD);
        dbEmployees.add(MO);
        dbEmployees.add(MA);
        dbEmployees.add(FM);
        dbEmployees.add(Acc1);
        dbEmployees.add(Acc2);
        dbEmployees.add(PM);
        dbEmployees.add(Photo1);
        dbEmployees.add(Photo2);
        dbEmployees.add(Audio1);
        dbEmployees.add(Audio2);
        dbEmployees.add(Graphic1);
        dbEmployees.add(Graphic2);
        dbEmployees.add(Deco1);
        dbEmployees.add(Deco2);
        dbEmployees.add(Network1);
        dbEmployees.add(Network2);
        dbEmployees.add(Network3);
        dbEmployees.add(Network4);
        dbEmployees.add(SM);
        dbEmployees.add(Chef);
        dbEmployees.add(SW);
        dbEmployees.add(VP);
        dbEmployees.add(Sec1);
        dbEmployees.add(Sec2);
    }

    public ArrayList<Employee> getEmployees() {
        // function used to test the database
        return dbEmployees;
    }

    public Employee getEmployeeFromDb(final String searchedName) {
        // function to return the Employee from the database based on the searched name
        int index = IntStream.range(0, dbEmployees.size())
                .filter(i -> dbEmployees.get(i).getName().equals(searchedName))
                .findFirst()
                .orElse(-1);
        if (index == -1) return null;
        else return dbEmployees.get(index);
    }

    public ArrayList<Employee> getEmployeesFromDbBySubTeam(final String searchedDepartment, final String searchedRole) {
        ArrayList<Employee> employees = new ArrayList<>();

        for (int i=0 ; i< dbEmployees.size() ; i++) {

            if (dbEmployees.get(i).getDepartment().equals(searchedDepartment) & dbEmployees.get(i).getRole().equals(searchedRole)){
                    employees.add(dbEmployees.get(i));
            }
        }
        return employees;
    }

    public String assignRoleToSubTeam(String role){
        switch (role) {
            case "Decor":
                return "Decorating Architect";
            case "Photo":
                return "Photographer";
            case "Music":
                return "Audio specialist";
            case "Design":
                return "Graphic designer";
            case "Data":
                return "Network Engineer";
            case "Food":
                return "Top chef";
            case "Service":
                return "Senior waitress";
            default:
                return null;
        }

    }

}

