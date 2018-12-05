package app;

import app.gui.LanternaGui;
import app.model.*;

import java.util.*;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;

public class Application {
    
    public static void main(String [] args){
        int x = -1;
        GUIScreen bankScreen = TerminalFacade.createGUIScreen();   
        LanternaGui bankGui = new LanternaGui(bankScreen);
        
	List<Client> clients = new LinkedList<>();
	clients.add(new Client("Pawel"));
	clients.add(new Client("Paulina"));
	for(Client c:clients){
            if (c.getClientId() == 101){
		c.newAccount(new AccountNormal(((c.getClientId()*1000)+100)));
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountCredit(200, 400000));
		c.newAccount(new AccountSavings(300, 0.80));				
            }
	}
        
        bankGui.welcome();
        while(x!=0){
            x = bankGui.menu();
            switch(x){
                case 1:{
                    bankGui.w1_dodaj_klienta(clients);
                } break;
                case 2:{
                    //clients = 
                    bankGui.w2_usun_uzytkownika(clients);
                } break;
                case 3:{
                    bankGui.w3_klienci(clients);
                } break;
                case 4:{
                    bankGui.w4_wybierz_klienta(clients);
                } break;
            }   
        }
        bankScreen.getScreen().stopScreen(); 
    }
}
