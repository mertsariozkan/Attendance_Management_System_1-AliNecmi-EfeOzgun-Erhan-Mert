package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains UI for initial screen
 * which user will be able to select what type
 * of user he/she is
 * **/


public class InitialScreen extends JFrame {

    public InitialScreen() {
        setSize(300,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
     // center the jframe on screen
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Attendance Management System");
        label.setBounds(50,20,300,100);

        JButton studentLogin = new JButton("Student Login");
        JButton lecturerLogin = new JButton("Lecturer Login");
        JButton adminLogin = new JButton("Admin Login");

        studentLogin.setBounds(getSize().width/4,100,150,50);
        lecturerLogin.setBounds(getSize().width/4,200,150,50);
        adminLogin.setBounds(getSize().width/4,300,150,50);

        studentLogin.addActionListener(e -> {
            setVisible(false);
            new LoginScreen("Student");
        });

        lecturerLogin.addActionListener(e -> {
            setVisible(false);
            new LoginScreen("Lecturer");
        });

        adminLogin.addActionListener(e -> {
            setVisible(false);
            new LoginScreen("Admin");
        });

        add(label);
        add(studentLogin);
        add(lecturerLogin);
        add(adminLogin);

        setVisible(true);


    }

}
