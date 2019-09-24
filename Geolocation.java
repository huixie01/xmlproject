package xmRadioLibrary;

public class Geolocation implements Location {
	public String lon;
	public String lat;
	public String addr;
	public String type="geo";
	public Geolocation(String lon, String lat, String addr) {
		this.lon=lon;
		this.lat=lat;
		this.addr=addr;
	}
	public String toString() {
		return lon + ", " + lat + ", " + addr;
	}

}
