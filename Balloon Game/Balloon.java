import Shapes.Circle;
import Shapes.Drawable;

import java.awt.*;

/**
 * Deniz Alkislar
 * Balloon Class 1.0
 * Ankara - 17.03.2016
 */ 

public class Balloon extends Circle implements Drawable
{
    // properties
    private static final int RADIUS = 25;
    private Color color;
    
    // constructors
    public Balloon (int x, int y)
    {
        super(RADIUS, x, y);
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        
        color = new Color (r, g, b);
    }
    
    // methods
    public void grow()
    {
        if (super.radius != 100) {
           super.radius++;
        }
        else {
           super.setSelected(true); 
        }
    }
    
    public Color getColor()
    {
        return color;
    }

    public void draw (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.drawOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }   
}
