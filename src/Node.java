
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class Node
{

    public int row;
    public int col;
    public Color color;

    public Node(int row, int col, Color color)
    {
        this.row = row;
        this.col = col;
        this.color = color;
    }
    
    public int[] getPosition()
    {
        int[] pos = new int[2];
        pos[0]=row;
        pos[1]=col;
        return pos;       
    }
    //hacer getters and setters de todo?
}
