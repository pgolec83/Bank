package app;

import app.gui.LanternaGui;
import app.model.*;

import java.util.*;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;

public class Application {
    
    public static void main(String [] args){
        GUIScreen bankScreen = TerminalFacade.createGUIScreen();   
        LanternaGui bankGui = new LanternaGui(bankScreen);
        
	List<Client> clients = new LinkedList<Client>();
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
        
        bankGui.w1_welcome();
        int x = bankGui.menu();
        //bankGui.w2_klienci(clients);
        //clients = bankGui.w3_dodaj_klienta(clients);
        //bankGui.w2_klienci(clients);
        bankScreen.getScreen().stopScreen(); 
    }
}
