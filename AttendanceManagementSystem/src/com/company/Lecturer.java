package com.company;

import java.util.ArrayList;

public class Lecturer {
    private int id;
    private String name;
    private String password;
    private ArrayList<Section> sections;

    public Lecturer(int id) {
        DatabaseOperations db = new DatabaseOperations();
        this.id = id;
        sections = db.getSectionsOfLecturer(this);
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
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }



}
