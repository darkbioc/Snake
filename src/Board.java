
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
public class Board extends JPanel {

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!directionRight) {
                        DirectionType.LEFT;
                        directionLeft = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public ScoreBoard scoreBoard;

    public static final int NUM_ROWS = 20;
    public static final int NUM_COLS = 20;
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

    public Board() {
        super();
        initValues();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
    }

    public void initValues() {
        setFocusable(true);
        cleanBoard();
        deltaTime = 500;
    }

    public void initGame() {
        AudioPlayer.player.stop(song);
        removeKeyListener(keyAdapter);
        initValues();
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
        gameOver = false;
        playSong(".wav");
    }

    private boolean canMoveTo() {
        if () {
            return false;
        }
        return true;
    }

    private boolean checkColision() {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Snake.draw(g, squareWidth(), squareHeight());

        drawBoarder(g);
    }

    public void drawBoarder(Graphics g) {

    }

    public void drawBoard(Graphics g) {

    }

    public void setScoreboard(ScoreBoard scoreboard) {
        this.scoreBoard = scoreboard;
    }

    public void playSong(String song) {
        InputStream music;
        try {
            music = new FileInputStream(new File(song));
            audioSong = new AudioStream(music);
            AudioPlayer.player.start(audioSong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void playEffect(String effect) {
        InputStream music;
        try {
            music = new FileInputStream(new File(effect));
            audioEffect = new AudioStream(music);
            AudioPlayer.player.start(audioEffect);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void gameOver() {

    }

    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }

}

//deltaTime, food, specialFood, snake/timer
//actionPerformed, gameOver, canMove, checkColision(boolean), checkFood(boolean), eatFood