import java.io.*;
import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PhotoStage {

	
	
	//Start with a database connection
	public static void main(String[] args) {
		//define variables
		String url = "jdbc:mysql://localhost:3306/photostage";
		String username = "user";
		String password = "password123";
		
		System.out.println("Connecting database...");

		//Check if the database connection works
		try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
			//Message if it connects successfully 
		    System.out.println("Database connected!");
		    
		    //Prepare the values to insert to the database
		    PreparedStatement pstat = (PreparedStatement) connection.prepareStatement("insert into photos(fileLocation,tagName) values(?,?)");
		    
			FileInputStream fin = new FileInputStream("C:\\CodeRepository\\PhotoStage\\photos\\download.jpg");
			pstat.setBinaryStream(1, fin, fin.available());
			pstat.setString(2, "origin");
			
			//Insert the values to the database
			int result = pstat.executeUpdate();
			System.out.println(result + " Record Successfully Inserted");
			
			 ResultSet rs1;
			 Statement stmt = null;
			 rs1 = stmt.executeQuery("select * from photos");
			
			InputStream binaryStream = rs1.getBinaryStream(1);
			
			//Close the connection after you're done
			connection.close();
			
		} catch (SQLException e) {
			//Give an error message if it can't connect
		    throw new IllegalStateException("Can't connect to the database!", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			 throw new IllegalStateException("No image found!", e);
			 
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Not a valid image!", e);
		}
	}

}
