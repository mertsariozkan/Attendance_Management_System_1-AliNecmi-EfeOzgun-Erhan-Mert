package com.company;

import java.util.ArrayList;

//Lecture Class which provides the core elements and details of a lecture.
public class Lecture {

    private int lectureId;
    private String name;
    private int hours;
    private int maxAttendance;
    private ArrayList<Section> sections;

    public Lecture(int lectureId, String name, int hours, int maxAttendance) { //Lecture Constructor.
        this.lectureId = lectureId;
        this.name = name;
        this.hours = hours;
        this.maxAttendance = maxAttendance;
    }

    //Setters and Getters.
    
    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public ArrayList<Section> getSections() {
        DatabaseOperations db = new DatabaseOperations();
        sections = db.getSectionsOfLecture(this);
        return sections;
    }
}
