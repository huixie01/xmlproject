package xmRadioLibrary;


public class TmcLocation extends Location {
	public String startId;
	public char dir;
	public String startExt;
	public String type="tmc";
	public TmcLocation(String startId, char dir, String startExt){
		this.startId = startId;
		this.dir = dir;
		this.startExt = startExt;		
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		return startId + ", " + dir + ", " + startExt;
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
	
	
}
