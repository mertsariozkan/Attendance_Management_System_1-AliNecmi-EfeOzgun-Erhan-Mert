package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class contains UI for lecturer console
 *
 * **/

public class LecturerConsole extends JFrame {

    DatabaseOperations db;
    public LecturerConsole(Lecturer lecturer) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String currentDate = dtf.format(LocalDate.now());


        db = new DatabaseOperations();
        setSize(900,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


        // Left of frame is set for sections that current lecturer teaches, and buttons for repaint right of frame.
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,getWidth()/3,getHeight());
        leftPanel.setLayout(null);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black,3));

        //Right of frame
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(leftPanel.getWidth(),0,getWidth()*2/3,getHeight());
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black,3));



        //get sections of lecturer in a list
        ArrayList<String> sectionNames = new ArrayList<>();
        for(Section s : lecturer.getSections()) {
            sectionNames.add(String.valueOf(s.getSectionId()));
        }
        String[] sectionsArray = sectionNames.toArray(String[]::new);


        JList list = new JList(sectionsArray);
        list.setBounds(0,0,leftPanel.getWidth(),leftPanel.getHeight()/2);
        list.setBorder(BorderFactory.createLineBorder(Color.black,3));

        //New Attendance button for taking current date's attendance
        JButton newAttendanceButton = new JButton();
        newAttendanceButton.setIcon(new ImageIcon("icons/pencil.png"));
        newAttendanceButton.setBounds(50,400,30,30);
        newAttendanceButton.setToolTipText("Take attendance");
        newAttendanceButton.addActionListener(e -> {
            rightPanel.removeAll();

            JLabel dateLabel = new JLabel("Date: "+currentDate);
            dateLabel.setBounds(10,5,300,20);

            ScrollPane studentPane = new ScrollPane();
            studentPane.setBounds(0,10,rightPanel.getWidth(),400);

            JTable table = new JTable(new BooleanTableModel(lecturer.getSections().get(list.getSelectedIndex())));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10,25,rightPanel.getWidth()-20,rightPanel.getHeight()-100);

            JButton submitButton = new JButton("Save Attendance");
            submitButton.setBounds(5, rightPanel.getHeight()-70,300,30);
            submitButton.addActionListener(e1 -> {
                //Save attendance
            });

            rightPanel.add(dateLabel);
            rightPanel.add(scrollPane);
            rightPanel.add(submitButton);
            repaint();

        });

        //Message button is for sending messages to enrolled students
        JButton messageButton = new JButton();
        messageButton.setIcon(new ImageIcon("icons/message.png"));
        messageButton.setBounds(100,400,30,30);
        messageButton.setToolTipText("Send new message to registered students");
        messageButton.addActionListener(e -> {
            JLabel titleLabel = new JLabel("Title: ");
            titleLabel.setBounds(20,50,70,30);
            JTextField titleField = new JTextField();
            titleField.setBounds(100,50,150,30);
            JLabel contentLabel = new JLabel("Content: ");
            contentLabel.setBounds(20,100,70,30);
            JTextArea contentArea = new JTextArea();
            contentArea.setBounds(20,150,rightPanel.getWidth()-40,200);
            JButton sendButton = new JButton("Send message");
            sendButton.setBounds(20,400,150,30);

            sendButton.addActionListener(e1 -> {
                //Send message
                db.sendMessage(lecturer.getSections().get(list.getSelectedIndex()).getSectionId(),titleField.getText(),contentArea.getText(),currentDate);
            });
            rightPanel.removeAll();
            rightPanel.add(titleLabel);
            rightPanel.add(titleField);
            rightPanel.add(contentLabel);
            rightPanel.add(contentArea);
            rightPanel.add(sendButton);
            repaint();

        });

        //Edit button for editing previous attendances
        JButton editButton = new JButton();
        editButton.setIcon(new ImageIcon("icons/edit.png"));
        editButton.setBounds(150,400,30,30);
        editButton.setToolTipText("Edit attendances");
        editButton.addActionListener(e -> {
            rightPanel.removeAll();
            String[][] dates = {
                    { "2019/3/10"},
                    { "2019/3/16"}
            };
            String[] columnNames = { "Past lecture dates" };
            JTable dateTable = new JTable(dates, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            dateTable.setRowHeight(20);
            dateTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            dateTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth());
            JScrollPane scrollPane = new JScrollPane(dateTable);
            scrollPane.setBounds(10,10,rightPanel.getWidth()-20,rightPanel.getHeight()-80);

            JButton editSelectedDateButton = new JButton("Edit");
            editSelectedDateButton.setBounds(5,rightPanel.getHeight()-70,100,30);
            editSelectedDateButton.addActionListener(e1 -> {
                rightPanel.removeAll();
                JLabel selectedDate = new JLabel("Date: "+dates[dateTable.getSelectedRow()][0]);
                selectedDate.setBounds(10,5,300,20);

                JTable table = new JTable(new BooleanTableModel(lecturer.getSections().get(list.getSelectedIndex())));
                JScrollPane scrollPane1 = new JScrollPane(table);
                scrollPane1.setBounds(10,25,rightPanel.getWidth()-20,rightPanel.getHeight()-100);

                JButton submitButton = new JButton("Save Attendance");
                submitButton.setBounds(5, rightPanel.getHeight()-70,300,30);
                submitButton.addActionListener(e2 -> {
                    //Save attendance
                });

                rightPanel.add(selectedDate);
                rightPanel.add(scrollPane1);
                rightPanel.add(submitButton);
                rightPanel.repaint();
            });

            rightPanel.add(scrollPane);
            rightPanel.add(editSelectedDateButton);
            repaint();
        });

        //NA Button is for displaying NA students in selected section
        JButton naButton = new JButton();
        naButton.setIcon(new ImageIcon("icons/na.png"));
        naButton.setBounds(200,400,30,30);
        naButton.setToolTipText("Show N/A students");
        naButton.addActionListener(e -> {
            rightPanel.removeAll();
            String[][] naStudents = {
                    { "20150601041", "Mert Sariozkan", "20" },
                    { "20150601042", "Erhan Sahan", "18" }
            };
            String[] columnNames = { "Student Id", "Student Name", "Absenteeism" };
            JTable naTable = new JTable(naStudents, columnNames);
            naTable.setRowHeight(20);
            naTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            naTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth()/3);
            naTable.getColumnModel().getColumn(1).setMinWidth(rightPanel.getWidth()/3);
            naTable.getColumnModel().getColumn(2).setMinWidth(rightPanel.getWidth()/3);
            JScrollPane scrollPane = new JScrollPane(naTable);
            scrollPane.setBounds(0,0,rightPanel.getWidth(),rightPanel.getHeight());
            rightPanel.add(scrollPane);
            repaint();

        });


        leftPanel.add(list);
        leftPanel.add(newAttendanceButton);
        leftPanel.add(messageButton);
        leftPanel.add(editButton);
        leftPanel.add(naButton);


        add(leftPanel);
        add(rightPanel);
        setVisible(true);
    }


    //This class required for creating table in right frame
    class BooleanTableModel extends AbstractTableModel {
        private Lecture lecture;
        private DatabaseOperations db;
        private String[] columns;
        private Object[][] data;

        public BooleanTableModel(Section section) {
            super();
            db = new DatabaseOperations();
            lecture = db.getLecture(section.getLectureId());
            columns = new String[lecture.getHours()+2];
            columns[0] = "Student Id";
            columns[1] = "Student Name";
            for(int i=2;i<lecture.getHours()+2;i++) {
                columns[i] = i+". Hour";
            }

            ArrayList<Student> students = db.getStudentsOfSection(section);

            data = new Object[students.size()][4];
            for(int i=0;i<students.size();i++) {
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = Boolean.FALSE;
                data[i][3] = Boolean.FALSE;
            }
        }


        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return columns.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        // This method is used by the JTable to define the default
        // renderer or editor for each cell. For example if you have
        // a boolean data it will be rendered as a check box. A
        // number value is right aligned.
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return data[0][columnIndex].getClass();
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return false;
                case 1:
                    return false;
                default:
                    return true;
            }
        }
    }

}


