import java.util.*;
import java.io.*; 
import javax.swing.table.*;
import javax.swing.JComboBox;
public class RecordsLogic
{
    final String idStudentIdentifier = "ScurrIdNum";
    final String idTeacherIdentifier = "TcurrIdNum";
    final String adminTeacherIdentifier = "AcurrIdNum";
    private boolean sortOrder = false;
    
    public void readRecords(ModelData recordData, DefaultTableModel studentModel, DefaultTableModel teacherModel, DefaultTableModel adminModel){
        clearRecords(recordData, studentModel, teacherModel, adminModel);
        
        for(String[] currParts : getRecordParts("TextFiles\\StudentRecords.txt")){
            StudentStruct currUser = new StudentStruct(currParts[0], currParts[1], currParts[2], currParts[3]);
            recordData.addStudentRecord(currUser);
        }
        
        for(String[] currParts : getRecordParts("TextFiles\\TeacherRecords.txt")){
            TeacherStruct currUser = new TeacherStruct(currParts[0], currParts[1], currParts[2], currParts[3]);
            recordData.addTeacherRecord(currUser);
        }
        
        for(String[] currParts : getRecordParts("TextFiles\\AdminRecords.txt")){
            AdminStruct currUser = new AdminStruct(currParts[0], currParts[1], currParts[2], "");
            recordData.addAdminRecord(currUser);
        }
        
        updateRecordTables(recordData,studentModel, teacherModel, adminModel);
    }
    
    private ArrayList<String[]> getRecordParts(String filepath){
        ArrayList<String[]> parts = new ArrayList<String[]>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String nextLine = br.readLine();
            while(nextLine != null){
                String[] readUser = nextLine.split(",");
                parts.add(readUser);
                nextLine = br.readLine();
            }
            br.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return parts;
    }
    
    public void updateRecordTables(ModelData recordData, DefaultTableModel studentModel, DefaultTableModel teacherModel, DefaultTableModel adminModel){
        studentModel.setRowCount(0); teacherModel.setRowCount(0); adminModel.setRowCount(0);
        
        for(StudentStruct student : recordData.getStudentRecords()){
            studentModel.addRow(student.toString().split(","));
        }
        for(TeacherStruct teacher : recordData.getTeacherRecords()){
            teacherModel.addRow(teacher.toString().split(","));
        }
        for(AdminStruct admin : recordData.getAdminRecords()){
            adminModel.addRow(admin.toString().split(","));
        }
        
    }
    
    public void clearRecords(ModelData recordData, DefaultTableModel studentModel, DefaultTableModel teacherModel, DefaultTableModel adminModel){
        recordData.clearStudentRecords(); recordData.clearTeacherRecords(); recordData.clearAdminRecords();
        studentModel.setRowCount(0); teacherModel.setRowCount(0); adminModel.setRowCount(0);
        
    }
    
    public void chatUsers(ModelData recordData, JComboBox userList){
        for(StudentStruct student : recordData.getStudentRecords()){
            userList.addItem(student.getUserID()+","+student.getFirstName()+","+student.getLastName());
        }
        for(TeacherStruct teacher : recordData.getTeacherRecords()){
            userList.addItem(teacher.getUserID()+","+teacher.getFirstName()+","+teacher.getLastName());
        }
        for(AdminStruct admin : recordData.getAdminRecords()){
            userList.addItem(admin.getUserID()+","+admin.getFirstName()+","+admin.getLastName());
        }
    }
    
    public void createStudentRecord(String firstName, String lastName, String attendance){
        StudentStruct createdUser = new StudentStruct(generateID(idStudentIdentifier),firstName, lastName, attendance);
        writeRecordToFile(createdUser.getRecordFilePath(),createdUser.toString().split(","));
    }
    
