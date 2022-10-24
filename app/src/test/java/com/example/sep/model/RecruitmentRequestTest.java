package com.example.sep.model;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class RecruitmentRequestTest {

    RecruitmentRequest request;

    @Before
    public void init() { request = TestVariables.rr1; }

    @Test
    public void createRequest() {
        // testing model
        assertEquals(request, TestVariables.rr1);
    }

    @Test
    public void changeRequest() {
        // Testing setters & getters
        request.setStatus(TestVariables.rr2.getStatus());
        request.setContractType(TestVariables.rr2.getContractType());
        request.setRequestingDepartment(TestVariables.rr2.getRequestingDepartment());
        request.setEventId(TestVariables.rr2.getEventId());
        request.setJobDescription(TestVariables.rr2.getJobDescription());
        request.setJobTitle(TestVariables.rr2.getJobTitle());
        request.setYearsOfExperience(TestVariables.rr2.getYearsOfExperience());
        assertEquals(request.getStatus(), TestVariables.rr2.getStatus());
        assertEquals(request.getEventId(), TestVariables.rr2.getEventId());
        assertEquals(request.getContractType(), TestVariables.rr2.getContractType());
        assertEquals(request.getRequestingDepartment(), TestVariables.rr2.getRequestingDepartment());
        assertEquals(request.getJobDescription(), TestVariables.rr2.getJobDescription());
        assertEquals(request.getJobTitle(), TestVariables.rr2.getJobTitle());
        assertEquals(request.getYearsOfExperience(), TestVariables.rr2.getYearsOfExperience());
    }

}