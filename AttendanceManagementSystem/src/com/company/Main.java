package com.company;

public class Main {

    public static void main(String[] args) {
        DatabaseOperations db = new DatabaseOperations();
        System.out.println(db.getStudentDepartment(1));
        //new DatabaseOperations(); //Accessing the database & its operations.
        //new InitialScreen(); 	  //Start app with initial screen, because first thing we want to do is to select which type of user we are.
    }
}


