package com.iriad11.pundrauniversity.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.iriad11.pundrauniversity.R;
import com.iriad11.pundrauniversity.adapters.TableAdapter;
import com.iriad11.pundrauniversity.datafromsheet.DBHelper;
import com.iriad11.pundrauniversity.datafromsheet.DataModel;

import java.util.ArrayList;


public class Contacts extends Fragment implements AdapterView.OnItemSelectedListener {

    private ViewGroup tablev;
    private ListView listview;
    private View v;
    private DBHelper mydb;
    private ArrayList<DataModel> items;
    private String arrname;
    private TableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_contacts, container, false);
        Spinner spinner=(Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Contacts");

        mydb=new DBHelper(getView().getContext());
        listview=(ListView) v.findViewById(R.id.listv);
        LayoutInflater inflater = (LayoutInflater) getView().getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        tablev=(ViewGroup) inflater.inflate(R.layout.table_view,listview,false);
        listview.addHeaderView(tablev);

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        String sSelected=parent.getItemAtPosition(pos).toString();


        if(pos==1) {
            items = mydb.getAllCotacts("admin");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText( getView().getContext() , sSelected+" selected" , Toast.LENGTH_SHORT ).show();
        }
        else if(pos==2){
            Toast.makeText( getView().getContext() , sSelected+" selected" , Toast.LENGTH_SHORT ).show();
            items = mydb.getAllCotacts("bba");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
        }
        else if(pos==3) {
            Toast.makeText( getView().getContext() , sSelected+" selected" , Toast.LENGTH_SHORT ).show();
            items = mydb.getAllCotacts("cse");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
        }
        else if(pos==4) {
            items = mydb.getAllCotacts("eee");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==5) {
            items = mydb.getAllCotacts("ph");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==6) {
            items = mydb.getAllCotacts("ce");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==7) {
            items = mydb.getAllCotacts("is");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==8) {
            items = mydb.getAllCotacts("eng");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==9) {
            items = mydb.getAllCotacts("law");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }
        else if(pos==10) {
            items = mydb.getAllCotacts("acs");
            adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_phon,items);
            listview.setAdapter(adapter);
            Toast.makeText(getView().getContext(), sSelected+" selected", Toast.LENGTH_SHORT).show();
        }

        else if(pos==0) {
            Toast.makeText(getView().getContext(), "Select am item from the drop down menu", Toast.LENGTH_SHORT).show();
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String item = items.get((int)id).getPhone().toString();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel: "+item));
                    startActivity(callIntent);
                }
            }
        });

    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
