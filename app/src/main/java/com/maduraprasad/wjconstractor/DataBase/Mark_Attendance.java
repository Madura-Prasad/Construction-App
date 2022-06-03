package com.maduraprasad.wjconstractor.DataBase;

public class Mark_Attendance {
    private String Name;
    private String Attendance;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAttendance() {
        return Attendance;
    }

    public void setAttendance(String attendance) {
        Attendance = attendance;
    }

    public Mark_Attendance(String name, String attendance) {
        Name = name;
        Attendance = attendance;
    }

    public Mark_Attendance() {
        Name = "";
        Attendance = "";
    }
}
