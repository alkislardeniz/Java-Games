package Shapes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Deniz Alkislar
 * ShapeContainer Class 1.2
 * Ankara - 17.03.2016
 */ 

public class ShapeContainer implements Iterable<Shape>
{  
    // properties
    private ArrayList<Shape> shapes;

    // constructors 
    public ShapeContainer()
    {
        shapes = new ArrayList<>();
    }
    
    // methods
    @Override
    public Iterator<Shape> iterator() 
    {
        Iterator<Shape> it = new Iterator<Shape>()
        {
            int currentIndex = 0;
            
            @Override
            public boolean hasNext() 
            {
                return currentIndex < shapes.size();
            }
            
            @Override
            public Shape next() 
            {
                return shapes.get(currentIndex++);
            }
        };
        return it;
    }
   
    public void add (Shape s)
    {
        shapes.add(s);
    }
    
    public void removeSelected()
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Selectable)
            {
               if ( ( (Selectable)(shapes.get(i)) ).getSelected() )
               {
                  shapes.remove(i);
               }
            }
        }
    }
       
    public void setAllSelected()
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Selectable)
            {
                ((Selectable) shapes.get(i)).setSelected(true);   
            }
        }
    }
    
    public int selectAllAt (int x, int y)
    {
        int numberOfShapes;
        
        numberOfShapes = 0;
        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Selectable)
            {
               if ( ( (Selectable) shapes.get(i) ).contains(x, y) != null )
               {
                  ((Selectable) shapes.get(i)).setSelected(true); 
                  numberOfShapes++;
               }
            }
        }
        return numberOfShapes;
    }

    public String toString()
    {
        String list = "";

        for (int i = 0; i < shapes.size(); i++)
        {
            list = list + shapes.get(i).toString();
        }
        return list;
    }
    
    public int size()
    {
        return shapes.size();
    }
}