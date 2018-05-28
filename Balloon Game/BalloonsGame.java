import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 * Deniz Alkislar
 * BalloonsGame 1.0
 * Ankara - 17.03.2016
 */ 

public class BalloonsGame
{
    public static void main (String[] args)
    {
        // program code
        JFrame frame = new JFrame();

        frame.add(new JLabel(new ImageIcon(BalloonsGame.class.getResource("Icons/logo.png"), BorderLayout.NORTH)));
        frame.add(new BalloonsGamePanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle ("Balloons Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setVisible (true);
    }   
}