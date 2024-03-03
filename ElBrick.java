/*
 * Purpose: Implementing a Tetris game using Object Oriented Design
 * Author: Didunoluwa Johnson
 * Date Modified: Feb 20, 2024
 */


public class ElBrick extends TetrisBrick
{



    public ElBrick(int col, int numSeg ,int clrNum)
    {

        super( numSeg,clrNum);
        this.initPosition(col);

    }

    public void initPosition(int col)
    {

        position = new int [][]{   {col ,0},
                {col , 1},
                {col ,2},
                {col +1, 2}


        };orientation=1;
    }
    public void rotate()
    {
        switch(orientation)
        {
            case 1: position = new int [][] {  {position[0][0]+1 ,position[0][1]},
                    {position[1][0]   ,position[1][1]-1},
                    {position[2][0]-1, position[2][1]-2 },
                    {position[3][0] - 2 , position[3][1]-1}
            };orientation=2;
                break;
            case 2:  position = new int [][] {  {position[0][0]-1 ,position[0][1]},
                    {position[1][0]  ,position[1][1]-1},
                    {position[2][0]+1, position[2][1] -2},
                    {position[3][0]  , position[3][1]-3}
            };orientation =3;
                break;
            case 3:  position = new int [][] {  {position[0][0]-1 ,position[0][1]},
                    {position[1][0] ,position[1][1]+1},
                    {position[2][0]+1, position[2][1]+2 },
                    {position[3][0]+2 , position[3][1]+1}
            };orientation =4;
                break;
            case 4: position = new int [][]{  {position[0][0]+1 ,position[0][1]},
                    {position[1][0] ,position[1][1]+1},
                    {position[2][0]-1, position[2][1]+2 },
                    {position[3][0] , position[3][1]+3}
            };orientation =1;
                break;
        }
    }
    public void unrotate()
    {
        rotate();
        rotate();
        rotate();
    }
}
