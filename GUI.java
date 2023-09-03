import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class GUI
{
    //# NOTES:
    //# ADDING SEVERAL ACTION LISTENERS TO A SINGLE COMPONENT IS LIFO
    
    final Controller controller;
    
    public GUI(Controller controller){
        this.controller = controller;
    }
    
    //# Data
    
    String[] timetableColumns = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] studentRecordColumns = {"ID","FirstName","LastName","Attendance"};
    String[] teacherRecordColumns = {"ID","FirstName","LastName","Subject"};
    String[] adminRecordColumns = {"ID","FirstName","LastName","TEMP"};
    
    String[] userTypes = {"Students", "Teachers", "Admins"};
    
    //# Main window and tabs:
    
    JFrame prototypeWindow = new JFrame();
    JTabbedPane screenTabs = new JTabbedPane();
    
    //# Panels:
    
    JPanel homeTestPanel = new JPanel(null);
    JPanel recordsPagePanel = new JPanel(null);
    JPanel chatPanel = new JPanel(null);
    JPanel reportPanel = new JPanel(null);
    JPanel loginPanel = new JPanel(null);
    JPanel viewReportsPanel = new JPanel(null);
    JPanel viewTimetablePanel = new JPanel(null);
    JPanel canteenPanel = new JPanel(null);
    JPanel addStudentLogPanel = new JPanel(null);
    JPanel viewStudentLogPanel = new JPanel(null);
    JPanel forumPanel = new JPanel(null);
    
    //# Home page buttons
    
    JButton chatPageBtn = new JButton("Chat");
    JButton reportPageBtn = new JButton("Create Report");
    JButton viewTimetableBtn = new JButton("View Timetable");
    JButton getBalanceBtn = new JButton("Get Balance");
    JButton addBalanceBtn = new JButton("Add Balance");
    JButton canteenPageBtn = new JButton("Make Transaction");
    JButton viewStudentLogBtn = new JButton("View Student Logs");
    JButton addStudentLogBtn = new JButton("Add Student Logs");
    JButton openForumBtn = new JButton("View Forum");
    
    //# Teacher Admin Staff Home Buttons:
    
    JButton recordsPageBtn = new JButton("Records Page");
    JButton viewReportsPageBtn = new JButton("View Reports");
    
    //# Records Page Content:
    //textfield
    
    JTextField tfFirstName = new JTextField();
    JTextField tfLastName = new JTextField();
    JTextField tfAttendance = new JTextField();
    JTextField tfRecordSearch = new JTextField();
    //label
    JLabel lblFirstName = new JLabel("First Name:");
    JLabel lblLastName = new JLabel("Last Name:");
    JLabel lblAttendance = new JLabel ("Attendance:");
    JLabel lblRecordSearch = new JLabel ("Search:");
    //button
    JButton recordHomeBtn = new JButton("Home");
    JButton createRecordBtn = new JButton("Create Record");
    JButton readRecordsBtn = new JButton("Read from file");
    JButton clearRecordsBtn = new JButton("Clear Records");
    JButton recordSearchBtn = new JButton("Search");
    JButton recordSortBtn = new JButton("Sort");
    //Combobox
    JComboBox cbSortSelect;
    
    // STUDENT TABLE
    
    DefaultTableModel studentRecordModel = new DefaultTableModel(studentRecordColumns,0);
    JTable studentRecordTable = new JTable(studentRecordModel);
    JScrollPane studentRecordTableScroll = new JScrollPane(studentRecordTable);
    
    // TEACHER TABLE
    
    DefaultTableModel teacherRecordModel = new DefaultTableModel(teacherRecordColumns,0);
    JTable teacherRecordTable = new JTable(teacherRecordModel);
    JScrollPane teacherRecordTableScroll = new JScrollPane(teacherRecordTable);
    
    // ADMIN TABLE
    
    DefaultTableModel adminRecordModel = new DefaultTableModel(adminRecordColumns,0);
    JTable adminRecordTable = new JTable(adminRecordModel);
    JScrollPane adminRecordTableScroll = new JScrollPane(adminRecordTable);
    
    //
    JComboBox cbUserTypeSel = new JComboBox(userTypes);
    //# CHAT PAGE CONTENT
    
    JTextArea chatBox = new JTextArea();
    JScrollPane chatScroll = new JScrollPane(chatBox);
    JTextArea msgBox = new JTextArea();
    JScrollPane msgScroll = new JScrollPane(msgBox);
    JButton sendMsgBtn = new JButton("Send");
    JButton loadConvoBtn = new JButton("Open Chat");
    JComboBox cbUsers = new JComboBox();
    JScrollPane cbUsersScroll = new JScrollPane(cbUsers);
    JButton chatToHomeBtn = new JButton("Home");
    
    //# CREATE_REPORT PAGE CONTENT
    
    JTextArea taReportContent = new JTextArea();
    JScrollPane reportContentScroll = new JScrollPane(taReportContent);
    JButton createReportBtn = new JButton("Create Report");
    JButton createReportHomeBtn = new JButton("Home");
    //# VIEW_REPORT PAGE CONTENT
    
    JTextArea taViewReport = new JTextArea();
    JScrollPane viewReportScroll = new JScrollPane(taViewReport);
    JComboBox cbReportList = new JComboBox();
    JButton openReportBtn = new JButton("Open Report");
    JButton viewReportHomeBtn = new JButton("Home");
    
    //# LOGIN PAGE CONTENT
    
    JTextField tfLogUsername = new JTextField();
    JTextField tfLogPassword = new JTextField();
    JLabel lblLogUsername = new JLabel("Username");
    JLabel lblLogPassword = new JLabel("Password");
    JButton logInBtn = new JButton("Login");
    
    //# VIEW TIMETABLE CONTENT
    
    DefaultTableModel timetableModel = new DefaultTableModel(timetableColumns,0);
    JTable userTimetable = new JTable(timetableModel);
    JButton viewTimetableHomeBtn = new JButton("Home");
    
    //# CANTEEN PAGE CONTENT
    
    JTextArea taItemsAdded = new JTextArea();
    
    JLabel lblItem1 = new JLabel("Item 1: £2.70");
    JButton addItem1 = new JButton("Add");
    
    JLabel lblItem2 = new JLabel("Item 2: £3.50");
    JButton addItem2 = new JButton("Add");
    
    JLabel lblItem3 = new JLabel("Item 3: £1.80");
    JButton addItem3 = new JButton("Add");
    
    JLabel lblTotal = new JLabel("Total,£0.00");
    JButton finishTransactionBtn = new JButton("Pay");
    
    //# VIEW STUDENT LOG PAGE CONTENT
    
    JComboBox cbViewLogList = new JComboBox();
    JTextArea taStudentLogs = new JTextArea();
    JButton openSelectedUserLogBtn = new JButton("Open");
    JButton viewUserLogHomeBtn = new JButton("Home");
    //# ADD STUDENT LOG PAGE CONTENT
    JComboBox cbAddLogList = new JComboBox();
    JTextArea taAddStudentLog = new JTextArea();
    JButton addSelectedUserLogBtn = new JButton("Add");
    JButton addUserLogHomeBtn = new JButton("Home");
    
    //# FORUM PAGE CONTENT
    
    
    
    //# Initialisation of GUI elements
    
    public void initFrame(){
        javax.swing.ToolTipManager.sharedInstance().setInitialDelay(100); // Adjusts how long to hold over GUI element before its associated tooltip displays on screen
        prototypeWindow.add(screenTabs);
        prototypeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prototypeWindow.setSize(1920,1080);
        
        initTabs();
        initHomePanel();
        initRecordsPanel();
        initChatPanel();
        initReportPanel();
        initLoginPanel();
        initViewReportsPanel();
        initViewTimetablePanel();
        initCanteenPanel();
        initStudentLogPanels();
        initForumPanel();
        
        screenTabs.setSelectedIndex(PageTabIndex.LOGIN_PAGE.ordinal());
        //prototypeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //# Fullscreens the JFrame window
        //prototypeWindow.setUndecorated(true);                    //# Sets undecorated to remove the frame and border of the window
        prototypeWindow.setVisible(true); 
    }
    
    public void initTabs(){
        screenTabs.setUI(new BasicTabbedPaneUI(){ //#- Sets the JTabbedPane UI as an overridden BasicTabbedPaneUI with its Insets set to 0, so no space between the border and the tab, therefore removing the border from the tab
           private final Insets borderInsets = new Insets(0,0,0,0);
           @Override
           protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex){//# Overrides the paintContentBorder method to be empty so no border is painted on the tab
           }
           @Override
           protected Insets getContentBorderInsets(int tabPlacement){ //# When an attempt to retrieve the border insets is made, it returns the overridden value of (0,0,0,0) so there is no gap left for a border
               return borderInsets;
           }
           @Override
           protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height){ //# returns a height of 0 to hide the tabs
               return 0;
           }
        });
        
        screenTabs.addTab("HomeScreen", homeTestPanel);
        screenTabs.addTab("RecordsPage", recordsPagePanel);
        screenTabs.addTab("ChatPage", chatPanel);
        screenTabs.addTab("ReportPage", reportPanel);
        screenTabs.addTab("LoginPage", loginPanel);
        screenTabs.addTab("ViewReports", viewReportsPanel);
        screenTabs.addTab("ViewTimetable", viewTimetablePanel);
        screenTabs.addTab("CanteenPage", canteenPanel);
        screenTabs.addTab("ViewStudentLog", viewStudentLogPanel);
        screenTabs.addTab("AddStudentLog", addStudentLogPanel);
        screenTabs.addTab("ForumPage", forumPanel);
        
    }
    
    public void initHomePanel(){
        // Records Page Button
        homeTestPanel.setBackground(Color.GRAY);
        
        recordsPageBtn.setSize(200,50); recordsPageBtn.setLocation(50,750);
        recordsPageBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.RECORDS_PAGE.ordinal()));
        homeTestPanel.add(recordsPageBtn);
        
        chatPageBtn.setSize(200,50); chatPageBtn.setLocation(270,750);
        chatPageBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.CHAT_PAGE.ordinal()));
        homeTestPanel.add(chatPageBtn);
        
        reportPageBtn.setSize(200,50); reportPageBtn.setLocation(490,750);
        reportPageBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.REPORT_PAGE.ordinal()));
        homeTestPanel.add(reportPageBtn);
        
        viewReportsPageBtn.setSize(200,50); viewReportsPageBtn.setLocation(490,690); viewReportsPageBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.VIEW_REPORTS_PAGE.ordinal()));
        homeTestPanel.add(viewReportsPageBtn);
        
        viewTimetableBtn.setSize(200,50); viewTimetableBtn.setLocation(710,750); 
        viewTimetableBtn.addActionListener(AL->controller.getTimetableLogic().viewTimetable(timetableModel, controller.getUserBase().getUserID()));
        viewTimetableBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.VIEW_TIMETABLE_PAGE.ordinal()));
        homeTestPanel.add(viewTimetableBtn);
        
        getBalanceBtn.setSize(200,50); getBalanceBtn.setLocation(930,750); homeTestPanel.add(getBalanceBtn);
        getBalanceBtn.addActionListener(AL->controller.getTransactionLogic().getBalance(controller.getUserBase().getUserID()));
        
        addBalanceBtn.setSize(200,50); addBalanceBtn.setLocation(930, 690); homeTestPanel.add(addBalanceBtn);
        addBalanceBtn.addActionListener(AL->controller.getTransactionLogic().addBalance(controller.getUserBase().getUserID(), true,""));
        
        canteenPageBtn.setSize(200,50); canteenPageBtn.setLocation(930,630); homeTestPanel.add(canteenPageBtn);
        canteenPageBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.CANTEEN_PAGE.ordinal()));
        
        viewStudentLogBtn.setSize(200,50); viewStudentLogBtn.setLocation(1150, 750); homeTestPanel.add(viewStudentLogBtn);
        addStudentLogBtn.setSize(200,50);  addStudentLogBtn.setLocation(1150,690); homeTestPanel.add(addStudentLogBtn);
        viewStudentLogBtn.addActionListener(AL->controller.populateComboBoxStudents(cbViewLogList));
        viewStudentLogBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.VIEW_STUDENT_LOGS_PAGE.ordinal()));
        addStudentLogBtn.addActionListener(AL->controller.populateComboBoxStudents(cbAddLogList));
        addStudentLogBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.ADD_STUDENT_LOGS_PAGE.ordinal()));
        
        openForumBtn.setSize(200,50); openForumBtn.setLocation(1370,750); homeTestPanel.add(openForumBtn);
        openForumBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.FORUM_PAGE.ordinal()));
        
    }
    
    public void initRecordsPanel(){
        studentRecordTableScroll.setSize(600,300); studentRecordTableScroll.setLocation(50,10);
        teacherRecordTableScroll.setSize(600,300); teacherRecordTableScroll.setLocation(50,320);
        adminRecordTableScroll.setSize(600,300); adminRecordTableScroll.setLocation(50,630);
        recordsPagePanel.add(studentRecordTableScroll); recordsPagePanel.add(teacherRecordTableScroll); recordsPagePanel.add(adminRecordTableScroll);
        
        studentRecordTable.setEnabled(false);
        
        readRecordsBtn.setSize(120,20); readRecordsBtn.setLocation(830,370);
        recordsPagePanel.add(readRecordsBtn);
        
        clearRecordsBtn.setSize(120,20); clearRecordsBtn.setLocation(700,370);
        recordsPagePanel.add(clearRecordsBtn);
        
        tfFirstName.setSize(200,20); tfFirstName.setLocation(780, 55);
        lblFirstName.setSize(120,20); lblFirstName.setLocation(700,52);
        recordsPagePanel.add(tfFirstName); recordsPagePanel.add(lblFirstName);
        tfLastName.setSize(200,20); tfLastName.setLocation(780, 85);
        lblLastName.setSize(120,20); lblLastName.setLocation(700,82);
        recordsPagePanel.add(tfLastName); recordsPagePanel.add(lblLastName);
        
        tfAttendance.setSize(200,20); tfAttendance.setLocation(780, 115);
        lblAttendance.setSize(120,20); lblAttendance.setLocation(700,112);
        recordsPagePanel.add(tfAttendance); recordsPagePanel.add(lblAttendance);
        
        createRecordBtn.setSize(120,20); createRecordBtn.setLocation(960,370);
        recordsPagePanel.add(createRecordBtn);
        
        tfRecordSearch.setSize(150,20); tfRecordSearch.setLocation(750,410);
        lblRecordSearch.setSize(120,20); lblRecordSearch.setLocation(700,410);
        recordsPagePanel.add(tfRecordSearch); recordsPagePanel.add(lblRecordSearch);
        
        recordSearchBtn.setSize(80,20); recordSearchBtn.setLocation(910,410);
        
        recordsPagePanel.add(recordSearchBtn);
        
        String[] strRecordFields = {"ID", "FirstName", "LastName"};
        cbSortSelect = new JComboBox(strRecordFields);
        cbSortSelect.setSize(120,20); cbSortSelect.setLocation(700,180);
        recordSortBtn.setSize(80,20); recordSortBtn.setLocation(830,180);
        
        recordsPagePanel.add(cbSortSelect); recordsPagePanel.add(recordSortBtn); 
        
        recordHomeBtn.setSize(70,70); recordHomeBtn.setLocation(1700,900); recordHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
        recordsPagePanel.add(recordHomeBtn);
        
        //cbUserTypeSel.setSize(120,20); cbUserTypeSel.setLocation(700,240); recordsPagePanel.add(cbUserTypeSel);
        
        readRecordsBtn.addActionListener(AL->controller.getRecordsLogic().chatUsers(controller.getRecordData(),cbUsers));
        readRecordsBtn.addActionListener(AL->controller.getRecordsLogic().readRecords(controller.getRecordData(),studentRecordModel,teacherRecordModel,adminRecordModel));
        
        clearRecordsBtn.addActionListener(AL->controller.getRecordsLogic().chatUsers(controller.getRecordData(),cbUsers));
        clearRecordsBtn.addActionListener(AL->controller.getRecordsLogic().clearRecords(controller.getRecordData(),studentRecordModel,teacherRecordModel,adminRecordModel));
        
        createRecordBtn.addActionListener(AL->controller.getRecordsLogic().createStudentRecord(tfFirstName.getText(), tfLastName.getText(), tfAttendance.getText())); 
        
        recordSearchBtn.addActionListener(AL->controller.getRecordsLogic().searchRecords(controller.getRecordData(),tfRecordSearch.getText(), studentRecordModel, teacherRecordModel, adminRecordModel));
        
        recordSortBtn.addActionListener(AL->controller.getRecordsLogic().updateRecordTables(controller.getRecordData(),studentRecordModel,teacherRecordModel,adminRecordModel));
        recordSortBtn.addActionListener(AL->controller.getRecordsLogic().sortRecords(controller.getRecordData(),UserRecordFields.values()[cbSortSelect.getSelectedIndex()]));
        
    }
    
    public void initChatPanel(){
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);
        chatBox.setSize(1600,700); chatBox.setLocation(50,50); chatScroll.setSize(1600,700); chatScroll.setLocation(50,50); chatPanel.add(chatScroll);
        msgBox.setSize(1600, 80); msgBox.setLocation(50,860); msgScroll.setSize(1600,80); msgScroll.setLocation(50,760); chatPanel.add(msgScroll);
        msgBox.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");
        msgBox.setLineWrap(true);
        sendMsgBtn.setSize(200,30); sendMsgBtn.setLocation(50,970);
        chatPanel.add(sendMsgBtn);
        
        cbUsers.setSize(200,20); cbUsers.setLocation(1670,60); chatPanel.add(cbUsers); 
        
        loadConvoBtn.setSize(150,20); loadConvoBtn.setLocation(1685,90); 
        
        chatPanel.add(loadConvoBtn);
        
        chatToHomeBtn.setSize(70,70); chatToHomeBtn.setLocation(1700,900); 
        chatPanel.add(chatToHomeBtn);
        
        sendMsgBtn.addActionListener(AL->controller.getChatLogic().sendMsg(msgBox.getText(),chatBox,msgBox));
        loadConvoBtn.addActionListener(AL->controller.getChatLogic().initConvo(controller.getUserBase().getUserID(),cbUsers.getSelectedItem().toString().split(",")[0],controller.getUserBase().getFullName(),(cbUsers.getSelectedItem().toString().split(",")[1] +" "+ cbUsers.getSelectedItem().toString().split(",")[2]),chatBox)); //hard 2 read 
        chatToHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
    }
    
    public void initReportPanel(){
        reportContentScroll.setSize(1000, 800); reportContentScroll.setLocation(460,50); reportPanel.add(reportContentScroll);
        createReportBtn.setSize(300,40); createReportBtn.setLocation(810, 870); reportPanel.add(createReportBtn);
        createReportBtn.addActionListener(AL->controller.getReportLogic().makeReport(taReportContent.getText(), controller.getUserBase().getUserID()));
        createReportHomeBtn.setSize(80,80); createReportHomeBtn.setLocation(1700,900); reportPanel.add(createReportHomeBtn);
        createReportHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
    }
    
    public void initLoginPanel(){
        tfLogUsername.setSize(400,30); tfLogUsername.setLocation(760,80);  loginPanel.add(tfLogUsername);
        lblLogUsername.setSize(150,20); lblLogUsername.setLocation(760,50); loginPanel.add(lblLogUsername);
        
        tfLogPassword.setSize(400,30); tfLogPassword.setLocation(760,160); loginPanel.add(tfLogPassword);
        lblLogPassword.setSize(150,20); lblLogPassword.setLocation(760,130); loginPanel.add(lblLogPassword);
        
        logInBtn.setSize(100,30); logInBtn.setLocation(910,210); loginPanel.add(logInBtn);
        logInBtn.addActionListener(AL->controller.getLoginLogic().loginUser(tfLogUsername.getText(), tfLogPassword.getText(), controller,screenTabs));
    }
    
    public void initViewReportsPanel(){
        taViewReport.setEditable(false); 
        viewReportScroll.setSize(1000,800); viewReportScroll.setLocation(50,50); viewReportsPanel.add(viewReportScroll);
        cbReportList.setSize(200,20); cbReportList.setLocation(1060,50); viewReportsPanel.add(cbReportList);
        openReportBtn.setSize(120,40); openReportBtn.setLocation(1060,80); viewReportsPanel.add(openReportBtn);
        
        taViewReport.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");
        taViewReport.setWrapStyleWord(true);
        
        controller.getReportLogic().initReports(cbReportList);
        
        openReportBtn.addActionListener(AL->controller.getReportLogic().openReport(cbReportList.getSelectedIndex(), taViewReport));
        
        viewReportHomeBtn.setSize(80,80); viewReportHomeBtn.setLocation(1700,900); viewReportsPanel.add(viewReportHomeBtn);
        viewReportHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
    }
    
    public void initViewTimetablePanel(){
        userTimetable.setSize(1200,810);  userTimetable.setFocusable(false); userTimetable.setLocation(50,50); viewTimetablePanel.add(userTimetable);
        userTimetable.setRowHeight(115); userTimetable.setEnabled(false);
        viewTimetableHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
        viewTimetableHomeBtn.setSize(80,80); viewTimetableHomeBtn.setLocation(1700,900); viewTimetablePanel.add(viewTimetableHomeBtn);
        
    }
    
    public void initCanteenPanel(){
        taItemsAdded.setSize(500,850); taItemsAdded.setLocation(1380,30); canteenPanel.add(taItemsAdded);
        taItemsAdded.setFocusable(false); taItemsAdded.setEditable(false);
        lblTotal.setSize(120,80); lblTotal.setLocation(1450,880); canteenPanel.add(lblTotal);
        
        lblItem1.setSize(120, 80); lblItem1.setLocation(125,40); canteenPanel.add(lblItem1);
        lblItem2.setSize(120,80); lblItem2.setLocation(125,130); canteenPanel.add(lblItem2);
        lblItem3.setSize(120,80); lblItem3.setLocation(125,220); canteenPanel.add(lblItem3);
        
        addItem1.setSize(60,60); addItem1.setLocation(60,50); canteenPanel.add(addItem1); addItem1.addActionListener(AL->controller.getTransactionLogic().addItem("Item1", "2.70", taItemsAdded,lblTotal));
        addItem2.setSize(60,60); addItem2.setLocation(60,140); canteenPanel.add(addItem2); addItem2.addActionListener(AL->controller.getTransactionLogic().addItem("Item2", "3.50", taItemsAdded,lblTotal));
        addItem3.setSize(60,60); addItem3.setLocation(60,230); canteenPanel.add(addItem3); addItem3.addActionListener(AL->controller.getTransactionLogic().addItem("Item3", "1.80", taItemsAdded,lblTotal));
        
        
        
        
        finishTransactionBtn.setSize(60,60); finishTransactionBtn.setLocation(1380,890); canteenPanel.add(finishTransactionBtn);    
        finishTransactionBtn.addActionListener(AL->controller.getTransactionLogic().finishTransaction(controller.getUserBase().getUserID(), taItemsAdded, lblTotal));
        
    }
    
    public void initStudentLogPanels(){
        taStudentLogs.setSize(900,700); taStudentLogs.setLocation(50,50); viewStudentLogPanel.add(taStudentLogs);
        cbViewLogList.setSize(250,20); cbViewLogList.setLocation(980,60); viewStudentLogPanel.add(cbViewLogList);
        openSelectedUserLogBtn.setSize(200,30); openSelectedUserLogBtn.setLocation(1005,90); viewStudentLogPanel.add(openSelectedUserLogBtn);
        
        openSelectedUserLogBtn.addActionListener(AL->controller.getStudentLogLogic().readLog(cbViewLogList.getSelectedItem().toString().split(",")[1],taStudentLogs));
        
        viewUserLogHomeBtn.setSize(80,80); viewUserLogHomeBtn.setLocation(1700,900); viewStudentLogPanel.add(viewUserLogHomeBtn);
        
        taAddStudentLog.setSize(1000,800); taAddStudentLog.setLocation(460,50); addStudentLogPanel.add(taAddStudentLog);
        cbAddLogList.setSize(250,20); cbAddLogList.setLocation(1490, 60); addStudentLogPanel.add(cbAddLogList);
        
        addSelectedUserLogBtn.setSize(300,40); addSelectedUserLogBtn.setLocation(810,870); addStudentLogPanel.add(addSelectedUserLogBtn);
        
        addSelectedUserLogBtn.addActionListener(AL->controller.getStudentLogLogic().createLog(cbAddLogList.getSelectedItem().toString().split(",")[1], taAddStudentLog));
        
        addUserLogHomeBtn.setSize(80,80); addUserLogHomeBtn.setLocation(1700,900); addStudentLogPanel.add(addUserLogHomeBtn);
        
        viewUserLogHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
        addUserLogHomeBtn.addActionListener(AL->screenTabs.setSelectedIndex(PageTabIndex.HOME_PAGE.ordinal()));
        
        taStudentLogs.setEditable(false);
    }
    
    public void initForumPanel(){
        
    }
    
    public void setUserAccess(UserAuthEnum access){
        switch(access){
            case STUDENT:
                setVisibilityStudent();
                break;
            case TEACHER:
                setVisibilityTeacher();
                break;
            
            case ADMIN:
                setVisibilityAdmin();
                break;
                
            case STAFF:
                
                break;
        }
    }
    
    public void setVisibilityStudent(){
        
    }
    
    public void setVisibilityTeacher(){
        
    }
    
    public void setVisibilityAdmin(){
        
    }
}
