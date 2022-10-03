package com.example.sep.utils;

import static com.example.sep.utils.HelperFunctions.padWithZeroes;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HelperFunctionsTest {

    @Test
    public void padWithZeroesTest() {
        List<String> wantedOutput = Arrays.asList("01", "02", "03", "04", "05",
                "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15");
        for (int i=0; i< wantedOutput.size(); i++) {
            assertEquals(padWithZeroes(i+1), wantedOutput.get(i));
        }
    }
}