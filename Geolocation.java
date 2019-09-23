package xmRadioLibrary;

public class Geolocation implements Location {
	public float lon;
	public float lat;
	public String addr;
	public String type="geo";
	public Geolocation(float lon, float lat, String addr) {
		this.lon=lon;
		this.lat=lat;
		this.addr=addr;
	}

}
