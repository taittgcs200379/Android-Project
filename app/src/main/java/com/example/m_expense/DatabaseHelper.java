package com.example.m_expense;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "hiking_details";
    private static final  String HIKE_TABLE_NAME="hike_details";
    private static final String HIKE_COLUMN_ID="hike_id";
    private static final String HIKE_COLUMN_NAME="name";
    private static final String HIKE_COLUMN_DESTINATION="destination";
    private static final String HIKE_COLUMN_DATE="date";
    private static final String HIKE_COLUMN_LENGTH="length";
    private static final String HIKE_COLUMN_LEVEL="level";
    private static final String HIKE_COLUMN_CHOICE="choice";
    private static final String HIKE_COLUMN_DESCRIPTION="description";
    private SQLiteDatabase database;
    private static final String HIKE_DETAILS_QUERY= String.format(
            "CREATE TABlE %s(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            HIKE_TABLE_NAME, HIKE_COLUMN_ID, HIKE_COLUMN_NAME, HIKE_COLUMN_DESTINATION,HIKE_COLUMN_DATE,HIKE_COLUMN_LENGTH, HIKE_COLUMN_LEVEL,HIKE_COLUMN_CHOICE,HIKE_COLUMN_DESCRIPTION);
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        database = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(HIKE_DETAILS_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME +"database upgrade to version" + newVersion + "-old data lost");
        onCreate(db);

    }
    public long insertHikeDetails(String name, String destination, String date, String length,String level, String choice, String description){
        ContentValues rowValues = new ContentValues();
        rowValues.put(HIKE_COLUMN_NAME, name);
        rowValues.put(HIKE_COLUMN_DESTINATION, destination);
        rowValues.put(HIKE_COLUMN_DATE, date);
        rowValues.put(HIKE_COLUMN_LENGTH, length);
        rowValues.put(HIKE_COLUMN_LEVEL, level);
        rowValues.put(HIKE_COLUMN_CHOICE, choice);
        rowValues.put(HIKE_COLUMN_DESCRIPTION, description);
        return database.insertOrThrow(HIKE_TABLE_NAME, null, rowValues);
    }
    public String getHikeDetails(){
        Cursor results= database.query("hike_details", new String[]{"hike_id", "name","destination", "date","length","level","choice","description"},
                null, null, null ,null, "name");
        String resultText="";
        results.moveToFirst();
        while (!results.isAfterLast()){
            int id = results.getInt(0);
            String name = results.getString(1);
            String destination = results.getString(2);
            String date = results.getString(3);
            String length = results.getString(4);
            String level = results.getString(5);
            String choice = results.getString(6);
            String description = results.getString(7);
            resultText+= "ID: "+id + "\n"
                    + "Name of Hiking: "+ name + "\n"
                    + "Destination: "+destination + "\n"
                    + "Date: "+date + "\n"
                    + "Length: "+length + "\n"
                    + "Level: "+level + "\n"
                    + "Parking Choice: "+ choice + "\n"
                    + "Description: "+description+"\n"+"\n";
            results.moveToNext();
        }
        return resultText;
    }
    public ArrayList<HikingModel>getAllData(){
        ArrayList<HikingModel> arrayList =new ArrayList<>();
        String selectQuery=" SELECT * FROM " + HIKE_TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HikingModel hikingModel = new HikingModel(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(HIKE_COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_DESTINATION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_DATE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_LENGTH)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_LEVEL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_CHOICE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(HIKE_COLUMN_DESCRIPTION))
                );
                arrayList.add(hikingModel);
            }while (cursor.moveToNext());

        }
        db.close();
        return arrayList;
    }

}

