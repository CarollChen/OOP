// Name: Chen Jingyuan
// USC loginid: 9837356925
// CS 455 PA3
// Fall 2014

import java.awt.Color;
import java.awt.Graphics;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   
   private static final int START_X = 10;  // where to start drawing maze in frame
   private static final int START_Y = 10;  // (i.e., upper-left corner)
   
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;

  
   private int row;
   private int col;
   private Maze maze;
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {      
	   this.row = maze.numRows();
	   this.col = maze.numCols();
	   this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics g2=(Graphics) g;
	   g2.drawRect(START_X, START_Y, BOX_WIDTH * this.col, BOX_HEIGHT*this.row);
	   g2.setColor(Color.BLACK);
	   for(int i = 0; i < row; i++){
		   for(int j=0; j < col; j++){
			   if(!((i == row -1)&&(j == col -1))){
				   MazeCoord brick = new MazeCoord(i,j);
				   if(maze.getValAt(brick)==1){
					   g2.fillRect(START_X+BOX_WIDTH*j, START_Y+BOX_HEIGHT*i, BOX_WIDTH, BOX_HEIGHT);
				   }
			   }
			   else{
				   g2.setColor(Color.GREEN);
				   g2.fillRect(START_X+BOX_WIDTH*(col - 1), START_Y+BOX_HEIGHT*(row -1), BOX_WIDTH, BOX_HEIGHT);
			   }
			   
		   }
		     
	   }
	   g2.setColor(Color.BLUE);
	   LinkedList<MazeCoord> path = maze.getPath();
	   if(!path.isEmpty()){
		   ListIterator<MazeCoord> Iter = path.listIterator();
		   MazeCoord first = Iter.next();
		   while(Iter.hasNext()){
			   MazeCoord second = Iter.next();
			   g2.drawLine(first.getCol()*BOX_WIDTH-BOX_WIDTH/2+START_X, first.getRow()*BOX_HEIGHT-BOX_HEIGHT/2+START_Y, second.getCol()*BOX_WIDTH-BOX_WIDTH/2+START_X, second.getRow()*BOX_HEIGHT-BOX_HEIGHT/2+START_Y);
			   first = second;
		   }
		   
	   }
	   
   }
   
}


