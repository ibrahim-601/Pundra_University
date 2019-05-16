package com.iriad11.pundrauniversity.datafromsheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEE_COLUMN_NAME = "name";
    public static final String EMPLOYEE_COLUMN_DESIGNATION = "designation";
    public static final String EMPLOYEE_COLUMN_PHONE = "phone";
    public static final String EMPLOYEE_COLUMN_FATHER = "department";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table employee " +
                        "( id integer primary key autoincrement, name text, designation text, phone text, department text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
    }

    public void upgrade(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }

    public boolean insertEmployee (String name, String desig, String phone, String dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("designation", desig);
        contentValues.put("department", dept);
        db.insert("employee", null, contentValues);
        return true;
    }


    public ArrayList<DataModel> getAllCotacts(String dept) {
        ArrayList<DataModel> array_list = new ArrayList<DataModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from employee where department= '"+dept+"' ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            DataModel data=new DataModel();
            data.add(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_DESIGNATION)),
                    res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_PHONE)));
            array_list.add(data);
            res.moveToNext();
        }
        res.close();
        return array_list;

    }
}