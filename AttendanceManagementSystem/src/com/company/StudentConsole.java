package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.company.LecturerConsole.BooleanTableModel;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class contains UI for student console
 *
 * **/


public class StudentConsole extends JFrame {
	
    DatabaseOperations db;
    public StudentConsole(Student student) {
    	
    	db = new DatabaseOperations();
        setSize(900,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,getWidth()/3,getHeight());
        leftPanel.setLayout(null);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        //Right of frame
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(leftPanel.getWidth(),0,getWidth()*2/3,getHeight());
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));
        
        


        ArrayList<String> sections = new ArrayList<>();
        sections.add("Student's Name	  : " + db.getStudentName(student.getId()));
        sections.add("Student's ID		  : " + student.getId());
        sections.add("Student's Department: " + db.getStudentDepartment(student.getId()));
        sections.add("Student's Class	  : " + db.getStudentClass(student.getId())); 

        
        
        String[] sectionsArray = sections.toArray(String[]::new);

        JList list = new JList(sectionsArray);
        list.setBounds(0,0,leftPanel.getWidth(),leftPanel.getHeight()/2);
        list.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        
        JButton messageButton = new JButton();
        messageButton.setIcon(new ImageIcon("icons/message.png"));
        messageButton.setBounds(100,400,30,30);
        messageButton.setToolTipText("Check Messages");
        messageButton.addActionListener(e -> {
            rightPanel.removeAll();
          
            // getting message title from database
            ArrayList<Message> UserMessages = db.getMessage(student.getId());                
            String [][] messageTitles = new String[UserMessages.size()][1];
            
            for (int i = 0; i < UserMessages.size(); i++) {
            	messageTitles[i][0] = "Sender : "+UserMessages.get(i).getSender()+ " Title : "+UserMessages.get(i).getTitle()+" Date :"+UserMessages.get(i).getDate();
            }
            String[] columnNames = { "Received Messages" };
            
            JTable messageTable = new JTable(messageTitles, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
        
           ArrayList<String> messagesArrayList = new ArrayList<>();
           for(int i = 0;i<UserMessages.size();i++) { 
             messagesArrayList.add(UserMessages.get(i).getMessage());
           }
            messageTable.setRowHeight(20);
            messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            messageTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth());
            JScrollPane scrollPane = new JScrollPane(messageTable);
            scrollPane.setBounds(10,10,rightPanel.getWidth()-20,rightPanel.getHeight()-80);

            JButton checkMessageButton = new JButton("Check");
			checkMessageButton.setBounds(5, rightPanel.getHeight() - 70, 100, 30);
			checkMessageButton.addActionListener(e1 -> {

				JTextPane messageList = new JTextPane();
				messageList.setText(UserMessages.get(messageTable.getSelectedRow()).getMessage());
				messageList.setBounds(0, 0, leftPanel.getWidth() * 2, leftPanel.getHeight());
				messageList.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
				rightPanel.removeAll();
				JLabel selectedMessage = new JLabel();
				selectedMessage.setBounds(10, 5, 300, 20);

				rightPanel.add(messageList);
				rightPanel.repaint();

			});

            rightPanel.add(scrollPane);
            rightPanel.add(checkMessageButton);
            repaint();
        });
            
            

        
        JButton checkAttendanceButton = new JButton();
        checkAttendanceButton.setIcon(new ImageIcon("icons/edit.png"));
        checkAttendanceButton.setBounds(150,400,30,30);
        checkAttendanceButton.setToolTipText("Check Attendances");
        checkAttendanceButton.addActionListener(e -> {
            rightPanel.removeAll();
            ArrayList<Section> sections1 = db.getSectionsOfStudent(student);
            String[][] courseName = new String[sections1.size()][1];
            for (int i = 0; i < sections1.size(); i++) {
            	courseName[i][0] = sections1.get(i).getLecture().getName();
            }
            
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
            	   Attendance attendance = db.getAttendanceList(student.getId(),sections1.get(attendanceTable.getSelectedRow()).getSectionId());
                   String[][] absenteeismArray = new String[attendance.getDates().size()][2];
                   for (int i = 0; i < attendance.getDates().size(); i++) {
                	   absenteeismArray[i][0] = attendance.getDates().get(i);
                       absenteeismArray[i][1] = String.valueOf(attendance.getHours().get(i));

                   }

                   String[] columnNames1 = {"Date ", "Absenteeism" };
                   JTable naTable = new JTable(absenteeismArray, columnNames1);
                   naTable.setRowHeight(20);
                   naTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                   naTable.getColumnModel().getColumn(0).setMinWidth(rightPanel.getWidth() / 2);
                   naTable.getColumnModel().getColumn(1).setMinWidth(rightPanel.getWidth() / 2);
                   JScrollPane scrollPane1 = new JScrollPane(naTable);
                   scrollPane1.setBounds(0, 0, rightPanel.getWidth(), rightPanel.getHeight());
                   rightPanel.add(scrollPane1);
                   repaint();
                   
            });
            rightPanel.add(DetailedAttendanceButton);
            rightPanel.add(scrollPane);
        //    rightPanel.add(DetailedAttendanceButton);
            repaint();
        });

 

        leftPanel.add(list);
        leftPanel.add(messageButton);
        leftPanel.add(checkAttendanceButton);


        add(leftPanel);
        add(rightPanel);
        setVisible(true);
    }
    

}