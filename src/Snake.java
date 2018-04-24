
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alu20919409n
 */
public class Snake {
    
    
    public ArrayList<Node> listNodes;
    private DirectionType direction;
    public int snakeLength;
    
    public Snake(int snakeLength){
        listNodes = new ArrayList<Node>();
        this.direction = DirectionType.DOWN;
        this.snakeLength = snakeLength;
    }
    
   
    public void draw(Graphics g, int row, int col, int nodeWidth, int nodeHeight) {
    Util.drawSquare(g, row, col, color, nodeWidth, nodeHeight);
    }
}
