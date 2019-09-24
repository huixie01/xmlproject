package xmRadioLibrary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrafficIncident {
	public String id;
	public String ecode;
	public String se;
	public Location loc;
	public LocalDateTime startTime;
	public LocalDateTime endTime;
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-dd-MMEHH:mm:ssZ");
	
	public String desc;
	public int delay;
	public TrafficIncident(String id, String ecode, String se) {
		this.id=id;
		this.ecode=ecode;
		this.se=se;
		this.loc=null;
	}
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	public void getExtraElements() {
		System.out.println("startTime: " + startTime.toString());
		System.out.println("endTime: " + endTime.toString());
		System.out.println("desc: " + desc);
	}
	public Location getLocation() {
		return loc;
	}
}
