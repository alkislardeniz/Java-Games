package Shapes;

/**
 * Deniz Alkislar
 * Circle Class 1.0
 * Ankara - 01.03.2016
 */ 

public class Circle extends Shape implements Selectable
{  
    public static final String TYPE = "Circle";
    
    // properties
    protected int radius;
    protected boolean selected;
  
    // constructors
    public Circle (int radius, int x, int y)
    {
        super(x, y);
        this.radius = radius;
        selected = false;
    }
  
    // methods
    public double getArea()
    {
        return Math.round( Math.PI * radius * radius);
    }
    
    public String getName()
    {
        return TYPE;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public void setRadius(int radius)
    {
        this.radius = radius;
    }
    
    public boolean getSelected()
    {
        return selected;
    }
    
    public Shape contains (int x, int y)
    {
        if ((x - getX()) * (x - getX()) + (y - getY()) * (y - getY()) <= radius * radius) {
           return this;
        }
        return null;
    }
  
    public void setSelected (boolean selected)
    {
        this.selected = selected;
    }
    
    public String toString()
    {
        return "Shape Type: " + getName() + "\n" +
               "Radius: " + getRadius() + "\n" +
               "Area: " + getArea() + "\n" +
               "Location: " + "(" + super.getX() + ", " + super.getY() + ")" + "\n" +
               "Selected: " + this.selected + "\n" + "\n";
    }  
}