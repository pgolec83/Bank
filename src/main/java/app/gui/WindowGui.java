package app.gui;

import app.model.*;
import app.model.AccountCredit;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;


public class WindowGui{
    private final String bankTitle = new String("Aplikacja bankowa");
    private Client loggedUser = null;
    private JFrame bankFrame;
    private JPanel bankPanel;
    private JTable users, accounts;
    private JMenuBar bankMenu;
    private JMenu menuBank,  menuUser, menuAcc, menuOpp;
    private JMenuItem menuBankAbout, menuBankExit;
    private JMenuItem menuUserAdd, menuUserDel, menuUserActive, menuUserShow;
    private JMenuItem menuAccAdd, menuAccDel, menuAccShow;
    private JMenuItem menuOppAdd, menuOppWithdraw, menuOppTransfer, menuOppShow;
    private List<Client> clients;
    private List<Operation> operations;
    
    public WindowGui(){
        clients = new LinkedList<>();
        operations = new LinkedList<>();
        createData();
                
        bankFrame = new JFrame();
        bankFrame.setTitle(bankTitle + " - brak aktywnego użytkownika");
        bankFrame.setLocation(100,100);
        bankFrame.setSize(800,600);
        bankFrame.setResizable(false);
        bankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        bankMenu = new JMenuBar();
        menuBank = new JMenu("Bank");
        menuUser = new JMenu("Użytkownik");
        menuAcc = new JMenu("Konta użytkownika");
        menuOpp = new JMenu("Operacje na koncie");
 
        menuBankAbout = new JMenuItem("O aplikacji...");
        menuBankAbout.addActionListener((ActionEvent e) -> {
            actionMsgAbout();
        });       
        menuBankExit = new JMenuItem("Wyjdź z aplikacji");
        menuBankExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        menuUserAdd = new JMenuItem("Dodaj użytkownika");
        menuUserAdd.addActionListener((ActionEvent e) -> {
            actionUserAdd();
        });
        menuUserDel = new JMenuItem("Usuń użytkownika");
        menuUserDel.addActionListener((ActionEvent e) -> {
            actionUserDel();
        });
        menuUserActive = new JMenuItem("Wybierz aktywnego użytkownika");
        menuUserActive.addActionListener((ActionEvent e) -> {
            actionUserActive();
        });
        menuUserShow = new JMenuItem("Wyświetl użytkowników");
        menuUserShow.addActionListener((ActionEvent e) -> {
            paneShowUsers();
        });
        menuAccAdd = new JMenuItem("Dodaj konto");
        menuAccAdd.addActionListener((ActionEvent e) -> {
            actionAccAdd();
        });
        menuAccDel = new JMenuItem("Usuń konto");
        menuAccDel.addActionListener((ActionEvent e) -> {
            actionAccDel();
        });
        menuAccShow = new JMenuItem("Wyświetl konta użytkownika");
        menuAccShow.addActionListener((ActionEvent e) -> {
            paneShowAcc();
        });    
        menuOppAdd = new JMenuItem("Wpłata na konto");
        menuOppAdd.addActionListener((ActionEvent e) -> {
            actionOppAdd();
        });
        menuOppWithdraw = new JMenuItem("Wypłata z konta");
        menuOppWithdraw.addActionListener((ActionEvent e) -> {
            
        });
        menuOppTransfer = new JMenuItem("Przelew pomiędzy kontami");
        menuOppTransfer.addActionListener((ActionEvent e) -> {
            
        });
        menuOppShow = new JMenuItem("Wyświetl operacje użytkownika");
        menuOppShow.addActionListener((ActionEvent e) -> {
            paneShowOpp();
        });
        
        menuBank.add(menuBankAbout);
        menuBank.addSeparator();
        menuBank.add(menuBankExit);
        
        menuUser.add(menuUserAdd);
        menuUser.add(menuUserDel);
        menuUser.add(menuUserActive);
        menuUser.add(menuUserShow);
        
        menuAcc.add(menuAccAdd);
        menuAcc.add(menuAccDel);
        menuAcc.add(menuAccShow);
        
        menuOpp.add(menuOppAdd);
        menuOpp.add(menuOppWithdraw);
        menuOpp.add(menuOppTransfer);
        menuOpp.add(menuOppShow);
        
        bankMenu.add(menuBank);
        bankMenu.add(menuUser);
        bankMenu.add(menuAcc);
        bankMenu.add(menuOpp);
        
        bankFrame.setJMenuBar(bankMenu);
        bankFrame.setVisible(true);
        
        setMenu(0);
        
        bankPanel = new JPanel();
        bankFrame.add(bankPanel);
        paneShowUsers();
    }
      
