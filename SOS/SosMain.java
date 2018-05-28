import javax.swing.*;
import cs101.sosgame.*;

/**
 * Deniz Alkislar
 * SosMain 1.0
 * Ankara - 29.03.2016
 */ 

public class SosMain
{
    public static void main (String[] args)
    {
        int size = 3;
        
        // program code
        JFrame f = new JFrame("SOS Game");
        
        Object[] possibilities = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String s = (String)JOptionPane.showInputDialog(
                    f,
                    "Board Size:",
                    "Create your board",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "3");
        if (s != null) {
            size = Integer.parseInt(s);
        }
        
        String p1 = (String) JOptionPane.showInputDialog (f,
                                                   "Please enter first player's name:", 
                                                   "Create your board", JOptionPane.INFORMATION_MESSAGE, null, null, "P1");
        String p2 = (String) JOptionPane.showInputDialog (f,
                                                    "Please enter second player's name:",
                                                    "Create your board", JOptionPane.INFORMATION_MESSAGE, null, null, "P2");
        SOS newGame = new SOS(size);

        f.add(new SOSGUIPanel(newGame, p1, p2));
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setResizable (false);
        f.setVisible(true);
    }   
}