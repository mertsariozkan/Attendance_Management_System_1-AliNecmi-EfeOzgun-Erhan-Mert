package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.company.LecturerConsole.BooleanTableModel;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentConsole extends JFrame {
    public StudentConsole() {
        setSize(900,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,getWidth()/3,getHeight());
        leftPanel.setLayout(null);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(leftPanel.getWidth(),0,getWidth()*2/3,getHeight());
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        ArrayList<String> messagesArrayList = new ArrayList<>();
        messagesArrayList.add("Hello todays class is cancelled.");
        

        String[] messagesArray = messagesArrayList.toArray(String[]::new);

        JList messageList = new JList(messagesArray);
        messageList.setBounds(0,0,leftPanel.getWidth()*2,leftPanel.getHeight());
        messageList.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        ArrayList<String> sections = new ArrayList<>();
        sections.add("Student's Name: ");
        sections.add("Student's ID: ");
        sections.add("Student's Department: ");
        sections.add("Student's Class: "); // From Database

        String[] sectionsArray = sections.toArray(String[]::new);

        JList list = new JList(sectionsArray);
        list.setBounds(0,0,leftPanel.getWidth(),leftPanel.getHeight()/2);
        list.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        JButton newAttendanceButton = new JButton();
        newAttendanceButton.setIcon(new ImageIcon("icons/pencil.png"));
        newAttendanceButton.setBounds(50,400,30,30);
        newAttendanceButton.setToolTipText("Check Grades");
        newAttendanceButton.addActionListener(e -> {
            rightPanel.removeAll();

            

            ScrollPane attendancePane = new ScrollPane();
            attendancePane.setBounds(0,10,rightPanel.getWidth(),400);

            JTable table = new JTable(new BooleanTableModel());
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10,25,rightPanel.getWidth()-20,rightPanel.getHeight()-100);

            
            rightPanel.add(scrollPane);
            repaint();

        });
        JButton messageButton = new JButton();
        messageButton.setIcon(new ImageIcon("icons/message.png"));
        messageButton.setBounds(100,400,30,30);
        messageButton.setToolTipText("Check Messages");
        messageButton.addActionListener(e -> {
            rightPanel.removeAll();
            String[][] messages = {
                    { "Title: CourseBook, Sender: Ahmet, Date: 2019/3/10"},
                    { "Title: Course, Sender: Ali, Date: 2019/6/10"}
            };
            String[] columnNames = { "Received Messages" };
            
            JTable messageTable = new JTable(messages, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            messageTable.setRowHeight(20);
            messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            messageTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth());
            JScrollPane scrollPane = new JScrollPane(messageTable);
            scrollPane.setBounds(10,10,rightPanel.getWidth()-20,rightPanel.getHeight()-80);

            JButton checkMessageButton = new JButton("Check");
            checkMessageButton.setBounds(5,rightPanel.getHeight()-70,100,30);
            checkMessageButton.addActionListener(e1 -> {
                rightPanel.removeAll();
                JLabel selectedMessage = new JLabel("Sender: "); //senderName from database
                selectedMessage.setBounds(10,5,300,20);

         
                rightPanel.add(messageList);
                rightPanel.repaint();
            });

            rightPanel.add(scrollPane);
            rightPanel.add(checkMessageButton);
            repaint();
        });
            
            

        
        JButton editButton = new JButton();
        editButton.setIcon(new ImageIcon("icons/edit.png"));
        editButton.setBounds(150,400,30,30);
        editButton.setToolTipText("Check Attendances");
        editButton.addActionListener(e -> {
            rightPanel.removeAll();
            String[][] courseName = {
                    { "SE 311"},
                    { "SE 375"},
                    { "CE 303"}
            };
            String[] columnNames = { "Lectures"};
            JTable attendanceTable = new JTable(courseName, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            attendanceTable.setRowHeight(20);
            attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            attendanceTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth());
            JScrollPane scrollPane = new JScrollPane(attendanceTable);
            scrollPane.setBounds(10,10,rightPanel.getWidth()-20,rightPanel.getHeight()-80);

            JButton DetailedAttendanceButton = new JButton("Detailed Attendance");
            DetailedAttendanceButton.setBounds(5,rightPanel.getHeight()-70,175,30);
            DetailedAttendanceButton.addActionListener(e1 -> {
                rightPanel.removeAll();
                JLabel attendanceTitle = new JLabel("Attendance Status: ");
                attendanceTitle.setBounds(10,5,300,20);

                JTable table = new JTable(new AttendanceTableModel());
                JScrollPane scrollPane1 = new JScrollPane(table);
                scrollPane1.setBounds(10,25,rightPanel.getWidth()-20,rightPanel.getHeight()-100);

              
                rightPanel.add(attendanceTitle);
                rightPanel.add(scrollPane1);
                rightPanel.repaint();
            });

            rightPanel.add(scrollPane);
            rightPanel.add(DetailedAttendanceButton);
            repaint();
        });

           
        JButton naButton = new JButton();
        naButton.setIcon(new ImageIcon("icons/na.png"));
        naButton.setBounds(200,400,30,30);
        naButton.setToolTipText("Check Fee Status");
        naButton.addActionListener(e -> {
            rightPanel.removeAll();
            String[][] naStudents = {
                    { "2019-1", "11437.50", "11437.50" },
                    { "2019-2", "11437.50", "0" }
            };
            String[] columnNames = { "Year-Semester", "Debt-Amount", "Paid" };
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
    
    class AttendanceTableModel extends AbstractTableModel {
        String[] columns = {"Date", "Lecture1" , "Lecture2" };
        Object[][] data = {
                {"2019/3/10", "X" , "X" },
                {"2019/6/10", "X" , " " },
                
        };
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
    }
    
    class MessageTableModel extends AbstractTableModel {
        String[] columns = {"SenderName", "Message"};
        Object[][] data = {
                {"Ali", "senin sddd"},
                {"Veli", "hello"},
                
        };
        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return columns.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    }
    class BooleanTableModel extends AbstractTableModel {
        String[] columns = {"Course ID", "1.Quiz", "Midterm", "Final"};
        Object[][] data = {
                {"SE 311", "80", "66" , "45"},
                {"SE 375", "95","55" , "78"},
                {"CE 303", "75", "69", "56"},
                {"ENG 310", "100","45" , "23" },
                {"GER 301", "55", "87", "99" }
        };
        

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


