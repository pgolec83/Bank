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
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LanternaGui {
    public GUIScreen bankScreen;
    public int choice;
    public String activeUser;
    
    public LanternaGui(GUIScreen screen){
        this.bankScreen = screen;
        bankScreen.getScreen().startScreen();
        activeUser = "brak";
    }
    
    public int menu(){
        choice = 0;
        Window windowMenu = new Window("BANK MENU");
        Label lbl_menu = new Label("Wybierz operacje:");
        Label lbl_user_active = new Label(activeUser);
        
        Button choice1Button = new Button("Utwórz użytkownika", () -> {
            choice = 1;
            windowMenu.close();
        });      
        Button choice2Button = new Button("Usuń użytkownika", () -> {
            choice = 2;
            windowMenu.close();
        });       
        Button choice3Button = new Button("Wyświetl konto użytkownika", () -> {
            choice = 3;
            windowMenu.close();
        });        
        Button choice4Button = new Button("Wybierz aktywnego użytkownika", () -> {
            choice = 4;
            windowMenu.close();
        });        
        Button choice5Button = new Button("Dodaj konto Normalne", () -> {
            choice = 5;
        });        
        Button choice6Button = new Button("Dodaj konto Kredytowe", () -> {
            choice = 6;
        });        
        Button choice7Button = new Button("Dodaj konto Oszczędnościowe", () -> {
            choice = 7;
        });        
        Button exitButton = new Button("WYJŚCIE", () -> {
            if(potwierdz("Opuszczasz aplikację BANK")){
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
        Button okButton = new Button("START", () -> {
            windowWelcome.close();
        });
        windowWelcome.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(lbl_projekt, LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(lbl_autorzy, LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowWelcome.addComponent(okButton, LinearLayout.GROWS_HORIZONTALLY);
        bankScreen.showWindow(windowWelcome, GUIScreen.Position.CENTER);
    }
    
    public List<Client> w1_dodaj_klienta(List<Client> lista) {
        final Window windowDodaj = new Window("BANK");
        List<Client> nowa_lista = lista;
        windowDodaj.setSoloWindow(true);
        Label lbl_dodaj = new Label("Wpisz nazwę nowego klienta:");
        TextBox txt_dodaj = new TextBox();
        txt_dodaj.setPreferredSize(new TerminalSize(10,1));
        Button dodajButton = new Button("Dodaj", () -> {
            if(txt_dodaj.getText().equals("")){
                MessageBox.showMessageBox(bankScreen, "", "Nie wpisano nazwy!");
            } else {
                nowa_lista.add(new Client(txt_dodaj.getText()));
                MessageBox.showMessageBox(bankScreen, "", "Dodano klienta: " + txt_dodaj.getText());
                txt_dodaj.setText("");
            }
        });
        Button wyjdzButton = new Button("Wyjdz", () -> {
            windowDodaj.close();
        });
        windowDodaj.addComponent(lbl_dodaj, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(txt_dodaj, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(dodajButton, LinearLayout.GROWS_VERTICALLY);
        windowDodaj.addComponent(wyjdzButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowDodaj, GUIScreen.Position.CENTER);
        return nowa_lista;
    }

    public List<Client> w2_usun_uzytkownika(List<Client> lista){
        List<Client> nowa_lista = lista;
        final Window windowUsun = new Window("BANK");
        windowUsun.setSoloWindow(false);
        Label lbl_wybierz = new Label("Wybierz użytkownika do usunięcia:");
        Table tableWybierz = new Table(lista.size());
        nowa_lista.forEach((c) -> {
            tableWybierz.addRow(new Button(c.getName(), () -> {
                if(potwierdz("Usuwanie użytkownika " + c.getName())){
                    if(activeUser.equals(c.getName())) activeUser = "brak";
                    MessageBox.showMessageBox(bankScreen, "", "Usunięto klienta: " + c.getName());
                    nowa_lista.remove(c);
                    windowUsun.close();
                }  
            }));
        });
        Button wyjdzButton = new Button("Wyjdz", () -> {
            windowUsun.close();
        });
        lbl_wybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowUsun.addComponent(lbl_wybierz, LinearLayout.GROWS_VERTICALLY);
        windowUsun.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        tableWybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowUsun.addComponent(tableWybierz, LinearLayout.GROWS_VERTICALLY);
        windowUsun.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowUsun.addComponent(wyjdzButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowUsun, GUIScreen.Position.CENTER);
        return nowa_lista;
    }    

    public void w3_klienci(List<Client> lista) {
        // docelowo wybór klienta z listy i wyswietlenie jego kont
        final Window windowKlienci = new Window("BANK");
        windowKlienci.setSoloWindow(true);
        Label lbl_klienci = new Label("Klienci naszego banku:");
        Button okButton = new Button("Zamknij", () -> {
            windowKlienci.close();
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
    
    public void  w4_wybierz_klienta(List<Client> lista){
        final Window windowWybierz = new Window("BANK");
        windowWybierz.setSoloWindow(false);
        Label lbl_wybierz = new Label("Wybierz aktywnego użytkownika:");
        Table tableWybierz = new Table(lista.size());
        for(Client c:lista){
            tableWybierz.addRow(new Button(c.getName(), () -> {
                activeUser = c.getName();
                windowWybierz.close();
            }));
        }
        lbl_wybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowWybierz.addComponent(lbl_wybierz, LinearLayout.GROWS_VERTICALLY);
        windowWybierz.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        tableWybierz.setAlignment(Component.Alignment.TOP_LEFT);
        windowWybierz.addComponent(tableWybierz, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowWybierz, GUIScreen.Position.CENTER);
    }
    
    public Boolean potwierdz(String text){
        final AtomicBoolean wybor = new AtomicBoolean();
        final Window windowPotwierdz = new Window("Potwierdzenie");
        windowPotwierdz.setSoloWindow(false);
        Label lbl_operacja = new Label(text);
        Label lbl_potwierdz = new Label("Czy jesteś pewien?");
        Button okButton = new Button("OK", () -> {
            wybor.set(true);
            windowPotwierdz.close();
        });
        Button cancelButton = new Button("Anuluj", () -> {
            wybor.set(false);
            windowPotwierdz.close();
        });
        windowPotwierdz.addComponent(lbl_operacja, LinearLayout.GROWS_VERTICALLY);
        windowPotwierdz.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowPotwierdz.addComponent(lbl_potwierdz, LinearLayout.GROWS_VERTICALLY);
        windowPotwierdz.addComponent(new EmptySpace(), LinearLayout.GROWS_VERTICALLY);
        windowPotwierdz.addComponent(okButton, LinearLayout.GROWS_VERTICALLY);
        windowPotwierdz.addComponent(cancelButton, LinearLayout.GROWS_VERTICALLY);
        bankScreen.showWindow(windowPotwierdz, GUIScreen.Position.CENTER);
        return wybor.get();
    }

}