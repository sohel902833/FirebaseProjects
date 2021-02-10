package com.example.quizeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ListFragment extends Fragment implements QuizeListAdapter.OnQuizeListItemClick {



    private RecyclerView listView;
    private QuizeListViewModule  quizeListViewModule;
    private QuizeListAdapter adapter;

    private ProgressBar progressBar;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    private NavController navController;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController= Navigation.findNavController(view);
        listView=view.findViewById(R.id.list_view);
        adapter=new QuizeListAdapter(this);
    progressBar=view.findViewById(R.id.list_progress);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);
        fadeInAnimation= AnimationUtils.loadAnimation(getContext(),R.anim.fade_n);
        fadeOutAnimation= AnimationUtils.loadAnimation(getContext(),R.anim.fad_out);




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        quizeListViewModule=new ViewModelProvider(getActivity()).get(QuizeListViewModule.class);
        quizeListViewModule.getQuizelistModuleData().observe(getViewLifecycleOwner(), new Observer<List<QuizeListModule>>() {
            @Override
            public void onChanged(List<QuizeListModule> quizeListModuleList) {

                    listView.startAnimation(fadeInAnimation);
                    progressBar.startAnimation(fadeOutAnimation);
                    adapter.setQuizelistmodules(quizeListModuleList);
                    adapter.notifyDataSetChanged();

            }
        });




    }

    @Override
    public void onItemClick(int position) {

        ListFragmentDirections.ActionListFragmentToDetailsFragment action=ListFragmentDirections.actionListFragmentToDetailsFragment();
        action.setPosition(position);
        navController.navigate(action);

    }
}
