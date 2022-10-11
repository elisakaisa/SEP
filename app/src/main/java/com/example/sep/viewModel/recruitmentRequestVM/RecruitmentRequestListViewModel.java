package com.example.sep.viewModel.recruitmentRequestVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep.BaseActivity;
import com.example.sep.database.FinancialRequestList;
import com.example.sep.database.RecruitmentRequestList;
import com.example.sep.model.RecruitmentRequest;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecruitmentRequestListViewModel extends AndroidViewModel {
    public RecruitmentRequestListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<RecruitmentRequest>> requests;

    public LiveData<List<RecruitmentRequest>> getRequests() {
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
            FileInputStream fin = getApplication().getApplicationContext().openFileInput(BaseActivity.RES_REQUEST_FILE);
            ObjectInputStream oin = new ObjectInputStream(fin);
            BaseActivity.recruitmentRequestList = (RecruitmentRequestList) oin.readObject();
            requests.setValue(BaseActivity.recruitmentRequestList.getTheRequests());
            oin.close();

        } catch (Exception e){
            e.printStackTrace();
            BaseActivity.recruitmentRequestList = new RecruitmentRequestList();
            requests.setValue(BaseActivity.recruitmentRequestList.getTheRequests());
        }
    }
}
