package com.example.sep.database;
/*
mockup of an event database
 */

import com.example.sep.model.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EventList implements Serializable {
    private ArrayList<Event> theEvents;

    public EventList() { }

    public ArrayList<Event> getTheEvents() {
        if (theEvents == null) theEvents = new ArrayList<>();
        return theEvents;
    }

    public void addEvent(Event event) {
        if (theEvents == null) theEvents = new ArrayList<>();
        theEvents.add(event);
    }
    public void deleteEvent(Integer idx) { theEvents.remove(idx.intValue());}

    public void updateEvent(Event event, Integer idx) {
        deleteEvent(idx);
        addEvent(event);
    }

    public int setNewEventId() {
        // method to look for the biggest id in the event list and returning a new unique id by adding one to the biggest
        int maxId = 0;
        if (theEvents.size() > 0) {
            maxId = theEvents.stream().max(Comparator.comparing(Event::getId)).get().getId();
        }
        return maxId + 1;
    }

    public Event findEventById(int id) {
        //method to find the event by its id
        return theEvents.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }
}
