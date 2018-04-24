
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
public class Util {

    public static void drawSquare(Graphics g, int row, int col, Color color, int nodeWidth, int nodeHeight) {
        int x = col * nodeWidth;
        int y = row * nodeHeight;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, nodeWidth - 2,
                nodeHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + nodeHeight - 1, x, y);
        g.drawLine(x, y, x + nodeWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + nodeHeight - 1,
                x + nodeWidth - 1, y + nodeHeight - 1);
        g.drawLine(x + nodeWidth - 1,
                y + nodeHeight - 1,
                x + nodeWidth - 1, y + 1);
    }
}

//        Color color = Color.GREEN;            