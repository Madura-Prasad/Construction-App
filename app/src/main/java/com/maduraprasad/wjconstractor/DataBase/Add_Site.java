package com.maduraprasad.wjconstractor.DataBase;

public class Add_Site {
    private String Name;
    private String SupervisorName;
    private String Location;

    public Add_Site(String name, String supervisorName, String location) {
        Name = name;
        SupervisorName = supervisorName;
        Location = location;
    }

    public Add_Site() {
        Name = "";
        SupervisorName = "";
        Location = "";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSupervisorName() {
        return SupervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        SupervisorName = supervisorName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
