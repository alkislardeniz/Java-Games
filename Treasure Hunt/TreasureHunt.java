import javax.swing.JFrame;

/**
 * Deniz Alkislar
 * TreasureHunt Class 1.0
 * Ankara - 12.03.2016
 */

public class TreasureHunt
{
    public static void main (String[] args)
    {
        //program
        JFrame frame = new GameFrame();
        frame.setTitle ("Treasure Hunt Game");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);
    }   
}