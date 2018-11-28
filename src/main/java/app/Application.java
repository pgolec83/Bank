package app;

import java.util.*;
import app.model.*;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.EmptySpace;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.terminal.TerminalSize;


public class Application {
    
    public static void w1_welcome(GUIScreen guiScreen) {
        final Window window = new Window("Witaj w aplikacji BANK");        
        window.setSoloWindow(true);
        Label lbl_projekt = new Label("Projekt w ramach przedmiotu Komunikacja Człowiek-Komputer.");
        Label lbl_autorzy = new Label("Grupa: PS1   Autorzy: Paulina Chludzińska, Paweł Golec");
        Button okButton = new Button("START", new Action(){
            @Override
            public void doAction(){
                window.close();
            }
        });
        window.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        window.addComponent(lbl_projekt, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(lbl_autorzy, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        window.addComponent(okButton, LinearLayout.GROWS_HORIZONTALLY);
        guiScreen.showWindow(window, GUIScreen.Position.CENTER);
    }

    public static void w2_klienci(GUIScreen guiScreen, List<Client> lista) {
        final Window window = new Window("BANK");
        window.setSoloWindow(true);
        Label lbl_klienci = new Label("Klienci naszego banku:");
        Button okButton = new Button("Zamknij", new Action(){
            @Override
            public void doAction(){
                window.close();
            }
        });
        
        window.addComponent(lbl_klienci, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);       
        for(Client l:lista){
            Label lbl_klient = new Label("Klient: " + l.getName());
            window.addComponent(lbl_klient, LinearLayout.GROWS_VERTICALLY);
        }
        window.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        window.addComponent(okButton, LinearLayout.GROWS_VERTICALLY);
        
        guiScreen.showWindow(window, GUIScreen.Position.CENTER);        
    }
    
    public static List<Client> w3_dodaj_klienta(GUIScreen guiScreen, List<Client> lista) {
        final Window window = new Window("BANK");
        List<Client> lista_klientow = lista;
        
        
        
        window.setSoloWindow(true);
        Label lbl_dodaj = new Label("Wpisz nazwę nowego klienta:");
        TextBox txt_dodaj = new TextBox();
        txt_dodaj.setPreferredSize(new TerminalSize(10,1));
        Button dodajButton = new Button("Dodaj", new Action(){
            @Override
            public void doAction(){
                lista_klientow.add(new Client(txt_dodaj.getText()));
                txt_dodaj.setText("");
            }
        });
        Button wyjdzButton = new Button("Wyjdz", new Action(){
            @Override
            public void doAction(){
                window.close();
            }
        });
        
        
        window.addComponent(lbl_dodaj, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(txt_dodaj, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        window.addComponent(dodajButton, LinearLayout.GROWS_VERTICALLY);
        window.addComponent(wyjdzButton, LinearLayout.GROWS_VERTICALLY);
        
        guiScreen.showWindow(window, GUIScreen.Position.CENTER);
        return lista_klientow;
    }
    
    public static void main(String [] args){
        final GUIScreen guiScreen = TerminalFacade.createGUIScreen();   
        guiScreen.getScreen().startScreen();
        
	List<Client> clients = new LinkedList<Client>();
	clients.add(new Client("Pawel"));
	clients.add(new Client("Agnieszka"));
	clients.add(new Client("Wieslaw"));
	for(Client c:clients){
            if (c.getClientId() == 101){
		c.newAccount(new AccountNormal(((c.getClientId()*1000)+100)));
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountNormal(100));
		c.newAccount(new AccountCredit(200, 400000));
		c.newAccount(new AccountSavings(300, 0.80));				
            }
	}
        w1_welcome(guiScreen);
        w2_klienci(guiScreen, clients);
        clients = w3_dodaj_klienta(guiScreen, clients);
        w2_klienci(guiScreen, clients);



        guiScreen.getScreen().stopScreen();

        
    }
}
