package xmRadioLibrary;

public class Geolocation extends Location {
	private String lon;
	private String lat;
	private String addr;
	
	public String type="geo";
	public Geolocation(String lon, String lat, String addr) {
		this.lon=lon;
		this.lat=lat;
		this.addr=addr;
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		return lon + ", " + lat + ", " + addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddr() {
		return this.addr;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	

}
