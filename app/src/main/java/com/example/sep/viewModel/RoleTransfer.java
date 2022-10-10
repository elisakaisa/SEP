package com.example.sep.viewModel;
/*
class used to get the role, department and name from the login activity to the baseActivity and its children fragments
 */

public class RoleTransfer {
    private static String mRole, mDepartment, mName;

    public static String getRole() {
        return mRole;
    }
    public static String getDepartment() {return mDepartment; }
    public static String getName() {return mName; }

    public static void setRole(String role) {
        mRole = role;
    }
    public static void setDepartment(String department) { mDepartment = department; }
    public static void setName(String name) { mName = name; }
}
