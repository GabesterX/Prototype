import java.io.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JComboBox;
public class Controller
{
    private ModelData recordData = new ModelData();
    
    private RecordsLogic rl = new RecordsLogic();
    private ChatLogic cl = new ChatLogic();
    private ReportLogic rp = new ReportLogic();
    private LoginLogic ll = new LoginLogic();
    private TimetableLogic tl = new TimetableLogic();
    private TransactionLogic trl = new TransactionLogic();
    private StudentLogLogic sll = new StudentLogLogic();
    
    private StudentStruct studentUser;
    private TeacherStruct teacherUser;
    private AdminStruct adminUser;
    
    private UserAuthEnum userAuth;
    
    public void setUserAuth(UserAuthEnum enteredAuth){
        this.userAuth = enteredAuth;
    }
    
    public void setStudentUser(String userID, String firstName, String lastName, String attendance){
        studentUser = new StudentStruct(userID, firstName, lastName, attendance);
    }
    
    public void setTeacherUser(String userID, String firstName, String lastName, String subject){
        teacherUser = new TeacherStruct(userID, firstName, lastName, subject);
    }
    
    public void setAdminUser(String userID, String firstName, String lastName,String temp){
        adminUser = new AdminStruct(userID, firstName, lastName, temp);
    }
    
    public void populateComboBoxStudents(JComboBox studentBox){
        for(StudentStruct student : recordData.getStudentRecords()){
            studentBox.addItem( student.getFullName()+","+student.getUserID() );
        }
    }
    
    public UserStruct getUserBase(){
        switch(userAuth){
            case STUDENT:
                return (UserStruct)studentUser;
            case TEACHER:
                return (UserStruct)teacherUser;
            case ADMIN:
                return(UserStruct)adminUser;
        }
        return studentUser;
    }
    
    
    
    public ModelData getRecordData(){
        return this.recordData;
    }
    
    public RecordsLogic getRecordsLogic(){
        return this.rl;
    }
    
    public ChatLogic getChatLogic(){
        return this.cl;
    }
    
    public ReportLogic getReportLogic(){
        return this.rp;
    }
    
    public LoginLogic getLoginLogic(){
        return this.ll;
    }
    
    public TimetableLogic getTimetableLogic(){
        return this.tl;
    }
    
    public TransactionLogic getTransactionLogic(){
        return this.trl;
    }
    
    public StudentLogLogic getStudentLogLogic(){
        return this.sll;
    }
}
