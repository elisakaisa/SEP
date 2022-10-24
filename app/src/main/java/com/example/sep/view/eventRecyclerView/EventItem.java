package com.example.sep.view.eventRecyclerView;

/*
Model of the part of the event that is displayed in the recyclerView
 */

import com.example.sep.model.Event;

import java.io.Serializable;

public class EventItem implements Serializable {
    private int idx;
    private Event iEvent;

    private String iStatus, iClientName, iEventType;
    private int iLevel;

    public EventItem(Event event, int idx) {
        iEvent = event;

        iClientName = event.getClientName();
        iLevel = event.getLevel();
        iStatus = event.getStatus();
        iEventType = event.getEventType();

        this.idx = idx;
    }

    public String getClientName(){
        return iClientName;
    }
    public int getLevel() { return iLevel; }
    public String getStatus() { return iStatus; }
    public String getEventType() { return iEventType; }

    public Event getEvent(){
        return iEvent;
    }

    public int getIdx(){return idx;}
}
