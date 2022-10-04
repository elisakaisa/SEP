package com.example.sep.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Event;

public class EventViewModel extends ViewModel {

    public MutableLiveData<Event> event;
    public LiveData<Event> getEvent() {
        if(event == null) {
            event = new MutableLiveData<>();
            loadEvent();
        }
        return event;
    }

    private void loadEvent() {
        // asynchronous call to load the event
    }
}
