package photoStageImages;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class JTableDisplayImage extends JFrame {
	
	//declare variables
	 private javax.swing.JLabel jLabel1;
	 private javax.swing.JScrollPane jScrollPane1;
	 private javax.swing.JTable jTable1;
	 private JTextField textTag;
	 private JButton buttonImages;
	 private JButton buttonSearch;
	 static String varJtable = null;
	
	 //Modifying the query for searching
	 public String queryModifier(){
	        return varJtable;

	    }
	 
	  public JTableDisplayImage() {
		  //Prepare the table for data inserting
		  
		  initComponents();
 		 
 	  	   
		
	    }
	  
	   	                          
	    private void initComponents() {

	    	//Make the JTable with scroll
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jLabel1 = new javax.swing.JLabel();
	        buttonSearch = new JButton("Search");
	        buttonSearch.setBounds(300, 650, 150, 40);
	        add(buttonSearch);
	        buttonImages = new JButton("Show images");
	        buttonImages.setBounds(300, 700, 150, 40);
	        add(buttonImages);	       
	        textTag = new JTextField("Tag here");
	        textTag.setBounds(200,610,100,20);
	        add(textTag);
	        
	        //Button to show all the images
	        buttonImages.addActionListener(new ActionListener(){
		      	 public void actionPerformed(ActionEvent e){  		
		      		varJtable = "select * from photos";	
		  	        populateJTable();
		  	     
		      }
		      });
	        
	        //Button to search for a specific tag
	        buttonSearch.addActionListener(new ActionListener(){
		      	 public void actionPerformed(ActionEvent e){  		
		      		varJtable = "select * from photos where tagName = '"+textTag.getText()+"'";	
		  	        populateJTable();
		  	     
		      }
		      });

	        jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null},
	                {null, null, null},
	                {null, null, null},
	                {null, null, null}
	            },
	            new String [] {
	            		
	            	//declare table-header names
	                "Id", "Image", "Tag"
	            }
	        ));
	       
	        jScrollPane1.setViewportView(jTable1);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(248, 248, 248)
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(261, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        pack();
	    }
	    
	    //method to display the values of all rows from the query
		  public void populateJTable(){
				 			  
		        PhotoDisplayQuery PhDisQuery = new PhotoDisplayQuery();
	
		        ArrayList<PhotoGetter> list = PhDisQuery.BindTable();
		        String[] columnName = {"Id","image","tagName"};
		        Object[][] rows = new Object[list.size()][3];
		      
		        for(int i = 0; i < list.size(); i++){
		            rows[i][0] = list.get(i).getID();
		                       
		            if(list.get(i).getMyImage() != null){
		                
		             ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getMyImage()).getImage()
		             .getScaledInstance(200, 200, Image.SCALE_SMOOTH) );   
		                
		            rows[i][1] = image;
		            }
		            else{
		                rows[i][1] = null;
		            }
		            rows[i][2] = list.get(i).getTagName();
		        }
		        
		        PhotoDisplayModel model = new PhotoDisplayModel(columnName,rows);
		        jTable1.setModel(model);
		        jTable1.setRowHeight(220);
		        jTable1.getColumnModel().getColumn(1).setPreferredWidth(154);
		        
		    
		
		    }
	    
	
		 
		

}
