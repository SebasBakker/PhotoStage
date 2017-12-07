package photoStageImages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class PhotoDisplayQuery {
	
	//Create connection to database
   public Connection getConnection(){
        Connection con = null;
        try {
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/photostage","user","password123");
        } catch (SQLException ex) {
        	System.out.println("Can't connect to the database");
        }
        return con;
    }
    
   //Read the results from the query out of an array
    public ArrayList<PhotoGetter> BindTable(){
   
    //Array declarations
   ArrayList<PhotoGetter> list = new ArrayList<PhotoGetter>();
   Connection con = getConnection();
   Statement stat;
   ResultSet resSet;
   
   try {
	   
	//execute the query for the photos
   stat = con.createStatement();
   resSet = stat.executeQuery("SELECT * FROM photos");
   
   //Go through the array to get the values
   PhotoGetter pGet;
   while(resSet.next()){
   pGet = new PhotoGetter(
   resSet.getString("id"),
   resSet.getString("tagName"),
   resSet.getBytes("fileLocation")
   );
   list.add(pGet);
   }
   
   } catch (SQLException ex) {
	   System.out.println("Can't create query");
   }
   return list;
    }
}
