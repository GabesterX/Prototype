
public class StudentStruct extends UserStruct
{
    private float attendance;
    private String idCountIdentifier = "ScurrIdNum";
    private String recordsFilePath = "TextFiles\\StudentRecords.txt";
    
    public StudentStruct(String userID, String firstName, String lastName, String attendance){
        this.setUserID(userID); this.setFirstName(firstName); this.setLastName(lastName); this.setAttendance(Float.parseFloat(attendance));
    }
    
    public StudentStruct(String userID, String firstName, String lastName){
        this.setUserID(userID); this.setFirstName(firstName); this.setLastName(lastName);
    }
    
    public String getRecordFilePath(){
        return recordsFilePath;
    }
    public void setAttendance(float attendance){
        this.attendance = attendance;
    }
    
    public float getAttendance(){
        return this.attendance;
    }
    
    @Override public String toString(){
        return this.getUserID() + "," + this.getFirstName() + "," + this.getLastName() + "," + getAttendance();
    }
    
}
