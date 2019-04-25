package com.company;

import java.util.ArrayList;

//Section Class which provides the core elements and details of a section.
public class Section {
    private int sectionId;
    private int lectureId;
    private Lecturer lecturer;
    private Lecture lecture;
    private String date;
    private ArrayList<Student> students; 

    public Section(int sectionId, int lectureId, Lecturer lecturer, String date) { //Section Constructor.
        this.sectionId = sectionId;
        this.lectureId = lectureId;
        this.lecturer = lecturer;
        this.date = date;
    }

    public Section(int sectionId) {
        this.sectionId = sectionId;
    }
    
    //Setters and Getters.
    
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Student> getStudents() { //Students which are bound to that specific section.
        DatabaseOperations db = new DatabaseOperations();
        students = db.getStudentsOfSection(this);
        return students;
    }

    public Lecture getLecture() {
        DatabaseOperations db = new DatabaseOperations();
        lecture = db.getLecture(getLectureId());
        return lecture;
    }
}
