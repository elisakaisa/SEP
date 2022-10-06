package com.example.sep.view.eventRecyclerView;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;
import com.example.sep.model.Event;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class EventItemAdapterTest {

    EventItemAdapter eventItem;

    @Before
    public void init() {
        ArrayList<EventItem> eventArrayList = new ArrayList<>();
        EventItem eventItem1 = new EventItem(TestVariables.event1, 0);
        EventItem eventItem2 = new EventItem(TestVariables.event2, 1);
        eventArrayList.add(eventItem1);
        eventArrayList.add(eventItem2);
        eventItem = new EventItemAdapter(eventArrayList);
    }

    @Test
    public void getItemCountTest() {
        assertEquals(eventItem.getItemCount(), 2);
    }
}