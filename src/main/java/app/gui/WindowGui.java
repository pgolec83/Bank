package app.gui;

import app.model.*;
import app.model.AccountCredit;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import static javax.swing.JOptionPane.*;


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
    
    public WindowGui(){
        clients = new LinkedList<>();
        clients.add(new Client("Pawel"));
	clients.add(new Client("Paulina"));
	clients.add(new Client("Adam"));
	clients.add(new Client("Alicja"));
	clients.add(new Client("Bartek"));
	clients.add(new Client("Barbara"));
	clients.add(new Client("Cezary"));
	clients.add(new Client("Damian"));
	clients.add(new Client("Dorota"));
	clients.add(new Client("Eryk"));
	clients.add(new Client("Ewa"));
	clients.add(new Client("Fabian"));
	clients.add(new Client("Fiona"));
	clients.add(new Client("Grzegorz"));
	clients.add(new Client("Gosia"));
        Client k = clients.get(0);
	k.newAccount(new AccountNormal(k.getClientId()));
	k.newAccount(new AccountNormal(k.getClientId()));
	k.newAccount(new AccountCredit(k.getClientId(), 400000));
	k.newAccount(new AccountSavings(k.getClientId(), 0.80));
        
        bankFrame = new JFrame();
        bankFrame.setTitle(bankTitle + " - brak zalogowanego użytkownika");
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
        menuBankExit = new JMenuItem("Wyjdź z aplikacji");
        menuBank.add(menuBankAbout);
        menuBankAbout.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(bankFrame,
                    "Aplikacja Bankowa\n\n"
                            + "Projekt w ramach przedmiotu Komunikacja Człowiek-Komputer\n\n"
                            + "Autorzy:\n"
                            + "- Paulina Chludzińska\n"
                            + "- Paweł Golec\n\n"
                            + "Wydział Informatyki, Politechnika Białostocka, 2018/19",
                    bankTitle,
                    JOptionPane.INFORMATION_MESSAGE);
        });
        menuBank.addSeparator();
        menuBank.add(menuBankExit);
        menuBankExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        menuUserAdd = new JMenuItem("Dodaj użytkownika...");
        menuUserAdd.addActionListener((ActionEvent e) -> {
            String newName = JOptionPane.showInputDialog(bankFrame, "Podaj nazwę użytkownika", "Dodawanie użytkownika", JOptionPane.QUESTION_MESSAGE);
            if(newName != null){
                if(!newName.isEmpty()){
                    clients.add(new Client(newName));
                    paneShowUsers();
                } else {
                    JOptionPane.showMessageDialog(bankFrame, "Nie podano nazwy użytkownika!", "Dodawanie użytkownika", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(bankFrame, "Anulowane dodawanie użytkownika!", "Dodawanie użytkownika", JOptionPane.WARNING_MESSAGE);
            } 
        });
        menuUserDel = new JMenuItem("Usuń użytkownika...");
        menuUserDel.addActionListener((ActionEvent e) -> {
            int toDel = -1;
            if(users.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(bankFrame, "Nie wybrałeś użytkownika!", "Usuwanie użytkownika", JOptionPane.WARNING_MESSAGE);
            } else {
                int userID = Integer.parseInt(users.getValueAt(users.getSelectedRow(), (users.getSelectedColumn())-1).toString());
                for(Client d:clients)
                    if(d.getClientId() == userID) toDel = clients.indexOf(d);                                    
            }       
            if(toDel>0) {
                clients.remove(toDel);
                paneShowUsers();
            }
        });
        menuUserActive = new JMenuItem("Wybierz aktywnego użytkownika");
        menuUserActive.addActionListener((ActionEvent e) -> {
            if(users.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(bankFrame, "Nie wybrałeś użytkownika!", "Wybór użytkownika", JOptionPane.WARNING_MESSAGE);
            } else {
                int userID = Integer.parseInt(users.getValueAt(users.getSelectedRow(), (users.getSelectedColumn())-1).toString());
                for(Client d:clients)
                    if(d.getClientId() == userID) loggedUser = d;
                bankFrame.setTitle(bankTitle + " - " + loggedUser.getName());
                JOptionPane.showMessageDialog(bankFrame, "Wybrano aktywnego użytkownika: "+loggedUser.getName(), "Wybór użytkownika", JOptionPane.INFORMATION_MESSAGE);
                if(loggedUser.getAccounts().size()>0){
                    menuAccShow.setEnabled(true);
                }
                menuAccAdd.setEnabled(true);              
            } 
        });
        menuUserShow = new JMenuItem("Wyświetl użytkowników");
        menuUserShow.addActionListener((ActionEvent e) -> {
            paneShowUsers();
            menuUserAdd.setEnabled(true);
            menuUserDel.setEnabled(true);
            menuUserActive.setEnabled(true);
        });
        menuUser.add(menuUserAdd);
        menuUser.add(menuUserDel);
        menuUser.add(menuUserActive);
        menuUser.add(menuUserShow);
        menuAccAdd = new JMenuItem("Dodaj konto");
        menuAccAdd.setEnabled(false);
        menuAccDel = new JMenuItem("Usuń konto");
        menuAccDel.setEnabled(false);
        menuAccShow = new JMenuItem("Wyświetl konta użytkownika");
        menuAccShow.addActionListener((ActionEvent e) -> {
            //menuUserAdd.setEnabled(false);
            //menuUserDel.setEnabled(false);
            //menuUserActive.setEnabled(false);
            paneShowAcc();
        });
        //menuAccShow.setEnabled(false);
        menuAcc.add(menuAccAdd);
        menuAcc.add(menuAccDel);
        menuAcc.add(menuAccShow);
        menuOppAdd = new JMenuItem("Wpłata na konto");
        menuOppAdd.setEnabled(false);
        menuOppWithdraw = new JMenuItem("Wypłata z konta");
        menuOppWithdraw.setEnabled(false);
        menuOppTransfer = new JMenuItem("Przelew pomiędzy kontami");
        menuOppTransfer.setEnabled(false);
        menuOppShow = new JMenuItem("Wyświetl operacje na koncie");
        menuOppShow.setEnabled(false);
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
    }
    
    public void paneShowAcc(){
        if(bankPanel.getComponentCount()>0){
            bankPanel.remove(0);
        }
        if(loggedUser==null){
            JOptionPane.showMessageDialog(bankFrame, "Nie wybrano aktywnego użytkownika!", "Brak użytkownika", JOptionPane.WARNING_MESSAGE);
            paneShowUsers();
        }
        List<Account> listAcc = loggedUser.getAccounts();
        String tableHeaders[] = {"ID", "Account Type", "Balance", "Credit Limit", "Interest Rate", "Operations"};
        String tableData[][] = new String[listAcc.size()][6];
        for(int j=0;j<listAcc.size();j++){
            tableData[j][0] = String.valueOf(listAcc.get(j).getId());
            tableData[j][2] = String.valueOf(listAcc.get(j).getBalance());
            tableData[j][5] = String.valueOf(listAcc.get(j).getOperations().size());
            if(listAcc.get(j).getAccType()=="N"){
                tableData[j][1] = "Normal Account";
                tableData[j][3] = "n/d";
                tableData[j][4] = "n/d";
            }
            if(listAcc.get(j).getAccType()=="C"){
                tableData[j][1] = "Credit Account";
                tableData[j][3] = "n/d";
                tableData[j][4] = "n/d";
            }
            if(listAcc.get(j).getAccType()=="S"){
                tableData[j][1] = "Savings Account";
                tableData[j][3] = "n/d";
                tableData[j][4] = "n/d";
            }            
        }
        accounts = new JTable(tableData, tableHeaders);
        JScrollPane paneAccs = new JScrollPane(accounts);
        paneAccs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneAccs.setBounds(0, 0, 795, 550);
        bankPanel.add(paneAccs);
        
        
        
        
    }
}    

  
