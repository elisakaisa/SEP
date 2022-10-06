package com.example.sep.model;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Test;

public class EventTest {

    @Test
    public void createEvent() {
        Event event = TestVariables.event;
        assertEquals(event.getId(), "id");
        assertEquals(event.getRecordNumber(), "recordNumber");
        assertEquals(event.getEventType(), "eventType");
        assertEquals(event.getFromDate(), "fromDate");
        assertEquals(event.getToDate(), "toDate");
        assertEquals(event.getComments(), "comments");
        assertEquals(event.getAttendees(), 10);
        assertEquals(event.getBudget(), 1000);
        assertFalse(event.isDecorations());
        assertTrue(event.isFood());
        assertEquals(event.getLevel(), 0);

        // test change of data
        event.setComments("changed comment");
        assertEquals(event.getComments(), "changed comment");

        // test level changes from 0 to 1 (from customer service to senior customer service
        event.addLevel();
        assertEquals(event.getLevel(), 1);

        //test add FM review to created object
        event.setFMReview("budget good");
        assertEquals(event.getFMReview(), "budget good");
    }

}