package com.example.sep.viewModel.eventVM;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep.BaseActivity;
import com.example.sep.database.EventList;
import com.example.sep.model.Event;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class EventListViewModel extends AndroidViewModel {


    public MutableLiveData<List<Event>> events;

    public EventListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Event>> getEvent() {
        if(events == null) {
            events = new MutableLiveData<>();
            loadEvents();
        }
        return events;
    }

    private void loadEvents() {
        // asynchronous call to load the event
        //Deserialise eventslist here
        try{
            FileInputStream fin = getApplication().getApplicationContext().openFileInput(BaseActivity.EVENT_LIST_FILE);
            ObjectInputStream oin = new ObjectInputStream(fin);
            BaseActivity.eventList = (EventList) oin.readObject();
            events.setValue(BaseActivity.eventList.getTheEvents());
            oin.close();

        } catch (Exception e){
            e.printStackTrace();
            BaseActivity.eventList = new EventList();
            events.setValue(BaseActivity.eventList.getTheEvents());
        }
    }
}
