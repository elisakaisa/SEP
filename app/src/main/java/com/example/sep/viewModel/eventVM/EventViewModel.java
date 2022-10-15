package com.example.sep.viewModel.eventVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Event;
import com.example.sep.view.eventRecyclerView.EventItem;

public class EventViewModel extends ViewModel {

    public MutableLiveData<Event> event;
    public int identifier;

    public LiveData<Event> getEvent() {
        if(event == null) {
            event = new MutableLiveData<>();
        }
        return event;
    }

    public void setEvent(Event eventObj) {
        if(event == null) {
            event = new MutableLiveData<>();
        }
        event.postValue(eventObj);
    }

    public void setIdentifier(int idx) {
        identifier = idx;
    }

    public int getIdentifier() { return identifier; }
}
