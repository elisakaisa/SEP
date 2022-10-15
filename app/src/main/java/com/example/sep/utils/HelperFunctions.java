package com.example.sep.utils;

/*
Small methods used in different classes for ie. formatting
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sep.R;

public class HelperFunctions {

    public static String padWithZeroes(int number) {
        // method to pad single digit numbers with a 0
        String sNumber = String.valueOf(number);
        if (number < 10) { sNumber = "0" + number; }
        return sNumber;
    }

    public static void loadFragment(FragmentManager fm, Fragment fragment) {
        //method to navigate in between fragments
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // addToBackStack handles the onBackPressed in fragments
        fragmentTransaction.replace(R.id.content_container, fragment, "").addToBackStack("tag");
        fragmentTransaction.commit();
    }
}
