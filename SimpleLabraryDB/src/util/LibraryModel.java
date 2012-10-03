package util;
/*
 * LibraryModel.java
 * Author:
 * Created on:
 */



import javax.swing.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
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
    	
    	
    	String result = "=============================\nBook Lookup Result:\n";
    	
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from book where book.isbn = " + isbn);
			
			while(rs.next()){
				result += "book id: " + rs.getInt(1) + "\nbook name: " 
						+ rs.getString("title") + "\nedition number: " + rs.getString("edition_no")
						+ "\nnumber of copy: " + rs.getString("numofcop")
						+ "\nnumber left: " + rs.getString("numleft")
						+ "\n=============================\n";
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return result;
    }

    public String showCatalogue() {
    		
    	String result = "Catalogue:\n";
    	
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from book");
			
			while(rs.next()){
				result += "book id: " + rs.getInt(1) + "\nbook name: " 
						+ rs.getString("title") + "\nedition number: " + rs.getString("edition_no")
						+ "\nnumber of copy: " + rs.getString("numofcop")
						+ "\nnumber left: " + rs.getString("numleft")
						+ "\n=============================\n";
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return result;
    }

    public String showLoanedBooks() {
	return "Show Loaned Books Stub";
    }

    public String showAuthor(int authorID) {
	return "Show Author Stub";
    }

    public String showAllAuthors() {
    	
    	String result = "Authors List:\n";
    	
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from author");
			
			while(rs.next()){
				result += "authorid: " + rs.getInt(1) + "\nauthor name: " 
						+ rs.getString("name") + rs.getString("surname") 
						+ "\n=============================\n";
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return result;
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
