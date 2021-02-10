package com.example.quizeapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {


    private  OnFirestoreTaskComplete onFirestoreTaskComplete;

    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private CollectionReference quizeRef=firebaseFirestore.collection("QuizList");

    public FirebaseRepository(OnFirestoreTaskComplete onFirestoreTaskComplete){
                this.onFirestoreTaskComplete=onFirestoreTaskComplete;
    }




    public void getQuizeData(){
        quizeRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                            onFirestoreTaskComplete.quizelistDataAdded(task.getResult().toObjects(QuizeListModule.class));
                    }else {
                            onFirestoreTaskComplete.onErro(task.getException());
                    }
            }
        });

    }





public interface  OnFirestoreTaskComplete{
        void quizelistDataAdded(List<QuizeListModule>  quizeListModuleList);
        void onErro(Exception e);
}





}
