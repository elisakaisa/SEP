package com.example.sep.view.eventRecyclerView;

import com.example.sep.model.Event;

import java.io.Serializable;

public class EventItem implements Serializable {
    String iClientName;
    Integer idx;
    Event iEvent;

    public EventItem(Event event, Integer idx) {
        iEvent = event;
        iClientName = event.getClientName();

        this.idx = idx;
    }

    public String getiClientName(){
        return iClientName;
    }

    public Event getiEvent(){
        return iEvent;
    }

    public Integer getIdx(){return idx;}
}
