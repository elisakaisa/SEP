package com.example.sep.viewModel.financialRequestVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.FinancialRequest;

public class FinancialRequestViewModel extends ViewModel {

    public MutableLiveData<FinancialRequest> request;
    public int identifier;

    public LiveData<FinancialRequest> getRequest() {
        if (request == null) {
            request = new MutableLiveData<>();
        }
        return request;
    }

    public void setRequest(FinancialRequest requestObj) {
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
