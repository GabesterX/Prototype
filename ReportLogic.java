import java.io.*;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JTextArea;
public class ReportLogic
{
    ArrayList<ReportStruct> reportList = new ArrayList<ReportStruct>();
    
    public void initReports(JComboBox cbReportList){
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\Reports.txt"));
            String nextLine = br.readLine();
            while(nextLine != null){
                String[] splitLine = nextLine.split(",");
                if(splitLine[2].equals("unresolved")){
                    System.out.println("Report found");
                    ReportStruct currReport = new ReportStruct(splitLine[0], splitLine[1], splitLine[3]);
                    reportList.add(currReport);
                    cbReportList.addItem(splitLine[0]);
                }
                nextLine = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void makeReport(String reportContent, String userID){
        reportContent = reportContent.replace("\n","~}@");
        reportContent += "~}@";
        String repID = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\InitialisationData.txt"));
            String nextLine = br.readLine();
            String initFileContent = "";
            while(nextLine != null){
                String[] lineParts = nextLine.split(",");
                if(lineParts[0].equals("reportCount")){
                    int repCount = Integer.parseInt(lineParts[1]) + 1;
                    String tempRepID = ""+repCount;
                    while(tempRepID.length() < 5){
                        tempRepID = "0" + tempRepID;
                    }
                    repID = "R" + tempRepID;
                    initFileContent += ("reportCount," + tempRepID +"\n");
                    
                }else{
                    initFileContent += (nextLine + "\n");
                }
                nextLine = br.readLine();
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("TextFiles\\InitialisationData.txt"));
            for(String line : initFileContent.split("\n")){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            FileWriter fw = new FileWriter("TextFiles\\Reports.txt", true);
            fw.write(repID +","+userID+","+reportContent+"\n");
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openReport(int selectedIndex, JTextArea taViewReport){
        String[] reportContent = reportList.get(selectedIndex).getReportContent().split("~}@");
        String textContent = "";
        for(String line : reportContent){
            textContent += (line + "\n");
        }

        taViewReport.setText(textContent);
    }
}
