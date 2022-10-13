package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class RecruitmentRequestListTest {

    RecruitmentRequestList cRequestList;

    @Before
    public void initList() {
        cRequestList = new RecruitmentRequestList();
        cRequestList.getTheRequests();
        cRequestList.addRecruitmentRequest(TestVariables.rr1);
        cRequestList.addRecruitmentRequest(TestVariables.rr2);
    }

    @Test
    public void addRecruitmentRequest() {
        assertEquals(cRequestList.getTheRequests().size(), 2);
    }

    @Test
    public void getTheRequests() {
        assertEquals(
                cRequestList.getTheRequests().get(1).getEventId(),
                TestVariables.rr2.getEventId());
    }

    @Test
    public void deleteRecruitmentRequest() {
        cRequestList.deleteRecruitmentRequest(1);
        assertEquals(cRequestList.getTheRequests().size(), 1);
    }

    @Test
    public void updateRequest() {
        cRequestList.updateRequest(TestVariables.rr2, 0);
        assertEquals(
                cRequestList.getTheRequests().get(0),
                TestVariables.rr2);
        assertEquals(
                cRequestList.getTheRequests().get(1),
                TestVariables.rr2);
    }
}