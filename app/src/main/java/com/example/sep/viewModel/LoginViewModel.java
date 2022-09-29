package com.example.sep.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep.model.User;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> user = new MutableLiveData<>();

    public MutableLiveData<User> getUser() { return user; }
}
