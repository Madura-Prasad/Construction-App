package com.maduraprasad.wjconstractor.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBConnector extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WJConstraction";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    //Admin Table 
    public static final String AdminTableName = "Admin";
    public static final String AdminTableId = "id";
    public static final String AdminTableFullName = "fullname";
    public static final String AdminTableEmail = "email";
    public static final String AdminTablePassword = "password";
    public static final String AdminTableConfirmPassword = "confirmpassword";


    //Add Construction Table
    public static final String ConstructionTableName = "Add_Site";
    public static final String ConstructionTableId = "id";
    public static final String ConstructionTableSiteName = "siteName";
    public static final String ConstructionTableSiteSupervisorName = "SupervisorName";
    public static final String ConstructionTableLocation = "location";


    //Add Supervisor Table
    public static final String SupervisorTableName = "Supervisor";
    public static final String SupervisorTableId = "id";
    public static final String SupervisorTableFullNAme = "fullname";
    public static final String SupervisorTableEmail = "email";
    public static final String SupervisorTablePassword = "password";
    public static final String SupervisorTableConfirmPassword = "confirmpassword";


    //Add Masonry Table
    public static final String MasonryTableName = "Masonry";
    public static final String MasonryTableId = "id";
    public static final String MasonryTableFullName = "FullName";
    public static final String MasonryTableAddress = "Address";
    public static final String MasonryTableNic = "Nic";
    public static final String MasonryTableAge = "Age";
    public static final String MasonryTableSupervisorName = "SupervisorName";


    //Attendance
    public static final String AttendanceTableName = "Attendance";
    public static final String AttendanceTableId = "id";
    public static final String AttendanceTableFullName = "Name";
    public static final String AttendanceAttende = "Attendance";


    public DBConnector(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String admin = "CREATE TABLE " + AdminTableName + "(" + AdminTableId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AdminTableFullName + " TEXT, " + AdminTableEmail + " TEXT, " + AdminTablePassword + " TEXT, " + AdminTableConfirmPassword + " TEXT);";
        String addsite = "CREATE TABLE " + ConstructionTableName + "(" + ConstructionTableId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ConstructionTableSiteName + " TEXT, " + ConstructionTableSiteSupervisorName + " TEXT, " + ConstructionTableLocation + " TEXT);";
        String supervisor = "CREATE TABLE " + SupervisorTableName + "(" + SupervisorTableId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SupervisorTableFullNAme + " TEXT, " + SupervisorTableEmail + " TEXT, " + SupervisorTablePassword + " TEXT, " + SupervisorTableConfirmPassword + " TEXT);";
        String masonry = "CREATE TABLE " + MasonryTableName + "(" + MasonryTableId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MasonryTableFullName + " TEXT, " + MasonryTableAddress + " TEXT, " + MasonryTableNic + " TEXT, " + MasonryTableAge + " TEXT, " + MasonryTableSupervisorName + " TEXT);";
        String attendance = "CREATE TABLE " + AttendanceTableName + "(" + AttendanceTableId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AttendanceTableFullName + " TEXT, " + AttendanceAttende + " TEXT);";
        db.execSQL(admin);
        db.execSQL(addsite);
        db.execSQL(supervisor);
        db.execSQL(masonry);
        db.execSQL(attendance);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AdminTableName);
        db.execSQL("DROP TABLE IF EXISTS " + ConstructionTableName);
        db.execSQL("DROP TABLE IF EXISTS " + SupervisorTableName);
        db.execSQL("DROP TABLE IF EXISTS " + MasonryTableName);
        db.execSQL("DROP TABLE IF EXISTS " + AttendanceTableName);
        onCreate(db);
    }

    //Add Site Read Data
    public Cursor readAllData() {
        String query = "SELECT * FROM " + ConstructionTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //Add Supervisor Read Data
    public Cursor SupervisorreadAllData() {
        String query = "SELECT * FROM " + SupervisorTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Add Attendance Read Data
    public Cursor AttendancereadAllData() {
        String query = "SELECT * FROM " + AttendanceTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //UpdateDataSupervisor
    public void UpdateDataSupervisor(String row_id, String name, String email, String password, String cpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SupervisorTableFullNAme, name);
        cv.put(SupervisorTableEmail, email);
        cv.put(SupervisorTablePassword, password);
        cv.put(SupervisorTableConfirmPassword, cpassword);

        long result = db.update(SupervisorTableName, cv, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Update.", Toast.LENGTH_SHORT).show();
        }
    }


    //Supervisor Delete
    public void deleteOneRowSupervisor(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(SupervisorTableName, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Delete.", Toast.LENGTH_SHORT).show();
        }
    }


    //Add Masonry Read Data
    public Cursor readAllDataMasonry() {
        String query = "SELECT * FROM " + MasonryTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //UpdateDataMasonry
    public void UpdateDataMasonry(String row_id, String name, String address, String nic, String age, String sname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MasonryTableFullName, name);
        cv.put(MasonryTableAddress, address);
        cv.put(MasonryTableNic, nic);
        cv.put(MasonryTableAge, age);
        cv.put(MasonryTableSupervisorName, sname);

        long result = db.update(MasonryTableName, cv, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Update.", Toast.LENGTH_SHORT).show();
        }
    }


    //Masonry Delete
    public void deleteOneRowMasonry(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(MasonryTableName, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Delete.", Toast.LENGTH_SHORT).show();
        }
    }


    //Supervisor DeleteAll
    public void deleteSupervisorAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + SupervisorTableName);
    }


    //Masonry DeleteAll
    public void deleteMasonryAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + MasonryTableName);
    }

    //Attendance DeleteAll
    public void deleteAttendanceAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + AttendanceTableName);
    }
}

