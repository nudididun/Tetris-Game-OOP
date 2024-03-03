/*
 * Purpose: Implementing a Tetris game using Object Oriented Design
 * Author: Didunoluwa Johnson
 * Date Modified: Feb 20, 2024
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisDisplay extends JPanel
{
    TetrisGame game;
    Timer timer;
    private Color[] colors = {Color.BLUE,Color.CYAN,Color.GREEN, Color.MAGENTA, Color.ORANGE,Color.YELLOW,Color.RED};

    private int start_x=50;
    private int start_y =30;
    private int cell_size = 20;
    private int wallWid =12;
    private int speed =20;
    private char keyWanted;
    private String str = "dlrR";

    private int delay = 300;

    public TetrisDisplay(TetrisGame gam)
    {
        game= gam;
        timer = new Timer(delay, new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                cycleMove();
            }
        });
        timer.start();
        this.setBackground(Color.black);

        this.addKeyListener (new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                translateKey(e);
            }
        });
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void translateKey(KeyEvent e) {
        int code = e.getKeyCode();
        int delay2 = 200;
        switch (code){
            case KeyEvent.VK_SPACE:
                if(game.state==2)
                {
                    timer.stop();
                    game.state=3;
                }else
                {
                    timer.restart();
                    game.state= 2;
                }
                break;
            case KeyEvent.VK_LEFT:
                game.makeMove('l');
                break;
            case KeyEvent.VK_RIGHT:
                game.makeMove('r');
                break;
            case KeyEvent.VK_UP:
                game.makeMove('R');
                break;
            case KeyEvent.VK_N:
                game.newGame();
                break;
        }
    }


    public void cycleMove()
    {
        setFocusable(true);
        if(game.getState()== 1)
        {
            game.makeMove('a');
        }
        else
        {
            game.makeMove('d');
        }
        repaint();
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        drawWell(g);
        drawBackground(g);
        drawBrick(g);
        drawScore(g);

    }

    public void drawWell(Graphics g)
    {
        g.setColor(Color.WHITE);
        // right wall
        g.fillRect(start_x-wallWid, start_y-wallWid, wallWid, cell_size*game.fetchRows()+2*wallWid);

        // Left Wall
        g.fillRect(start_x+cell_size*game.fetchCols(),
                start_y-wallWid, wallWid,
                cell_size*game.fetchRows()+2*wallWid);
        // Bottom Wall
        g.fillRect(start_x-wallWid,
                start_y-wallWid+cell_size*game.fetchRows()+wallWid,
                cell_size*game.fetchCols()+2*wallWid, wallWid);
    }

    public void drawBrick(Graphics g)
    {

        for(int seg = 0; seg < game.getFallingBrick().getNumSegments(); seg++)
        {
            int cell_clr = game.getFallingBrick().getColorNum();

            g.setColor(colors[cell_clr]);
            g.fillRect(start_x+(game.getFallingBrick().position[seg][0]*cell_size), start_y+(game.getSegmentRow(seg)*cell_size), cell_size, cell_size);
            g.setColor(Color.BLACK);
            g.drawRect(start_x+(game.getFallingBrick().position[seg][0]*cell_size), start_y+(game.getSegmentRow(seg)*cell_size), cell_size, cell_size);
        }


    }

    public void drawBackground(Graphics g)
    {
        for (int row = 0; row < game.getRows(); row++)
        {
            for (int col = 0; col < game.getCols(); col++)
            {
                if (game.background[row][col] != -1)
                {
                    g.setColor(colors[game.background[row][col]]);
                } else
                {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(start_x + col * cell_size,
                        start_y + row * cell_size, cell_size, cell_size);

                if (game.background[row][col] != -1)
                {
                    g.setColor(Color.BLACK);
                    g.drawRect(start_x + col * cell_size,
                            start_y + row * cell_size, cell_size, cell_size);
                }
            }
        }
    }
    public void drawScore(Graphics g)
    {
        int x= 275; int y= 30;
        //      game.clearRows();
        int size = 20;
        g.setFont(new Font("ARIAL",Font.BOLD,size));
        g.setColor(Color.ORANGE);
        g.drawString("score:"+game.score, x, y);
    }



}
