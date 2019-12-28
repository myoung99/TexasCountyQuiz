import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Region {
	/*
	 * the region is going to have a polygon, color, empty list of coordinates 
	 */
	Polygon shape;
	private Color color;
//	private float lon;
//	private float lad;
	private ArrayList<Coord2D> coordinatesList; // an array list of coord2ds called coordinatesList
//	private float current;
//	private float c;
	
	
	public Region(Polygon shape, Color color, ArrayList<Coord2D> coordinatesList) {
		this.shape = shape;
		this.color = color; // the color needs to be black for every region
		this.coordinatesList = coordinatesList;
//		this.lon = lon;
//		this.lad = lad;
		shape = new Polygon();
		coordinatesList = new ArrayList<Coord2D>();
	}

	public Region() {
		// TODO Auto-generated constructor stub
	}

	public Polygon getShape() {
		return shape;
	}

	public void setShape(Polygon shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Coord2D> getCoordinatesList() {
		return coordinatesList;
	}
	
	public void setCoordinatesList(ArrayList<Coord2D> coordinatesList) {
		this.coordinatesList = coordinatesList;
	}

	/*
	 * these methods need to look through the array list and get the lowest or highest lon or lad 
	 */
	
	public float getMinLon(ArrayList<Coord2D> coordinates) {
		float current = 1000;
		float min = 0;
		for(int i = 0; i < coordinates.size(); i++) {
			min = coordinates.get(i).getLon();
			current = Math.min(current, min);
	}
		return current;
	}
	
	public float getMaxLon(ArrayList<Coord2D> coordinates) {
		float current = -1000;
		float max = 0;
		for(int i = 0; i < coordinates.size(); i++) { // read through the list
			max = coordinates.get(i).getLon();
			current = Math.max(current, max);
		}
		return current;
	}
	
	public float getMinLad(ArrayList<Coord2D> coordinates) {
		float current = 1000;
		float min = 0;
		for(int i = 0; i < coordinates.size(); i++) {
			min = coordinates.get(i).getLad();
			current = Math.min(current, min);
	}
		return current;
	}
	
	public float getMaxLad(ArrayList<Coord2D> coordinates) {
		float current = -1000;
		float max = 0;
		for(int i = 0; i < coordinates.size(); i++) { // read through the list
			max = coordinates.get(i).getLad();
			current = Math.max(current, max);
		}
		return current;
	}
}
