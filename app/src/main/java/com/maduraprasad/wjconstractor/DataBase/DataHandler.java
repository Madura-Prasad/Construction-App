package com.maduraprasad.wjconstractor.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AdminTableConfirmPassword;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AdminTableEmail;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AdminTableFullName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AdminTableName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AdminTablePassword;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AttendanceAttende;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AttendanceTableFullName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.AttendanceTableName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.ConstructionTableLocation;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.ConstructionTableName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.ConstructionTableSiteName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.ConstructionTableSiteSupervisorName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableAddress;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableAge;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableFullName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableNic;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.MasonryTableSupervisorName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.SupervisorTableConfirmPassword;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.SupervisorTableEmail;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.SupervisorTableFullNAme;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.SupervisorTableName;
import static com.maduraprasad.wjconstractor.DataBase.DBConnector.SupervisorTablePassword;

public class DataHandler {
    private Context con;
    private DBConnector dbCon;
    private SQLiteDatabase db;
    private int result;

    public DataHandler(Context con) {
        this.con = con;
    }

    public void openDB() {
        this.dbCon = new DBConnector(con);
        this.db = dbCon.getWritableDatabase();
    }

    //Admin
    public void Admin_Create(Admin admin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AdminTableFullName, admin.getFull_Name());
        contentValues.put(AdminTableEmail, admin.getEmail());
        contentValues.put(AdminTablePassword, admin.getPassword());
        contentValues.put(AdminTableConfirmPassword, admin.getConfirm_Password());
        db.insert(AdminTableName, null, contentValues);
    }

    //Add Site
    public void Add_Constraction_Site(Add_Site add_site) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstructionTableSiteName, add_site.getName());
        contentValues.put(ConstructionTableSiteSupervisorName, add_site.getSupervisorName());
        contentValues.put(ConstructionTableLocation, add_site.getLocation());
        db.insert(ConstructionTableName, null, contentValues);
    }

    //Add Supervisor
    public void Supervisor_create(Supervisor supervisor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SupervisorTableFullNAme, supervisor.getFull_Name());
        contentValues.put(SupervisorTableEmail, supervisor.getEmail());
        contentValues.put(SupervisorTablePassword, supervisor.getPassword());
        contentValues.put(SupervisorTableConfirmPassword, supervisor.getConfirm_Password());
        db.insert(SupervisorTableName, null, contentValues);
    }

    //Add Masonry
    public void Masonry_create(Masonry masonry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MasonryTableFullName, masonry.getFull_Name());
        contentValues.put(MasonryTableAddress, masonry.getAddress());
        contentValues.put(MasonryTableNic, masonry.getNic());
        contentValues.put(MasonryTableAge, masonry.getAge());
        contentValues.put(MasonryTableSupervisorName, masonry.getSupervisorName());
        db.insert(MasonryTableName, null, contentValues);
    }

    //Add Attendance
    public void Mark_Attendance(Mark_Attendance mark_attendance) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AttendanceTableFullName, mark_attendance.getName());
        contentValues.put(AttendanceAttende, mark_attendance.getAttendance());
        db.insert(AttendanceTableName, null, contentValues);
    }


    //Check Username already Used Administrator
    public boolean checkUsername(String fullname) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + AdminTableName + " WHERE fullname= '" + fullname + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //Check Username already Used Attendance
    public boolean checkAttendanceName(String name) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + AttendanceTableName + " WHERE Name= '" + name + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //Check Username already Used Supervisor
    public boolean checkUsernameSupervisor(String fname) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + SupervisorTableName + " WHERE fullname= '" + fname + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //Check Username already Used Masonry
    public boolean checkUsernameMasonry(String fullname) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + MasonryTableName + " WHERE fullname= '" + fullname + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //Check Sign In Record Database Admin
    public boolean checkUsernamePassword(String fullname, String pass) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + AdminTableName + " WHERE fullname=? and password=?", new String[]{fullname, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Check Sign In Record Database Supervisor
    public boolean checkUsernamePasswordSupervisor(String fullname, String pass) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + SupervisorTableName + " WHERE fullname=? and password=?", new String[]{fullname, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