    public void paneShowUsers(){
        if(bankPanel.getComponentCount()>0){
            bankPanel.remove(0);
        }
        String tableHeaders[] = {"ID","Name","Created date","Accounts"};
        String tableData[][] = new String[clients.size()][4];
        for(int i=0;i<clients.size();i++){
            tableData[i][0] = String.valueOf(clients.get(i).getClientId());
            tableData[i][1] = clients.get(i).getName();
            tableData[i][2] = clients.get(i).getCreated();
            tableData[i][3] = String.valueOf(clients.get(i).getAccounts().size());
        }
        users = new JTable(tableData, tableHeaders);
        users.getColumnModel().getColumn(0).setPreferredWidth(100);
        users.getColumnModel().getColumn(1).setPreferredWidth(395);
        users.getColumnModel().getColumn(2).setPreferredWidth(200);
        users.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane paneUsers = new JScrollPane(users);
        paneUsers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        paneUsers.setBounds(0, 0, 795, 550);       
        bankPanel.add(paneUsers);
        setMenu(14);
    }
    
    public void paneShowAcc(){
        if(bankPanel.getComponentCount()>0){
            bankPanel.remove(0);
        }
        if(loggedUser==null){
            JOptionPane.showMessageDialog(bankFrame, "Nie wybrano aktywnego użytkownika!", "Brak użytkownika", JOptionPane.WARNING_MESSAGE);
            paneShowUsers();
        }
        String tableHeaders[] = {"ID", "Account Type", "Balance", "Credit Limit", "Interest Rate", "Operations"};
        String tableData[][] = new String[loggedUser.getAccounts().size()][6];
        for(int j=0;j<loggedUser.getAccounts().size();j++){
            tableData[j][0] = String.valueOf(loggedUser.getAccounts().get(j).getId());
            tableData[j][2] = String.valueOf(loggedUser.getAccounts().get(j).getBalance()) + " PLN";
            tableData[j][5] = String.valueOf(loggedUser.getAccounts().get(j).getOperations());
            if(loggedUser.getAccounts().get(j).getAccType()=="N"){
                tableData[j][1] = "Normal Account";
                tableData[j][3] = "-";
                tableData[j][4] = "-";
            }
            if(loggedUser.getAccounts().get(j).getAccType()=="C"){
                tableData[j][1] = "Credit Account";
                tableData[j][3] = String.valueOf((((AccountCredit)loggedUser.getAccounts().get(j)).getCreditLimit())) + " PLN";
                tableData[j][4] = "-";
            }
            if(loggedUser.getAccounts().get(j).getAccType()=="S"){
                tableData[j][1] = "Savings Account";
                tableData[j][3] = "-";
                tableData[j][4] = String.valueOf((((AccountSavings)loggedUser.getAccounts().get(j)).getInterestRate())*100) + " %";
            }            
        }
        accounts = new JTable(tableData, tableHeaders);
        JScrollPane paneAccs = new JScrollPane(accounts);
        paneAccs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneAccs.setBounds(0, 0, 795, 550);
        bankPanel.add(paneAccs);
        setMenu(23);      
    }

