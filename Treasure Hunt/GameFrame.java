import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Deniz Alkislar
 * GameFrame Class 1.0
 * Ankara - 12.03.2016
 */ 

public class GameFrame extends JFrame
{
    //icons
    ImageIcon cross = new ImageIcon(this.getClass().getResource("Icons/cross.png"));
    ImageIcon redCross = new ImageIcon(this.getClass().getResource("Icons/redCross.png"));
    ImageIcon logo = new ImageIcon(this.getClass().getResource("Icons/logo.png"));
    ImageIcon gem = new ImageIcon(this.getClass().getResource("Icons/gem.png"));
    ImageIcon emerald = new ImageIcon(this.getClass().getResource("Icons/emerald.png"));
    ImageIcon goblet = new ImageIcon(this.getClass().getResource("Icons/goblet.png"));
    ImageIcon amethyst = new ImageIcon(this.getClass().getResource("Icons/amethyst.png"));
    
    // properties
    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;
    private final int GRID_WIDTH = 5;
    private final int GRID_HEIGHT = 5;
   
    private JPanel huntArea;
    private JPanel score;
    private JLabel scoreBoard;
    
    private JButton[] buttons;
    private boolean[] buttonsPress;
    private ImageIcon[] img;
    
    private int treasurePlace;
    private int attempt;
    
    // constructors
    public GameFrame()
    {   
        add(new JLabel(logo), BorderLayout.NORTH);
        createComponents();
        setSize (FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
    }

    // creates game area with buttons
    public void createButtons()
    { 
        treasurePlace = (int) (Math.random() * (GRID_WIDTH * GRID_HEIGHT ));
        
        img = new ImageIcon[4];
        img[0] = gem;
        img[1] = emerald;
        img[2] = goblet;
        img[3] = amethyst;
        
        huntArea = new JPanel();
        huntArea.setLayout(new GridLayout (GRID_WIDTH, GRID_HEIGHT));
        
        buttons = new JButton[(GRID_WIDTH * GRID_HEIGHT)];
        buttonsPress = new boolean[(GRID_WIDTH * GRID_HEIGHT)];
        
        for (int i = 0; i < (GRID_WIDTH * GRID_HEIGHT); i++)
        {
            buttons[i] = new JButton ();
            huntArea.add (buttons[i]);
            buttons[i].setIcon(cross);
            buttonsPress[i] = false;
            buttons[i].addActionListener(new AddButtonListener());
        }
        add(huntArea);
    }
    
    //adds labels and logo
    public void createScoreBoard()
    {
        attempt = 0;
        score = new JPanel();
        scoreBoard = new JLabel ("Welcome to Treasure Hunt Game!");
        score.add (scoreBoard);
        
        add(score, BorderLayout.SOUTH);
    }
    
    // creates all the components at once
    public void createComponents()
    {
        createScoreBoard();
        createButtons();
    }
    
    // actionlisters for buttons
    class AddButtonListener implements ActionListener
    {              
        public void actionPerformed (ActionEvent event)
        {  
            if (event.getSource() == buttons[treasurePlace])
            {
               attempt++;
               scoreBoard.setText ("You got the treasure in " + attempt + " attempts!");
               buttons[treasurePlace].setIcon(img[(int) (Math.random() * 4)]); 
               
               // prepare new game
               treasurePlace = (int) (Math.random() * (GRID_WIDTH * GRID_HEIGHT));
               attempt = 0;

               for (int i = 0; i < (GRID_WIDTH * GRID_HEIGHT); i++)
               {
                   buttonsPress[i] = false; 
               }
            }
              
            else
            {   
               if (attempt == 0) {
                  restart();
               }
               
               // checks botton is pressed before then updates screen
               for (int i = 0; i < (GRID_WIDTH * GRID_HEIGHT); i++)
               {
                   if (event.getSource() == buttons[i] && buttonsPress[i] == false)
                   {
                      buttonsPress[i] = true;
                      attempt++;
                      ((JButton) (event.getSource())).setIcon(redCross);
                   }
               }
                 
               if (attempt != 1) {
                  scoreBoard.setText ("Number of attempts are " + attempt);                
               }
               else {
                  scoreBoard.setText ("Number of attempt is " + attempt);
               }
            }
        }
        
        // restarts the area when the game is finished
        public void restart()
        {
            for (int i = 0; i < (GRID_WIDTH * GRID_HEIGHT); i++)
            {
                buttons[i].setIcon(new ImageIcon(this.getClass().getResource("Icons/cross.png")));
            }   
        }
    }
}