package xmRadioLibrary;


public class TmcLocation implements Location {
	private String id;
	private char direction;
	private String offset;
	private String startExt;
	//private String type="TrafficIncident";
	private String table;
	public TmcLocation(String startId, char dir, String startExt){
		this.id = startId;
		this.direction = dir;
		this.startExt = startExt;
		this.offset = "";
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		return id + ", " + direction + ", " + offset + "," + startExt;
	}
	public void setStartId(String startId) {
		this.id = startId;
	}
	public void setDir(char dir) {
		this.direction = dir;
	}
	public char getDir() {
		return  this.direction;
	}
	public void setStartExt(String startExt) {
		this.startExt = startExt;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getStartId() {
		return this.id;
	}
	
	
	
}
