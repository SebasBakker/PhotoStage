package photoStageImages;


import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class JTableDisplayImage extends JFrame {
	
	//declare variables
	 private javax.swing.JLabel jLabel1;
	 private javax.swing.JScrollPane jScrollPane1;
	 private javax.swing.JTable jTable1;
	
	  public JTableDisplayImage() {
		  
		  //Executed the methods
	        initComponents();
	        populateJTable();
	    }
	  
	   	                          
	    private void initComponents() {

	    	//Make the JTable with scroll
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jLabel1 = new javax.swing.JLabel();

	    

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
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
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
		        Object[][] rows = new Object[list.size()][6];
		        
		        for(int i = 0; i < list.size(); i++){
		            rows[i][0] = list.get(i).getID();
		                       
		            if(list.get(i).getMyImage() != null){
		                
		             ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getMyImage()).getImage()
		             .getScaledInstance(150, 120, Image.SCALE_SMOOTH) );   
		                
		            rows[i][1] = image;
		            }
		            else{
		                rows[i][1] = null;
		            }
		            rows[i][2] = list.get(i).getTagName();
		        }
		        
		        PhotoDisplayModel model = new PhotoDisplayModel(columnName,rows);
		        jTable1.setModel(model);
		        jTable1.setRowHeight(120);
		        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
		    }
	    

	   

}
