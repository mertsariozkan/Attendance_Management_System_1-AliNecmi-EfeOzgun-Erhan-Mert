package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperations {

    private Connection connection;

    public DatabaseOperations() {
        connectToDatabase();
        prepareDatabase();

    }

    public void prepareDatabase() {
        String createLecturesTable = "CREATE TABLE IF NOT EXISTS Lectures (\n"
                + " lectureId integer primary key,\n"
                + " name text not null,\n"
                + " hours integer not null\n"
                + ");";
        String createSectionsTable = "CREATE TABLE IF NOT EXISTS Sections (\n"
                + " sectionId integer primary key,\n"
                + " lectureId integer not null,\n"
                + " lecturerId integer not null,\n"
                + " date text not null\n"
                + ");";
        String createLecturersTable = "CREATE TABLE IF NOT EXISTS Lecturers (\n"
                + " lecturerId integer primary key,\n"
                + " name text not null,\n"
                + " password text not null\n"
                + ");";
        String createStudentsTable = "CREATE TABLE IF NOT EXISTS Students (\n"
                + " studentId integer primary key,\n"
                + " name text not null,\n"
                + " password text not null\n"
                + ");";
        String createMessagesTable = "CREATE TABLE IF NOT EXISTS Messages (\n"
                + " id integer primary key,\n"
                + " sectionId integer not null,\n"
                + " title text not null,\n"
                + " message text not null,\n"
                + " date text not null\n"
                + ");";
        String createAttendancesTable = "CREATE TABLE IF NOT EXISTS Attendances (\n"
                + " id integer primary key,\n"
                + " sectionId integer not null,\n"
                + " studentId integer not null,\n"
                + " date text not null\n"
                + ");";
        String createEnrollmentsTable = "CREATE TABLE IF NOT EXISTS Enrollments (\n"
                + " id integer primary key,\n"
                + " sectionId integer not null,\n"
                + " studentId integer not null\n"
                + ");";
        String createAdminsTable = "CREATE TABLE IF NOT EXISTS Admins (\n"
                + " id integer primary key,\n"
                + " username text not null,\n"
                + " password text not null\n"
                + ");";
        try {
            connection.createStatement().execute(createAdminsTable);
            connection.createStatement().execute(createAdminsTable);
            connection.createStatement().execute(createAttendancesTable);
            connection.createStatement().execute(createEnrollmentsTable);
            connection.createStatement().execute(createLecturersTable);
            connection.createStatement().execute(createLecturesTable);
            connection.createStatement().execute(createMessagesTable);
            connection.createStatement().execute(createSectionsTable);
            connection.createStatement().execute(createStudentsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLecturer(int lecturerId, String name, String password) {
        String insertLecturer = "INSERT INTO Lecturers(lecturerId,name,password) VALUES ('" + lecturerId + "','" + name + "','" + password + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertLecturer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(int studentId, String name, String password) {
        String insertStudent = "INSERT INTO Students(studentId,name,password) VALUES ('" + studentId + "','" + name + "','" + password + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLecture(int lectureId, String name, String hours) {
        String insertLecture = "INSERT INTO Lectures(lectureId,name,hours) VALUES ('" + lectureId + "','" + name + "','" + hours + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertLecture);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSection(int lectureId, int lecturerId, String date) {
        String insertSection = "INSERT INTO Sections(lectureId,lecturerId,date) VALUES ('" + lectureId + "','" + lecturerId + "','" + date + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertSection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enrollStudent(int sectionId, int studentId) {
        String insertEnrollment = "INSERT INTO Enrollments(sectionId,studentId) VALUES ('" + sectionId + "','" + studentId + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertEnrollment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void takeAttendance(int sectionId, int studentId, String date) {
        String insertAttendance = "INSERT INTO Attendances(sectionId,studentId,date) VALUES ('" + sectionId + "','" + studentId + "','" + date + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertAttendance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExist(String userType, String userId, String password) {
        String sqlString = null;
        switch (userType) {
            case "Student":
                //Check if user exist
                sqlString = "SELECT * FROM Students WHERE studentId='"+userId+"' AND password='"+password+"';";
                break;
            case "Lecturer":
                //Check if user exist
                sqlString = "SELECT * FROM Lecturers WHERE lecturerId='"+userId+"' AND password='"+password+"';";
                break;
            case "Admin":
                //Check if user exist
                sqlString = "SELECT * FROM Admins WHERE username='"+userId+"' AND password='"+password+"';";
                break;
        }
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Section> getSectionsOfLecturer(Lecturer lecturer) {
        ArrayList<Section> sections = new ArrayList<>();
        String sqlString = "SELECT * FROM Sections WHERE lecturerId='"+lecturer.getId()+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            while(resultSet.next()) {
                Section section = new Section(resultSet.getInt("sectionId"),resultSet.getInt("lectureId"),lecturer,resultSet.getString("date"));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;

    }

    public ArrayList<Section> getSectionsOfStudent(Student student) {
        ArrayList<Section> sections = new ArrayList<>();
        String sqlString = "SELECT sectionId FROM Enrollments WHERE studentId='"+student.getId()+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            while(resultSet.next()) {
                Section section = getSection(resultSet.getInt("sectionId"));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;

    }

    public ArrayList<Student> getStudentsOfSection(Section section) {
        ArrayList<Student> students = new ArrayList<>();
        String sqlString = "SELECT studentId FROM Enrollments WHERE sectionId='"+section.getSectionId()+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            while(resultSet.next()) {
                Student student = getStudent(resultSet.getInt("studentId"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;

    }

    public Student getStudent(int studentId) {
        String sqlString = "SELECT * FROM Students WHERE studentId='"+studentId+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            if(resultSet.next()) {
                return new Student(resultSet.getInt("studentId"),resultSet.getString("name"),resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Section getSection(int sectionId) {
        String sqlString = "SELECT * FROM Sections WHERE sectionId='"+sectionId+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            if(resultSet.next()) {
                return new Section(resultSet.getInt("sectionId"),resultSet.getInt("lectureId"),new Lecturer(resultSet.getInt("lecturerId")),resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lecture getLecture(int lectureId) {
        String sqlString = "SELECT * FROM Lectures WHERE lectureId='"+lectureId+"';";
        ResultSet resultSet = getResultSet(sqlString);
        try{
            if(resultSet.next()) {
                return new Lecture(resultSet.getInt("lectureId"),resultSet.getString("name"),resultSet.getInt("hours"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMessage(int sectionId, String title, String content, String date) {
        String insertMessage = "INSERT INTO Messages(sectionId,title,message,date) VALUES ('" + sectionId + "','" + title + "','" + content + "','" + date + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public boolean connectToDatabase() {
        try {
            String connectionUrl = "jdbc:sqlite:database.db";
            connection = DriverManager.getConnection(connectionUrl);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getResultSet(String sqlString) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}