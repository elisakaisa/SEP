package com.example.sep.view.eventRecyclerView;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;
import com.example.sep.model.Event;

import org.junit.Before;
import org.junit.Test;

public class EventItemTest {

    EventItem eventItem1, eventItem2;

    @Before
    public void init() {
        eventItem1 = new EventItem(TestVariables.event1, 0);
        eventItem2 = new EventItem(TestVariables.event2, 1);
    }

    @Test
    public void getClientName() {
        assertEquals(eventItem1.getClientName(), "client1");
    }

    @Test
    public void getLevel() {
        assertEquals(eventItem1.getLevel(), 0);
    }

    @Test
    public void getEvent() {
        assertEquals(eventItem1.getEvent(), TestVariables.event1);
        assertEquals(eventItem2.getEvent(), TestVariables.event2);
    }

    @Test
    public void getIdx() {
        assertEquals(eventItem1.getIdx(), 0);
        assertEquals(eventItem2.getIdx(), 1);
    }
}