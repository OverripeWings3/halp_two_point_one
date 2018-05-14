package com.iteso.is699367.halp_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.halp_3.beans.Classes;

import java.util.ArrayList;

public class AgendaFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference firebaseDatabase;
    DatabaseReference mUserClasses;
    FirebaseAuth mAuth;
    ArrayList<Classes> classes = new ArrayList<Classes>();


    public AgendaFragment() {
        // Required empty public constructor
    }

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        fab = rootView.findViewById(R.id.fab1);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ActivityAddTask.class); //change later %$"#%&"#$%#"$%"#$%

                    startActivity(intent);

                }
            });
        }

        recyclerView = rootView.findViewById(R.id.fragment_recycler1);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mUserClasses = firebaseDatabase.child("users").child(user.getUid()).child("classes");

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        //TODO: fill in task array goes here


        Log.i("COUNT ARRAY LIST0", String.valueOf(classes.size()));

        mUserClasses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //User value = dataSnapshot.getValue(User.class);
                //message.setText(value.toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Classes value = snapshot.getValue(Classes.class);
                    Log.d("FIREBASE", "Key is: " + snapshot.getKey() + " Value is: " + snapshot.getValue());
                    classes.add(value);
                }
                Log.i("COUNT ARRAY LIST", String.valueOf(classes.size()));
                AdapterClasses adapterProduct = new AdapterClasses(0, getActivity(), classes);
                recyclerView.setAdapter(adapterProduct);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        /*do{
            DatabaseReference mTempTask = mUserClasses.child(String.valueOf(i));
            Log.i("COUNT ARRAY LIST2", String.valueOf(classes.size()));
            mTempTask.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String duedate = dataSnapshot.child("duedate").getValue(String.class);
                    String done = dataSnapshot.child("done").getValue(String.class);
                    String clas = dataSnapshot.child("class").getValue(String.class);
                    Classes temp = new Classes(name, duedate, done, clas);
                    Log.i("TASK ******::::::::::", name + " " + duedate + " " + done + " " + clas);
                    if(name != null)
                        classes.add(temp);
                    Log.i("55COUNT ARRAY LIST1", String.valueOf(classes.size()));
                    if(name == null ){
                        Log.i("COUNT ARRAY LIST", String.valueOf(classes.size()));
                        AdapterClasses adapterProduct = new AdapterClasses(0, getActivity(), classes);
                        recyclerView.setAdapter(adapterProduct);
                        numChild = classes.size();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            i++;
            Log.i("COUNT ARRAY LIST1", String.valueOf(classes.size()));
        }while (i < 15);*/


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
