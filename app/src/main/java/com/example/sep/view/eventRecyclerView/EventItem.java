package com.example.sep.view.eventRecyclerView;

import com.example.sep.model.Event;

import java.io.Serializable;

public class EventItem implements Serializable {
    Integer idx;
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
