package com.iriad11.pundrauniversity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.iriad11.pundrauniversity.R;
import com.iriad11.pundrauniversity.adapters.ListviewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static com.iriad11.pundrauniversity.adapters.Constants.FIRST_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.FOURTH_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.SECOND_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.THIRD_COLUMN;


public class Cgpa extends Fragment {

    private ListView listview;
    private ListviewAdapter adapter;
    private ViewGroup listv;
    private View v;
    private Button btnad,btncal;
    private TextView cg;
    private EditText cre,gpa;
    private Vector<Double> credits[];
    private int i;
    private float myFloat,sum,current;
    private ArrayList<HashMap> list;
    private ArrayList<HashMap> listhead;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cgpa, container, false);
        i=0;
        cg=(TextView)v.findViewById(R.id.cgpaoutput);
        cre=(EditText)v.findViewById(R.id.creditinput);
        gpa=(EditText)v.findViewById(R.id.cgpainput);
        btnad=(Button)v.findViewById(R.id.cgpabtn);
        btnad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cre.getText().length()==0){
                    cre.setError("Please enter some data");
                }
                else if(gpa.getText().length()==0){
                    gpa.setError("Please enter some data");
                }
                else if(Double.parseDouble(cre.getText().toString())<=0){
                    cre.setError("Credit must be greater than 0");
                }
                else if(Double.parseDouble(gpa.getText().toString())>4){
                    gpa.setError("GPA can not be greater than 4");
                }
                else{
                    populate(i,cre.getText().toString(),gpa.getText().toString());
                    i++;
                    adapter=new ListviewAdapter(getActivity(),list);
                    listview.setAdapter(adapter);

                }
            }
        });
        btncal=(Button)v.findViewById(R.id.cgpacalc);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cg.setText(String.valueOf(current));
            }
        });
        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("CGPA Calculator");
        populateh();
        listview=(ListView)v.findViewById(R.id.cgpalist);
        adapter=new ListviewAdapter(getActivity(),list);
        listview.setAdapter(adapter);
    }
    public void populate(int i, String j,String k){
        float jk=Float.parseFloat(j)*Float.parseFloat(k);
        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,Integer.toString(i+1));
        temp.put(SECOND_COLUMN, j);
        temp.put(THIRD_COLUMN, k);
        temp.put(FOURTH_COLUMN, Float.toString(jk));
        list.add(temp);
        myFloat=myFloat+jk;
        sum=sum+Float.parseFloat(j);
        current=(float)(myFloat/sum);
    }
    public void populateh(){
        list= new ArrayList<HashMap>();

        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,"Serial");
        temp.put(SECOND_COLUMN, "Credit");
        temp.put(THIRD_COLUMN, "GPA");
        temp.put(FOURTH_COLUMN, "Total");
        list.add(temp);
    }
}