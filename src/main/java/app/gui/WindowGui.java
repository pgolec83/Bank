package app.gui;

import app.model.*;
import java.awt.Component;
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
    private String loggedUser = null;
    private JFrame bankFrame;
    private JMenuBar bankMenu;
    private JMenu menuBank,  menuUser, menuAcc, menuOpp;
    private JMenuItem menuBankAbout, menuBankExit;
    private JMenuItem menuUserAdd, menuUserDel, menuUserShow;
    private JMenuItem menuAccAdd, menuAccDel, menuAccShow;
    private JMenuItem menuOppAdd, menuOppWithdraw, menuOppTransfer, menuOppShow;
    private List<Client> clients;
    private Component[] componentList; 
    
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
	for(Client c:clients){
            if (c.getClientId() == 101){
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountCredit(200, 400000));
		c.newAccount(new AccountSavings(300, 0.80));				
            }
	}
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
        menuUserDel = new JMenuItem("Usuń użytkownika...");
        menuUserShow = new JMenuItem("Wyświetl użytkowników");
        menuUserShow.addActionListener((ActionEvent e) -> {
            paneShowUsers();
        });
        menuUser.add(menuUserAdd);
        menuUser.add(menuUserDel);
        menuUser.add(menuUserShow);
        menuAccAdd = new JMenuItem("Dodaj konto");
        menuAccAdd.setEnabled(false);
        menuAccDel = new JMenuItem("Usuń konto");
        menuAccDel.setEnabled(false);
        menuAccShow = new JMenuItem("Wyświetl konta użytkownika");
        menuAccShow.addActionListener((ActionEvent e) -> {
     
            
            
            
            //bankFrame.remove(0);
            //bankFrame.remove(0);
            //bankFrame.validate();
        });
        /*
        Component[] componentList = panelName.getComponents();

//Loop through the components
for(Component c : componentList){

    //Find the components you want to remove
    if(c instanceof JCheckBox){

        //Remove it
        clientPanel.remove(c);
    }
}

//IMPORTANT
panelName.revalidate();
panelName.repaint();
        
        
        */
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
        paneShowUsers();
    }
    
    public void paneShowUsers(){
        //bankFrame.removeAll();
        
        String tableHeaders[] = {"ID","Name","Created date","Accounts"};
        String tableData[][] = new String[clients.size()][4];
        for(int i=0;i<clients.size();i++){
            tableData[i][0] = String.valueOf(clients.get(i).getClientId());
            tableData[i][1] = clients.get(i).getName();
            tableData[i][2] = clients.get(i).getCreated();
            tableData[i][3] = String.valueOf(clients.get(i).getAccounts().size());
        }
        JTable users = new JTable(tableData, tableHeaders);
        users.getColumnModel().getColumn(0).setPreferredWidth(100);
        users.getColumnModel().getColumn(1).setPreferredWidth(395);
        users.getColumnModel().getColumn(2).setPreferredWidth(200);
        users.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane paneUsers = new JScrollPane(users);
        paneUsers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        paneUsers.setBounds(0, 0, 795, 550);
        
        bankFrame.add(paneUsers);
        
    }

     //JTable jt = new JTable(data, column);
       // JScrollPane sp = new JScrollPane(jt);

}    
        /* 
        final JTextField tf = new JTextField();
        tf.setBounds(10,30,200,20);
        frTest.add(tf);
        JButton btn = new JButton("Medenfaken!");
        btn.setBounds(10,50,200,20);
        frTest.add(btn);
        btn.addActionListener(new ActionListener(){
 
        });
        

       
        sp.setBounds(10,100,200,300);
        frTest.add(sp);
        DefaultListModel<String> lista = new DefaultListModel<>();

        JList<String> listaEl = new JList<>(lista);
        listaEl.setBounds(10, 410, 100, 100);
        frTest.add(listaEl);
        frTest.setLayout(null);
        frTest.setVisible(true);
        */

