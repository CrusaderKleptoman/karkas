package com.example.karkas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

class MyDataBase {

    // Данные базы данных и таблиц
    private static final String DATABASE_NAME = "Passenger";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pass";

    // Название столбцов
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "ФИО";
    private static final String COLUMN_NUMBER = "Номер_поезда";
    private static final String COLUMN_ROUT = "Маршрут";
    private static final String COLUMN_LOC = "Номер_вагона";
    private static final String COLUMN_PLACE = "Вокзал";
    private static final String COLUMN_TIME = "Время_отправления";
    private static final String COLUMN_PHONE = "Номер_телефона";


    // Номера столбцов
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_DATE = 1;
    private static final int NUM_COLUMN_TITLE = 2;
    private static final int NUM_COLUMN_ROUT = 3;
    private static final int NUM_COLUMN_LOC = 4;
    private static final int NUM_COLUMN_PLACE = 5;
    private static final int NUM_COLUMN_TIME = 6;
    private static final int NUM_COLUMN_PHONE = 7;

    private SQLiteDatabase mDataBase;

    public MyDataBase(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    //обавление записей
    public long insert(String name, int number, String rout, int loc, String place, String time, int phone){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, number);
        values.put(COLUMN_ROUT, rout);
        values.put(COLUMN_LOC, loc);
        values.put(COLUMN_PLACE, place);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_PHONE, phone);
        return mDataBase.insert(TABLE_NAME, null, values);
    }

    //Считывание записей
    public HashMap<String, Object> getInformation(int id){
        HashMap<String, Object> result = new HashMap<>();

        //mDataBase = this.getReadableDatabase();
        String selection = "_id = ?";
        String[] selectionArgs = new String[] {String.valueOf(id)};
        Cursor cursor = mDataBase.query(TABLE_NAME , null , selection , selectionArgs , null , null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            result.put("id", cursor.getInt(cursor.getColumnIndex("COLUMN_ID")));
            result.put("name", cursor.getString(cursor.getColumnIndex("COLUMN_NAME")));
            result.put("number", cursor.getInt(cursor.getColumnIndex("COLUMN_NUMBER")));
            result.put("rout", cursor.getString(cursor.getColumnIndex("COLUMN_ROUT")));
            result.put("loc", cursor.getInt(cursor.getColumnIndex("COLUMN_LOC")));
            result.put("place", cursor.getString(cursor.getColumnIndex("COLUMN_PLACE")));
            result.put("time", cursor.getString(cursor.getColumnIndex("COLUMN_TIME")));
            result.put("phone", cursor.getInt(cursor.getColumnIndex("COLUMN_PHONE")));
        }

        cursor.close();
        return result;
    }


public class OpenHelper extends SQLiteOpenHelper {


    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Создание таблицы
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " LONG, " +
                COLUMN_ROUT + " TEXT, " +
                COLUMN_LOC + " LONG, " +
                COLUMN_PLACE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_PHONE + " LONG);";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    }
}