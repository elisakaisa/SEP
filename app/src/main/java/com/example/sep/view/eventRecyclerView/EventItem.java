package com.example.sep.view.eventRecyclerView;

import com.example.sep.model.Event;

import java.io.Serializable;

public class EventItem implements Serializable {
    Integer idx;
    Event iEvent;

    String iClientName;
    Integer iLevel;

    public EventItem(Event event, Integer idx) {
        iEvent = event;

        iClientName = event.getClientName();
        iLevel = event.getLevel();

        this.idx = idx;
    }

    public String getClientName(){
        return iClientName;
    }
    public Integer getLevel() { return iLevel; }

    public Event getEvent(){
        return iEvent;
    }

    public Integer getIdx(){return idx;}
}
