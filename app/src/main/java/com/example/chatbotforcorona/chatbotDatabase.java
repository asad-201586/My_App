package com.example.chatbotforcorona;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class chatbotDatabase extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "chatbotDatabase";
    private static final String TABLE_NAME = "userInfo";
    private static final int VERSTION_NUMBER = 2;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lasttName";
    private static final String MOBILE_NUMBER = "mobileNumber";
    private static final String EMAIL = "email";
    private static final String DISTRICT = "district";
    private static final String PASSWORD = "password";
    private static final String RETYPE_PASSWORD = "retypePassword";




    public chatbotDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSTION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+FIRST_NAME+" VARCHAR,"+LAST_NAME+" VARCHAR,"+MOBILE_NUMBER+" NUMBER,"+EMAIL+" VARCHAR PRIMARY KEY,"+DISTRICT+" VARCHAR,"+PASSWORD+" TEXT,"+RETYPE_PASSWORD+" TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME+"");
        onCreate(db);

    }

    public boolean insertData(String firstName,String lastName,String mobileNumber,String email,String district,String password,String retypePassword){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,email);
        contentValues.put(PASSWORD,password);
        contentValues.put(RETYPE_PASSWORD,retypePassword);
        contentValues.put(FIRST_NAME,firstName);
        contentValues.put(LAST_NAME,lastName);
        contentValues.put(MOBILE_NUMBER,mobileNumber);
        contentValues.put(DISTRICT,district);

        long a = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (a==-1) return false;
        else return true;
    }


    public boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME+"  where email=?",new  String[] {email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public boolean checkLogin(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME+" where email=? and password=?",new String[] {email,password});
        if (cursor.getCount()>0) return false;
        else return true;
    }
}
