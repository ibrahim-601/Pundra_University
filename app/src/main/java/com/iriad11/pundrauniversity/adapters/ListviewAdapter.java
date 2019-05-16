package com.iriad11.pundrauniversity.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iriad11.pundrauniversity.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.iriad11.pundrauniversity.adapters.Constants.FIRST_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.FOURTH_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.SECOND_COLUMN;
import static com.iriad11.pundrauniversity.adapters.Constants.THIRD_COLUMN;

public class ListviewAdapter extends BaseAdapter {
    public ArrayList<HashMap> list;
    Activity activity;

    public ListviewAdapter(Activity activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.listserial);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.listcredit);
            holder.txtThird = (TextView) convertView.findViewById(R.id.listshowgpa);
            holder.txtFourth = (TextView) convertView.findViewById(R.id.listtotal);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN).toString());
        holder.txtSecond.setText(map.get(SECOND_COLUMN).toString());
        holder.txtThird.setText(map.get(THIRD_COLUMN).toString());
        holder.txtFourth.setText(map.get(FOURTH_COLUMN).toString());

        return convertView;
    }

}