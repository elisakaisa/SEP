package com.example.sep.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Event;
import com.example.sep.view.eventRecyclerView.EventItem;

public class EventViewModel extends ViewModel {

    // TODO: see if it can be refactored for Event instead of eventItem

    public MutableLiveData<EventItem> event;
    public LiveData<EventItem> getEvent() {
        if(event == null) {
            event = new MutableLiveData<>();
            loadEvent();
        }
        return event;
    }

    private void loadEvent() {
        // asynchronous call to load the event
    }

    public void setEvent(EventItem eventObj) {
        if(event == null) {
            event = new MutableLiveData<>();
        }
        event.postValue(eventObj);
    }
}
