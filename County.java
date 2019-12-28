import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
/*
 * each county is going to have its own polygon, color, and an empty list of coordinates to start
 * 
 */

public class County extends Region{

	private String countyName;
	private int countyCode;
	private int stateCode;
	private int pid;
	private ArrayList<Coord2D> coordinatesList;
	private Color color;
	
	public County(Polygon shape, Color color, String name, int countyCode, int stateCode, int pid,
			ArrayList<Coord2D> coordinatesList ) {
		super(shape, color, coordinatesList);
		this.countyName = name;
		this.countyCode = countyCode;
		this.stateCode = stateCode;
		this.pid = pid;
		this.coordinatesList = coordinatesList;
		// generate a random color
		color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	
	public County() {
		// TODO Auto-generated constructor stub
		color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}

	/*
	 * draw the county on to the map
	 */
	public void drawOn(Graphics g) {
		g.setColor(color);
		g.fillPolygon(shape);
//		System.out.println(countyName + ": " + color);
	}

	public ArrayList<Coord2D> getCoordinatesList() {
		return coordinatesList;
	}

	public void setCoordinatesList(ArrayList<Coord2D> coordinatesList) {
		this.coordinatesList = coordinatesList;
	}

	public String getName() {
		return countyName;
	}

	public void setName(String name) {
		this.countyName = name;
	}

	public int getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(int countyCode) {
		this.countyCode = countyCode;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}	

}
