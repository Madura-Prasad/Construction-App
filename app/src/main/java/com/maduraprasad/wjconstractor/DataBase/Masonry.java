package com.maduraprasad.wjconstractor.DataBase;

public class Masonry {
    private String Full_Name;
    private String Address;
    private String Nic;
    private String Age;
    private String SupervisorName;

    public Masonry(String full_Name, String address, String nic, String age, String supervisorName) {
        Full_Name = full_Name;
        Address = address;
        Nic = nic;
        Age = age;
        SupervisorName = supervisorName;
    }

    public Masonry() {
        Full_Name = "";
        Address = "";
        Nic = "";
        Age = "";
        SupervisorName = "";
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSupervisorName() {
        return SupervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        SupervisorName = supervisorName;
    }
}
