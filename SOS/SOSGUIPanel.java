import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import cs101.sosgame.*;

/**
 * Deniz Alkislar
 * SOSGUIPanel Class 1.0
 * Ankara - 29.03.2016
 */ 

public class SOSGUIPanel extends JPanel
{
    // properties
    private SOS game;
    private SOSCanvas canvas;
    private String p1;
    private String p2;
    private final int COMPONENT_WIDTH = SOSCanvas.PANEL_WIDTH;
    private final int COMPONENT_HEIGHT = 45;
    private final int PANEL_WIDTH = SOSCanvas.PANEL_WIDTH;
    private final int PANEL_HEIGHT = COMPONENT_HEIGHT + SOSCanvas.PANEL_HEIGHT + 50;
    private final ImageIcon logo = new ImageIcon(getClass().getResource("Icons/logo.png"));
    private JRadioButton sButton, oButton;
    private boolean sSelect, oSelect; 
    private JLabel p1Name, p2Name;
    private JPanel gameComponents;
        
    // constructors
    public SOSGUIPanel (SOS game, String p1, String p2)
    {
        this.game = game;
        this.p1 = p1;
        this.p2 = p2;   
        canvas = new SOSCanvas (game);
        
        sButton = new JRadioButton ("S");
        oButton = new JRadioButton ("O");
        oButton.addActionListener ( new RadioButtonListener());
        sButton.addActionListener ( new RadioButtonListener());
        sButton.setSelected(true);
        
        p1Name = new JLabel (" " + p1 + ": " + game.getPlayerScore1());
        p2Name = new JLabel (" " + p2 + ": " + game.getPlayerScore2());
        p1Name.setOpaque(true);
        p2Name.setOpaque(true);
        p1Name.setBackground (Color.RED);
         
        oSelect = false;
        sSelect = true;
        
        gameComponents = new JPanel();   
        gameComponents.setPreferredSize (new Dimension (COMPONENT_WIDTH, COMPONENT_HEIGHT));
        gameComponents.setLayout (new FlowLayout());
        gameComponents.setBackground (Color.WHITE);
        gameComponents.add(p1Name);
        gameComponents.add(sButton);
        gameComponents.add(oButton);
        gameComponents.add(p2Name);
        
        setPreferredSize (new Dimension (PANEL_WIDTH, PANEL_HEIGHT));
        setLayout (new BorderLayout());
        setBackground (Color.WHITE);
        
        canvas.addMouseListener (new MyMouseAdapter());
        add(new JLabel(logo), BorderLayout.NORTH);
        add (canvas, BorderLayout.CENTER);
        add (gameComponents, BorderLayout.SOUTH); 
    }
  
    // inner classes
    class MyMouseAdapter extends MouseAdapter
    {
        @Override 
        public void mousePressed (MouseEvent e)
        {
            Point point;

            if (e.getX() >= canvas.BORDER_SPACE &&
                e.getY() >= canvas.BORDER_SPACE &&
                e.getY() <= canvas.BORDER_SPACE + game.getDimension() * canvas.getSpace() &&
                e.getX() <= canvas.BORDER_SPACE + game.getDimension() * canvas.getSpace())
            {
                point = e.getPoint();
                
                if (oSelect)
                {
                    game.play ('o', ((int) ((point.getY() - (canvas.BORDER_SPACE)) / canvas.getSpace())) + 1, 
                              ((int) ((point.getX() - (canvas.BORDER_SPACE)) / canvas.getSpace())) + 1);
                }
                else if (sSelect)
                {
                    game.play ('s', ((int) ((point.getY() - (canvas.BORDER_SPACE)) / canvas.getSpace())) + 1, 
                              ((int) ((point.getX() - (canvas.BORDER_SPACE)) / canvas.getSpace())) + 1);
                }
            }
            
            if (game.getTurn() == 1)
            {
                p1Name.setBackground (Color.RED);
                p2Name.setBackground (Color.WHITE);
            }   
            else if (game.getTurn() == 2)
            {
                p1Name.setBackground (Color.WHITE);
                p2Name.setBackground (Color.RED);
            }
            
            p1Name.setText (" " + p1 + ": " + game.getPlayerScore1());
            p2Name.setText (" " + p2 + ": " + game.getPlayerScore2());

            String winner;
            
            if (game.getPlayerScore1() > game.getPlayerScore2()) {
               winner = p1;
            }
            else if (game.getPlayerScore1() < game.getPlayerScore2()) {
                winner = p2;
            }      
            else {
                winner = "draw";
            }
            
            canvas.repaint();
            
            if (game.isGameOver() && (!(winner.equals("draw"))))
            {
               JOptionPane.showMessageDialog (null, "Winner is " + winner + "!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (game.isGameOver() && (winner.equals("draw")))
            {
                JOptionPane.showMessageDialog (null, "It's a " + winner + "!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (e.getSource() == oButton)
            {
               oSelect = true;
               sSelect = false;
               sButton.setSelected(false);
            }
            else
            {
               oSelect = false;
               sSelect = true;
               oButton.setSelected(false);
            }
        }
    }
         
}