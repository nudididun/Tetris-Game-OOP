/*
 * Purpose: Implementing a Tetris game using Object Oriented Design
 * Author: Didunoluwa Johnson
 * Date Modified: Feb 20, 2024
 */

public class TetrisGame
{
    TetrisBrick fallingBrick;
    private int rows = 20;
    private int cols= 12;
    protected int[][] background;
    protected int state;
    protected int score;


    public TetrisGame(int rs, int cs)
    {
        rows = rs;
        cols = cs;
        background = new int[rs][cs];
        state = 1;
        initBoard();
        spawnBrick();
    }

    public int fetchBackground(int row,int col)
    {
        return background [row] [col];

    }
    public TetrisBrick getFallingBrick()
    {
        return fallingBrick;
    }

    public void newGame()
    {
        state = 1;
        initBoard();
        spawnBrick();

    }

    public void initBoard()
    {
        for(int seg = 0; seg<getRows(); seg++)
        {
            for(int seg2 = 0; seg2<getCols(); seg2++)
            {
                background[seg][seg2] =-1;
            }
        }
    }



    public void spawnBrick()
    {
        int brickNum = (int)(Math.random()*8) ;
        int orient = 0;
        int numSeg = 4;
        int cols =getCols();

        if(brickNum == 0)
        {

            fallingBrick = new ElBrick(cols/2,  numSeg,brickNum );

        }
        else if (brickNum == 1)
        {
            fallingBrick = new EssBrick(cols/2, numSeg,brickNum );

        }
        else if (brickNum == 2)
        {
            fallingBrick = new JayBrick(cols/2,  numSeg,brickNum );

        }
        else if (brickNum == 3)
        {

            fallingBrick = new SquareBrick(cols/2,numSeg,brickNum );

        }
        else if (brickNum == 4)
        {

            fallingBrick = new LongBrick(cols/2,  numSeg,brickNum );

        }
        else if (brickNum == 5)
        {

            fallingBrick = new StackBrick(cols/2,  numSeg,brickNum );

        }
        else if (brickNum == 6)
        {

            fallingBrick = new ZeeBrick(cols/2, numSeg, brickNum );

        }

    }

    public void makeMove(char code)
    {
        switch(code)
        {
            case 'd':
                fallingBrick.moveDown();
                if (!validateDownMove() )
                {
                    fallingBrick.moveUp();

                    transferColor();
                    clearRows();
                    spawnBrick();

                }
                break;
            case 'r':
                fallingBrick.moveRight();
                if(!validateRightMove())
                {
                    fallingBrick.moveLeft();
                }
                break;
            case 'l':
                fallingBrick.moveLeft();
                if(!validateLeftMove())
                {
                    fallingBrick.moveRight();
                }
                break;
            case 'R' :
                fallingBrick.rotate();
                if(!validateLeftMove()&& !validateRightMove() && !validateDownMove())
                {
                    fallingBrick.unrotate();

                }
                break;

            case 'a':
                spawnBrick();
                break;
        }
        if(validSpawn())
        {
            state = 2;
        }else{
            state =3;
            checkForEnd();
        }
    }
    public boolean validSpawn()
    {
        for(int seg =0; seg< fallingBrick.numSegments; seg ++)
        {
            if(fallingBrick.getSegCols(seg)<=0)
            {
                return false;
            }
        } return true;
    }
    public boolean validateDownMove()
    {
        for(int seg = 0 ; seg<fallingBrick.numSegments ; seg++)
        {
            if(fallingBrick.getSegRows(seg)>getRows()-1)
            {
                return false;
            } else
            {
                if (!validateClrTransfer(fallingBrick.position[seg][1],
                        fallingBrick.position[seg][0]))
                {
                    return false;
                }
            }
        }return true;

    }
    public boolean validateRightMove()
    {
        for(int seg = 0 ; seg<fallingBrick.numSegments ; seg++)
        {
            if(fallingBrick.getSegCols(seg)>getCols()-1)
            {
                return false;
            } else
            {
                if (!validateClrTransfer(fallingBrick.position[seg][1],
                        fallingBrick.position[seg][0]))
                {
                    return false;
                }
            }
        }return true;

    }
    public boolean validateLeftMove()
    {
        for(int seg = 0 ; seg<fallingBrick.numSegments ; seg++)
        {
            if(fallingBrick.getSegCols(seg)<0)
            {
                return false;
            } else
            {
                if (!validateClrTransfer(fallingBrick.position[seg][1],
                        fallingBrick.position[seg][0]))
                {
                    return false;
                }
            }
        }return true;

    }
    public boolean validateClrTransfer(int row, int col)
    {
        if(background[row][col] != -1)
        {
            return false;
        }   return true;
    }

    public void transferColor()
    {
        int backRow;
        int backCol;

        for(int seg = 0; seg<fallingBrick.getNumSegments(); seg++)
        {
            backRow = getSegmentRow(seg);
            backCol = getSegmentCol(seg);
            background[backRow][backCol] = fallingBrick.getColorNum();
        }
    }
    public int getRows()
    {
        return background.length;
    }

    public int getCols()
    {
        return background[0].length;
    }
    public int getState()
    {
        return state;
    }

    public int getSegmentRow(int seg)
    {
        return fallingBrick.getSegRows(seg);
    }
    public int getSegmentCol(int seg)
    {
        return fallingBrick.getSegCols(seg);
    }
    public int fetchRows(){
        return background.length;
    }

    public int fetchCols(){
        return background[0].length;
    }
    public boolean checkForEnd()
    {
        boolean end = false;
        for(int seg=0; seg < getCols(); seg++)
        {
            if(background[0][seg] !=0)
            {
                end = true;
            }
        }return end;
    }
    public boolean rowSpace(int row_num)
    {
        boolean lineComplete = true;

        for(int col = 0; col< cols; col++)
        {
            if(background[row_num][col]== -1)
            {
                lineComplete = false;
                return true;
            }
        }return false;
    }
    public void copyRow(int row_num)
    {
        for(int col =0; col<getCols();col++)
        {
            if(row_num ==getRows()-1)
            {
                background[row_num][col]= 0;
            }else{
                background[row_num+1][col]= background[row_num][col];
                if (row_num<getRows()-1 &&row_color(row_num,col) ==false)
                {
                    background[row_num+1][col]= background[row_num][col];
                    row_num++;
                }
            }
        }
    }
    public boolean row_color(int row_num,int col)
    {
        boolean value = false;

        if(background[row_num +1][col]!=0&& row_num<rows)
        {
            value = true;
        }
        return value;
    }
    public void copyAllRows( int row_num)
    {
        for(int seg =row_num-1; seg !=0;seg--)
        {
            copyRow(seg);
        }
    }
    public void clearRows()
    {
        int clearedRows = 0;
        for(int row = getRows()-1;row>=0; row--)
        {
            if(rowSpace(row)==false)
            {
                copyAllRows(row);
                row++;
                clearedRows++;
            }
        }switch(clearedRows)
    {
        case 1:
            score +=100;
            break;
        case 2:
            score += 300;
            break;
        case 3:
            score += 600;
            break;
        case 4:
            score+= 1200;
            break;
    }System.out.println();
    }



}
