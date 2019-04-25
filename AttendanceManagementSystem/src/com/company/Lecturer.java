package com.company;

import java.util.ArrayList;

//Lecturer Class which provides the core elements and details of a lecturer.
public class Lecturer {
    private int id;
    private String name;
    private String password;
    private ArrayList<Section> sections;

    public Lecturer(int id) { //Lecturer Constructor.
        this.id = id;
    }

    //Setters and Getters.
    
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
        sections = db.getSectionsOfLecturer(this);
        return sections;
    }


}
