package com.example.sep.utils;

public class HelperFunctions {

    public static String padWithZeroes(int number) {
        String sNumber = String.valueOf(number);
        if (number < 10) { sNumber = "0" + number; }
        return sNumber;
    }
}
