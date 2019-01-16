package com.example.ngergo.schedule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JaratokTab1 extends Fragment {

    public ArrayList<BusNumber> jaratok= new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.jaratok_tab1, container, false);

        recyclerView = rootView.findViewById(R.id.recycler);

        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<BusNumber> list) {
                BusAdapter busAdapter = new BusAdapter(getActivity(),list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                RecyclerView.LayoutManager rvLayoutManager = layoutManager;
                recyclerView.setLayoutManager(rvLayoutManager);
                recyclerView.setAdapter(busAdapter);
            }
        });

        return rootView;
    }

    public List<BusNumber> readData(final FirebaseCallback firebaseCallback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("bus_number");
        myRef.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jaratok = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    jaratok.add(new BusNumber(ds.child("CodeNumber").getValue(String.class), ds.child("BusRouteName").getValue(String.class),ds.child("bus_back").getValue(String.class)));
                }
                firebaseCallback.onCallback(jaratok);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read data.", databaseError.toException());
            }
        }));
        return jaratok;
    }

    protected interface FirebaseCallback
    {
        void onCallback(ArrayList<BusNumber> list);
    }
}
