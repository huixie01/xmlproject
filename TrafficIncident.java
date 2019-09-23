package xmRadioLibrary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrafficIncident {
	public int id;
	public int ecode;
	public int se;
	public Location loc;
	public LocalDateTime startTime;
	public LocalDateTime endTime;
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-dd-MMTHH:mm:ssZ");
	public String desc;
	public int delay;
	public TrafficIncident(int id, int ecode, int se) {
		this.id=id;
		this.ecode=ecode;
		this.se=se;
	}
	public void setLocation(Location loc) {
		this.loc = loc;
	}

}
