package xmRadioLibrary;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.time.Instant;

public class TrafficIncident<T> {
	public String id;
	public String ecode;
	public String se;
	private T t;
	private String table;
	private String lv;
	private String fv;
	private String at;
	
	
	
	public LocalDateTime startTime;
	public LocalDateTime endTime;
	
	
	
	public String desc;
	public int delay;
	
	public TrafficIncident(String id, String ecode, String se) {
		this.id=id;
		this.ecode=ecode;
		this.se=se;
		this.t=null;
		
		
	}
	public void set(T t) { this.t = t; }
    public T get() { return t; }
	
    public void setDesc(String text) {
    	this.desc = text;
    }
	
    public void setStartTime(String startTm) {
    	Instant instant = Instant.parse(startTm);
    	this.startTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }
    
    public void setEndTime(String endTm) {
    	Instant instant = Instant.parse(endTm);
    	this.endTime = LocalDateTime.ofInstant(instant,ZoneId.of(ZoneOffset.UTC.getId()));
    }
	public void getExtraElements() {
		System.out.println("startTime: " + startTime.toString());
		System.out.println("endTime: " + endTime.toString());
		System.out.println("desc: " + desc);
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getEventCode() {
		return this.ecode;
	}
	
	public String getSeverity() {
		return this.se;
	}
	
	
	public String toString() {
		String obj_str = "id: " + id;
		obj_str += "\n ecode: " + ecode;
		obj_str += "\n se: " + se;
		if (t != null)
			obj_str += "\n loc: " + t.toString();
		
			
		if (startTime != null)
			obj_str += "\n startTime: " + startTime.toString();
		if (endTime != null)
			obj_str += "\n endTime: " + endTime.toString();
		if (desc != null)
			obj_str += "\n desc: " + desc;
		obj_str +="\n\n";
		return obj_str;
	}
}
