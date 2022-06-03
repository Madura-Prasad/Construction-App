package com.maduraprasad.wjconstractor.DataBase;

public class Supervisor {
    private String Full_Name;
    private String Email;
    private String Password;
    private String Confirm_Password;

    public Supervisor(String full_Name, String email, String password, String confirm_Password) {
        Full_Name = full_Name;
        Email = email;
        Password = password;
        Confirm_Password = confirm_Password;
    }

    public Supervisor() {
        Full_Name = "";
        Email = "";
        Password = "";
        Confirm_Password = "";
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirm_Password() {
        return Confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        Confirm_Password = confirm_Password;
    }
}
