package maze;

import java.util.Random;
import java.util.Stack;

public class Grid {
    
Cell cells[];

public void generate (int n) 
    {
        
    cells = new Cell[n*n];
        
    for (int i = 0; i < cells.length; i++)
    {
        cells[i] = new Cell(i);        
    } //i
    
    int count = 0;
    
    for (int j = 0; j < n; j++) 
    {
        for (int k = 0; k < n; k++) 
        {
            cells[count].setX(j);
            cells[count].setY(k);
            count++;
        }
    } //getting x, y coordinates
    
    // *** Setting up adjacent cells *** //
    
    for (int i = 0; i < cells.length; i++){
        
        //upper left corner (x0, y0)
        if (cells[i].getX() == 0 && cells[i].getY() == 0) 
        {
            cells[i].toNorth = null;
            cells[i].toWest = null;
            cells[i].toSouth = cells[i+n];
            cells[i].toEast = cells[i+1];
        }
       
        //upper right corner (x0, yn)
        else if (cells[i].getX() == 0 && cells[i].getY() == n-1) 
        {
            cells[i].toNorth = null;
            cells[i].toWest = cells[i-1];
            cells[i].toEast = null;
            cells[i].toSouth = cells[n+i];
        }
        
        //lower left corner
        else if (cells[i].getX() == n-1 && cells[i].getY() == 0)
        {
            cells[i].toNorth = cells[i-n];
            cells[i].toEast = cells[i+1];
            cells[i].toWest = null;
            cells[i].toSouth = null;
        }
        
        //lower right corner
        else if (cells[i].getX() == (n-1) && cells[i].getY() == (n-1)) 
        {
            cells[i].toNorth = cells[i-n];
            cells[i].toWest = cells[i-1];
            cells[i].toEast = null;
            cells[i].toSouth = null;
        }
        
        
        //upper strip
        else if (cells[i].getX() == 0 && (cells[i].getY() > 0 && cells[i].getY() < n-1)) 
        {
            cells[i].toNorth = null;
            cells[i].toSouth = cells[i+n];
            cells[i].toWest = cells[i-1];
            cells[i].toEast = cells[i+1];
        }
        
        //lower strip
        else if (cells[i].getX() == n-1 && (cells[i].getY() > 0 && cells[i].getY() < n-1))
        {
            cells[i].toNorth = cells[i-n];
            cells[i].toSouth = null;
            cells[i].toWest = cells[i-1];
            cells[i].toEast = cells[i+1];
        }
        
        //left strip
        else if (cells[i].getY() == 0 && (cells[i].getX() > 0 && cells[i].getX() < n-1))
        {
            cells[i].toNorth = cells[i-n];
            cells[i].toSouth = cells[i+n];
            cells[i].toEast = cells[i+1];
            cells[i].toWest = null;
        }
        
        //right strip
        else if (cells[i].getY() == n-1 && (cells[i].getX() > 0 && cells[i].getX() < n-1))
        {
            cells[i].toNorth = cells[i-n];
            cells[i].toSouth = cells[i+n];
            cells[i].toWest = cells[i-1];
            cells[i].toEast = null;
        }
        
        //middle
        else 
        {
           cells[i].toNorth = cells[i-n];
           cells[i].toSouth = cells[i+n];
           cells[i].toWest = cells[i-1]; 
           cells[i].toEast = cells[i+1];
        } 
        
        } //for i - adjacent cells

    } //generate
    
public void createMaze (Grid grid) 
{
    
	Random rnd = new Random();
    int dir;
        
    Stack<Cell> stack = new Stack<>();
    Cell currentCell = grid.cells[0];
    stack.push(currentCell);
    
    while (!stack.empty()) 
    {
    	
        dir = rnd.nextInt(7);
         
        if (currentCell.hasUnvisitedNeighbors(currentCell) == true) 
        {
            
        switch(dir) 
        {
            case 0: //NORTH
            if (currentCell.toNorth != null && currentCell.toNorth.getVisited() == false) 
            {
                currentCell.wallN = false;
                currentCell.toNorth.wallS = false;
                currentCell.setVisited(true);
                currentCell.toNorth.setVisited(true);
                stack.push(currentCell);
                currentCell = currentCell.toNorth;
                break;
            } //if
                        
            case 1: //EAST
            if (currentCell.toEast != null && currentCell.toEast.getVisited() == false) 
            {
                currentCell.wallE = false;
                currentCell.toEast.wallW = false;
                currentCell.setVisited(true);
                currentCell.toEast.setVisited(true);
                stack.push(currentCell);
                currentCell = currentCell.toEast;
                break;
            } //if

            case 2: //SOUTH
            if (currentCell.toSouth != null && currentCell.toSouth.getVisited() == false) 
            {
                currentCell.wallS = false;
                currentCell.toSouth.wallN = false;
                currentCell.setVisited(true);
                currentCell.toSouth.setVisited(true);
                stack.push(currentCell);
                currentCell = currentCell.toSouth;
                break;
            } //if            
            
            case 3: //WEST
            if (currentCell.toWest != null && currentCell.toWest.getVisited() == false) 
            {
                currentCell.wallW = false;
                currentCell.toWest.wallE = false;
                currentCell.setVisited(true);
                currentCell.toWest.setVisited(true);
                stack.push(currentCell);
                currentCell = currentCell.toWest;
                break;
            } //if
            
            case 4: //NORTH
                if (currentCell.toNorth != null && currentCell.toNorth.getVisited() == false) 
                {
                    currentCell.wallN = false;
                    currentCell.toNorth.wallS = false;
                    currentCell.setVisited(true);
                    currentCell.toNorth.setVisited(true);
                    stack.push(currentCell);
                    currentCell = currentCell.toNorth;
                    break;
                } //if
                            
                case 5: //EAST
                if (currentCell.toEast != null && currentCell.toEast.getVisited() == false) 
                {
                    currentCell.wallE = false;
                    currentCell.toEast.wallW = false;
                    currentCell.setVisited(true);
                    currentCell.toEast.setVisited(true);
                    stack.push(currentCell);
                    currentCell = currentCell.toEast;
                    break;
                } //if

                case 6: //SOUTH
                if (currentCell.toSouth != null && currentCell.toSouth.getVisited() == false) 
                {
                    currentCell.wallS = false;
                    currentCell.toSouth.wallN = false;
                    currentCell.setVisited(true);
                    currentCell.toSouth.setVisited(true);
                    stack.push(currentCell);
                    currentCell = currentCell.toSouth;
                    break;
                } //if            
                
                case 7: //WEST
                if (currentCell.toWest != null && currentCell.toWest.getVisited() == false) 
                {
                    currentCell.wallW = false;
                    currentCell.toWest.wallE = false;
                    currentCell.setVisited(true);
                    currentCell.toWest.setVisited(true);
                    stack.push(currentCell);
                    currentCell = currentCell.toWest;
                    break;
                } //if
            
            
            default :
            break;

        } //switch
        
        /* 8 cases were implemented for 4 directions, because Random.nextInt() 
         * generates the same number too often with the boundary set to 3  */
        
        }//if (cellCheck)
        
        else 
        {
            currentCell = stack.pop();
        
        } //else stack.pop()
        
    } //while
    
} //createMaze

} //class Grid
