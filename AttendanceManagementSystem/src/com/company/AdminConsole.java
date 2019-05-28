package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminConsole extends JFrame {

    DatabaseOperations db;

    public AdminConsole() {
        db = new DatabaseOperations();
        setSize(900, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, getWidth(), getHeight() / 10);
        topPanel.setLayout(null);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, topPanel.getHeight(), getWidth(), getHeight() - topPanel.getHeight());
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));


        JButton lecturerOperationsButton = new JButton("Lecturer Operations");
        lecturerOperationsButton.setBounds(0, 0, topPanel.getWidth() / 3, topPanel.getHeight());
        lecturerOperationsButton.addActionListener(e -> {
            mainPanel.removeAll();

            ArrayList<Lecturer> lecturersList = db.getAllLecturers();
            String[][] lecturers = new String[lecturersList.size()][2];
            for (int i = 0; i < lecturersList.size(); i++) {
                lecturers[i][0] = String.valueOf(lecturersList.get(i).getId());
                lecturers[i][1] = lecturersList.get(i).getName();
            }
            String[] columnNames = {"Lecturer Id", "Lecturer Name"};
            JTable lecturerTable = new JTable(lecturers, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            lecturerTable.setRowHeight(20);
            lecturerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            lecturerTable.getColumnModel().getColumn(0).setPreferredWidth(mainPanel.getWidth() / 7);
            lecturerTable.getColumnModel().getColumn(1).setPreferredWidth(mainPanel.getWidth() * 5 / 6);
            JScrollPane scrollPane = new JScrollPane(lecturerTable);
            scrollPane.setBounds(10, 10, mainPanel.getWidth() - 20, mainPanel.getHeight() - 80);

            JButton addLecturerButton = new JButton("Create New Lecturer");
            addLecturerButton.setBounds(5, mainPanel.getHeight() - 70, 300, 30);
            addLecturerButton.addActionListener(e1 -> {
                //Add new lecturer
                mainPanel.removeAll();

                JLabel idLabel = new JLabel("Lecturer Id: ");
                idLabel.setBounds(100, 50, 150, 30);
                JTextField idField = new JTextField();
                idField.setBounds(300, 50, 150, 30);
                JLabel nameLabel = new JLabel("Lecturer Name: ");
                nameLabel.setBounds(100, 100, 150, 30);
                JTextField nameField = new JTextField();
                nameField.setBounds(300, 100, 150, 30);
                JLabel passwordLabel = new JLabel("Lecturer Password: ");
                passwordLabel.setBounds(100, 150, 150, 30);
                JTextField passwordField = new JTextField();
                passwordField.setBounds(300, 150, 150, 30);
                JButton addButton = new JButton("Add Lecturer");
                addButton.setBounds(100, 200, 150, 30);
                addButton.addActionListener(e2 -> {
                    db.addLecturer(Integer.parseInt(idField.getText()), nameField.getText(), passwordField.getText());
                    JOptionPane.showMessageDialog(this, "Lecturer added successfully.");
                });
                mainPanel.add(idLabel);
                mainPanel.add(idField);
                mainPanel.add(nameLabel);
                mainPanel.add(nameField);
                mainPanel.add(passwordLabel);
                mainPanel.add(passwordField);
                mainPanel.add(addButton);
                mainPanel.repaint();
            });

            JButton deleteLecturerButton = new JButton("Delete Selected Lecturer");
            deleteLecturerButton.setBounds(300, mainPanel.getHeight() - 70, 300, 30);
            deleteLecturerButton.addActionListener(e1 -> {
                //Delete selected lecturer and it's all data like sections
                if (db.deleteLecturer(lecturersList.get(lecturerTable.getSelectedRow()))) {
                    JOptionPane.showMessageDialog(this, "Lecturer is deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Lecturer can not be deleted because of current sections.");
                }

            });

            mainPanel.add(scrollPane);
            mainPanel.add(addLecturerButton);
            mainPanel.add(deleteLecturerButton);

            mainPanel.repaint();
        });


        JButton studentOperationsButton = new JButton("Student Operations");
        studentOperationsButton.setBounds(topPanel.getWidth() / 3, 0, topPanel.getWidth() / 3, topPanel.getHeight());
        studentOperationsButton.addActionListener(e -> {
            mainPanel.removeAll();

            ArrayList<Student> studentsList = db.getAllStudents();
            String[][] students = new String[studentsList.size()][4];
            for (int i = 0; i < studentsList.size(); i++) {
                students[i][0] = String.valueOf(studentsList.get(i).getId());
                students[i][1] = studentsList.get(i).getName();
                students[i][2] = studentsList.get(i).getDepartment();
                students[i][3] = String.valueOf(studentsList.get(i).getClass_());

            }
            String[] columnNames = {"Student Id", "Student Name", "Department", "Class"};
            JTable studentTable = new JTable(students, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            studentTable.setRowHeight(20);
            studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            studentTable.getColumnModel().getColumn(0).setPreferredWidth(mainPanel.getWidth() / 4);
            studentTable.getColumnModel().getColumn(1).setPreferredWidth(mainPanel.getWidth() / 4);
            studentTable.getColumnModel().getColumn(2).setPreferredWidth(mainPanel.getWidth() / 4);
            studentTable.getColumnModel().getColumn(3).setPreferredWidth(mainPanel.getWidth() / 4);

            JScrollPane scrollPane = new JScrollPane(studentTable);
            scrollPane.setBounds(10, 10, mainPanel.getWidth() - 20, mainPanel.getHeight() - 80);

            JButton addStudentButton = new JButton("Create New Student");
            addStudentButton.setBounds(5, mainPanel.getHeight() - 70, 300, 30);
            addStudentButton.addActionListener(e1 -> {
                //Add new lecturer
                mainPanel.removeAll();

                JLabel idLabel = new JLabel("Student Id: ");
                idLabel.setBounds(100, 50, 150, 30);
                JTextField idField = new JTextField();
                idField.setBounds(300, 50, 150, 30);
                JLabel nameLabel = new JLabel("Student Name: ");
                nameLabel.setBounds(100, 100, 150, 30);
                JTextField nameField = new JTextField();
                nameField.setBounds(300, 100, 150, 30);
                JLabel passwordLabel = new JLabel("Student Password: ");
                passwordLabel.setBounds(100, 150, 150, 30);
                JTextField passwordField = new JTextField();
                passwordField.setBounds(300, 150, 150, 30);
                JLabel depLabel = new JLabel("Department: ");
                depLabel.setBounds(100, 200, 150, 30);
                JTextField depField = new JTextField();
                depField.setBounds(300, 200, 150, 30);
                JLabel classLabel = new JLabel("Class: ");
                classLabel.setBounds(100, 250, 150, 30);
                JTextField classField = new JTextField();
                classField.setBounds(300, 250, 150, 30);
                JButton addButton = new JButton("Add Student");
                addButton.setBounds(100, 300, 150, 30);
                addButton.addActionListener(e2 -> {
                    db.addStudent(Integer.parseInt(idField.getText()), nameField.getText(), passwordField.getText(), depField.getText(), Integer.parseInt(classField.getText()));
                    JOptionPane.showMessageDialog(this, "Student added successfully.");
                });
                mainPanel.add(idLabel);
                mainPanel.add(idField);
                mainPanel.add(nameLabel);
                mainPanel.add(nameField);
                mainPanel.add(passwordLabel);
                mainPanel.add(passwordField);
                mainPanel.add(depLabel);
                mainPanel.add(depField);
                mainPanel.add(classField);
                mainPanel.add(classLabel);
                mainPanel.add(addButton);
                mainPanel.repaint();
            });

            JButton deleteStudentButton = new JButton("Delete Selected Student");
            deleteStudentButton.setBounds(300, mainPanel.getHeight() - 70, 300, 30);
            deleteStudentButton.addActionListener(e1 -> {
                //Delete selected student
                if (db.deleteStudent(studentsList.get(studentTable.getSelectedRow()))) {
                    JOptionPane.showMessageDialog(this, "Student is deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Student can not be deleted because of current sections.");
                }

            });

            mainPanel.add(scrollPane);
            mainPanel.add(addStudentButton);
            mainPanel.add(deleteStudentButton);

            mainPanel.repaint();
        });


        JButton lectureOperationsButton = new JButton("Lecture Operations");
        lectureOperationsButton.setBounds(topPanel.getWidth() * 2 / 3, 0, topPanel.getWidth() / 3, topPanel.getHeight());
        lectureOperationsButton.addActionListener(e -> {
            mainPanel.removeAll();

            JPanel leftPanel = new JPanel();
            leftPanel.setBounds(0,0,mainPanel.getWidth()/2,mainPanel.getHeight());
            JPanel rightPanel = new JPanel();
            rightPanel.setBounds(mainPanel.getWidth()/2,0,mainPanel.getWidth()/2,mainPanel.getHeight());

            ArrayList<Lecture> lectureList = db.getAllLectures();
            String [][] lectures = new String[lectureList.size()][4];
            for (int i = 0; i < lectureList.size(); i++) {
                lectures[i][0] = String.valueOf(lectureList.get(i).getLectureId());
                lectures[i][1] = lectureList.get(i).getName();
                lectures[i][2] = String.valueOf(lectureList.get(i).getHours());
                lectures[i][3] = String.valueOf(lectureList.get(i).getMaxAttendance());

            }
            String[] columnNames = {"Lecture Id","Lecture Name","Number of Hours","Attendance Limit"};
            JTable lectureTable = new JTable(lectures, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            lectureTable.setRowHeight(20);
            lectureTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            lectureTable.getColumnModel().getColumn(0).setPreferredWidth(leftPanel.getWidth()/4);
            lectureTable.getColumnModel().getColumn(1).setPreferredWidth(leftPanel.getWidth()/4);
            lectureTable.getColumnModel().getColumn(2).setPreferredWidth(leftPanel.getWidth()/4);
            lectureTable.getColumnModel().getColumn(3).setPreferredWidth(leftPanel.getWidth()/4);

            JScrollPane scrollPane = new JScrollPane(lectureTable);
            scrollPane.setBounds(10, 10, leftPanel.getWidth() - 20, leftPanel.getHeight() - 120);

            JButton getSectionsButton = new JButton("Get Sections ");
            getSectionsButton.setBounds(5,leftPanel.getHeight()-100,200,30);
            getSectionsButton.addActionListener(e1 -> {
                rightPanel.removeAll();

                ArrayList<Section> sectionList = db.getSectionsOfLecture(lectureList.get(lectureTable.getSelectedRow()));
                String [][] sections = new String[sectionList.size()][3];
                for (int i = 0; i < sectionList.size(); i++) {
                    sections[i][0] = String.valueOf(sectionList.get(i).getSectionId());
                    sections[i][1] = String.valueOf(sectionList.get(i).getLecturer().getId());
                    sections[i][2] = String.valueOf(sectionList.get(i).getDate());

                }
                String[] columnNames1 = {"Section Id","Lecturer Id","Date"};
                JTable sectionTable = new JTable(sections, columnNames1) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                sectionTable.setRowHeight(20);
                sectionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                sectionTable.getColumnModel().getColumn(0).setPreferredWidth(rightPanel.getWidth()/3);
                sectionTable.getColumnModel().getColumn(1).setPreferredWidth(rightPanel.getWidth()/3);
                sectionTable.getColumnModel().getColumn(2).setPreferredWidth(rightPanel.getWidth()/3);

                JScrollPane scrollPane1 = new JScrollPane(sectionTable);
                scrollPane1.setBounds(10, 10, rightPanel.getWidth() - 20, rightPanel.getHeight() - 120);

                JButton addSectionButton = new JButton("Create New Section");
                addSectionButton.setBounds(5, rightPanel.getHeight() - 70, 200, 30);
                addSectionButton.addActionListener(e2 -> {
                    //Add new lecturer
                    mainPanel.removeAll();

                    ArrayList<Integer> studentIds = new ArrayList<>();

                    JLabel sectionLabel = new JLabel("Section Id: ");
                    sectionLabel.setBounds(100,50,150,30);
                    JTextField sectionField = new JTextField();
                    sectionField.setBounds(300,50,150,30);
                    JLabel idLabel = new JLabel("Lecturer Id: ");
                    idLabel.setBounds(100,100,150,30);
                    JTextField idField = new JTextField();
                    idField.setBounds(300,100,150,30);
                    JLabel dateLabel = new JLabel("Date: ");
                    dateLabel.setBounds(100,150,150,30);
                    JTextField dateField = new JTextField();
                    dateField.setBounds(300,150,150,30);

                    JLabel studentLabel = new JLabel("Student Id: ");
                    studentLabel.setBounds(100,200,150,30);
                    JTextField studentField = new JTextField();
                    studentField.setBounds(300,200,150,30);

                    JButton enrollStudentButton = new JButton("Enroll Student");
                    enrollStudentButton.setBounds(500,200,150,30);
                    enrollStudentButton.addActionListener(e3 -> {
                        studentIds.add(Integer.valueOf(studentField.getText()));
                        JOptionPane.showMessageDialog(this,"Student enrolled.");
                    });

                    JButton addButton = new JButton("Add Section");
                    addButton.setBounds(100,250,150,30);
                    addButton.addActionListener(e3 -> {
                        db.addSection(Integer.parseInt(sectionField.getText()),lectureList.get(lectureTable.getSelectedRow()).getLectureId(), Integer.parseInt(idField.getText()),dateField.getText());
                        for(int id : studentIds) {
                            db.enrollStudent(Integer.parseInt(sectionField.getText()),id);
                        }
                        JOptionPane.showMessageDialog(this,"Section added successfully.");
                    });
                    mainPanel.add(idLabel);
                    mainPanel.add(idField);
                    mainPanel.add(dateField);
                    mainPanel.add(dateLabel);
                    mainPanel.add(sectionField);
                    mainPanel.add(sectionLabel);
                    mainPanel.add(studentField);
                    mainPanel.add(studentLabel);
                    mainPanel.add(enrollStudentButton);
                    mainPanel.add(addButton);
                    mainPanel.repaint();
                });

                JButton deleteSectionButton = new JButton("Delete Selected Section");
                deleteSectionButton.setBounds(200, rightPanel.getHeight() - 70, 200, 30);
                deleteSectionButton.addActionListener(e2 -> {
                    //Delete selected student
                    db.deleteSection(sectionList.get(sectionTable.getSelectedRow()));
                    JOptionPane.showMessageDialog(this,"Section is deleted successfully.");

                });

                rightPanel.add(scrollPane1);
                rightPanel.add(addSectionButton);
                rightPanel.add(deleteSectionButton);

                rightPanel.repaint();
            });

            JButton addLectureButton = new JButton("Create New Lecture");
            addLectureButton.setBounds(5, leftPanel.getHeight() - 70, 200, 30);
            addLectureButton.addActionListener(e1 -> {
                //Add new lecturer
                mainPanel.removeAll();

                JLabel idLabel = new JLabel("Lecture Id: ");
                idLabel.setBounds(100,50,150,30);
                JTextField idField = new JTextField();
                idField.setBounds(300,50,150,30);
                JLabel nameLabel = new JLabel("Lecture Name: ");
                nameLabel.setBounds(100,100,150,30);
                JTextField nameField = new JTextField();
                nameField.setBounds(300,100,150,30);
                JLabel hourLabel = new JLabel("Number of Hours: ");
                hourLabel.setBounds(100,150,150,30);
                JTextField hourField = new JTextField();
                hourField.setBounds(300,150,150,30);
                JLabel attLabel = new JLabel("Attendance Limit: ");
                attLabel.setBounds(100,200,150,30);
                JTextField attField = new JTextField();
                attField.setBounds(300,200,150,30);
                JButton addButton = new JButton("Add Lecture");
                addButton.setBounds(100,250,150,30);
                addButton.addActionListener(e2 -> {
                    db.addLecture(Integer.parseInt(idField.getText()),nameField.getText(),hourField.getText(), Integer.parseInt(attField.getText()));
                    JOptionPane.showMessageDialog(this,"Lecture added successfully.");
                });
                mainPanel.add(idLabel);
                mainPanel.add(idField);
                mainPanel.add(nameLabel);
                mainPanel.add(nameField);
                mainPanel.add(hourField);
                mainPanel.add(hourLabel);
                mainPanel.add(attField);
                mainPanel.add(attLabel);
                mainPanel.add(addButton);
                mainPanel.repaint();
            });

            JButton deleteLectureButton = new JButton("Delete Selected Lecture");
            deleteLectureButton.setBounds(200, mainPanel.getHeight() - 70, 200, 30);
            deleteLectureButton.addActionListener(e1 -> {
                //Delete selected student
                if(db.deleteLecture(lectureList.get(lectureTable.getSelectedRow()))) {
                    JOptionPane.showMessageDialog(this,"Lecture is deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this,"Lecture can not be deleted because of current sections.");
                }

            });

            leftPanel.add(scrollPane);
            leftPanel.add(getSectionsButton);
            leftPanel.add(addLectureButton);
            leftPanel.add(deleteLectureButton);

            mainPanel.add(leftPanel);
            mainPanel.add(rightPanel);
            mainPanel.repaint();
        });


        topPanel.add(lecturerOperationsButton);
        topPanel.add(studentOperationsButton);
        topPanel.add(lectureOperationsButton);

        add(topPanel);
        add(mainPanel);
        setVisible(true);

    }

}
