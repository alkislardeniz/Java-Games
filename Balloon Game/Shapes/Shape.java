package Shapes;

/**
 * Deniz Alkislar
 * Shape Class 1.0
 * Ankara - 01.03.2016
 */ 

public abstract class Shape implements Locatable
{  
    // properties
    protected int x;
    protected int y;
  
    // constructors
    public Shape(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
  
    // methods
    public abstract double getArea();
    public abstract String getName();
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}