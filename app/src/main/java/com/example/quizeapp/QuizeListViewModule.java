package com.example.quizeapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizeListViewModule  extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<QuizeListModule>> quizelistModuleData=new MutableLiveData<>();

    public LiveData<List<QuizeListModule>> getQuizelistModuleData() {
        return quizelistModuleData;
    }

    private  FirebaseRepository firebaseRepository=new FirebaseRepository(this);

    public  QuizeListViewModule(){
        firebaseRepository.getQuizeData();
    }


    @Override
    public void quizelistDataAdded(List<QuizeListModule> quizeListModuleList) {
        quizelistModuleData.setValue(quizeListModuleList);
    }

    @Override
    public void onErro(Exception e) {

    }
}
