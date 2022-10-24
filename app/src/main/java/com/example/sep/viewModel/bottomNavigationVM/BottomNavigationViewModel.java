package com.example.sep.viewModel.bottomNavigationVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class BottomNavigationViewModel extends ViewModel {

    private MutableLiveData<String> selectedNavigationPage;

    public LiveData<String> getSelectedNavigationPage() {
        if(selectedNavigationPage == null) {
            selectedNavigationPage = new MutableLiveData<>();
        }

        return selectedNavigationPage;
    }


    public void setSelectedNavigationPage(String item) {
        if(selectedNavigationPage == null) {
            selectedNavigationPage = new MutableLiveData<>();
        }
        selectedNavigationPage.setValue(item);
    }


}
