package com.example.sep.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep.BaseActivity;
import com.example.sep.database.FinancialRequestList;
import com.example.sep.model.FinancialRequest;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class FinancialRequestListViewModel extends AndroidViewModel {
    public FinancialRequestListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<FinancialRequest>> requests;

    public LiveData<List<FinancialRequest>> getRequests() {
        if (requests == null) {
            requests = new MutableLiveData<>();
            loadRequests();
        }
        return requests;
    }

    private void loadRequests() {
        // asynchronous call to load the event
        //Deserialise eventslist here
        try{
            FileInputStream fin = getApplication().getApplicationContext().openFileInput(BaseActivity.FIN_REQUEST_FILE);
            ObjectInputStream oin = new ObjectInputStream(fin);
            BaseActivity.fRequestList = (FinancialRequestList) oin.readObject();
            requests.setValue(BaseActivity.fRequestList.getTheRequests());
            oin.close();

        } catch (Exception e){
            e.printStackTrace();
            BaseActivity.fRequestList = new FinancialRequestList();
            requests.setValue(BaseActivity.fRequestList.getTheRequests());
        }
    }
}
