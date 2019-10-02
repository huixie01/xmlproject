package xmRadioLibrary;

public class Event {
	public String _id;
	public String description;
	
	
	public String eventCode;
	public String severity;
	public String validStart;
	public String validEnd;
	public String type="TrafficIncident";
	public String lastUpdated;
	
	public void setEvent(String id, String desc, String sev, String ecode, String stime, String etime) {
		this._id = id;
		
		this.description = desc;
		
		this.severity=sev;
		this.eventCode=ecode;
		this.validStart=stime;
		this.validEnd=etime;
	}
}
