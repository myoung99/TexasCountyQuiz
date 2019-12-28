import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/*
 * this is the jpanel that is responsible for displaying the following:
 * the map
 * amount of lives remaining
 * the buttons with options for the user to select
 */
public class Canvas extends JPanel implements MouseListener {

	private JButton btn1, btn2, btn3, btn4, btn5;
	private JPanel texasPanel, buttonPanel;
	private QuizManager manager;
	private County[] c;
	private int pid;
	private JLabel lblStatus;
	private JPanel statusPanel;
	private Polygon selectedShape;
	private String county;
	private int lives;
	private boolean isCorrect;
	private JPanel livesPanel;
	private JLabel lblLives;

	public Canvas() {
		super();
		manager = new QuizManager();
		c = new County[5];
		selectedShape = new Polygon();
		lives = 5; // Initialize the lives at 5
		// frame = new TexasFrame();

		setLayout(new BorderLayout(0, 0));

		statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		add(statusPanel, BorderLayout.NORTH);

		//prompts the user to pick a county 
		lblStatus = new JLabel("Click on a county...");
		lblStatus.setForeground(Color.white);
		statusPanel.add(lblStatus);

		//separate panel for displaying lives
		livesPanel = new JPanel();
		livesPanel.setForeground(Color.RED);
		livesPanel.setBackground(Color.BLACK);
		add(livesPanel, BorderLayout.WEST);

		//corresponding lives label
		lblLives = new JLabel("Lives: ");
		lblLives.setForeground(Color.RED);
		livesPanel.add(lblLives);

		//separate panel for the map
		texasPanel = new JPanel();
		texasPanel.setPreferredSize(new Dimension(500, 500));
		texasPanel.setBackground(Color.black);
		add(texasPanel, BorderLayout.CENTER);

		//separate panel for the buttons
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

		//button 1
		btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn1) {
					if (btn1.getText() == getCounty()) { //button text matches county
						System.out.println("Correct guess");
						isCorrect = true;
						getLivesLabel().setText("You Win");
					} else {
						System.out.println("You're wrong"); //incorrect guess
						isCorrect = false;
						lives--;
						getLivesLabel().setText("Lives: " + lives);
						if(lives <= 0) {
							getLivesLabel().setText("You Lose");
							System.exit(0);
						}
//						if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
//							System.exit(0);
//						}
					}
				}
				System.out.println(lives);
			}
		});
		btn1.setVisible(false);
		buttonPanel.add(btn1);

		//the following buttons do the same is the first
		btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn2) {
					if (btn2.getText() == getCounty()) {
						System.out.println("Correct guess");
						isCorrect = true;
						getLivesLabel().setText("You Win");
					} else {
						System.out.println("You're wrong");
						isCorrect = false;
						lives--;
						getLivesLabel().setText("Lives: " + lives);
						if(lives <= 0) {
							getLivesLabel().setText("You Lose");
						}
//						if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
//							System.exit(0);
//						}
					}
				}
				System.out.println(lives);
			}
		});
		btn2.setVisible(false);
		buttonPanel.add(btn2);

		btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn3) {
					if (btn3.getText() == getCounty()) {
						System.out.println("Correct guess");
						isCorrect = true;
						getLivesLabel().setText("You Win");
					} else {
						System.out.println("You're wrong");
						isCorrect = false;
						lives--;
						getLivesLabel().setText("Lives: " + lives);
						if(lives <= 0) {
							getLivesLabel().setText("You Lose");
						}
//						if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
//							System.exit(0);
//						}
					}
				}
				System.out.println(lives);
			}
		});
		btn3.setVisible(false);
		buttonPanel.add(btn3);

		btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn4) {
					if (btn4.getText() == getCounty()) {
						System.out.println("Correct guess");
						isCorrect = true;
						getLivesLabel().setText("You Win");
					} else {
						System.out.println("You're wrong");
						isCorrect = false;
						lives--;
						getLivesLabel().setText("Lives: " + lives);
						if(lives <= 0) {
							getLivesLabel().setText("You Lose");
						}
//						if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
//							System.exit(0);
//						}
					}
				}
				System.out.println(lives);
			}
		});
		btn4.setVisible(false);
		buttonPanel.add(btn4);

		btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn5) {
					if (btn5.getText() == getCounty()) {
						System.out.println("Correct guess");
						isCorrect = true;
						getLivesLabel().setText("You Win");
					} else {
						System.out.println("You're wrong");
						isCorrect = false;
						lives--;
						getLivesLabel().setText("Lives: " + lives);
						if(lives <= 0) {
							getLivesLabel().setText("You Lose");
						}
//						if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
//							System.exit(0);
//						}
					}
				}
			}
		});
		btn5.setVisible(false);
		buttonPanel.add(btn5);

		this.addMouseListener(this);
	}

	public int updateLives() {
		
		return lives;
	}

	/*
	 * this method needs to draw all of the counties using the methods in the
	 * quizManagaer class??
	 */
	public void paint(Graphics gfx) {
		super.paint(gfx);
		manager.drawCounties(gfx);
	}

	public void updateShape() {
		Graphics2D g2 = (Graphics2D) getGraphics();
		BasicStroke fat = new BasicStroke(2); // apply the fat stroke to the county polygon
		g2.setStroke(fat);
		g2.setColor(Color.white);
		selectedShape = manager.isSelected(manager.selectedName());// get the polygon that was selected
		g2.drawPolygon(selectedShape);
	}

	/*
	 * all of the buttons need to be invisible until a county is clicked have a
	 * mouse listener for the counties when a county is clicked need to make buttons
	 * with random county names and one needs to be the one they clicked all of the
	 * buttons need to be set to visible then the game can start!!
	 */
	public void setBtnText(JButton btn) {
		btn.setText(getName());
	}

	public JButton getBtn1() {
		return btn1;
	}

	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}

	public JButton getBtn2() {
		return btn2;
	}

	public void setBtn2(JButton btn2) {
		this.btn2 = btn2;
	}

	public JButton getBtn3() {
		return btn3;
	}

	public void setBtn3(JButton btn3) {
		this.btn3 = btn3;
	}

	public JButton getBtn4() {
		return btn4;
	}

	public void setBtn4(JButton btn4) {
		this.btn4 = btn4;
	}

	public JButton getBtn5() {
		return btn5;
	}

	public void setBtn5(JButton btn5) {
		this.btn5 = btn5;
	}
	
	public JLabel getLivesLabel() {
		return lblLives;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Clicked"); // this is printing
		if(getLivesLabel().getText().equalsIgnoreCase("you win")) {
			getLivesLabel().setText("'Lives: " + 5);
		} else if(getLivesLabel().getText().equalsIgnoreCase("you lose")) {
			System.exit(0);
		} 
		getLivesLabel().setText("Lives: " + lives);

		
		pid = manager.findCounty(e.getX(), e.getY()).getPid(); // get the pid of the selected county
		System.out.println(pid); // printing the correct pid

		county = manager.findCountyByPID(pid).getName(); // checking to see if it gets the correct county

		System.out.println("Selected county: " + county); // printing correct county
		// call the random county method
		c = manager.randomCounty(5, pid);

		btn1.setText(c[0].getName());
		btn2.setText(c[1].getName());
		btn3.setText(c[2].getName());
		btn4.setText(c[3].getName());
		btn5.setText(c[4].getName());

		if (county != null) {
			lblStatus.setText("What is the name of that county?");
			updateShape();
		}

		// set all of the buttons to true
		if (county != null) {
			btn1.setVisible(true);
			btn2.setVisible(true);
			btn3.setVisible(true);
			btn4.setVisible(true);
			btn5.setVisible(true);
		}

		// repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void actionPerformed(ActionEvent e) {
	// // TODO Auto-generated method stub
	// if(e.getSource() == btn1) {
	// if(btn1.getName().equals(county)) {
	//
	// }
	// }
	// if(btn1.getName().equals(county)) {
	//
	// }
	// }

	// public void actionPerformed(ActionEvent e) {
	// }

	public String getCounty() {
		return county;
	}

}
