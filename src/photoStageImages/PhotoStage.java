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


@SuppressWarnings("serial")
public class PhotoStage extends JFrame{
	
	//Jframe interface declarations
	
    JButton button ;
    JButton button2;
    JButton button3;
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
    button2.setBounds(100, 300, 100, 40);
    
    button3 = new JButton("Show images");
    button3.setBounds(300, 300, 150, 40);
    
    textTag = new JTextField("Tag");
    textTag.setBounds(200,350,100,20);


    label = new JLabel();
    label.setBounds(10,10,900,500);   
  
  
    //Button to insert images and tag to the database
    button.addActionListener(new ActionListener(){
    
       public void actionPerformed(ActionEvent e){
           try{
        	   
        	   //Connection to the database
        	   Connection con =  DriverManager.getConnection(url, username, password);
               PreparedStatement preStat = con.prepareStatement("insert into photos(fileLocation,tagName) values(?,?)");
               
               //Prepare selected image for insert to database
               InputStream is = new FileInputStream(new File(s));
               preStat.setBlob(1,is);
               preStat.setString(2, textTag.getText());
               preStat.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data Inserted");
           }catch(Exception ex){
               ex.printStackTrace();        
           }
       }
    });
    
    //button to browse the image into jlabel
    button2.addActionListener(new ActionListener(){
        
    	//Lets you choose a file to upload to the database
     public void actionPerformed(ActionEvent e){
         JFileChooser fileChooser = new JFileChooser();
         
         //Default at home
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         
         //Lets you filter so you can only see images/gifs
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
             s = path;
              }
         
         //If the user didn't select anything print no data selected
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No Data selected");
         }
     }
    });
  
    //when pressed show all teh images
     button3.addActionListener(new ActionListener(){
      	 public void actionPerformed(ActionEvent e){
      	      new JTableDisplayImage().setVisible(true);
      }
      });
	
    
    //displaying jframe
    add(label);
    add(textTag);
    add(button);
    add(button2);
    add(button3);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700,420);
    setVisible(true);
    }
    
    //Method To Resize The Image size
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
 
    public static void main(String[] args){
        new PhotoStage();

    }
   }
