package com.example.sep.viewModel.recruitmentRequestVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.RecruitmentRequest;

public class RecruitmentRequestViewModel extends ViewModel {
    public MutableLiveData<RecruitmentRequest> request;
    public int identifier;

    public LiveData<RecruitmentRequest> getRequest() {
        if (request == null) {
            request = new MutableLiveData<>();
        }
        return request;
    }

    public void setRequest(RecruitmentRequest requestObj) {
        if (request == null) {
            request = new MutableLiveData<>();
        }
        request.postValue(requestObj);
    }

    public void setIdentifier(int idx) {
        identifier = idx;
    }

    public int getIdentifier() { return identifier; }

}
