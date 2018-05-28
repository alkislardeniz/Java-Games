import java.awt.*;
import javax.swing.*;
import cs101.sosgame.*;

/**
 * Deniz Alkislar
 * SOSCanvas Class 1.0
 * Ankara - 29.03.2016
 */ 

public class SOSCanvas extends JPanel
{
    // properties
    private SOS game;
    private int dimensions;
    private final double STRING_POSX = 2.5;
    private final double STRING_POSY = 1.5;
    public static final int BORDER_SPACE = 50;
    public static final int PANEL_WIDTH = 490;
    public static final int PANEL_HEIGHT = 500;
    private final ImageIcon bg = new ImageIcon(getClass().getResource("Icons/bg.png"));
    private int space;
        
    // constructors
    public SOSCanvas (SOS game)
    {
        this.game = game;
        dimensions = game.getDimension();
        space = (PANEL_HEIGHT - (2 * BORDER_SPACE)) / dimensions;
        setPreferredSize (new Dimension (PANEL_WIDTH, PANEL_HEIGHT));
        setBackground (Color.WHITE);
        setLayout  (new BorderLayout());
    }
  
    // methods
    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        g.drawImage(bg.getImage(), 0, 0, null);
        int xP = BORDER_SPACE;
        int yP = BORDER_SPACE;
            
        // draws horizontal lines
        for (int i = 0; i <= dimensions; i++)
        {
            g.drawLine (xP, yP, xP + (space * dimensions), yP);
            yP += space;
        }
        yP = BORDER_SPACE;
        
        // draws vertical lines
        for (int i = 0; i <= dimensions; i++)
        {
            g.drawLine (xP, yP, xP, yP + (space * dimensions));
            xP += space;
        } 
          
        // draws strings
        xP = BORDER_SPACE + ((int) ((space / STRING_POSX)));
        yP = BORDER_SPACE + ((int) ((space / STRING_POSY)));
        g.setFont (new Font ("Verdana", Font.PLAIN, space / 2));
        for (int i = 0; i < dimensions; i++)
        {            
            for (int j = 0; j < dimensions; j++)
            {
                String s = "" + game.getCellContents (i, j);
                                   
                if (s.equals("s")) {
                    g.setColor (Color.BLUE);
                } 
                else {
                    g.setColor (Color.RED);
                }
                
                if (!(s.equals("."))) {
                   g.drawString (s, xP, yP);
                }
                xP += space;

                if (j == dimensions - 1)
                {  
                    xP = BORDER_SPACE + ((int) ((space / STRING_POSX)));
                    yP += space;
                }
            } 
        }     
    }
    
    public int getSpace()
    {
        return space;
    }     
}