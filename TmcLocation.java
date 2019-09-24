package xmRadioLibrary;


public class TmcLocation implements Location {
	public String startId="10000";
	public char dir='+';
	public String startExt="0";
	public String type="tmc";
	public TmcLocation(String startId, char dir, String startExt){
		this.startId = startId;
		this.dir = dir;
		this.startExt = startExt;		
	}
	public String toString() {
		return startId + ", " + dir + ", " + startExt;
	}
	
}
