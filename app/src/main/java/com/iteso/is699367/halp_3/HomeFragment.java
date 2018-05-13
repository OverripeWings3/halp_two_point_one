package com.iteso.is699367.halp_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.is699367.halp_3.beans.Tasks;
import com.iteso.is699367.halp_3.database.DataBaseHandler;
import com.iteso.is699367.halp_3.database.TaskControl;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        TaskControl taskControl = new TaskControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        ArrayList<Tasks> tasks = taskControl.getTasks( dh);

        AdapterTasks adapterProduct = new AdapterTasks(0, getActivity(), tasks);
        recyclerView.setAdapter(adapterProduct);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
