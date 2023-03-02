import javax.swing.JFrame;

public class MyJFrame extends JFrame {

	private MyPanel panel; 
	
	MyJFrame() {
		panel = new MyPanel();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(panel); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
