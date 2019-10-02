package xmRadioLibrary;

public class Geolocation implements Location {
	public Coordinate coordinate;
	
	private String addr;
	
	private String type="Point";
	private String id;
	public Geolocation(String lon, String lat, String addr) {
		this.coordinate = new Coordinate(lon,lat);
		this.addr=addr;
	}
	
	public String getType() {
		return this.type;
	}
	public String toString() {
		return coordinate.toString() + ", " + addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddr() {
		return this.addr;
	}
	
	
	

}
