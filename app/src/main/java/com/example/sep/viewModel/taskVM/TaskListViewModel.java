package com.example.sep.viewModel.taskVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep.BaseActivity;
import com.example.sep.database.TaskList;
import com.example.sep.model.Task;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Task>> tasks;


    public TaskListViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Task>> getTask() {
        if(tasks == null) {
            tasks = new MutableLiveData<>();
            loadTasks();
        }
        return tasks;
    }


    private void loadTasks() {
        // asynchronous call to load the task
        // Deserialize taskList
        try{
            FileInputStream fin = getApplication().getApplicationContext().openFileInput(BaseActivity.TASK_LIST_FILE);
            ObjectInputStream oin = new ObjectInputStream(fin);
            BaseActivity.taskList = (TaskList) oin.readObject();
            tasks.setValue(BaseActivity.taskList.getTheTasks());
            oin.close();

        } catch (Exception e){
            e.printStackTrace();
            BaseActivity.taskList = new TaskList();
            tasks.setValue(BaseActivity.taskList.getTheTasks());
        }
    }
}