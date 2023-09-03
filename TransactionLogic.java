import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
public class TransactionLogic
{
    public void getBalance(String userID){
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\UserBalance.txt"));
            String nextLine = br.readLine();
            while(nextLine != null){
                if(nextLine.split(",")[0].equals(userID)){
                    JOptionPane.showMessageDialog(null,nextLine.split(",")[1]);
                    br.close();
                    return;
                }
                nextLine = br.readLine();
            }
            JOptionPane.showMessageDialog(null,"User not found");
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getStringBalance(String userID){
        try{
            BufferedReader br = new BufferedReader( new FileReader("TextFiles\\UserBalance.txt"));
            String nextLine = br.readLine();
            while(nextLine != null){
                if(nextLine.split(",")[0].equals(userID)){
                    br.close();
                    return nextLine.split(",")[1];
                }
                nextLine = br.readLine();
            }
            JOptionPane.showMessageDialog(null, "User not found");
            br.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    public void addBalance(String userID, boolean optionPaneDialog,String inputAmount){
        float amountToAdd;
        String strAmountToAdd = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("TextFiles\\UserBalance.txt"));
            String nextLine = br.readLine();
            String rewriteContent = "";
            while(nextLine != null){
                if(nextLine.split(",")[0].equals(userID)){
                    if(optionPaneDialog){
                        strAmountToAdd = JOptionPane.showInputDialog("Current Balance: " + nextLine.split(",")[1] + ", Enter amount to add");
                    }else{
                        strAmountToAdd = inputAmount;
                    }
                    
                    
                    amountToAdd = Float.parseFloat(strAmountToAdd);
                    float currAmount = Float.parseFloat(nextLine.split(",")[1]);
                    rewriteContent += (userID+","+String.format("%.2f",amountToAdd+currAmount));
                }else{
                    rewriteContent += (nextLine + "\n");
                }
                nextLine = br.readLine();
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter("TextFiles\\UserBalance.txt",false));
            System.out.println(rewriteContent);
            for(String line : rewriteContent.split("\\n")){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //# add Item ID and stuff later
    public void addItem(String itemName, String itemCost, JTextArea taItems, JLabel total){
        String currItems = taItems.getText();
        String newItemCost = String.format("%.2f",Double.parseDouble(itemCost));
        currItems += itemName + "," + "£"+newItemCost + "\n";
        taItems.setText(currItems);
        
        String currTotal = total.getText().split(",")[1].substring(1);
        String finalTotal = "" + ((Double.parseDouble(currTotal)) + (Double.parseDouble(itemCost)));
        while(finalTotal.split("\\.")[1].length() < 2){
            finalTotal = finalTotal + "0";
        }
        if(finalTotal.split("\\.")[1].length() > 2){
            String[] splitFinal = finalTotal.split("\\.");
            finalTotal = finalTotal.substring(0, splitFinal[0].length() + 3);
        }
        
        total.setText("Total,£"+ finalTotal);
    }
    
    public void finishTransaction(String userID, JTextArea taItems, JLabel lblTotal){
        String userBal = getStringBalance(userID);
        float numBal = Float.parseFloat(userBal);
        String[] splitTotal = lblTotal.getText().split(",");
        float deductAmount = Float.parseFloat(splitTotal[1].substring(1));
        addBalance(userID, false, "-"+deductAmount);
        JOptionPane.showMessageDialog(null,"Transaction Made");
        taItems.setText("");
        lblTotal.setText("Total,£0.00");
    }
}
