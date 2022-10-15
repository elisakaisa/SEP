package com.example.sep.view.recruitmentRequestRecyclerView;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;
import com.example.sep.view.financialRequestRecyclerView.FinancialRequestItem;

import org.junit.Before;
import org.junit.Test;

public class RecruitmentRequestItemTest {

    RecruitmentRequestItem requestItem1, requestItem2;

    @Before
    public void init() {
        requestItem1 = new RecruitmentRequestItem(TestVariables.rr1, 0);
        requestItem2 = new RecruitmentRequestItem(TestVariables.rr2, 1);
    }

    @Test
    public void getIdx() {
        assertEquals(requestItem1.getIdx(), 0);
        assertEquals(requestItem2.getIdx(), 1);
    }

    @Test
    public void getRequest() {
        assertEquals(requestItem1.getRequest(), TestVariables.rr1);
        assertEquals(requestItem2.getRequest(), TestVariables.rr2);
    }

    @Test
    public void getJobTitle() {
        assertEquals(requestItem1.getJobTitle(), TestVariables.rr1.getJobTitle());
    }

    @Test
    public void getStatus() {
        assertEquals(requestItem2.getStatus(), TestVariables.rr2.getStatus());
    }
}