import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;

public class TexasFrame extends JFrame {
	// set the frame height and width to a constant
	private final int MAX_HEIGHT = 700;
	private final int MAX_WIDTH = 700;
	private Canvas c;
	private JLabel lblStatus;
	public TexasFrame(String title) throws HeadlessException {
		super(title);
		this.setPreferredSize(new Dimension(MAX_HEIGHT, MAX_WIDTH));
		
		c = new Canvas();

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new BorderLayout(0, 0));

		
		getContentPane().add(c, BorderLayout.CENTER);
		
		this.pack();

	}
	

}
