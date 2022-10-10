package com.example.sep.view.eventRecyclerView;

/*
Model of the part of the event that is displayed in the recyclerView
 */

import com.example.sep.model.Event;

import java.io.Serializable;

public class EventItem implements Serializable {
    int idx;
    Event iEvent;

    String iClientName;
    int iLevel;

    public EventItem(Event event, int idx) {
        iEvent = event;

        iClientName = event.getClientName();
        iLevel = event.getLevel();

        this.idx = idx;
    }

    public String getClientName(){
        return iClientName;
    }
    public int getLevel() { return iLevel; }

    public Event getEvent(){
        return iEvent;
    }

    public int getIdx(){return idx;}
}
