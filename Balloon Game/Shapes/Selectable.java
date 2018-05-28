package Shapes;

/**
 * Deniz Alkislar
 * Selectable Interface 1.0
 * Ankara - 01.03.2016
 */ 

public interface Selectable
{    
    // methods
    boolean getSelected();
    Shape contains (int x, int y);
    void setSelected (boolean selected);
}
