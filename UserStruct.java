
public class UserStruct
{
    private String userID;
    private String firstName;
    private String lastName;
    
    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getUserID(){
        return userID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getFullName(){
        return (firstName +" "+lastName);
    }
}
