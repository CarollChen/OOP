// Name: Chen Jingyuan
// USC loginid: Chen950
// CS 455 PA1
// Fall 2014

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;


public class RandomWalkComponent extends JComponent {
	
	public final static int walkStartPointX = 200;
	public final static int walkStartPointY = 200;
	public final static int stepSize = 5;
	
	public RandomWalkComponent (int num){
		
		this.num = num;
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2=(Graphics2D) g;		
		ImPoint walkStartPoint = new ImPoint(walkStartPointX, walkStartPointY);
		Drunkard drunkard = new Drunkard(walkStartPoint, stepSize);
		
		for(i = 0; i < num; i ++){
			
			Point2D startPoint = drunkard.getCurrentLoc().getPoint2D(); //get current location
			drunkard.takeStep();
			Point2D endPoint = drunkard.getCurrentLoc().getPoint2D(); //get location after random move
			Line2D line=new Line2D.Double(startPoint, endPoint);
			g2.draw(line);
			
		}		
		
	}
	
	
	private int num;
	private int i;

}
