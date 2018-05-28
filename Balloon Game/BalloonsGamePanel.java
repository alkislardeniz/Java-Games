import Shapes.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;

/**
 * Deniz Alkislar
 * BalloonsGamePanel 1.0
 * Ankara - 17.03.2016
 */ 

public class BalloonsGamePanel extends JPanel
{
    // properties
    private final int NO_OF_BALLOONS = 25;
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 600;
    private final int END_SECOND = 20;
    private final int CREATE_SPEED = 100;
    private final int TIMER_SPEED = 1000;
    private final ImageIcon img = new ImageIcon(getClass().getResource("Icons/bg.png"));
    
    private ShapeContainer balloons;
    private Timer timer;
    private Timer elapsedTime;
    private String name;
    private int timePassed;
    private int score;
    private int highscore;
    private JLabel remainingTime;
    private JLabel scoreBoard;
    private JLabel highScore;
    private Iterator<Shapes.Shape> it;
    
    // constructors
    public BalloonsGamePanel()
    {    
        balloons = new ShapeContainer(); 
        timer = new Timer (CREATE_SPEED, new TimerListener());
        elapsedTime = new Timer (TIMER_SPEED, new ElapsedTimerListener());
        score = 0;
        highscore = 0;
        timePassed = 0;
        name = "";
        
        scoreBoard = new JLabel ("Score: " + score);
        highScore = new JLabel ("High Score: " + highscore);
        remainingTime = new JLabel ("" + (END_SECOND - timePassed));
        remainingTime.setFont(remainingTime.getFont().deriveFont(40f));
        remainingTime.setForeground(new Color(171, 179, 184));
        
        remainingTime.setHorizontalAlignment (SwingConstants.CENTER);
        scoreBoard.setHorizontalAlignment (SwingConstants.CENTER);
        highScore.setHorizontalAlignment (SwingConstants.CENTER);

        setPreferredSize (new Dimension (PANEL_WIDTH, PANEL_HEIGHT));
        setLayout (new BorderLayout());
        
        createBalloons();
        addMouseListener(new MyMouseListener());
       
        //add(remainingTime, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.NORTH);
        add(highScore, BorderLayout.SOUTH);
        
        timer.start();
        elapsedTime.start();
    }
    
    // methods
    @Override
    public void paintComponent(Graphics g)
    {
        it = balloons.iterator();
        
        super.paintComponent(g);
        g.drawImage(img.getImage(), 0, 0, null);
        for (int i = 0; i < balloons.size(); i++)
        {
            Balloon b = ( (Balloon)it.next() );
            g.setColor (b.getColor());
            b.draw(g);
        }
    }
    
    public void createBalloons()
    {
        for (int i = 0; i < NO_OF_BALLOONS; i++)
        {
            int x = (int) ((Math.random() * PANEL_WIDTH));
            int y = (int) ((Math.random() * PANEL_HEIGHT));
            
            balloons.add (new Balloon (x, y));
        }
    }

    // timer listeners
    class TimerListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        { 
            it = balloons.iterator();
            
            for (int i = 0; i < balloons.size(); i++)
            {
                ( (Balloon)it.next()).grow(); 
            } 
            balloons.removeSelected();

            if (balloons.size() <= 15)
            {
                int x = (int) (Math.random() * PANEL_WIDTH);
                int y = (int) (Math.random() * PANEL_HEIGHT);
                
                balloons.add (new Balloon (x, y));
            } 
            repaint();  
        }     
    }

    class ElapsedTimerListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {        
            timePassed++;
            remainingTime.setText ("" + (END_SECOND - timePassed));
            if (timePassed == END_SECOND)
            {
               timer.stop();
               elapsedTime.stop();
                 
               // checks if player got the highscore then gets player's name
               if (score == highscore)
               {
                   name = JOptionPane.showInputDialog (null, 
                                                       "You got the high score!" +
                                                       "\n" + "Please enter your name: ", 
                                                       "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
                   if (!(name.equals("")))
                   highScore.setText ("High Score: " + highscore + " (" + name + ")");  
               }
               
               int option = JOptionPane.showConfirmDialog (null,
                                                           "Your score is " + score + "." + "\n" +
                                                           "Do you want to play again?", 
                                                           "Game Over!", 
                                                           JOptionPane.YES_NO_OPTION);
               
               if (option == JOptionPane.YES_OPTION)
               { 
                   balloons.setAllSelected();
                   score = 0;
                   timePassed = 0;
                   scoreBoard.setText ("Score: " + score);   
                   elapsedTime.restart();
                   timer.restart();
                   
               }
            }
        }
    }

    // mouse listeners
    class MyMouseListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent event)
        {
            int currentScore;
            
            // score update if at least 2 balloons are pinned
            currentScore = balloons.selectAllAt (event.getX(), event.getY());
            if (currentScore > 1 && timer.isRunning())
            {
                score += currentScore;
            }        
            scoreBoard.setText ("Score: " + score);
            
            // high score update
            if (score > highscore)
            {
                highscore = score;
                highScore.setText ("High Score: " + highscore);  
            }
        }
    }
}                