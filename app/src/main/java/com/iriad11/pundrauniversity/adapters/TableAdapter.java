package com.iriad11.pundrauniversity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iriad11.pundrauniversity.R;
import com.iriad11.pundrauniversity.datafromsheet.DataModel;

import java.util.ArrayList;

public class TableAdapter extends ArrayAdapter<DataModel> {
    int vg;
    ArrayList<DataModel> items_list;
    Context context;
    private View.OnClickListener onXXXClickListener;

    public TableAdapter(Context context,int vg,int id, ArrayList<DataModel> items_list){
        super(context,vg,id,items_list);
        this.context=context;
        this.items_list=items_list;
        this.vg=vg;
    }


    static  class viewholder{
        public TextView name;
        public TextView desig;
        public TextView phone;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View rowView=convertView;
        if(rowView==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView =inflater.inflate(vg,parent,false);
            viewholder holder=new viewholder();
            holder.name=(TextView) rowView.findViewById(R.id.table_name);
            holder.desig=(TextView) rowView.findViewById(R.id.table_des);
            holder.phone=(TextView) rowView.findViewById(R.id.table_phon);
            rowView.setTag(holder);
        }

        viewholder holder=(viewholder) rowView.getTag();
        holder.name.setText(items_list.get(position).getName());
        holder.desig.setText(items_list.get(position).getDesignation());
        holder.phone.setText(items_list.get(position).getPhone());
        return rowView;
    }

    public String get(int id){
        String phone=items_list.get(id).getPhone();
        return phone;
    }
}
