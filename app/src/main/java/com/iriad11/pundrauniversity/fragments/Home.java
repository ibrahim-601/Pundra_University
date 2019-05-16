package com.iriad11.pundrauniversity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iriad11.pundrauniversity.MainActivity;
import com.iriad11.pundrauniversity.R;


public class Home extends Fragment {

    private Button btn_con, btn_rout, btn_bus, btn_cgpa, btn_web;
    private Fragment fragment;
    private FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        btn_con = view.findViewById(R.id.btn_contacs);
        btn_con.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_contacts);
            }
        });

        btn_rout = view.findViewById(R.id.btn_routine);
        btn_rout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_routine);
            }
        });

        btn_bus = view.findViewById(R.id.btn_bus);
        btn_bus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_bus);
            }
        });

        btn_cgpa = view.findViewById(R.id.btn_cgpa);
        btn_cgpa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_cgpa);
            }
        });

        btn_web = view.findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_web);
            }
        });

        getActivity().setTitle("Pundra University");
    }
}
