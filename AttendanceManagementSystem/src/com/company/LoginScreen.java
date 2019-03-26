package com.company;

import javax.swing.*;

public class LoginScreen extends JFrame {

    public LoginScreen(String userType) {
        setSize(300,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Attendance Management System");
        label.setBounds(50,20,300,100);

        JLabel idLabel = new JLabel("Id: ");
        JTextField idField = new JTextField();

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();

        idLabel.setBounds(getSize().width/4,100,100,30);
        idField.setBounds(getSize().width/2,100,100,30);
        passwordLabel.setBounds(getSize().width/4,150,100,30);
        passwordField.setBounds(getSize().width/2,150,100,30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(getSize().width/3,200,100,50);
        loginButton.addActionListener(e -> {
            switch (userType) {
                case "Student":
                    //Student login
                    break;
                case "Lecturer":
                    //Lecturer login
                    setVisible(false);
                    new LecturerConsole();
                    break;
                case "Admin":
                    //Admin login
                    break;
            }
        });


        add(label);
        add(idLabel);
        add(idField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        setVisible(true);
    }
}