    public void paneShowOpp(){
        if(bankPanel.getComponentCount()>0){
            bankPanel.remove(0);
        }
        if(loggedUser==null){
            JOptionPane.showMessageDialog(bankFrame, "Nie wybrano aktywnego użytkownika!", "Brak użytkownika", JOptionPane.WARNING_MESSAGE);
            paneShowUsers();
        }
        int userOps = 0;
        for(Operation o:operations){
            if (o.getUserOpp()==loggedUser.getClientId()) userOps++;
        }
        String tableHeaders[] = {"ID", "Date", "From Account", "To Account", "Value", "Title"};
        String tableData[][] = new String[userOps][6];
        int op = 0;
        for(Operation o:operations){
            if (o.getUserOpp()==loggedUser.getClientId()){
                tableData[op][0] = String.valueOf(o.getOppId());
                tableData[op][1] = o.getOppDate();
                tableData[op][2] = String.valueOf(o.getOppAccFrom());
                tableData[op][3] = String.valueOf(o.getOppAccTo());
                tableData[op][4] = String.valueOf(o.getOppValue());
                tableData[op][5] = o.getOppTitle();
                op++;
            }
        }
        JTable operationsList = new JTable(tableData, tableHeaders);
        operationsList.getColumnModel().getColumn(0).setPreferredWidth(100);
        operationsList.getColumnModel().getColumn(1).setPreferredWidth(150);
        operationsList.getColumnModel().getColumn(2).setPreferredWidth(100);
        operationsList.getColumnModel().getColumn(3).setPreferredWidth(100);
        operationsList.getColumnModel().getColumn(4).setPreferredWidth(100);
        operationsList.getColumnModel().getColumn(5).setPreferredWidth(245);
        JScrollPane paneOpps = new JScrollPane(operationsList);
        paneOpps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneOpps.setBounds(0, 0, 795, 550);
        bankPanel.add(paneOpps);
        
    }
    
