package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class FinancialRequestListTest {

    // variables
    FinancialRequestList cRequestList;

    @Before
    public void initList() {
        cRequestList = new FinancialRequestList();
        cRequestList.getTheRequests();
        cRequestList.addFinancialRequest(TestVariables.fr1);
        cRequestList.addFinancialRequest(TestVariables.fr2);
    }

    @Test
    public void addFinancialRequest() {
        assertEquals(cRequestList.getTheRequests().size(), 2);
    }

    @Test
    public void getTheRequests() {
        assertEquals(
                cRequestList.getTheRequests().get(1).getRequiredAmount(),
                TestVariables.fr2.getRequiredAmount());
    }

    @Test
    public void deleteFinancialRequest() {
        cRequestList.deleteFinancialRequest(1);
        assertEquals(cRequestList.getTheRequests().size(), 1);
    }

    @Test
    public void updateEvent() {
        cRequestList.updateEvent(TestVariables.fr2, 0);
        assertEquals(
                cRequestList.getTheRequests().get(0).getProjectReference(),
                TestVariables.fr2.getProjectReference());
        assertEquals(
                cRequestList.getTheRequests().get(1).getProjectReference(),
                TestVariables.fr2.getProjectReference());
    }
}