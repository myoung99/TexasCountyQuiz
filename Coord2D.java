/*
 * this class is responsible for turning the longitude and latitude into x and y 
 * coordinates for the map
 */
public class Coord2D {
	
	private float lon;
	private float lad;
	
	public Coord2D(float lon, float lad) {
		this.lon = lon;
		this.lad = lad;
	}
	
	/*
	 * this method is going to be called when the readCoordinates class gets to a two part line (the lon and lad)
	 */
	public void createCordinate(float lon, float lad) {
		Coord2D coord = new Coord2D(lon, lad); 
		// read the line
		// trim, replace, split
		// parse the string into a float
		// call the createCoordinate method with those two floats as the parameters 
		
		this.lon = lon;
		this.lad = lad;
		
	}

	// call these later when we need to make points 
	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getLad() {
		return lad;
	}

	public void setLad(float lad) {
		this.lad = lad;
	}
	
	

}
