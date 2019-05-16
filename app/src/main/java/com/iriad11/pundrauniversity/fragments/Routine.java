package com.iriad11.pundrauniversity.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.iriad11.pundrauniversity.R;
import com.iriad11.pundrauniversity.adapters.Fullscreen;


public class Routine extends Fragment implements AdapterView.OnItemSelectedListener {
    private View v;
    private Spinner spin;
    private ImageView img,img2;
    boolean isImageFitToScreen;
    private Toolbar tool;
    private String st1,st2;

    private RequestOptions requestOptions = new RequestOptions();

    public static final String[] string1={
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DOXhXSFBxUEpld1U",	//bba day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DS0VPeElDQmpoWjQ",	//ce day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DZU9jMUJoYmJLaGs",	//cse day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7Dd1FTbW01QWY4V1E",	//eee day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DRjEyNUFjeVBNb2s",	//eng day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DcFc5R3dwNEVhUkE",	//is day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DWGhQZXlVdWFZcHM",   //llb day
            "https://drive.google.com/uc?export=download&id=1ptfr7lpLoByZ6E9J7yWOHlnhhys4nK3o", //mba day
            "https://drive.google.com/uc?export=download&id=1IRCiiudK90T46QoWB_46QMKhbyTEcLyf"  //mph day
    };

    public static final String[] string2={
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7Db24yUC10UGRkVmc",  //ce eve
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DQUdfNlIzSGgyM1E",  //cse eve
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DejQ4U25IWjZqYlE",  //eee eve
            "https://drive.google.com/uc?export=download&id=1t5GJ615uT6pjBSnnV-mHupe3jpSBMfA5",  //is masters
            "https://drive.google.com/uc?export=download&id=11RCq_nRzjAJQyF1zSGv9bWXb8QwNWV64",	//llm
            "https://drive.google.com/uc?export=download&id=1d3--y1NSv4MRc9ZZ8ohGuQmeMhD6Glf6"  //mba eve
    };

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_routine, container, false);
        spin=(Spinner) v.findViewById(R.id.routinespin);
        spin.setOnItemSelectedListener(this);
        img=(ImageView) v.findViewById(R.id.routinimg);
        img2=(ImageView) v.findViewById(R.id.routinimg2) ;
        tool=(Toolbar)v.findViewById(R.id.toolbar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Fullscreen.class);
                intent.putExtra("image", st1);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Fullscreen.class);
                intent.putExtra("image", st2);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);

        getActivity().setTitle("Class Routine");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(pos==1){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            st1=string1[0];
            //st2=string2[0];

            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

        /*    Glide.with(getContext()).
                 load(st2).
                 apply(requestOptions).
                 into(img2);    */

        } else if(pos==2){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[1];
            st2=string2[0];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        } else if(pos==3){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[2];
            st2=string2[1];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        } else if(pos==4){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[3];
            st2=string2[2];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        } else if(pos==5){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            st1=string1[4];
            //st2=string2[4];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

        /*    Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);     */
        } else if(pos==6){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[5];
            st2=string2[3];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        } else if(pos==7){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[6];
            st2=string2[4];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        } else if(pos==8){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            st1=string1[7];
            st2=string2[5];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

            Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);
        }
        else if(pos==9){
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            st1=string1[8];
            //st2=stringe2[3];
            Glide.with(getContext()).
                    load(st1).
                    apply(requestOptions).
                    into(img);

        /*    Glide.with(getContext()).
                    load(st2).
                    apply(requestOptions).
                    into(img2);     */
        } else if(pos==0){
            Toast.makeText( getView().getContext() , "Please select a department" , Toast.LENGTH_SHORT ).show();
        } else{
            Toast.makeText( getView().getContext() , "This menu is not available yet. Please check for update." , Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

/*
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DOXhXSFBxUEpld1U",	//bba
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DS0VPeElDQmpoWjQ",	//ce day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DZU9jMUJoYmJLaGs",	//cse day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7Dd1FTbW01QWY4V1E",	//eee day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DRjEyNUFjeVBNb2s",	//eng
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DcFc5R3dwNEVhUkE",	//is honors day
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DWGhQZXlVdWFZcHM",   //llb
            "https://drive.google.com/uc?export=download&id=1ptfr7lpLoByZ6E9J7yWOHlnhhys4nK3o", //mba day
            "https://drive.google.com/uc?export=download&id=1IRCiiudK90T46QoWB_46QMKhbyTEcLyf"  //mph

            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7Db24yUC10UGRkVmc",  //ce eve
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DQUdfNlIzSGgyM1E",  //cse eve
            "https://drive.google.com/uc?export=download&id=0B63nnSvWuc7DejQ4U25IWjZqYlE",  //cee eve
	        "https://drive.google.com/uc?export=download&id=1t5GJ615uT6pjBSnnV-mHupe3jpSBMfA5",   //is masters
	        "https://drive.google.com/uc?export=download&id=11RCq_nRzjAJQyF1zSGv9bWXb8QwNWV64",	//llm
            "https://drive.google.com/uc?export=download&id=1d3--y1NSv4MRc9ZZ8ohGuQmeMhD6Glf6"  //mba eve
*/