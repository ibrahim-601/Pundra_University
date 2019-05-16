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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.iriad11.pundrauniversity.R;
import com.iriad11.pundrauniversity.adapters.Fullscreen;
import com.iriad11.pundrauniversity.adapters.TableAdapter;
import com.iriad11.pundrauniversity.datafromsheet.DBHelper;
import com.iriad11.pundrauniversity.datafromsheet.DataModel;

import java.util.ArrayList;


public class Bus extends Fragment {

    private ViewGroup tablev;
    private ListView listview;
    private View v;
    private ArrayList<DataModel> items;
    private DBHelper mydb;
    private String arrname,st1;
    private TableAdapter adapter;
    private ImageView img;
    private Button btn;

    private static final String string= "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DU3Z1eS1aWlVtMVU";

            /*
            https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DU3Z1eS1aWlVtMVU    bus
            */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_bus, container, false);
        btn = v.findViewById(R.id.btn_bsch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Fullscreen.class);
                intent.putExtra("image",string);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Bus Route");
        mydb=new DBHelper(getView().getContext());
        listview=(ListView) v.findViewById(R.id.listbus);
        LayoutInflater inflater = (LayoutInflater) getView().getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        tablev=(ViewGroup) inflater.inflate(R.layout.table_view,listview,false);
        listview.addHeaderView(tablev);
        itemSelect("Bus");
    }


    public void itemSelect(String dept) {
        items = mydb.getAllCotacts(dept);
        adapter=new TableAdapter(getView().getContext(),R.layout.table_view,R.id.table_name,items);
        listview.setAdapter(adapter);
        Toast.makeText( getView().getContext() , dept , Toast.LENGTH_SHORT ).show();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String item = items.get((int)id).getPhone().toString();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel: "+item));
                    startActivity(callIntent);
                }
            }
        });
    }


}
