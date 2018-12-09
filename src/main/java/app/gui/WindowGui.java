package app.gui;

import app.model.Client;
import javax.swing.JFrame;

public class WindowGui extends JFrame {
    
    public WindowGui(){
        super("Zajebista aplikacja bankowa!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    
}
