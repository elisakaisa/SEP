package com.example.sep.utils;

/*
Small methods used in different classes for ie. formatting
 */

public class HelperFunctions {

    public static String padWithZeroes(int number) {
        String sNumber = String.valueOf(number);
        if (number < 10) { sNumber = "0" + number; }
        return sNumber;
    }
}
