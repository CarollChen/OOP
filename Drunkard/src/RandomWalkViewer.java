// Name: Chen Jingyuan
// USC loginid: Chen950
// CS 455 PA1
// Fall 2014

import java.util.Scanner;
import javax.swing.JFrame;


public class RandomWalkViewer {
	
	public final static int frameWidth = 400;
	public final static int frameHeight = 400;
	

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("RandomWalk"); 
		Scanner stepNum = new Scanner(System.in);
		int num = 0;
		boolean frameCreated = false;
		
		frame.setSize(frameWidth, frameHeight);
		
		
		while(!frameCreated){ //keep asking until we get the valid input
			System.out.print("Enter number of steps: ");
			
			if(stepNum.hasNextInt()){
				num = stepNum.nextInt();
				if(num <= 0){
					System.out.println("ERROR: Number entered must be greater than 0.");
				}
				else
					frameCreated = true;
			}
			else{
				stepNum.nextLine();
				System.out.println("ERROR: Number entered must be greater than 0.");
			}
			
		}
		
		RandomWalkComponent component = new RandomWalkComponent(num);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(component);	
		frame.setVisible(true);
		
		// TODO Auto-generated method stub
	

	}

}
