package util;
/*
 * LibraryModel.java
 * Author: Yan
 * Created on: postgreSql
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
			e.printStackTrace();
		} 
    	dialogParent = parent;
    }

    boolean bookLookup = false;
    public String bookLookup(int isbn) {    	
    	String result = "Book Lookup Result:\n";

    	try {
			Statement st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs = st.executeQuery("select * from book where book.isbn = " + isbn);
			ResultSet rsA = st2.executeQuery("select a.name, a.surname, " +
					"ba.authorseqno from book_author ba, " +
					"author a  where ba.isbn =" + isbn + "and a.authorid = ba.authorid " +
					"order by authorseqno;");
			if(rs.next()){
				result += "book id: " + rs.getInt(1) + "\nbook name: " 
						+ rs.getString("title") + "\nedition number: " + rs.getString("edition_no")
						+ "\nnumber of copy: " + rs.getString("numofcop")
						+ "\nnumber left: " + rs.getString("numleft");
				result += "\nauthors: \n";
				while(rsA.next())
					result += rsA.getInt("authorseqno") + ". " + rsA.getString("name") 
							+ rsA.getString("surname")+"\n";
				bookLookup = true;
			}else {bookLookup = false; result += "No Such Book";}	
			
			
		} catch (SQLException e) {
			int n = JOptionPane.showConfirmDialog(
				    dialogParent,
				    "Confirmation",
				    e.getMessage(),
				    JOptionPane.YES_NO_OPTION);
			
		}    	
    	
    	return result;
    }

    public String showCatalogue() {    		
    	String result = "Catalogue:\n";
    	boolean empty = true;
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from book");			
			
			while(rs.next()){
				result += "book id: " + rs.getInt(1) + "\nbook name: " 
						+ rs.getString("title") + "\nedition number: " + rs.getString("edition_no")
						+ "\nnumber of copy: " + rs.getString("numofcop")
						+ "\nnumber left: " + rs.getString("numleft")
						+ "\n=============================\n";
				empty = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return empty?result+"no such result":result;
    }

    public String showLoanedBooks() {
    	String result = "Loaned Books:\n";
    	boolean empty = true;
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select b.isbn, b.title, " +
						"b.edition_no, b.numofcop, numleft from book b, " +
						"cust_book c where c.isbn = b.isbn;");			
			
			
			while(rs.next()){
				result += "book isbn: " + rs.getInt(1) + "\nbook name: " 
						+ rs.getString("title") + "\nedition number: " + rs.getString("edition_no")
						+ "\nnumber of copy: " + rs.getString("numofcop")
						+ "\nnumber left: " + rs.getString("numleft")
						+ "\n=============================\n";
				empty = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return empty?result+"no Loaned Books":result;
    }

    public String showAuthor(int authorID) {
    	String result = "Auhtor Lookup Result:\n";
    	
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from author where author.authorid = " + 
						authorID);
			
			if(rs.next())
				result += "authorid: " + rs.getInt(1) + "\nauthor name: " 
						+ rs.getString("name") + rs.getString("surname") 
						+ "\n=============================\n";
			else result += "No Such Author";				
		} catch (SQLException e) {
			int n = JOptionPane.showConfirmDialog(
				    dialogParent,
				    "Confirmation",
				    e.getMessage(),
				    JOptionPane.YES_NO_OPTION);
		}    	
    	
    	return result;
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
			int n = JOptionPane.showConfirmDialog(
				    dialogParent,
				    "Confirmation",
				    e.getMessage(),
				    JOptionPane.YES_NO_OPTION);
			e.printStackTrace();
		}    	
    	
    	return result;
    }

    boolean findCustomer = false;
    public String showCustomer(int customerID) {
    	String result = "Customer Search Result:\n";
    	
    	findCustomer = false;
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer where customer.customer" +
					"id =" +customerID);
			
			if(rs.next()){
				result += "customer id: " + rs.getInt(1) + "\ncustomer name: " 
						+ rs.getString("f_name") + rs.getString("l_name") 
						+ "\ncity: " + rs.getString("city") 
						+ "\n=============================\n";
				findCustomer = true;
			} else{result += "No Such Customer";}
			
			
			
		} catch (SQLException e) {
			int n = JOptionPane.showConfirmDialog(
				    dialogParent,
				    "Confirmation",
				    e.getMessage(),
				    JOptionPane.YES_NO_OPTION);
			e.printStackTrace();
		}    	
    	
    	return result;
    }

    public String showAllCustomers() {
    	String result = "Customers List:\n";
    	
    	try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer");
			
			while(rs.next()){
				result += "customer id: " + rs.getInt(1) + "\ncustomer name: " 
						+ rs.getString("f_name") + rs.getString("l_name") 
						+ "\ncity: " + rs.getString("city") 
						+ "\n=============================\n";
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return result;
    }

    public String borrowBook(int isbn, int customerID, int day, int month, int year) {
    	
    	// identify customer by id
    	showCustomer(customerID);
    	if(!findCustomer){
    		return "No such customer";
    	}   	
    	
    	// identify the book
    	bookLookup(isbn);
    	if(!bookLookup){return "No such book";}
    	
    	// validate the copy left is bigger than 1
    	Statement smt;
    	int numLeft = -1;
		try {
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("select numleft from book where isbn = " + isbn + "for update");
	    	rs.next();
	    	numLeft = rs.getInt(1);
	    	if(numLeft < 1){
	    		return "No more availabe copy for book: " + isbn;
	    	}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(
				    dialogParent,
				    "Confirmation",
				    e.getMessage(),
				    JOptionPane.YES_NO_OPTION);
			e.printStackTrace();
		}
    	
    	// update the cust_book by insert a tuple
		try {			
			smt = con.createStatement();
			int rs = smt.executeUpdate( "insert into cust_book values (" + isbn +",date(\'"
					+ year + "-" + month + "-" + day + "\')," + customerID +")");

					
			if(rs == 0){
				return "Insert fail";
			}
		} catch (SQLException e1) {
			if(e1.getMessage().equals("ERROR: duplicate key value violates " +
					"unique constraint \"cust_book_pkey\""))
				return "You have borrowed the book.";
		}
		
		
		// update the book table by decrease the number left	
		try {
			// confirm panel
			int n = JOptionPane.showConfirmDialog(
					dialogParent,
					"Confirmation",
					"Blocked the tuple, Are you sure borrow this book?",
					JOptionPane.YES_NO_OPTION);
							
			if(n == 1){return "transaction cancelled";}
			smt = con.createStatement();
			int r = smt.executeUpdate("update book set numleft = "+ (numLeft - 1) +
					" where isbn = " + isbn);
			if(r == 0){return "update failed";}		
			
			return bookLookup(isbn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return "Not successfully update the database for borrow.";
    }

    public String returnBook(int isbn, int customerid) {
    	try {
		    	// identify the customerid
		    	showCustomer(customerid);
		    	if(!findCustomer){
		    		return "No such customer";
		    	}
		    	
		    	
		    	// identify the isbn and validate whether the book is borrowed by the customer
		    	bookLookup(isbn);
		    	if(!bookLookup){return "No such book";}
		    	
		    	Statement smt;
				
					smt = con.createStatement();
					ResultSet rs = smt.executeQuery("select * from cust_book where isbn =" +
			    			 isbn + " and customerid = " +
			    			customerid + "for update");
					if(!rs.next()){return "You did not borrow this book!";}
		
	    	//synchronized(the book){
		    	// delete the book from cust_book 
		    	smt = con.createStatement();
		    	int result = smt.executeUpdate("delete from cust_book where isbn = " + isbn  + " and customerid = " + customerid);
		    	if(result == 0){ return "delete from the cust_book table fail";}
		    	
		    	// update the book table by increase the numleft
		    	smt = con.createStatement();
				ResultSet rsforNum = smt.executeQuery("select numleft from book where isbn = " + isbn);
		    	rsforNum.next();
		    	int numLeft = rsforNum.getInt(1);
		
		    	smt = con.createStatement();
				int rst = smt.executeUpdate("update book set numleft = "+ (numLeft + 1) +
						" where isbn = " + isbn);
				
				if(rst == 0){
					return "update fail";
				}
	    	//}
    	} catch (SQLException e) {
			e.printStackTrace();
				return "transaction failed";
		}    	
    	return "Return Book Successfully";
    }

    public void closeDBConnection() {
    	try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
