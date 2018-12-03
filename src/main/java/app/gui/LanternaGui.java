package app.gui;

import app.model.Client;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.EmptySpace;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui.GUIScreen;
import java.util.List;

public class LanternaGui {
    public GUIScreen bankScreen;
    
    public LanternaGui(GUIScreen screen){
        this.bankScreen = screen;
        bankScreen.getScreen().startScreen();
    }
    
    Button exitButton = new Button("Wyjdz", new Action(){
       @Override
       public void doAction(){
           bankScreen.getActiveWindow().close();
       } 
    });
    
    public int menu(){
        int choice = 1;
        Window windowMenu = new Window("BANK MENU");
        Label lbl_menu = new Label("Wybierz operacje:");
        Button exitButton = new Button("Wyjdz", new Action(){
            @Override
            public void doAction(){
                windowMenu.close();
            } 
        });
        
        exitButton.setAlignment(Component.Alignment.BOTTOM_RIGHT);
        windowMenu.addComponent(exitButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowMenu, GUIScreen.Position.FULL_SCREEN);
        return choice;
    }
    
    
    
    public void w1_welcome() {
        
        Window windowWelcome = new Window("Witaj w aplikacji BANK");        
        windowWelcome.setSoloWindow(true);
        Label lbl_projekt = new Label("Projekt w ramach przedmiotu Komunikacja Człowiek-Komputer.");
        Label lbl_autorzy = new Label("Grupa: PS1   Autorzy: Paulina Chludzińska, Paweł Golec");
        Button okButton = new Button("START", new Action(){
            @Override
            public void doAction(){
                windowWelcome.close();
            }
        });
        windowWelcome.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(lbl_projekt, LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(lbl_autorzy, LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(okButton, LinearLayout.GROWS_HORIZONTALLY);
        bankScreen.showWindow(windowWelcome, GUIScreen.Position.CENTER);
    }
  
    public void w2_klienci(List<Client> lista) {
        final Window windowKlienci = new Window("BANK");
        windowKlienci.setSoloWindow(true);
        Label lbl_klienci = new Label("Klienci naszego banku:");
        Button okButton = new Button("Zamknij", new Action(){
            @Override
            public void doAction(){
                windowKlienci.close();
            }
        });
        windowKlienci.addComponent(lbl_klienci, LinearLayout.GROWS_VERTICALLY);
        windowKlienci.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);       
        for(Client l:lista){
            Label lbl_klient = new Label("Klient: " + l.getName());
            windowKlienci.addComponent(lbl_klient, LinearLayout.GROWS_VERTICALLY);
        }
        windowKlienci.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowKlienci.addComponent(okButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowKlienci, GUIScreen.Position.CENTER);        
    }
    
    public List<Client> w3_dodaj_klienta(List<Client> lista) {
        final Window windowDodaj = new Window("BANK");
        List<Client> lista_klientow = lista;
        windowDodaj.setSoloWindow(true);
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
                windowDodaj.close();
            }
        });
        windowDodaj.addComponent(lbl_dodaj, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(txt_dodaj, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(dodajButton, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(wyjdzButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowDodaj, GUIScreen.Position.CENTER);
        return lista_klientow;
    }
}
