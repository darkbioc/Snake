
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class Board extends JPanel implements ActionListener
{

    class MyKeyAdapter extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if (direction != DirectionType.RIGHT && (timer.isRunning() || gameOver))
                    {
                        direction = DirectionType.LEFT;
                    }

                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != DirectionType.LEFT && timer.isRunning() && !gameOver)
                    {
                        direction = DirectionType.RIGHT;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != DirectionType.DOWN && timer.isRunning() && !gameOver)
                    {
                        direction = DirectionType.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != DirectionType.UP && timer.isRunning() && !gameOver)
                    {
                        direction = DirectionType.DOWN;
                    }
                    break;
                case KeyEvent.VK_P:
                    switchPause();
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public ScoreBoard scoreBoard;

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 40;
    private int deltaTime;
    private Timer timer;
    boolean directionUp;
    boolean directionDown;
    boolean directionLeft;
    boolean directionRight;
    boolean gameOver = false;
    MyKeyAdapter keyAdapter;
    AudioStream audioSong = null;
    AudioStream audioEffect = null;
    private Food food;
    private Food specialFood;
    private Snake snake;
    private boolean coincident;
    private boolean specExist;
    private DirectionType direction = DirectionType.RIGHT;

    public Board()
    {
        super();
        initValues();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
        setBackground(Color.BLACK);
    }

    public void initValues()
    {
        setFocusable(true);
        deltaTime = 100;
        snake = new Snake(3);
        food = new Food((int) (Math.random() * NUM_ROWS), (int) (Math.random() * NUM_COLS), Color.red);
    }

    public void initGame()
    {
        //AudioPlayer.player.stop(audioSong);
        initValues();

        removeKeyListener(keyAdapter);
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
        gameOver = false;
        //playSong(".wav");
        direction = DirectionType.RIGHT;
    }

    /* private boolean canMoveTo() {
        
    }
     */
    private boolean collisions()
    {
        Node head = snake.listNodes.get(0);
        Node body = null;
        for (int i = 1; i < snake.listNodes.size(); i++)
        {
            body = snake.listNodes.get(i);
            if (head.col == body.col && head.row == body.row)
            {
                return true;
            }
        }
        if (head.col < 0)
        {
            return true;
        }

        if (head.row < 0)
        {
            return true;
        }

        if (head.row > NUM_ROWS + 1)
        {
            return true;
        }
        if (head.col > NUM_COLS - 1)
        {
            return true;
        }

        return false;
    }

    public void eat()
    {
        coincident = true;
        while (coincident)
        {
            coincident = false;
            Node full = null;
            Node head = snake.listNodes.get(0);
            if (head.col == food.col && head.row == food.row)
            {
                food.col = ((int) (Math.random() * NUM_COLS));
                food.row = ((int) (Math.random() * NUM_ROWS));
                snake.eat(direction);
                scoreBoard.increment(1);
                for (int i = 0; i < snake.listNodes.size(); i++)
                {
                    full = snake.listNodes.get(i);
                    if (food.col == full.col && full.row == full.row)
                    {
                        coincident = true;
                        food.col = ((int) (Math.random() * NUM_COLS));
                        food.row = ((int) (Math.random() * NUM_ROWS));
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        /*System.out.println("Food row: " + food.row + " Food col: " + food.col);
        System.out.println("Head row: " + snake.listNodes.get(0).row + " Head col: " + snake.listNodes.get(0).col + "\n");*/
        if (collisions() == true)
        {
            gameOver();
        }
        else
        {
            snake.movement(direction);
            repaint();
            Toolkit.getDefaultToolkit().sync();
            eat();
            try
            {
                if(!specExist)
                    specialFood();
            }
            catch (InterruptedException ex)
            {
            }
            eatSpecial();
        }

    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        snake.draw(g, squareWidth(), squareHeight());
        food.draw(g, squareWidth(), squareHeight());
        // drawBoarder(g);
    }

    /*  public void drawBoarder(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0, 0, NUM_COLS * squareWidth(), NUM_ROWS * squareHeight());
    }*/
    public void setScoreboard(ScoreBoard scoreboard)
    {
        this.scoreBoard = scoreboard;
    }

    public void playSong(String song)
    {
        InputStream music;
        try
        {
            music = new FileInputStream(new File(song));
            audioSong = new AudioStream(music);
            AudioPlayer.player.start(audioSong);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void playEffect(String effect)
    {
        InputStream music;
        try
        {
            music = new FileInputStream(new File(effect));
            audioEffect = new AudioStream(music);
            AudioPlayer.player.start(audioEffect);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void gameOver()
    {
        timer.stop();
        scoreBoard.gameOver();
    }

    private int squareWidth()
    {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight()
    {
        return getHeight() / NUM_ROWS;
    }

    public void switchPause()
    {
        if (!gameOver)
        {
            if (!timer.isRunning())
            {
                scoreBoard.resume();
                timer.start();
            }
            else
            {
                timer.stop();
                scoreBoard.pause();
            }
        }
    }

    public void specialFood() throws InterruptedException
    {
        if (scoreBoard.getScore() % 10 == 0 && scoreBoard.getScore()!=0)
        {
            specExist=true;
            Node full = null;
            coincident = true;
            while (coincident)
            {
                coincident = false;
                specialFood = new Food(((int) (Math.random() * NUM_ROWS)), ((int) (Math.random() * NUM_COLS)), Color.PINK);
                for (int i = 0; i < snake.listNodes.size(); i++)
                {
                    full = snake.listNodes.get(i);
                    if (food.col == full.col && full.row == full.row)
                    {
                        coincident = true;
                        food.col = ((int) (Math.random() * NUM_COLS));
                        food.row = ((int) (Math.random() * NUM_ROWS));
                    }
                }
            }
            
            if(specExist)
            {
                Thread.sleep(5000);
                specialFood=null;
                specExist=false;
            }
        }

    }

    public void eatSpecial()
    {
        Node head = snake.listNodes.get(0);
        if (head.col == food.col && head.row == food.row)
        {
            scoreBoard.increment(5);
            specialFood = null;
            specExist=false;
        }
    }
    
}

//deltaTime, food, specialFood, snake/timer
//actionPerformed, gameOver, canMove, checkColision(boolean), checkFood(boolean), eatFood
