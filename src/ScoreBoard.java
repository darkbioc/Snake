
import javax.swing.JLabel;

public class ScoreBoard extends JLabel{
    
    private int score;
    //private int level;
    
    public ScoreBoard() {
        super();
        score = 0;
        setText("Score: "+score);
    }
    
    public void increment(int points) {
        score += points;
        setText("Score: "+score);
    }
    
    /*public void newLevel()
    {
        level++;
        setText("Score: "+score+" Level:"+level);
    }*/
    
    public void reset() {
        score = 0;
        setText("Score: "+score);
    }
    
    public void pause()
    {
        setText("PAUSED");
    }
    
    public void resume()
    {
        setText("Score: "+score);
    }
    public int getScore()
    {
        return score;
    }
    /*public void setLevel(int newLevel)
    {
        level=newLevel;
    }*/
    /*public int getLevel()
    {
        return level;
    }*/
    public void gameOver()
    {
        setText("GAME OVER! Score: "+score);
    }
}