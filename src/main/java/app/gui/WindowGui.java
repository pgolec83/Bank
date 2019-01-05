package app.gui;

import app.model.Client;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;
import static javax.swing.JOptionPane.*;


public class WindowGui{
    private final String bankTitle = new String("Aplikacja bankowa - ");
    private JFrame bankFrame;
    private JMenuBar bankMenu;
    private JMenu menuBank,  menuUser, menuAcc, menuOpp;
    private JMenuItem menuBankAbout, menuBankExit;
    private JMenuItem menuUserAdd, menuUserDel, menuUserShow;
    private JMenuItem menuAccAdd, menuAccDel, menuAccShow;
    private JMenuItem menuOppAdd, menuOppWithdraw, menuOppTransfer, menuOppShow;
    
    public WindowGui(){
        bankFrame = new JFrame();
        bankFrame.setTitle(bankTitle + "brak zalogowanego użytkownika");
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
        menuBankExit = new JMenuItem("Wyjdż z aplikacji");
        menuBank.add(menuBankAbout);
        menuBank.addSeparator();
        menuBank.add(menuBankExit);
        menuBankExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        menuUserAdd = new JMenuItem("Dodaj użytkownika...");
        menuUserDel = new JMenuItem("Usuń użytkownika...");
        menuUserShow = new JMenuItem("Wyświetl użytkowników");
        menuUser.add(menuUserAdd);
        menuUser.add(menuUserDel);
        menuUser.add(menuUserShow);
        
        menuAccAdd = new JMenuItem("Dodaj konto");
        menuAccAdd.setEnabled(false);
        menuAccDel = new JMenuItem("Usuń konto");
        menuAccDel.setEnabled(false);
        menuAccShow = new JMenuItem("Wyświetl konta użytkownika");
        menuAccShow.setEnabled(false);
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
    }
    
    public void paneStart(){
        JLabel txt = new JLabel("Witajcie w aplikacji bankowej!!");
        bankFrame.add(txt);

        
    }
    
    public void paneSecond(){
        JLabel txt = new JLabel("Witajcie, w drugim panelu");
        bankFrame.removeAll();
        bankFrame.add(txt);
}    
    /*
          
        final JTextField tf = new JTextField();
        tf.setBounds(10,30,200,20);
        frTest.add(tf);
        
        
        JButton btn = new JButton("Medenfaken!");
        btn.setBounds(10,50,200,20);
        frTest.add(btn);
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                tf.setText("Madenfaken BIATCH!");
                JOptionPane.showMessageDialog(frTest, "FUCK!", "CUNT!", QUESTION_MESSAGE);
                
            }
        });
        
        JLabel lblHO = new JLabel("HO HO HO, BIATCH!");
        lblHO.setBounds(10,10,200,20);
        frTest.add(lblHO);
        
        
        String data[][]={
            {"101","Amit","67000"},
            {"102","Jail","83000"},
            {"101","Amit","67000"},
            {"102","Jail","83000"},
            {"103","Sachin","23000"}
        };
        String column[]={"ID","NAME","SALARY"};
        JTable jt = new JTable(data, column);
        
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(10,100,200,300);
        frTest.add(sp);
        
        DefaultListModel<String> lista = new DefaultListModel<>();
        lista.addElement("Dziwkoj 1");
        lista.addElement("Bicza 2");
        lista.addElement("Kotkoj 3");
        lista.addElement("Piesel 4");
        JList<String> listaEl = new JList<>(lista);
        listaEl.setBounds(10, 410, 100, 100);
        frTest.add(listaEl);
        
        frTest.setLayout(null);
        frTest.setVisible(true);
        
    }
    */
}
