/*
 * Purpose: Implementing a Tetris game using Object Oriented Design
 * Author: Didunoluwa Johnson
 * Date Modified: Feb 20, 2024
 */

public class ZeeBrick extends TetrisBrick
{
    int startCol = 5;
    int startRow = 0;

    public ZeeBrick(int col, int numSeg ,int clrNum)
    {
        super(numSeg,clrNum);
        initPosition(col);

    }

    public void initPosition(int col)
    {

        position = new int [][]{   {col +1,0},
                {col+1, 1},
                {col ,1},
                {col, 2}
        };orientation = 2;
    }

    public void rotate()
    {
        switch(orientation)
        {
            case 1:  position = new int [][] {  {position[0][0] ,position[0][1]-2},
                    {position[1][0]+1 ,position[1][1]-1},
                    {position[2][0], position[2][1] },
                    {position[3][0]+1 , position[3][1]+1}
            };orientation=2;
                break;
            case 2: position  =  new int[][] {  {position[0][0] ,position[0][1]+2},
                    {position[1][0]-1   ,position[1][1]+1},
                    {position[2][0], position[2][1] },
                    {position[3][0] -1 , position[3][1]-1}
            };orientation=1;
                break;

        }
    }
    public void unrotate()
    {
        rotate();
    }

}
