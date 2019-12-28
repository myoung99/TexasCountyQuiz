import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class QuizManager {

	private ArrayList<Region> regions;
	private ArrayList<County> counties;
	private float minX, maxX, minY, maxY;
	private final int MAX_WIDTH, MAX_HEIGHT;
	private County selection;
	private float theMin, theMax, theMinY, theMaxY;
	private boolean isSelected;
	// private County[] countyArray;

	public QuizManager() {

		selection = new County(null, null, null, 0, 0, 0, null);

		//list of counties and regions
		counties = new ArrayList<County>();
		regions = new ArrayList<Region>();
		// countyArray = new County[5];

		//read in both provided files
		countyFileRead("countyData.txt");
		coordinateFileRead("longandlad.txt");

		MAX_WIDTH = 600;
		MAX_HEIGHT = 600;

		theMin = 1000;
		theMax = -1000;
		theMinY = 1000;
		theMaxY = -1000;

		//create shapes for each county
		makePolygons();

	}

	/*
	 * this method will return the county that the entered pid is associated with
	 */
	public County findCountyByPID(int id) {
		County foundCounty = new County();
		for (int i = 0; i < counties.size(); i++) {
			if (counties.get(i).getPid() == id) {
				foundCounty = counties.get(i);
			}
		}
		return foundCounty;
	}
	/*
	 * this method should iterate through the county list look to see if the current
	 * county contains the (x,y) that was clicked then return the polygon if the
	 * coordinate clicked is inside of the county if the coordinate clicked is not
	 * in a county (the black space) return null this should be called later when
	 * the user clicks on a county so we can find the users desired county selection
	 */

	public County findCounty(int x, int y) {
		Polygon p = null;
		County c = new County();
		for (int i = 0; i < counties.size(); i++) { // go through the array of counties
			if (counties.get(i).getShape().contains(x, y)) { // see if the current county's shape has the point entered
				p = counties.get(i).getShape(); // if it has the point, get the shape
				c = counties.get(i);
			} else { // if there isnt a shape with that point in it (black space) return null
				p = null;
			}
		}
		return c;
	}

	/*
	 * randomly pick and return n handles on a county other than the one these
	 * counties will be added as buttons once called in the canvas
	 */
	public County[] randomCounty(int n, int pid) { // n is the number of handles that need to be returned
		County[] countyArray = new County[5];
		Random rand = new Random();
		Random r = new Random();
		int rpid = 0; // random pid
		int randomIndex = 0; // random index for the county array
		randomIndex = r.nextInt(5); // get a random index for the selected county
		selection = findCountyByPID(pid);
		isSelected = true;
		countyArray[randomIndex] = findCountyByPID(pid); // put the selected county at that random pid
		for (int g = 0; g < countyArray.length; g++) { // read through the county array
			// int index = 0;
			randomIndex = r.nextInt(5); // create a random number
			rpid = (rand.nextInt(265) + 1); // create a random pid
			// generate a number, if the county array has something in it at that random
			// index...
			if (countyArray[randomIndex] == null) { // if that index doesnt have anything in it
				countyArray[randomIndex] = findCountyByPID(rpid); // assign the county to that index
			} else if (countyArray[randomIndex] != null) { // if that certain index has something in it
				do {
					randomIndex = r.nextInt(5); // look for another index
					if (countyArray[0] != null && countyArray[1] != null && countyArray[2] != null
							&& countyArray[3] != null && countyArray[4] != null) {
						return countyArray;
					}
				} while (countyArray[randomIndex] != null);

				if (countyArray[randomIndex] == null) {
					countyArray[randomIndex] = findCountyByPID(rpid);
				}
			}

			
			// index++;
		}
		return countyArray;

	}
	
	public String selectedName() {
		String str = selection.getName();
		return str; 
	}
	
	public County selectedCounty() {
		return selection;
	}

	/*
	 * iterates through the array of counties and draws each county 
	 */
	public void drawCounties(Graphics gfx) {
		for (int i = 0; i < counties.size(); i++) { // read through the array list of counties
			counties.get(i).drawOn(gfx); // draw each county
		}

	}
	
	/*
	 * changes the state of the polygon to isSelected if it was clicked
	 */
	public Polygon isSelected(String cname) {
		Polygon poly = new Polygon();
//		boolean isSelected;
		if(isSelected = true) { // if a county has been selected 
			poly = selection.getShape();
		}
		return poly;
	}
	
	public void isCorect() {
		
	}

	/*
	 * TODO this method should call 2 pixels and should make the polygons
	 */
	public void makePolygons() {
		ArrayList<Coord2D> coords = new ArrayList<Coord2D>();
		getXY();
		for (int i = 0; i < counties.size(); i++) {
			coords = counties.get(i).getCoordinatesList();
			Polygon rtnPoly = new Polygon();
			for (int k = 0; k < coords.size(); k++) {
				Point p = toPixels(coords.get(k).getLon(), coords.get(k).getLad()); // toPixels needs to pass through
																					// the lon and lad
				// System.out.println(p.getX() + "," + p.getY()); // this is printing actual
				// numbers now so its working!
				rtnPoly.addPoint((int) p.getX() + 30, (int) p.getY() + 30);
			}

			counties.get(i).setShape(rtnPoly); // this gets the current county from the loop and sets its shape to the
												// polygon

		}
	}

	/*
	 * TODO this should convert the floats(longitude and latitude) to x and y for
	 * points
	 * 
	 * vx = width * (wx � minX) / (maxX-minX) vy = height - height * (wy � minY) /
	 * (maxY � minY)
	 */
	public Point toPixels(float lon, float lad) {
		Point myPoint = new Point();
		int vectorX = (int) (MAX_WIDTH * ((lon - this.minX) / (maxX - minX)));
		int vectorY = (int) (MAX_HEIGHT - (MAX_HEIGHT * (lad - this.minY) / (this.maxY - this.minY)));
		myPoint.setLocation(vectorX, vectorY);
		return myPoint;
	}

	/*
	 * get the minimum and maximum x and y for each county 
	 */
	public void getXY() {

		minX = counties.get(0).getCoordinatesList().get(0).getLon();
		maxX = counties.get(0).getCoordinatesList().get(0).getLon();
		minY = counties.get(0).getCoordinatesList().get(0).getLad();
		maxY = counties.get(0).getCoordinatesList().get(0).getLad();

		for (int i = 0; i < counties.size(); i++) {
			for (int k = 0; k < counties.get(i).getCoordinatesList().size(); k++) {
				minX = Math.min(counties.get(i).getCoordinatesList().get(k).getLon(), minX);
				maxX = Math.max(counties.get(i).getCoordinatesList().get(k).getLon(), maxX);
				minY = Math.min(counties.get(i).getCoordinatesList().get(k).getLad(), minY);
				maxY = Math.max(counties.get(i).getCoordinatesList().get(k).getLad(), maxY);
			}
		}

	}

	/*
	 * TODO this method reads the counties and their corresponding information 
	 */
	public void countyFileRead(String fileName) {
		// variables to be used in the try...catch
		int counter = 0;
		int pid = 0;
		int sCode = 0;
		int cCode = 0;
		String name = null;

		String str;
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName)); // read the file
			str = in.readLine(); // each line is assigned to the str variable
			for (int i = 0; i < 7; i++) { // skip the first 7 lines
				str = in.readLine();
				// System.out.println("Line: " + str);
			}
			County c = new County();
			while (str != null) { // while there is something in the string keep reading the file
				int remainder = counter % 7; // divide by 7 and get the remainder to set up the cases

				str = str.trim();
				str = str.replaceAll("\"", "");

				switch (remainder) {// go through the switch to create the counties
				case 0: // if the remainder equals 0, you have a new county
					c.setPid(Integer.parseInt(str)); // parse the string to get the pid
					// pid = county.getPid();
					break;
				case 1:
					c.setStateCode(Integer.parseInt(str)); // parse to get state code
					// sCode = county.getStateCode();
					break;
				case 2:
					c.setCountyCode(Integer.parseInt(str)); // parse to get county code
					// cCode = county.getCountyCode();
					break;
				case 3:
					c.setName(str); // assign the string to the name of the county
					// System.out.println(str); // cameron is here
					break;
				case 4:
					c = new County(); // create a new county

					counties.add(c); // create an array list of counties
					break;

				}
				str = in.readLine(); // read the next lines
				counter++;
			}
			in.close();
		} catch (IOException e) {
			System.err.println("File Read Error");
		}

	}

	/*
	 * TODO this method reads the coordinates for each county
	 */
	public void coordinateFileRead(String fileName) {
		String str;
		Region current;
		int pid = 0;
		float lon = 0;
		float lad = 0;
		boolean hasCoordinates; // this boolean will be used later on in this method during the two part line if
								// statement
		// if hasCoordinates is false that means it got to the "end" string
		// if it is true that means that it needs to continue reading the lines and
		// getting the lon and lad pairs
		try {

			int index = 0;

			BufferedReader in = new BufferedReader(new FileReader(fileName));
			str = "";

			while (str != null) {
				str = in.readLine();
				if (str == null) {
					return;
				}
				str = str.trim(); // trim the leading and trailing white space
				str = str.replaceAll("\\s{2,}", " "); // replace the several spaces with just one space
				String[] parts = str.split(" "); // create an array of "parts" with the info in the file

				ArrayList<Coord2D> coords = new ArrayList<Coord2D>();
				ArrayList<Coord2D> coordinates = new ArrayList<Coord2D>();
				if (parts.length == 3) { // need to get the pid of the county
					// the "current" variable is a county
					// turn the string into an int and assign that to the pid
					index = Integer.parseInt(parts[0]);

					float x = Float.parseFloat(parts[1]);
					float y = Float.parseFloat(parts[2]);

					coords.add(new Coord2D(x, y));
					counties.get(index - 1).setCoordinatesList(coords);

				} else if (parts.length == 2) {
					float x = Float.parseFloat(parts[0]);
					float y = Float.parseFloat(parts[1]);

					counties.get(index - 1).getCoordinatesList().add(new Coord2D(x, y));

				} else if (parts.length == 1 && !str.trim().equals("END")) {
					regions.add(new Region());

					for (int i = 0; i < 2; i++) {
						str = in.readLine();

						str = str.trim();
						str = str.replaceAll("\\s{2,}", " ");
						parts = str.split(" ");

						if (parts.length == 2) {
							float x = Float.parseFloat(parts[0]);
							float y = Float.parseFloat(parts[1]);

							coordinates.add(new Coord2D(x, y));
							regions.get(0).setCoordinatesList(coordinates);

							regions.get(0).getCoordinatesList().add(new Coord2D(x, y));
						}
					}
				}
				
			}
			in.close();
		} catch (IOException e) {
			System.err.println("File Read Error");
		}
	}

}
