import java.awt.Dimension;

import javax.swing.JFrame;

public class MainDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    QuizManager manager = new QuizManager();
		manager.countyFileRead("countyData.txt");
		manager.coordinateFileRead("longandlad.txt");
		
		TexasFrame frame = new TexasFrame("Texas Counties");
//		Canvas c = new Canvas();
//		c.setPreferredSize(new Dimension(500,500));
//	    frame.getContentPane().add(c);
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    
	}

}
