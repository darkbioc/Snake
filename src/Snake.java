
import java.awt.Color;
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
    public int snakeLength = 4;

    public Snake() {
        this.snakeLength = snakeLength;
        listNodes = new ArrayList<Node>();
        this.direction = DirectionType.DOWN;
        for (int i = 0; i < snakeLength; i++) {
            listNodes.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2, Color.GREEN));
        }

    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.row, node.col, node.color, squareWidth, squareHeight);

        }
    }
}
