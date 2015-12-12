//Name: Chen Jingyuan
//USC loginid: 9837356925
//CS 455 PA3
//Fall 2014

import javax.swing.JFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze:
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 
 * The top left is the maze entrance, and the bottom right is the maze exit.
 * 
 */


public class MazeViewer {

   public static void main(String[] args )  {

      String fileName = "";

      try {

     //    if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
     //    }
     //    else {
     //       fileName = args[0];
      		fileName = "bigMaze.txt";

            int[][] mazeData = readMazeFile(fileName);

            JFrame frame = new MazeFrame(mazeData);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
         }

  //    }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   
   /**
      readMazeFile reads in and returns a maze from the file whose name is
      String given. The file format is shown in the MazeViewer class comments.
      
      @param fileName
                the name of a file to read from
      @returns 
               the array with maze contents. 0's correspond to 0's in the file
               and 1's correspond to 1's in the file. The first dimension is
               which row, and the second is which column. E.g. if the file
               started with 3 10, it would mean this array returned would have
               3 rows, and 10 columns.
      @throws FileNotFoundException
                 if there's no such file (subclass of IOException)
      @throws IOException
                 (hook given in case you want to do more error-checking.
                  that would also involve changing main to catch other 
                  exceptions)
    */
   private static int[][] readMazeFile(String fileName) throws IOException {
      
	   File file = new File(fileName);
	   Scanner mazeFile = new Scanner(file);
	   
	   int row = mazeFile.nextInt();
	   int col = mazeFile.nextInt();
	   
	   int[][] maze = new int[row][col] ;
	   
	   mazeFile.nextLine();
	   for(int i = 0; i < row; i++){
		   if(mazeFile.hasNextLine()){
			   String s = mazeFile.nextLine();
			  // System.out.println(s);
			   char[] num_char = s.toCharArray();
			   
			   for(int j = 0;j < col; j ++){
				   int num_temp = Integer.parseInt(num_char[j]+"");
				   maze[i][j] = num_temp;
				//   System.out.print(maze[i][j]);
				  // if(j == col-1){
					//   System.out.print("\n");
				   //}
			   }
		   }
	   }
	  
      return maze;   
     
   }

}

