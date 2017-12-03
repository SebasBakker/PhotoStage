package photoStageImages;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class PhotoStage extends JFrame{
	
	//Jframe interface declarations
	
    JButton button ;
    JButton button2;
    JLabel label;
    JTextField textTag;
    String s;
    
    //Database declarations
	String url = "jdbc:mysql://localhost:3306/photostage";
	String username = "user";
	String password = "password123";
	
    public PhotoStage(){
   
    //Setting the buttons and input fields
    button = new JButton("ADD");
    button.setBounds(200,300,100,40);
    
    button2 = new JButton("Browse");
    button2.setBounds(80, 300, 100, 40);
    
    textTag = new JTextField("Tag");
    textTag.setBounds(320,330,100,20);

    label = new JLabel();
    label.setBounds(10,10,900,500);   
  
    //button to browse the image into jlabel
    button2.addActionListener(new ActionListener(){
        
    	//Lets you choose a file to upload to the database
     public void actionPerformed(ActionEvent e){
         JFileChooser fileChooser = new JFileChooser();
         //Default at home
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         //Lets you fitler so you can only see images/gifs
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
             s = path;
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No Data");
         }
     }
    });

    //Button to insert images and tag to the database
    button.addActionListener(new ActionListener(){
    
       public void actionPerformed(ActionEvent e){
           try{
        	   //Connection to the database
        	   Connection con =  DriverManager.getConnection(url, username, password);
               PreparedStatement ps = con.prepareStatement("insert into photos(fileLocation,tagName) values(?,?)");
               //Prepare selected image for insert to database
               InputStream is = new FileInputStream(new File(s));
               ps.setBlob(1,is);
               ps.setString(2, textTag.getText());
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data Inserted");
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }
    });

    //displaying jframe
    add(label);
    add(textTag);
    add(button);
    add(button2);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700,420);
    setVisible(true);
    }
    
    //Method To Resize The Image size
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(500,300,400);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
 
    public static void main(String[] args){
        new PhotoStage();
    }
   }
