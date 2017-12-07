package photoStageImages;

public class PhotoGetter {

    private String id;
    private String tagName;
    private byte[] fileLocation;
    
    public PhotoGetter(){}
    
    public PhotoGetter(String Id, String TagName,byte[] image){
    
        this.id = Id;
        this.tagName = TagName;
        this.fileLocation = image;
       
    }
    
    
    public String getID(){
      return id;
    }
    
  
    
    public String getTagName(){
        return tagName;
    }
    
    public void setName(String TagName){
        this.tagName = TagName;
    }
    
    
    public byte[] getMyImage(){
        return fileLocation;
    }
}