package com.example.sep.viewModel;

public class RoleTransfer {
    private static String mRole, mDepartment;

    public static String getRole() {
        return mRole;
    }
    public static String getDepartment() {return mDepartment; }

    public static void setRole(String role) {
        mRole = role;
    }
    public static void setDepartment(String department) { mDepartment = department; }
}
