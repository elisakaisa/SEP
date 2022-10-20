package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;
import com.example.sep.model.Event;

import org.junit.Before;
import org.junit.Test;

public class EventListTest {

    // variables
    EventList cEventList;

    @Before
    public void initList() {
        cEventList = new EventList();
        cEventList.getTheEvents();
    }

    @Test
    public void addEventTest() {
        cEventList.addEvent(TestVariables.event1);
        cEventList.addEvent(TestVariables.event2);
        assertEquals(cEventList.getTheEvents().size(), 2);
    }

    @Test
    public void getTheEventsTest() {
        addEventTest();
        assertEquals(cEventList.getTheEvents().get(1).getId(), TestVariables.event2.getId());
    }

    @Test
    public void setNewEventIdTest() {
        assertEquals(cEventList.setNewEventId(), 1);
        addEventTest();
        assertEquals(cEventList.setNewEventId(), 5);
    }

    @Test
    public void deleteEvent() {
        addEventTest();
        cEventList.deleteEvent(1);

        assertEquals(cEventList.getTheEvents().size(), 1);
        assertEquals(cEventList.getTheEvents().get(0).getId(), TestVariables.event1.getId());
        assertNotEquals(cEventList.getTheEvents().get(0).getId(), 1);
    }

    @Test
    public void findEventById() {
        addEventTest();
        Event searchedEvent = cEventList.findEventById(4);
        assertEquals(searchedEvent.getEventType(), TestVariables.event2.getEventType());
        assertEquals(searchedEvent.getId(), TestVariables.event2.getId());
    }
}