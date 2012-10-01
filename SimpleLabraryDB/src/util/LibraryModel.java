package util;
/*
 * LibraryModel.java
 * Author:
 * Created on:
 */



import javax.swing.*;

public class LibraryModel {

    // For use in creating dialogs and making them modal
    private JFrame dialogParent;

    public LibraryModel(JFrame parent, String userid, String password) {
    	//connect to database
    	System.out.println("I am tring to use github!!!");
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
