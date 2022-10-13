package com.example.sep.viewModel.taskVM;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.Task;
import com.example.sep.view.eventRecyclerView.EventItem;
import com.example.sep.view.taskRecyclerView.TaskItem;

public class TaskItemViewModel extends ViewModel {

    private MutableLiveData<TaskItem> task;

    public LiveData<TaskItem> getTask(){
        if (task == null) {
            task = new MutableLiveData<>();
            loadTask();
        }
        return task;

    }

    private void loadTask(){}

    public void setTask(TaskItem taskObj) {
        if(task == null) {
            task = new MutableLiveData<>();
        }
        task.postValue(taskObj);
    }


}