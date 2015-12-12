// Name: Chen Jingyuan
// USC loginid: Chen950
// CS 455 PA1
// Fall 2014

public class DrunkardTester {

	public static void mdd(String[] args) {
		
		ImPoint loc1 = new ImPoint(3,5);
		ImPoint loc2 = new ImPoint(8,6);
		ImPoint loc3 = new ImPoint(2,4);
		ImPoint loc4 = new ImPoint(32,10);
		DrunkardTester(loc1, 2, 5);
		DrunkardTester(loc2, 3, 5);
		DrunkardTester(loc3, 1, 5);
		DrunkardTester(loc4, 4, 5);

	}
	private static void DrunkardTester(ImPoint loc, int theStepSize, int num) {
		
		int x = loc.getX();
		int y = loc.getY();	
		Drunkard drunk = new Drunkard(loc, theStepSize);

		System.out.println("Drunkard starts at (" + x + "," + y + "); step size is " + theStepSize);
		loc = drunk.getCurrentLoc();
		System.out.println("get starting loc [expected:(" + x + "," + y + ")]: ("+ loc.getX() +"," + loc.getY() + ")");
		
		for(int i = 0; i < num; i++){
			
			int oldX = loc.getX();
			int oldY = loc.getY();
			drunk.takeStep();
			loc = drunk.getCurrentLoc();
			
			System.out.print("took step to ("+ loc.getX() +"," + loc.getY() + ") ");
			if (((loc.getX() == oldX + theStepSize) && (loc.getY() == oldY)) ||
				((loc.getX() == oldX - theStepSize) && (loc.getY() == oldY)) ||
				((loc.getX() == oldX) && (loc.getY() == oldY + theStepSize)) ||
				((loc.getX() == oldX) && (loc.getY() == oldY- theStepSize)) 
				)			
				System.out.println("SUCCEEDED");
			else 	
				System.out.println("FAILED: not a valid step");
		}
		
	    }



}
