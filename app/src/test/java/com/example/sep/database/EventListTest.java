package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.model.Event;

import org.junit.Test;

public class EventListTest {

    EventList cEventList = new EventList();

    // init
    Event event1 = new Event(
            "id",
            "utzftzuf",
            "client1",
            "party",
            "2022-03-12",
            "2022-03-13",
            "some comment",
            234,
            10000,
            true,
            true,
            true,
            false,
            false,
            0
    );
    Event event2 = new Event(
            "id2",
            "hfg",
            "client2",
            "seminar",
            "2022-04-12",
            "2022-04-13",
            "other comment",
            342,
            50000,
            false,
            false,
            false,
            true,
            true,
            0
    );

    public void initList() {
        cEventList.getTheEvents();
        cEventList.addEvent(event1);
        cEventList.addEvent(event2);
    }

    @Test
    public void addEventTest() {
        initList();

        assertEquals(cEventList.getTheEvents().size(), 2);
    }

    @Test
    public void getTheEventsTest() {
        initList();

        assertEquals(cEventList.getTheEvents().get(1).getId(), "id2");
    }

    @Test
    public void deleteEvent() {
        initList();

        cEventList.deleteEvent(1);

        assertEquals(cEventList.getTheEvents().size(), 1);
        assertEquals(cEventList.getTheEvents().get(0).getId(), "id");
        assertNotEquals(cEventList.getTheEvents().get(0).getId(), "id2");
    }
}