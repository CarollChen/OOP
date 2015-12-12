// Name: Chen Jingyuan
// USC loginid: Chen950
// CS 455 PA1
// Fall 2014

/**
   Drunkard class
       Represents a "drunkard" doing a random walk on a grid.
*/

import java.util.Random;


public class Drunkard {

    /**
       Creates drunkard with given starting location and distance
       moved in a single step.
       @param startLoc starting location of drunkard
       @param theStepSize size of one step in the random walk
    */
    public Drunkard(ImPoint startLoc, int theStepSize) {
    this.loc = startLoc;
    this.stepSize = theStepSize;
    this.rand =new Random();	
    }


    /**
       Takes a step of length step-size (see constructor) in one of
       the four compass directions.  Changes the current location of the
       drunkard.
    */
    public void takeStep() {
    	this.direction = rand.nextInt(4);
    	//System.out.println(direction);
    	switch(direction){
    	case 0:
    		loc = loc.translate(this.stepSize, 0); //move to the right
    		break;
    	case 1:
    		loc = loc.translate(-this.stepSize, 0);  //move to the left
    		break;
    	case 2:
    		loc = loc.translate(0, this.stepSize);  //move up
    		break;
    	case 3:
    		loc = loc.translate(0, -this.stepSize);  //move down
    		break;
    	}

    }


    /**
       gets the current location of the drunkard.
       @return an ImPoint object representing drunkard's current location
    */
    public ImPoint getCurrentLoc() {
	return loc;  // REMOVE this line -- dummy code to get it to compile
    }
    
    private ImPoint loc;
    private int stepSize;
    private Random rand;
    private int direction;
    
}