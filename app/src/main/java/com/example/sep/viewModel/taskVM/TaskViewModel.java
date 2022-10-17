package com.example.sep.viewModel.taskVM;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Task;

public class TaskViewModel extends ViewModel {

    private MutableLiveData<Task> task;

    public int identifierTask;

    public LiveData<Task> getTask(){
        if (task == null) {
            task = new MutableLiveData<>();
        }
        return task;

    }

    public void setTask(Task taskObj) {
        if(task == null) {
            task = new MutableLiveData<>();
        }
        task.postValue(taskObj);
    }
    public void setIdentifierTask(int idx) {
        identifierTask = idx;
    }

    public int getIdentifierTask() { return identifierTask; }

}