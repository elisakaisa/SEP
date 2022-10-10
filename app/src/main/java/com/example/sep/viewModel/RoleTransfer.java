package com.example.sep.viewModel;

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
