package xmRadioLibrary;


public class TmcLocation extends Location {
	private String startId;
	private char dir;
	private String offset;
	private String startExt;
	private String type="tmc";
	private String table;
	public TmcLocation(String startId, char dir, String startExt){
		this.startId = startId;
		this.dir = dir;
		this.startExt = startExt;
		this.offset = "";
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		return startId + ", " + dir + ", " + offset + "," + startExt;
	}
	public void setStartId(String startId) {
		this.startId = startId;
	}
	public void setDir(char dir) {
		this.dir = dir;
	}
	public void setStartExt(String startExt) {
		this.startExt = startExt;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getStartId() {
		return this.startId;
	}
	
	
	
}
