package com.example.quizeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.quizeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartFragment extends Fragment {

    private ProgressBar startProgress;
    private TextView  startFeedbackText;
    private  static  final  String start_Tag="START_TAG";
    private FirebaseAuth firebaseAuth;

    private NavController navController;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        firebaseAuth=FirebaseAuth.getInstance();

        navController= Navigation.findNavController(view);
        startProgress=view.findViewById(R.id.start_progress);
        startFeedbackText=view.findViewById(R.id.start_feedback);
        startFeedbackText.setText("Cheking User Account .............");




    }@Override
    public void onStart() {

        super.onStart();

        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if(currentUser==null){
            startFeedbackText.setText("Creating Account.........");
                    firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startFeedbackText.setText("Account Created.........");
                                navController.navigate(R.id.action_startFragment_to_listFragment);
                            }else{
                                startFeedbackText.setText("User is Allready Log in........");
                                Log.d(start_Tag,"Start tag : "+task.getException());
                            }
                        }
                    });
        }else{
                 navController.navigate(R.id.action_startFragment_to_listFragment);
        }




    }
}
