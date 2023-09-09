import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloFunnyGorilla {
	
	public static void main(String[] args) {
		
		System.out.println("Hello, FunnyGorilla!");
		
	    InputStreamReader in= new InputStreamReader(System.in);
	    BufferedReader input = new BufferedReader(in);
	    String str;
	 
	    System.out.print("Student ID: :");
	    int id = -1;
        try {
			while ((str = input.readLine()) != null) {
			    
				//------- business logic
				id = Integer.valueOf(str);
			    
				String detail = getStudentDetail (id);
				
				System.out.println(detail);
				System.out.print("Student ID: ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
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
		
		System.out.println("-----> ID: " + studentID);
		
		String student = "Funny Gorilla";
		Connection con = null;
		Statement stmt = null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			//here studentmanagement is database name, root is username and password 
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","root");  			 
			stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from Student where student_id = " + studentID + ";");  
			
			while(rs.next())  
			  student = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);  
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
