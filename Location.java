package xmRadioLibrary;

public interface  Location {
	public String type="Tmc";
	//public String _id;
	//public String description;
	
	
	default String getType() {
		return "";
	}
	
	
}