    private void writeRecordToFile(String filepath,String[] recordParts){
        try{
            FileWriter fw = new FileWriter(filepath, true);
            String recordLine = "";
            for(String part : recordParts){
                recordLine += (part + ",");
            }
            fw.write(recordLine.substring(0, recordLine.length() - 1));
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private String generateID(String idCountIdentifier){
        String foundCount = "";
        String genID = "";
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\InitialisationData.txt"));
            
            String nextLine = br.readLine();
            String fileContent = "";
            
            while(nextLine != null){
                String[] data = nextLine.split(",");
                if(data[0].equalsIgnoreCase(idCountIdentifier)){
                    foundCount = data[1];
                    genID = "" + (Integer.parseInt(data[1]) + 1);
                    while(genID.length() < 5){
                        genID = "0" + genID;
                    }
                    fileContent += (idCountIdentifier +","+ genID + "\n");
                    System.out.println(idCountIdentifier +","+ genID + "\n");
                }else{
                    fileContent += (nextLine + "\n");
                }
                nextLine = br.readLine();
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("TextFiles\\InitialisationData.txt", false));
            for(String line : fileContent.split("\n")){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        genID = idCountIdentifier.charAt(0) + genID;
        return genID;
    }
    
    public void searchRecords(ModelData recordData, String searchKey, DefaultTableModel studentModel, DefaultTableModel teacherModel, DefaultTableModel adminModel){
        studentModel.setRowCount(0);
        teacherModel.setRowCount(0);
        adminModel.setRowCount(0);
        if(searchKey.equals("")){
            for(StudentStruct student :recordData.getStudentRecords()){
                studentModel.addRow(student.toString().split(","));
            }
            for(TeacherStruct teacher : recordData.getTeacherRecords()){
                teacherModel.addRow(teacher.toString().split(","));
            }
            for(AdminStruct admin : recordData.getAdminRecords()){
                adminModel.addRow(admin.toString().split(","));
            }
            return;
        }
        for(StudentStruct student : recordData.getStudentRecords()){
            if(student.toString().contains(searchKey)){
                studentModel.addRow(student.toString().split(","));
            }
        }
        for(TeacherStruct teacher : recordData.getTeacherRecords()){
            if(teacher.toString().contains(searchKey)){
                teacherModel.addRow(teacher.toString().split(","));
            }
        }
        for(AdminStruct admin : recordData.getAdminRecords()){
            if(admin.toString().contains(searchKey)){
                adminModel.addRow(admin.toString().split(","));
            }
        }
    }
    
    public void sortRecords(ModelData recordData, UserRecordFields compareVal){
        //# IMPLEMENT SELECTION FOR SORTING WITH RADIOBUTTONS, LEAVE AS JUST STUDENT RECORDS FOR NOW
        sortRecords(recordData.getStudentRecords(), compareVal);
    }
    
    public void sortRecords(ArrayList<StudentStruct> studentRecords, UserRecordFields compareVal){
        for(int k = 0; k < studentRecords.size(); k++){
            for(int i = 0; i < studentRecords.size() - 1; i++){
                if(compareFields(studentRecords.get(i), studentRecords.get(i+1), compareVal) ^ sortOrder){
                    Collections.swap(studentRecords, i, i+1);
                }
            }
        }
        sortOrder = !sortOrder;
    }
    //# OVERRIDE WITH OTHER USER STRUCT TYPES IN FUTURE IMPLEMENTATIONS
    private boolean compareFields(StudentStruct studentOne, StudentStruct studentTwo,UserRecordFields compareVal){
        boolean comparisonResult = false;
        switch(compareVal){
            case ID:
                int ID1 = Integer.parseInt(studentOne.getUserID().substring(1));
                int ID2 = Integer.parseInt(studentTwo.getUserID().substring(1));
                return ID1 > ID2;
            case FIRSTNAME:
                return (studentOne.getFirstName().compareToIgnoreCase(studentTwo.getFirstName()) < 0);
            case LASTNAME:
                return (studentOne.getLastName().compareToIgnoreCase(studentTwo.getLastName()) < 0 );
        }
        return false;
    }
}
