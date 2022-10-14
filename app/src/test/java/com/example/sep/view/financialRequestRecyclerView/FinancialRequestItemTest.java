package com.example.sep.view.financialRequestRecyclerView;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class FinancialRequestItemTest {

    FinancialRequestItem requestItem1, requestItem2;

    @Before
    public void init() {
        requestItem1 = new FinancialRequestItem(TestVariables.fr1, 0);
        requestItem2 = new FinancialRequestItem(TestVariables.fr2, 1);
    }

    @Test
    public void getIdx() {
        assertEquals(requestItem1.getIdx(), 0);
        assertEquals(requestItem2.getIdx(), 1);
    }

    @Test
    public void getAmount() {
        assertEquals(
                requestItem1.getAmount(),
                String.valueOf(TestVariables.fr1.getRequiredAmount()));
    }

    @Test
    public void getDepartment() {
        assertEquals(requestItem2.getDepartment(), TestVariables.fr2.getRequestingDepartment());
    }

    @Test
    public void getRequest() {
        assertEquals(requestItem1.getRequest(), TestVariables.fr1);
        assertEquals(requestItem2.getRequest(), TestVariables.fr2);
    }
}