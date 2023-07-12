package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tara
 * @see https://www.baeldung.com/java-connect-mysql
 * @see https://www.tutorialspoint.com/jdbc/jdbc-update-records.htm#
 */
public class DBInteraction {

	public Connection conn = null;

	public DBInteraction() {
		super();
		String connectionUrl = "jdbc:mysql://localhost:3306/shipcarte?serverTimezone=UTC";
		try {
			conn = DriverManager.getConnection(connectionUrl, "root", "pass");
		} catch (SQLException e) {
			// handle the exception
		}
	}

	/**
	 * 
	 * @param select_sql
	 * @return
	 * select * from employee
	 */
	public ResultSet Retrieve(String select_sql) {
		ResultSet rs = null;
		try {
			System.out.println("Selecting records from the table..."); 
			System.out.println("SQL: "+ select_sql);
			PreparedStatement ps = this.conn.prepareStatement(select_sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
	}
	
	/**
	 * 
	 * @param update_sql
	 * @return 
	 * UPDATE employee SET age = 3 WHERE first in ('gt')
	 */
	public int Update(String update_sql) {
		// Open a connection
	      try {
	    	 System.out.println("Updating records in the table..."); 
	    	 System.out.println("SQL: "+ update_sql);
	         Statement stmt = this.conn.createStatement();
	         return stmt.executeUpdate(update_sql);
	      } catch (SQLException e) {
	    	 e.printStackTrace();
	         return -1;
	      } 
	}
	
	/**
	 * 
	 * @param insert_sql
	 * @return
	 * insert into employee (first, last, age, address, city, state) values ('gt', 'ght', 7, '78 rat Nest','Hazard', 'ajax');
	 */
	public int Create(String insert_sql) {
		// Open a connection
	      try {
	    	 System.out.println("Inserting records in the table..."); 
	    	 System.out.println("SQL: "+ insert_sql);
	    	 Statement stmt = this.conn.createStatement();
	    	 return stmt.executeUpdate(insert_sql);
	      } catch (SQLException e) {
	    	 e.printStackTrace();
	         return -1;
	      } 
	}
	
	/**
	 * 
	 * @param delete_sql
	 * @return
	 * delete from employee where age = 7
	 */
	public int Delete(String delete_sql) {
		// Open a connection
	      try {
	    	 System.out.println("deleting records from the table..."); 
	    	 System.out.println("SQL: "+ delete_sql);
	    	 Statement stmt = this.conn.createStatement();
	    	 return stmt.executeUpdate(delete_sql);
	      } catch (SQLException e) {
	    	 e.printStackTrace();
	         return -1;
	      } 
	}
	
	public void closeConn() {
		try {
			if(!this.conn.isClosed()) {
			this.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testmysql() {
		String sqlSelectAllPersons = "SELECT * FROM employee";
		String connectionUrl = "jdbc:mysql://localhost:3306/shipcarte?serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "pass");
				PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String name = rs.getString("first");
				String lastName = rs.getString("last");
				System.out.println(name + " " + lastName);
				// do something with the extracted data...
			}
		} catch (SQLException e) {
			// handle the exception
		}
	}

	public static void main(String[] args) throws SQLException {
		testmysql();
	}
}
