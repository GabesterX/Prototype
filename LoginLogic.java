import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.io.*;

public class LoginLogic
{
    
    public void loginUser(String usernameEntered, String passwordEntered, Controller controller,JTabbedPane tabs){
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\Accounts.txt"));
            String nextLine = br.readLine();
            while(nextLine != null){
                String[] lineParts = nextLine.split(",");
                if(lineParts[1].equalsIgnoreCase(usernameEntered)){
                    if(lineParts[2].equals(passwordEntered)){
                        initUser(lineParts[0], controller);
                        JOptionPane.showMessageDialog(null, "Logged In");
                        tabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal());
                        System.out.println(controller.getUserBase().getFirstName());
                        return;
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect Password");
                        return;
                    }
                    
                }
                nextLine = br.readLine();
            }
            JOptionPane.showMessageDialog(null, "User does not exist");
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void initUser(String userID, Controller controller){
        String[] userParts;
        switch(userID.charAt(0)){
            case 'S':
                controller.setUserAuth(UserAuthEnum.STUDENT);
                userParts = getUserInfo("TextFiles\\StudentRecords.txt", userID);
                controller.setStudentUser(userParts[0], userParts[1], userParts[2],userParts[3]);
                break;
            case 'T':
                controller.setUserAuth(UserAuthEnum.TEACHER);
                userParts = getUserInfo("TextFiles\\TeacherRecords.txt", userID);
                controller.setTeacherUser(userParts[0], userParts[1], userParts[2], userParts[3]);
                break;
            case 'A':
                controller.setUserAuth(UserAuthEnum.ADMIN);
                userParts = getUserInfo("TextFiles\\AdminRecords.txt", userID);
                controller.setAdminUser(userParts[0], userParts[1], userParts[2],"");
                break;
            case 'O':
                //String[] userParts = getUserInfo("TextFiles\\StaffRecords.txt", userID);
                //controller.setStaffUser(userID);
                break;
        }
        
        
        return;
    }
    
    
    private String[] getUserInfo(String filePath,String userID){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String nextLine = br.readLine();
            while(nextLine != null){
                if(nextLine.split(",")[0].equals(userID)){
                    return nextLine.split(",");
                }
                nextLine = br.readLine();
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String[0];
    }
}

