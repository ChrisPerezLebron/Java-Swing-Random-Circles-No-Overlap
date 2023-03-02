import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	private final int FRAME_WIDTH = (int)screenSize.getWidth() - 100;
	private final int FRAME_HEIGHT = (int)screenSize.getHeight() - 100;
	
	private final double RADIUS_MAX = 50; 
	private final int CIRCLE_COUNT = 15000;
	
	
	private Random rand; 
	
	
	MyPanel() {
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		rand = new Random(); 
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		//define an ArrayList of Ellipse2D objects
		ArrayList<Ellipse2D> ellipseList = new ArrayList<>(); 
		
		
		int count = 0;
		//this will be set to true if we need to try a new location for this circle
		//because of collision with an already existing circle 
		boolean retry; 
			
		do {
			retry = false; 
			
			//come up with randomly determined 
			double xCenter = FRAME_WIDTH * rand.nextDouble();
			double yCenter = FRAME_HEIGHT * rand.nextDouble();
			double radius = RADIUS_MAX * rand.nextDouble();
			
			Ellipse2D circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(xCenter, yCenter, xCenter + radius, yCenter + radius);
			
			//now look at all previously defined circles. if you find that this circle overlaps
			//with a circle that already exists then set retry to true
			int index = 0; 
			while(!retry && index < ellipseList.size()) {
				
				//check if the circle we created intersects the current 
				//circle in the list of circles
				if(ellipseList.get(index).intersects(circle.getBounds2D()))
					retry = true;
				index++; 
				
			}
			
			//if no intersecting circle was found after the while loop completes
			//then add the circle to the list and increment count
			if(!retry) {
				ellipseList.add(circle);
				count++;
			}
			
			
			
		} while(retry || count < CIRCLE_COUNT); 
			
		
		System.out.println(ellipseList.size());
		
		//draw all circles 
		//this reuses the count variable from earlier
		for(count = 0; count < ellipseList.size(); count++)
			g2D.draw(ellipseList.get(count));

	}
	
	
}
