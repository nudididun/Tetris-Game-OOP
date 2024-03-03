/*
 * Purpose: Implementing a Tetris game using Object Oriented Design
 * Author: Didunoluwa Johnson
 * Date Modified: Feb 20, 2024
 */

import javax.swing.*;

public class TetrisWindow extends JFrame
{
    TetrisGame game;
    TetrisDisplay display;

    private int win_width = 400;
    private int win_height= 600;
    private int game_rows = 20;
    private int game_cols= 10;

    public TetrisWindow()
    {
        this.setTitle("My Tetris Project");
        this.setSize(win_width,win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocation(10,200);

        game =new TetrisGame(game_rows,game_cols);
        display = new TetrisDisplay(game);
        this.add(display);

        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        TetrisWindow myWindow = new TetrisWindow();

    }
}
