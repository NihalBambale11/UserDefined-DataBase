package AWTXJDBC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class FormDB extends GUIForm    {
	Scanner scan = new Scanner(System.in);
	private final String USER="root";
	private final String PASSWORD="1220";
	private final String DBURL="jdbc:mysql://localhost:3306/Form?autoReconnect=true&useSSL=false";
	public int ch ,rows,cho;
	public String q;
	
	void getDBConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");        //Register Driver
			Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD); /// GetConnection To DB

		    try {
		    	allQueries();
		    } 
		    catch (Exception e) {
				e.printStackTrace();
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 

	}
	
	
void allQueries() throws Exception{
	
	System.out.println("         *********CHOICE QUERY*********           ");
	System.out.println();
	System.out.println(" 1.CREATE NEW TABLE\n 2.UPDATE QUERY\n 3.SELECT QUERY\n 4.DELETE QUERY");
	System.out.println();
	System.out.println("Enter Which Query you want To Execute ");
	int ch = scan.nextInt();

	Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);   ///Connection
	java.sql.Statement stmt = conn.createStatement();    /// SQL Statement

do {
	//System.out.println("");


	switch(ch) {
	case 1:
		scan.nextLine();
		System.out.println("Enter the SQL QUERY CareFully");
		q= scan.nextLine();
		
		stmt.executeLargeUpdate(q);
		System.out.println("Table Created");
		break;
	
	case 2: 
		scan.nextLine();
		System.out.println("Enter the Update Query Carefully ");
		q= scan.nextLine();
		System.out.println("Query Updated");
		
		stmt.executeLargeUpdate(q);
		break;
	
	case 3:
		scan.nextLine();
		System.out.println("Enter the Select Query Carefully ");
		q= scan.nextLine();
		
		ResultSet rs = stmt.executeQuery(q);
		//System.out.println("          FirstName          MiddleName          LastName          AGE          Gender          CollegeName               PersonalEmailID               MobileNumber           Courses                    ");
        
		//System.out.print("FirstName:    \nMiddleName:    ");
		int row=1;
		while(rs.next()) {
		  // System.out.println("|              "+rs.getString("First_Name")+"                 "+rs.getString("Middle_Name")+"          "+rs.getString("Last_Name")+"          "+rs.getString("AGE")+"          "+rs.getString("Gender")+"          "
		   //                  +rs.getString("College_Name")+"               "+rs.getString("Personal_Email_ID")+"               "+rs.getString("Mobile_Number")+"           "+rs.getString("Courses")+"                    ");
			System.out.println("   row = "+row);
			System.out.println("         "+rs.getString("First_Name")+"                  ");
			System.out.println("         "+rs.getString("Middle_Name")+"                 ");
			System.out.println("         "+rs.getString("Last_Name")+  "                 ");
			System.out.println("         "+rs.getString("AGE")+"                         ");
			System.out.println("         "+rs.getString("Gender")+"                      ");
			System.out.println("         "+rs.getString("College_Name")+"                ");
			System.out.println("         "+rs.getString("Personal_Email_ID")+"           ");
			System.out.println("         "+ rs.getString("Mobile_Number")+"              ");
			System.out.println("         "+rs.getString("Courses")+"                     ");
			System.out.println();
			System.out.println();
			
			row++;
	        
		}
		break;
		
	case 4:
		scan.nextLine();
		System.out.println("Enter the Delete QUERY ");
		q=scan.nextLine();
		stmt.executeUpdate(q);
		System.out.println("ROW DELETED");
	    break;
	}
  }while(cho<5);
}
	
	
   public static void main(String[] args) throws SQLException {
		
		//GUIForm gf = new GUIForm();
		
		FormDB  fdb = new FormDB();
	
        fdb.getDBConnection();
   	
   }
}

