package com.example.sep.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

    @Test
    public void createEvent() {
        Event event = new Event(
                "id",
                "recordNumber",
                "clientName",
                "eventType",
                "fromDate",
                "toDate",
                "comments",
                10,
                1000,
                false,
                true,
                true,
                true,
                false,
                0);
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
    }

}