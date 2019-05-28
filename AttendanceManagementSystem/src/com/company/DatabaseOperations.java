package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseOperations {

    private Connection connection;

    public DatabaseOperations() {
        connectToDatabase();
        prepareDatabase();
        closeConnection();

    }

    public void prepareDatabase() {
        String createLecturesTable = "CREATE TABLE IF NOT EXISTS Lectures (\n"
                + " lectureId integer primary key,\n"
                + " name text not null,\n"
                + " hours integer not null,\n"
                + " maxAttendance integer not null\n"
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
                + " password text not null,\n"
                + " department text not null,\n"
                + " class integer not null\n"
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
                + " date text not null,\n"
                + " sectionId integer not null,\n"
                + " studentId integer not null,\n"
                + " hour integer not null,\n"
                + " isAttend integer not null\n"
                + ");";
        String createEnrollmentsTable = "CREATE TABLE IF NOT EXISTS Enrollments (\n"
                + " id integer primary key,\n"
                + " sectionId integer not null,\n"
                + " studentId integer not null,\n"
                + " attendance integer not null\n"
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
        connectToDatabase();
        String insertLecturer = "INSERT INTO Lecturers(lecturerId,name,password) VALUES ('" + lecturerId + "','" + name + "','" + password + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertLecturer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addStudent(int studentId, String name, String password, String department, int class_) {
        connectToDatabase();
        String insertStudent = "INSERT INTO Students(studentId,name,password,department,class) VALUES ('" + studentId + "','" + name + "','" + password + "','" + department + "','" + class_ + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addLecture(int lectureId, String name, String hours, int maxAttendance) {
        connectToDatabase();
        String insertLecture = "INSERT INTO Lectures(lectureId,name,hours,maxAttendance) VALUES ('" + lectureId + "','" + name + "','" + hours + "','" + maxAttendance + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertLecture);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addSection(int sectionId, int lectureId, int lecturerId, String date) {
        connectToDatabase();
        String insertSection = "INSERT INTO Sections(sectionId,lectureId,lecturerId,date) VALUES ('" + sectionId + "','" + lectureId + "','" + lecturerId + "','" + date + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertSection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void enrollStudent(int sectionId, int studentId) {
        connectToDatabase();
        String insertEnrollment = "INSERT INTO Enrollments(sectionId,studentId,attendance) VALUES ('" + sectionId + "','" + studentId + "','0')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertEnrollment);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateAttendanceOfStudent(int studentId, int sectionId, int numberOfAbsentHours) {
        connectToDatabase();
        String updateAttendance = "UPDATE Enrollments SET attendance=attendance+" + numberOfAbsentHours + " WHERE studentId='" + studentId + "' AND sectionId='" + sectionId + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(updateAttendance);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public ArrayList<String> getPastAttendanceDates(int sectionId) {
        connectToDatabase();
        ArrayList<String> dates = new ArrayList<>();
        String sqlString = "SELECT DISTINCT date FROM Attendances WHERE sectionId=" + sectionId;
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                dates.add(resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dates;
    }

    public boolean isStudentAttendedAt(int studentId, int sectionId, String date, int hour) {
        connectToDatabase();
        String sqlString = "SELECT isAttend FROM Attendances WHERE studentId='" + studentId + "' AND sectionId='" + sectionId + "' AND date='" + date + "' AND hour='" + hour + "'";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return resultSet.getInt("isAttend") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public void takeAttendance(int sectionId, int studentId, String date, int hour, int isAttend) {
        connectToDatabase();
        String insertAttendance = "INSERT INTO Attendances(sectionId,studentId,date,hour,isAttend) VALUES ('" + sectionId + "','" + studentId + "','" + date + "','" + hour + "','" + isAttend + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertAttendance);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateAttendance(int sectionId, int studentId, String date, int hour, int isAttend) {
        boolean isAttendBeforeUpdate = isStudentAttendedAt(studentId, sectionId, date, hour);
        boolean isAttendNow = (isAttend == 1);
        if (isAttendBeforeUpdate != isAttendNow) {
            connectToDatabase();
            String updateAttendanceString = "UPDATE Attendances SET isAttend=" + isAttend + " WHERE studentId='" + studentId + "' AND sectionId='" + sectionId + "' AND date='" + date + "' AND hour='" + hour + "'";
            Statement statement;
            try {
                statement = connection.createStatement();
                statement.execute(updateAttendanceString);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
            if (isAttendNow) {
                //decrease attendance by 1
                updateAttendanceOfStudent(studentId, sectionId, -1);
            } else {
                //increase attendance by 1
                updateAttendanceOfStudent(studentId, sectionId, 1);
            }
        }

    }

    public boolean isAttendanceTaken(int sectionId, String date) {
        connectToDatabase();
        String sqlString = "SELECT EXISTS(SELECT 1 FROM Attendances WHERE date='" + date + "' AND sectionId='" + sectionId + "')";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.getBoolean(1)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<Student> getNaStudents(int sectionId) {
        ArrayList<Student> naStudents = new ArrayList<>();
        String sqlString = "SELECT * FROM Enrollments WHERE sectionId='" + sectionId + "' AND attendance>='" + getLecture(getSection(sectionId).getLectureId()).getMaxAttendance() + "'";
        connectToDatabase();
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                naStudents.add(getNaStudent(resultSet.getInt("studentId"), resultSet.getInt("attendance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return naStudents;
    }

    public boolean isUserExist(String userType, String userId, String password) {
        connectToDatabase();
        String sqlString = null;
        switch (userType) {
            case "Student":
                //Check if user exist
                sqlString = "SELECT * FROM Students WHERE studentId='" + userId + "' AND password='" + password + "';";
                break;
            case "Lecturer":
                //Check if user exist
                sqlString = "SELECT * FROM Lecturers WHERE lecturerId='" + userId + "' AND password='" + password + "';";
                break;
            case "Admin":
                //Check if user exist
                sqlString = "SELECT * FROM Admins WHERE username='" + userId + "' AND password='" + password + "';";
                break;
        }
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<Student> getAllStudents() {
        connectToDatabase();
        ArrayList<Student> students = new ArrayList<>();
        String sqlString = "SELECT * FROM Students;";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("studentId"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("department"), resultSet.getInt("class"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return students;
    }

    public ArrayList<Lecturer> getAllLecturers() {
        connectToDatabase();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        String sqlString = "SELECT * FROM Lecturers;";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Lecturer lecturer = new Lecturer(resultSet.getInt("lecturerId"), resultSet.getString("name"));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lecturers;
    }

    public ArrayList<Lecture> getAllLectures() {
        connectToDatabase();
        ArrayList<Lecture> lectures = new ArrayList<>();
        String sqlString = "SELECT * FROM Lectures;";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Lecture lecture = new Lecture(resultSet.getInt("lectureId"), resultSet.getString("name"), resultSet.getInt("hours"), resultSet.getInt("maxAttendance"));
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lectures;
    }

    public boolean deleteLecturer(Lecturer lecturer) {
        connectToDatabase();
        if (lecturer.getSections().isEmpty()) {
            String sqlStr = "DELETE FROM Lecturers WHERE lecturerId='" + lecturer.getId() + "'";
            Statement statement;
            try {
                statement = connection.createStatement();
                statement.execute(sqlStr);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
                return true;
            }
        } else {
            return false;
        }

    }

    public boolean deleteStudent(Student student) {
        connectToDatabase();
        if (student.getSections().isEmpty()) {
            String sqlStr = "DELETE FROM Students WHERE studentId='" + student.getId() + "'";
            Statement statement;
            try {
                statement = connection.createStatement();
                statement.execute(sqlStr);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
                return true;
            }
        } else {
            return false;
        }
    }

    public void deleteSection(Section section) {
        connectToDatabase();
        String sqlStr = "DELETE FROM Sections WHERE sectionId='" + section.getSectionId() + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(sqlStr);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        connectToDatabase();
        String sqlStr2 = "DELETE FROM Enrollments WHERE sectionId='" + section.getSectionId() + "'";
        Statement statement2;
        try {
            statement2 = connection.createStatement();
            statement2.execute(sqlStr2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        connectToDatabase();
        String sqlStr3 = "DELETE FROM Attendances WHERE sectionId='" + section.getSectionId() + "'";
        Statement statement3;
        try {
            statement3 = connection.createStatement();
            statement3.execute(sqlStr3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public boolean deleteLecture(Lecture lecture) {
        connectToDatabase();
        if (lecture.getSections().isEmpty()) {
            String sqlStr = "DELETE FROM Lectures WHERE lectureId='" + lecture.getLectureId() + "'";
            Statement statement;
            try {
                statement = connection.createStatement();
                statement.execute(sqlStr);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
                return true;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Section> getSectionsOfLecturer(Lecturer lecturer) {
        connectToDatabase();
        ArrayList<Section> sections = new ArrayList<>();
        String sqlString = "SELECT * FROM Sections WHERE lecturerId='" + lecturer.getId() + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Section section = new Section(resultSet.getInt("sectionId"), resultSet.getInt("lectureId"), lecturer, resultSet.getString("date"));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sections;

    }

    public ArrayList<Section> getSectionsOfLecture(Lecture lecture) {
        connectToDatabase();
        ArrayList<Section> sections = new ArrayList<>();
        String sqlString = "SELECT * FROM Sections WHERE lectureId='" + lecture.getLectureId() + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Section section = new Section(resultSet.getInt("sectionId"), resultSet.getInt("lectureId"), new Lecturer(resultSet.getInt("lecturerId")), resultSet.getString("date"));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sections;

    }

    public ArrayList<Section> getSectionsOfStudent(Student student) {
        connectToDatabase();
        ArrayList<Section> sections = new ArrayList<>();
        String sqlString = "SELECT sectionId FROM Enrollments WHERE studentId='" + student.getId() + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Section section = getSection(resultSet.getInt("sectionId"));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sections;

    }

    public ArrayList<Student> getStudentsOfSection(Section section) {
        connectToDatabase();
        ArrayList<Student> students = new ArrayList<>();
        String sqlString = "SELECT studentId FROM Enrollments WHERE sectionId='" + section.getSectionId() + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            while (resultSet.next()) {
                Student student = getStudent(resultSet.getInt("studentId"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return students;

    }

    public Student getStudent(int studentId) {
        connectToDatabase();
        String sqlString = "SELECT * FROM Students WHERE studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return new Student(resultSet.getInt("studentId"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("department"), resultSet.getInt("class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public Student getNaStudent(int studentId, int attendance) {
        connectToDatabase();
        String sqlString = "SELECT * FROM Students WHERE studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return new Student(resultSet.getInt("studentId"), resultSet.getString("name"), attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public Section getSection(int sectionId) {
        connectToDatabase();
        String sqlString = "SELECT * FROM Sections WHERE sectionId='" + sectionId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return new Section(resultSet.getInt("sectionId"), resultSet.getInt("lectureId"), new Lecturer(resultSet.getInt("lecturerId")), resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public Lecture getLecture(int lectureId) {
        connectToDatabase();
        String sqlString = "SELECT * FROM Lectures WHERE lectureId='" + lectureId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return new Lecture(resultSet.getInt("lectureId"), resultSet.getString("name"), resultSet.getInt("hours"), resultSet.getInt("maxAttendance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public void sendMessage(int sectionId, String title, String content, String date) {
        connectToDatabase();
        String insertMessage = "INSERT INTO Messages(sectionId,title,message,date) VALUES ('" + sectionId + "','" + title + "','" + content + "','" + date + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(insertMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
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


    public String getStudentName(int studentId) {
        connectToDatabase();
        String sqlString = "SELECT name FROM Students WHERE studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public String getStudentDepartment(int studentId) {
        connectToDatabase();
        String sqlString = "SELECT department FROM Students WHERE studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public String getStudentClass(int studentId) {
        connectToDatabase();
        String sqlString = "SELECT class FROM Students WHERE studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(sqlString);
        try {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public ArrayList<Message> getMessage(int studentId) {
        connectToDatabase();
        ArrayList<Message> messages = new ArrayList<>();
        String getMessageTitleQuery = "Select Messages.id,Messages.sectionId,Messages.message,Messages.title,Messages.date,Lecturers.name From Messages,Enrollments,Students,Sections,Lecturers where Enrollments.sectionId = Messages.sectionId AND Enrollments.sectionID = Sections.sectionId AND Sections.lectureId = Lecturers.lecturerId AND Enrollments.studentId = Students.studentId AND Students.studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(getMessageTitleQuery);
        try {
            while (resultSet.next()) {
                Message message = new Message(resultSet.getInt("id"), resultSet.getInt("sectionId"), resultSet.getString("title"), resultSet.getString("message"), resultSet.getString("date"), resultSet.getString("name"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return messages;
    }

    public ArrayList<Lecture> getLectureNames(int studentId) {
        connectToDatabase();
        ArrayList<Lecture> lectureNames = new ArrayList<>();
        String getCourseNamesQuery = "Select Lectures.name,Lectures.lectureId,Lectures.hours,Lectures.maxAttendance From Sections,Enrollments,Students,Lectures Where Sections.lectureId=Lectures.lectureId AND Sections.sectionId = Enrollments.sectionId AND Enrollments.studentId=Students.studentId AND Students.studentId='" + studentId + "';";
        ResultSet resultSet = getResultSet(getCourseNamesQuery);
        try {
            while (resultSet.next()) {
                Lecture lecture = new Lecture(resultSet.getInt("lectureId"), resultSet.getString("name"), resultSet.getInt("hours"), resultSet.getInt("maxAttendance"));
                lectureNames.add(lecture);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lectureNames;
    }

    public Attendance getAttendanceList(int studentId,int sectionId) {
        connectToDatabase();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> hours = new ArrayList<>();

        String getAttendanceQuery = "Select * from Attendances Where studentId="+studentId+" AND sectionId='" + sectionId + "' AND isAttend=0;";
        ResultSet resultSet = getResultSet(getAttendanceQuery);
        boolean isExists = false;
        try {
            while (resultSet.next()) {
                for(int i=0;i<dates.size();i++) {
                    if(dates.get(i).equals(resultSet.getString("date"))) {
                        hours.set(i,hours.get(i)+1);
                        isExists = true;
                    }
                }
                if(!isExists) {
                    dates.add(resultSet.getString("date"));
                    hours.add(1);
                }
                isExists = false;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return new Attendance(dates,hours);
    }

    public ArrayList<String> getAbsenteeismDates(int studentId, int lectureId) {
        connectToDatabase();
        ArrayList<String> dates = new ArrayList<>();
        String getAbsenteeismDatesQuery = "Select Attendances.date From Students,Attendances,Lectures,Sections Where Students.studentId=Attendances.studentId AND Lectures.lectureId=Sections.lectureId AND Attendances.sectionId=Sections.sectionId AND Students.studentId='" + studentId + "'AND Lectures.lectureId='" + lectureId + "'";
        ResultSet resultSet = getResultSet(getAbsenteeismDatesQuery);
        try {
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                dates.add(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dates;
    }

}