    public void actionMsgAbout(){
        JOptionPane.showMessageDialog(bankFrame,
                        "Aplikacja Bankowa\n\n"
                                + "Projekt w ramach przedmiotu Komunikacja Człowiek-Komputer\n\n"
                                + "Autorzy:\n"
                                + "- Paulina Chludzińska\n"
                                + "- Paweł Golec\n\n"
                                + "Wydział Informatyki, Politechnika Białostocka, 2018/19",
                        bankTitle,
                        JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actionUserAdd(){
        String newName = JOptionPane.showInputDialog(bankFrame, "Podaj nazwę użytkownika", "Dodawanie użytkownika", JOptionPane.QUESTION_MESSAGE);
        if (newName != null) {
            if (!newName.isEmpty()) {
                clients.add(new Client(newName));
                paneShowUsers();
                setMenu(11);
            } else {
                JOptionPane.showMessageDialog(bankFrame, "Nie podano nazwy użytkownika!", "Dodawanie użytkownika", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(bankFrame, "Anulowane dodawanie użytkownika!", "Dodawanie użytkownika", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void actionUserDel(){
        int toDel = -1;
        if (users.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(bankFrame, "Nie wybrałeś użytkownika!", "Usuwanie użytkownika", JOptionPane.WARNING_MESSAGE);
        } else {
            int userID = Integer.parseInt(users.getValueAt(users.getSelectedRow(), 0).toString());
            for (Client d : clients) {
                if (d.getClientId() == userID) {
                    toDel = clients.indexOf(d);
                }
            }
        }
        if (toDel >= 0) {
            if (clients.get(toDel).equals(loggedUser)) {
                bankFrame.setTitle(bankTitle + " - brak aktywnego użytkownika");
                loggedUser = null;
            }
            clients.remove(toDel);
            paneShowUsers();
            setMenu(12);
        }
    }
    
    public void actionUserActive(){
        if(users.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(bankFrame, "Nie wybrałeś użytkownika!", "Wybór użytkownika", JOptionPane.WARNING_MESSAGE);
            } else {
                int userID = Integer.parseInt(users.getValueAt(users.getSelectedRow(), 0).toString());
                for(Client d:clients)
                    if(d.getClientId() == userID) loggedUser = d;
                bankFrame.setTitle(bankTitle + " - " + loggedUser.getName());
                JOptionPane.showMessageDialog(bankFrame, "Wybrano aktywnego użytkownika: "+loggedUser.getName(), "Wybór użytkownika", JOptionPane.INFORMATION_MESSAGE);
                setMenu(13);
            }
    }
    
    public void actionAccAdd(){
        String[] accType = {"Normal Account", "Credit Account", "Savings Account"};
        String newAcc = (String) JOptionPane.showInputDialog(bankFrame, "Wybierz rodzaj konta", "Nowe konto", JOptionPane.QUESTION_MESSAGE, null, accType, accType[0]);
        if (newAcc != null){
            if(newAcc=="Normal Account"){
                clients.get(clients.indexOf(loggedUser)).newAccount(new AccountNormal(loggedUser.getClientId()));
            }
            if(newAcc=="Credit Account"){
                String newCredit = JOptionPane.showInputDialog(bankFrame, "Podaj limit kredytowy:", "Nowe konto", JOptionPane.QUESTION_MESSAGE);
                clients.get(clients.indexOf(loggedUser)).newAccount(new AccountCredit(loggedUser.getClientId(), Double.valueOf(newCredit)));
            }
            if(newAcc=="Savings Account"){
                String newInterest = JOptionPane.showInputDialog(bankFrame, "Podaj oprocenotwanie konta:", "Nowe konto", JOptionPane.QUESTION_MESSAGE);
                clients.get(clients.indexOf(loggedUser)).newAccount(new AccountSavings(loggedUser.getClientId(), Double.valueOf(newInterest)/100)); 
            }
            paneShowAcc();
            setMenu(21);
        } else {
            JOptionPane.showMessageDialog(bankFrame, "Anulowane dodawanie konta!", "Nowe konto", JOptionPane.WARNING_MESSAGE);
        }  
    }
    
    public void actionAccDel(){
        int toDel = -1;
        if (accounts.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(bankFrame, "Nie wybrałeś konta!", "Usuwanie konta", JOptionPane.WARNING_MESSAGE);
        } else {
            int accID = Integer.parseInt(accounts.getValueAt(accounts.getSelectedRow(), 0).toString());
            for(Account a : loggedUser.getAccounts()){
                if(a.getId() == accID){
                    toDel = loggedUser.getAccounts().indexOf(a);
                }
            }
        }
        if (toDel >= 0){
            clients.get(clients.indexOf(loggedUser)).getAccounts().remove(toDel);
            paneShowAcc();
            setMenu(22);
        }
    }
    
    public void actionOppAdd(){
        String[] listUserAcc = new String[loggedUser.getAccounts().size()];
        for(int i=0;i<loggedUser.getAccounts().size();i++){
            listUserAcc[i] = String.valueOf(loggedUser.getAccounts().get(i).getId());
        }
        String selToAcc = (String) JOptionPane.showInputDialog(bankFrame, "Wybierz konto do operacji", "Wpłata na konto", JOptionPane.QUESTION_MESSAGE, null, listUserAcc, listUserAcc[0]);
        if(selToAcc == null) return;
        String newFunds = JOptionPane.showInputDialog(bankFrame, "Wartość środków do wpłacenia na konto:", "Wpłata na kotno", JOptionPane.QUESTION_MESSAGE);
        if(newFunds == null) return;
        for(Client c:clients){
            for(Account a:c.getAccounts()){
                if(a.getId()==Integer.valueOf(selToAcc)){                    
                    a.setBalance(Double.valueOf(newFunds));
                    a.addOperation();                    
                    operations.add(new Operation(loggedUser.getClientId(), 0, Integer.valueOf(selToAcc), Double.valueOf(newFunds), "Doładowanie"));
                    break;
                }   
            }
        }
        paneShowOpp();
    } 
    
    public void setMenu(int opt){
        // opt values:
        //  0 - starting conditions, 
        // 11 - first menu (User), first option (Add) 
        // 23 - second menu (Acc), third option (Show)
        // etc...
        switch(opt){
            case 0:{
                menuUserAdd.setEnabled(true);
                if(clients.isEmpty()) {
                    menuUserDel.setEnabled(false);
                    menuUserActive.setEnabled(false);
                    menuUserShow.setEnabled(false);
                } else {
                    menuUserDel.setEnabled(true);
                    menuUserActive.setEnabled(true);
                    menuUserShow.setEnabled(true);
                }
                menuAccAdd.setEnabled(false);
                menuAccDel.setEnabled(false);
                menuAccShow.setEnabled(false);
                menuOppAdd.setEnabled(false);
                menuOppWithdraw.setEnabled(false);
                menuOppTransfer.setEnabled(false);
                menuOppShow.setEnabled(false);
            } break;
            case 11:{
                menuUserDel.setEnabled(true);
                menuUserActive.setEnabled(true);
                menuUserShow.setEnabled(true);
            } break;
            case 12:{
                if(clients.isEmpty()) {
                    menuUserDel.setEnabled(false);
                    menuUserActive.setEnabled(false);
                    menuUserShow.setEnabled(false);
                }
                if(loggedUser==null){
                    menuOppAdd.setEnabled(false);
                    menuOppWithdraw.setEnabled(false);
                    menuOppTransfer.setEnabled(false);
                    menuOppShow.setEnabled(false);
                }
            }
            case 13:{
                if(loggedUser==null){
                    menuAccAdd.setEnabled(false);
                } else {
                    menuAccAdd.setEnabled(true);
                }
                menuAccDel.setEnabled(false);
                if(loggedUser==null || loggedUser.getAccounts().isEmpty()){
                    menuAccShow.setEnabled(false);
                } else {
                    menuAccShow.setEnabled(true);
                }
                menuOppAdd.setEnabled(true);
                menuOppWithdraw.setEnabled(true);
                menuOppShow.setEnabled(true);
            } break;
            case 14:{
                menuUserAdd.setEnabled(true);
                if(clients.isEmpty()) {
                    menuUserDel.setEnabled(false);
                    menuUserActive.setEnabled(false);
                    menuUserShow.setEnabled(false);
                } else {
                    menuUserDel.setEnabled(true);
                    menuUserActive.setEnabled(true);
                    menuUserShow.setEnabled(true);
                }
                menuAccDel.setEnabled(false);
                if(loggedUser==null || loggedUser.getAccounts().isEmpty()){
                    menuAccShow.setEnabled(false);
                } else {
                    menuAccShow.setEnabled(true);
                }
            }  break;
            case 21:{
                menuAccDel.setEnabled(true);
                menuAccShow.setEnabled(true);
            } break;
            case 22:{
                if(loggedUser.getAccounts().isEmpty()){
                    menuAccDel.setEnabled(false);
                    menuAccShow.setEnabled(false);
                } else {
                    menuAccDel.setEnabled(true);
                    menuAccShow.setEnabled(true);
                }
            } break;
            case 23:{
                menuUserAdd.setEnabled(false);
                menuUserDel.setEnabled(false);
                menuUserActive.setEnabled(false);
                menuAccAdd.setEnabled(true);
                if(loggedUser.getAccounts().isEmpty()){
                    menuAccDel.setEnabled(false);
                    menuAccShow.setEnabled(false);
                } else {
                    menuAccDel.setEnabled(true);
                    menuAccShow.setEnabled(true);
                }
            } break;
        }
    }    
    
    public void createData(){
        //wypelnienie przykladowymi danymi do testow
        clients.add(new Client("Pawel"));
        clients.get(0).newAccount(new AccountNormal(clients.get(0).getClientId()));
	clients.get(0).newAccount(new AccountNormal(clients.get(0).getClientId()));
        clients.get(0).newAccount(new AccountCredit(clients.get(0).getClientId(), 400000));
        clients.get(0).newAccount(new AccountSavings(clients.get(0).getClientId(), 0.05));
        operations.add(new Operation(1010000, 0, 1011001, 34000, "Wypłata z pracy"));
        clients.get(0).getAccounts().get(0).setBalance(34000);
        clients.get(0).getAccounts().get(0).addOperation();
        operations.add(new Operation(1010000, 0, 1011002, 50000, "Wygrana LOTTO"));
        clients.get(0).getAccounts().get(1).setBalance(50000);
        clients.get(0).getAccounts().get(1).addOperation();
        operations.add(new Operation(1010000, 0, 1013003, 20000, "Przyanany kredyt"));
        clients.get(0).getAccounts().get(2).setBalance(20000);
        clients.get(0).getAccounts().get(2).addOperation();
        operations.add(new Operation(1010000, 0, 1016004, 10000, "Oszczedzanie"));
        clients.get(0).getAccounts().get(3).setBalance(10000);
        clients.get(0).getAccounts().get(3).addOperation();
        operations.add(new Operation(1010000, 1011002, 1016004, 40000, "Oszczedzanie z wygranej"));
        clients.get(0).getAccounts().get(1).setBalance(-40000);
        clients.get(0).getAccounts().get(3).setBalance(40000);
        clients.get(0).getAccounts().get(1).addOperation();
        clients.get(0).getAccounts().get(3).addOperation();
	clients.add(new Client("Paulina"));
        clients.get(1).newAccount(new AccountNormal(clients.get(1).getClientId()));
	clients.get(1).newAccount(new AccountNormal(clients.get(1).getClientId()));
	operations.add(new Operation(1020000, 0, 1021005, 55000, "Zysk ze sprzedazy"));
        clients.get(1).getAccounts().get(0).setBalance(55000);
        clients.get(1).getAccounts().get(0).addOperation();
        operations.add(new Operation(1020000, 1021005, 1011001, 10000, "Splata pozyczki"));
        clients.get(0).getAccounts().get(0).setBalance(10000);
        clients.get(1).getAccounts().get(0).setBalance(-10000);
        clients.get(0).getAccounts().get(0).addOperation();
        clients.get(1).getAccounts().get(0).addOperation();        
        //pawel    1010000 - 1011001, 1011002, 1013003, 1016004
        //paulina  1020000 - 1021005, 1021006
        clients.add(new Client("Adam"));
        clients.get(2).newAccount(new AccountNormal(clients.get(2).getClientId()));
	clients.get(2).newAccount(new AccountNormal(clients.get(2).getClientId()));
        clients.get(2).newAccount(new AccountSavings(clients.get(2).getClientId(), 0.01));
	clients.add(new Client("Alicja"));
        clients.get(3).newAccount(new AccountNormal(clients.get(3).getClientId()));
	clients.get(3).newAccount(new AccountNormal(clients.get(3).getClientId()));
        clients.get(3).newAccount(new AccountCredit(clients.get(3).getClientId(), 20000));
	clients.add(new Client("Bartek"));
        clients.get(4).newAccount(new AccountNormal(clients.get(4).getClientId()));
	clients.get(4).newAccount(new AccountNormal(clients.get(4).getClientId()));
        clients.get(4).newAccount(new AccountSavings(clients.get(4).getClientId(), 0.09));
	clients.add(new Client("Barbara"));
        clients.get(5).newAccount(new AccountNormal(clients.get(5).getClientId()));
	clients.get(5).newAccount(new AccountNormal(clients.get(5).getClientId()));
	clients.add(new Client("Cezary"));
        clients.get(6).newAccount(new AccountNormal(clients.get(6).getClientId()));
	clients.get(6).newAccount(new AccountNormal(clients.get(6).getClientId()));
        clients.get(6).newAccount(new AccountCredit(clients.get(6).getClientId(), 543000));
        clients.get(6).newAccount(new AccountSavings(clients.get(6).getClientId(), 0.04));
	clients.add(new Client("Damian"));
        clients.get(7).newAccount(new AccountNormal(clients.get(7).getClientId()));
	clients.get(7).newAccount(new AccountNormal(clients.get(7).getClientId()));
	clients.add(new Client("Dorota"));
        clients.get(8).newAccount(new AccountNormal(clients.get(8).getClientId()));
	clients.get(8).newAccount(new AccountNormal(clients.get(8).getClientId()));
        clients.get(8).newAccount(new AccountSavings(clients.get(8).getClientId(), 0.06));
	clients.add(new Client("Eryk"));
        clients.get(9).newAccount(new AccountNormal(clients.get(9).getClientId()));
	clients.get(9).newAccount(new AccountNormal(clients.get(9).getClientId()));
        clients.get(9).newAccount(new AccountCredit(clients.get(9).getClientId(), 92000));
	clients.add(new Client("Ewa"));
        clients.get(10).newAccount(new AccountNormal(clients.get(10).getClientId()));
	clients.get(10).newAccount(new AccountNormal(clients.get(10).getClientId()));
        clients.get(10).newAccount(new AccountSavings(clients.get(10).getClientId(), 0.08));
	clients.add(new Client("Fabian"));
        clients.get(11).newAccount(new AccountNormal(clients.get(11).getClientId()));
	clients.get(11).newAccount(new AccountNormal(clients.get(11).getClientId()));
	clients.add(new Client("Fiona"));
        clients.get(12).newAccount(new AccountNormal(clients.get(12).getClientId()));
	clients.get(12).newAccount(new AccountNormal(clients.get(12).getClientId()));
        clients.get(12).newAccount(new AccountCredit(clients.get(12).getClientId(), 1742000));
        clients.get(12).newAccount(new AccountSavings(clients.get(12).getClientId(), 0.03));
	clients.add(new Client("Grzegorz"));
        clients.get(13).newAccount(new AccountNormal(clients.get(13).getClientId()));
	clients.get(13).newAccount(new AccountNormal(clients.get(13).getClientId()));
	clients.add(new Client("Gosia"));
        clients.get(14).newAccount(new AccountNormal(clients.get(14).getClientId()));
	clients.get(14).newAccount(new AccountNormal(clients.get(14).getClientId()));
        clients.get(14).newAccount(new AccountSavings(clients.get(14).getClientId(), 0.11));
        
    }
}    

  
