import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class PhotoUpload {

	
	
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
			
			//Instert the values to the database
			int result = pstat.executeUpdate();
			System.out.println(result + " Record Successfully Inserted");
			
			//Close the connection after you're done
			connection.close();
			
		} catch (SQLException e) {
			//Give an error message if it can't connect
		    throw new IllegalStateException("Can't connect to the database!", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new IllegalStateException("No image found!", e);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("Not a valid image!", e);
		}
	}

}
