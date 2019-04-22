package com.company;

import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private String password;
    private int attendance;
    private ArrayList<Section> sections;


    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //This constructor is for getting student dataset to display if he/she is NA.
    public Student(int id, String name, int attendance) {
        this.id = id;
        this.name = name;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Section> getSections() {
        DatabaseOperations db = new DatabaseOperations();
        sections = db.getSectionsOfStudent(this);
        return sections;
    }

    public int getAttendance() {
        return attendance;
    }
}

