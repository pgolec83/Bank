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
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.gui.component.Table;
import java.util.List;

public class LanternaGui {
    public GUIScreen bankScreen;
    public int choice;
    public String activeUser;
    
    public LanternaGui(GUIScreen screen){
        this.bankScreen = screen;
        bankScreen.getScreen().startScreen();
    }
    
    public int menu(){
        choice = 0;
        Window windowMenu = new Window("BANK MENU");
        Label lbl_menu = new Label("Wybierz operacje:");
        Label lbl_user_active = new Label(activeUser);
        
        Button choice1Button = new Button("Utwórz użytkownika", new Action(){
            @Override
            public void doAction(){
                choice = 1;
                windowMenu.close();
            }
        });
        
        Button choice2Button = new Button("Usuń użytkownika", new Action(){
            @Override
            public void doAction(){
                choice = 2;
                lbl_user_active.setText("User 2");
            }
        });
        
        Button choice3Button = new Button("Wyświetl konta użytkownika", new Action(){
            @Override
            public void doAction(){
                choice = 3;
                windowMenu.close();
            }
        });
        
        Button choice4Button = new Button("Wybierz aktywnego użytkownika", new Action(){
            @Override
            public void doAction(){
                choice = 4;
                windowMenu.close();
            }
        });
        
        Button choice5Button = new Button("Dodaj konto Normalne", new Action(){
            @Override
            public void doAction(){
                choice = 5;
            }
        });
        
        Button choice6Button = new Button("Dodaj konto Kredytowe", new Action(){
            @Override
            public void doAction(){
                choice = 6;    
            }
        });
        
        Button choice7Button = new Button("Dodaj konto Oszczędnościowe", new Action(){
            @Override
            public void doAction(){
                choice = 7;    
            }
        });
        
        Button exitButton = new Button("Wyjdz", new Action(){
            @Override
            public void doAction(){
                choice = 0;
                windowMenu.close();
            }
        });
        
        Panel panelActiveUser = new Panel("Aktywny użytkownik:", Orientation.HORISONTAL);
        lbl_user_active.setAlignment(Component.Alignment.TOP_LEFT);
        panelActiveUser.addComponent(lbl_user_active, LinearLayout.GROWS_HORIZONTALLY);
        windowMenu.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        panelActiveUser.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(panelActiveUser, LinearLayout.GROWS_VERTICALLY);
        windowMenu.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        lbl_menu.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(lbl_menu, LinearLayout.GROWS_VERTICALLY);
        windowMenu.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        choice1Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice1Button, LinearLayout.GROWS_VERTICALLY);
        choice2Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice2Button, LinearLayout.GROWS_VERTICALLY);
        choice3Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice3Button, LinearLayout.GROWS_VERTICALLY);
        choice4Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice4Button, LinearLayout.GROWS_VERTICALLY);
        choice5Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice5Button, LinearLayout.GROWS_VERTICALLY);
        choice6Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice6Button, LinearLayout.GROWS_VERTICALLY);
        choice7Button.setAlignment(Component.Alignment.TOP_LEFT);
        windowMenu.addComponent(choice7Button, LinearLayout.GROWS_VERTICALLY);
        windowMenu.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        exitButton.setAlignment(Component.Alignment.BOTTOM_RIGHT);
        windowMenu.addComponent(exitButton, LinearLayout.GROWS_VERTICALLY);
        
        bankScreen.showWindow(windowMenu, GUIScreen.Position.NEW_CORNER_WINDOW);
        return choice;
    }

    public void welcome() {
        
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
  
    public void w3_klienci(List<Client> lista) {
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
    
    public List<Client> w1_dodaj_klienta(List<Client> lista) {
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

    public void  w4_wybierz_klienta(List<Client> lista){
        final Window windowWybierz = new Window("BANK");
        windowWybierz.setSoloWindow(false);
        Label lbl_wybierz = new Label("Wybierz aktywnego użytkownika:");
        Table tableWybierz = new Table(lista.size());
        for(Client c:lista){
            tableWybierz.addRow(new Button(c.getName(), new Action(){
                @Override
                public void doAction(){
                    activeUser = c.getName();
                    windowWybierz.close();
                }
            }));
        }
        lbl_wybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowWybierz.addComponent(lbl_wybierz, LinearLayout.GROWS_VERTICALLY);
        windowWybierz.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        tableWybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowWybierz.addComponent(tableWybierz, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowWybierz, GUIScreen.Position.CENTER);
    }

}