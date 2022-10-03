package com.example.sep;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Task;

public class TaskDistributionViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Task> task = new MutableLiveData<>();

    public MutableLiveData<Task> getTask(){
        if (task == null) {
            task = new MutableLiveData<>();
        }
        return task;

    }


}