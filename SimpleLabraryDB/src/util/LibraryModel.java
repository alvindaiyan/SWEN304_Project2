package util;
/*
 * LibraryModel.java
 * Author:
 * Created on:
 */



import javax.swing.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class LibraryModel {

    // For use in creating dialogs and making them modal
    private JFrame dialogParent;
    private Connection con = null;
    
    
    public LibraryModel(JFrame parent, String userid, String password) {
    	//connect to database
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String url = "jdbc:postgresql:" + "//db.ecs.vuw.ac.nz/" + userid + "_jdbc";
    	
    	try {
			con = DriverManager.getConnection(url, userid, password);
		} catch (SQLException e) {
			// TODO cannot connect and print out the message
			e.printStackTrace();
		}
    	
    	
    	
    	
    	//System.out.println("I am tring to use github!!!");
    	dialogParent = parent;
    }

    public String bookLookup(int isbn) {
	return "Lookup Book Stub";
    }

    public String showCatalogue() {
	return "Show Catalogue Stub";
    }

    public String showLoanedBooks() {
	return "Show Loaned Books Stub";
    }

    public String showAuthor(int authorID) {
	return "Show Author Stub";
    }

    public String showAllAuthors() {
	return "Show All Authors Stub";
    }

    public String showCustomer(int customerID) {
	return "Show Customer Stub";
    }

    public String showAllCustomers() {
	return "Show All Customers Stub";
    }

    public String borrowBook(int isbn, int customerID,
			     int day, int month, int year) {
	return "Borrow Book Stub";
    }

    public String returnBook(int isbn, int customerid) {
	return "Return Book Stub";
    }

    public void closeDBConnection() {
    }
}
