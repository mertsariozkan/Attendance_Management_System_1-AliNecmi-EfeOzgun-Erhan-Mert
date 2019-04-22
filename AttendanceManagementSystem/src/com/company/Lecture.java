package com.company;

public class Lecture {

    private int lectureId;
    private String name;
    private int hours;
    private int maxAttendance;

    public Lecture(int lectureId, String name, int hours, int maxAttendance) {
        this.lectureId = lectureId;
        this.name = name;
        this.hours = hours;
        this.maxAttendance = maxAttendance;
    }

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
}
