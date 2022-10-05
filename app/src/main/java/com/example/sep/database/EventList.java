package com.example.sep.database;
/*
mockup of an event database
 */

import com.example.sep.model.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class EventList implements Serializable {
    private ArrayList<Event> theEvents;

    public EventList() { }

    public ArrayList<Event> getTheEvents() {
        if (theEvents == null) theEvents = new ArrayList<>();
        return theEvents;
    }

    public void addEvent(Event event) { theEvents.add(event); }
    public void deleteEvent(Integer idx) { theEvents.remove(idx.intValue());}
}
