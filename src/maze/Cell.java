package maze;

public class Cell {
	
	private boolean visited; //cell is visited by maze generator
    
	//int index;
    
    int x; //X coordinate
    int y; //Y coordinate
    
    //neighboring cells:
    Cell toNorth;
    Cell toSouth;
    Cell toWest;
    Cell toEast;
    
    //cell walls intact    
    boolean wallN = true;
    boolean wallS = true;
    boolean wallE = true;
    boolean wallW = true;

    
    
 // *** Constructor ***//
    
public Cell (int index) 
{
    visited = false;
    //this.index = index;
    
} //constructor



// *** Getter-setter methods ***//

public void setX (int x) 
{
	this.x = x;
} //setX

public void setY (int y) 
{
	this.y = y;
} //setY

public int getX () 
{
	return this.x;
} //getX

public int getY () 
{
	return this.y;
} //getY

public void setVisited(boolean visited)
{
	this.visited = visited;
} //setVisited

public boolean getVisited() 
{
	return this.visited;
} //getVisited


// *** Other methods *** //


public boolean hasUnvisitedNeighbors (Cell cell)
{
    
    if ((cell.toNorth == null || cell.toNorth.visited == true) && (cell.toSouth == null || cell.toSouth.visited == true) && (cell.toEast == null || cell.toEast.visited == true) && (cell.toWest == null || cell.toWest.visited == true)) 
    {
        return false;
    }
    else 
    {
        return true;
    }
    
} //hasUnvisitedNeighbors; setter method to check if cell has unvisited neighbors



} // Cell class
