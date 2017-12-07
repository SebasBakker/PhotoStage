package photoStageImages;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;




public class PhotoDisplayModel extends AbstractTableModel {
	//declare columns and rows for displaying all images
	private String[] columns;
    private Object[][] rows;
    
    
    public PhotoDisplayModel(String[] columnName, Object[][] data){
    
        this.columns = columnName;
        this.rows = data;
    }

    
    public Class<?> getColumnClass(int column){
    	//1 is the index of the column image
    	if(column == 1){
            return Icon.class;
        }
        else{
            return getValueAt(0,column).getClass();
        }
    }
    
    //get all rows
    public int getRowCount() {
     return this.rows.length;
    }
    
    //get all columns
    public int getColumnCount() {
     return this.columns.length;
    }

    //Read the values of a row
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.rows[rowIndex][columnIndex];
    }
    
    //get the names of a column
    public String getColumnName(int col){
        return this.columns[col];
    }


}