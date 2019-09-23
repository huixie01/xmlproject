package xmRadioLibrary;


public class TmcLocation implements Location {
	public int startId;
	public char dir;
	public int startExt;
	public String type="tmc";
	public TmcLocation(int startId, char dir, int startExt){
		this.startId = startId;
		this.dir = dir;
		this.startExt = startExt;		
	}
	
}
