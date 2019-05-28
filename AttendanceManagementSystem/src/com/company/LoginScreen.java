package com.company;

import javax.swing.*;


/**
 * This class contains UI for login screen
 * which user enters his ID and password informations.
 * In addition, user type is received in constructor,
 * so this user type info will be used when searching Users database.
 *
 * Later, we will create database connection for required functionality.
 *
 *
 * **/



public class LoginScreen extends JFrame {

    public LoginScreen(String userType) {
        setSize(300,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
     // center the jframe on screen
        setLocationRelativeTo(null);

        DatabaseOperations db = new DatabaseOperations();

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
            if(db.isUserExist(userType,idField.getText(),passwordField.getText())) {
                setVisible(false);
                switch (userType) {
                    case "Student":
                        //Student login
                        new StudentConsole(new Student(Integer.parseInt(idField.getText())));
                        break;
                    case "Lecturer":
                        //Lecturer login
                        new LecturerConsole(new Lecturer(Integer.parseInt(idField.getText())));
                        break;
                    case "Admin":
                        //Admin login
                        new AdminConsole();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this,"Your information is incorrect.","Warning",JOptionPane.WARNING_MESSAGE);
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
