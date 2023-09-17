import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloFunnyGorilla {
	
	public static void main(String[] args) {
		
		System.out.println("------> Hello, FunnyGorilla!");
		
	    InputStreamReader in= new InputStreamReader(System.in);
	    BufferedReader input = new BufferedReader(in);
	    String str = "";
	 
	    System.out.print("Student ID: :");
	    int id = -1; // assign an initial value
        try {
			while ((str = input.readLine()) != null) {
			    
				//------- business logic
				id = Integer.valueOf(str);
				
				//-- if id 0, quit the program
				if (0== id) break;
			    
				String detail = getStudentDetail (id);
				
				System.out.println(detail);
				System.out.print("Student ID: ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        
        System.out.println("Good Bye, FunnyGorilla! See you next time!");
		
	}
	
	/**
	 * business logic processing
	 * @param id
	 * @return
	 */
	public static String getStudentDetail (int id) {
		
		String result = checkDB (id);
		
		// business logic
		result = result.toUpperCase();
		
		return result;
	}
	
	/**
	 * Data access logic
	 * @param id
	 * @return
	 */
	public static String checkDB (int studentID) {
		
		System.out.println("Checking information for student : " + studentID);
		
		String student = ""; // assign an empty value
		Connection con = null;
		Statement stmt = null;
		ResultSet rst = null;
		try{  
			// load MySQL JDBC driver 
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			// create a DB Connection with student-management DB, with user name and password 
			// JDBC Database connection string: jdbc:mysql://${host}:3306/${databaseName}
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","root");  
		    
		    // create a Statement;
			stmt = con.createStatement();  
			
			// execute a SQL query and return a ResultSet of query string
			rst = stmt.executeQuery("select * from Student where student_id = " + studentID + ";");  
			
			// fetch data from query result
			while(rst.next())  
			  student = rst.getInt(1)     // get Student ID
		 	    + " " + rst.getString(2)  // get First Name
			    + " " + rst.getString(3)  // get Last Name
			    + " " + rst.getString(4)  // get Contact
			    + " " + rst.getString(5); // get Course 
			}catch(Exception e){ 
				System.out.println(e);
		    }  
		    finally {
//			    try {
//			    	stmt.close();
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
		    }
		  System.out.println ("------> returned: " + student);
		  
		  return student;
	}

}
