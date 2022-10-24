package com.example.sep.model;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class FinancialRequestTest {
    FinancialRequest request;

    @Before
    public void init() { request = TestVariables.fr1; }

    @Test
    public void createRequest() {
        // testing getters
        assertEquals(request.getRequiredAmount(), TestVariables.fr1.getRequiredAmount());
        assertEquals(request.getRequestingDepartment(), TestVariables.fr1.getRequestingDepartment());
        assertEquals(request.getProjectReference(), TestVariables.fr1.getProjectReference());
        assertEquals(request.getReason(), TestVariables.fr1.getReason());
    }

    @Test
    public void changeRequest() {
        // testing setters
        request.setReason(TestVariables.fr2.getReason());
        request.setProjectReference(TestVariables.fr2.getProjectReference());
        request.setRequestingDepartment(TestVariables.fr2.getRequestingDepartment());
        request.setRequiredAmount(TestVariables.fr2.getRequiredAmount());
        assertEquals(request.getRequiredAmount(), TestVariables.fr2.getRequiredAmount());
        assertEquals(request.getRequestingDepartment(), TestVariables.fr2.getRequestingDepartment());
        assertEquals(request.getProjectReference(), TestVariables.fr2.getProjectReference());
        assertEquals(request.getReason(), TestVariables.fr2.getReason());
    }

}