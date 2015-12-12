// Name: Chen Jingyuan
// USC loginid: 9837356925
// CS 455 PA3
// Fall 2014


import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).

   Assumptions about structure of the mazeData (given in constructor), and the
   path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate (START_SEARCH_ROW,
        START_SEARCH_COL) (constants defined below)
     -- exit loc is maze coordinate (numRows()-1, numCols()-1) 
           (methods defined below)
     -- mazeData input 2D array of 0's and 1's (see public FREE / WALL 
        constants below) where the first index indicates the row. 
        e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public int START_SEARCH_COL = 1;
   public int START_SEARCH_ROW = 1;

   public static final int FREE = 0;
   public static final int WALL = 1;
   
   public int[][] mazein;
   private int[][] mazePath;
   private int row;
   private int col;
   
   private LinkedList<MazeCoord> path;


   
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See Maze class comments, above,
      for what goes in this array.

    */
   public Maze(int[][] mazeData) {
      mazein = mazeData;
      row = mazeData.length;
      col = mazeData[0].length;
     
      path = new LinkedList<MazeCoord>();
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
    */
   public int numRows() {
	  
      return row;   
   }


   /**
     Returns the number of columns in the maze
     @return number of columns
   */
   public int numCols() {
	 
      return col;   
   } 


   /**
      Gets the maze data at loc.
      @param loc the location in maze coordinates
      @return the value at that location.  One of FREE and WALL
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
    */
   public int getValAt(MazeCoord loc) {
	  
      return mazein[loc.getRow()][loc.getCol()];   
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
	 
      return path;   
   }


   /**
      Find a path through the maze if there is one.
      @return whether path was found.
    */
   public boolean search()  {  
	  path.clear();
	  mazePath = addWall(mazein);//add wall to the maze to solve the boundary problem
	  if (mazePath[START_SEARCH_COL][START_SEARCH_ROW]==WALL){
		  return false;
	  }
	  if (mazePath[row][col]==WALL){
		  return false;
	  }
	  if(START_SEARCH_COL == row && START_SEARCH_ROW == col){
		  return true;
	  }
	  return pathExist(START_SEARCH_COL,START_SEARCH_ROW,row,col);
	  
	 
	 
		  
      

   }
   private int[][] addWall(int[][] maze){
	   int[][] mazeWithWall = new int[row+2][col+2];
	   for(int i=0;i<row+2;i++){
		   for(int j=0; j<col+2; j++){
			   if((i == 0)||(j==0)||(i==row+1)||(j==col+1)){
				   mazeWithWall[i][j]=1;
			   }
			   else{
				   mazeWithWall[i][j]= maze[i-1][j-1];
			   }
		   }
	   }
	   return mazeWithWall;
	   
   }
   private boolean pathExist(int startx,int starty,int finishx, int finishy){
	   
	   MazeCoord[] dir = new MazeCoord[4];
	   dir[0]=new MazeCoord(1,0);
	   dir[1]=new MazeCoord(0,1);
	   dir[2]=new MazeCoord(-1,0);
	   dir[3]=new MazeCoord(0,-1);
	   mazePath[startx][starty] = WALL;// marked as visited
	   MazeCoord now = new MazeCoord(startx,starty);
	   path.add(now);//add the start point to the path
	   for(int i=0; i<4; i++){
		   if((startx+dir[i].getRow() == finishx) && (starty+dir[i].getCol() == finishy)){
			   path.add(new MazeCoord(finishx,finishy));//add the exit point to the path
			   return true;   
		   }
		   if(mazePath[startx+dir[i].getRow()][starty+dir[i].getCol()]==FREE){
			   if(pathExist(startx+dir[i].getRow(),starty+dir[i].getCol(),row,col)){
				   return true;
			   }
			   
		   }
			  
	   }
	   path.removeLast();
	   return false;
   }

}