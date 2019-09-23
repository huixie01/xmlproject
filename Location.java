package xmRadioLibrary;

public interface Location {
	String type = "";
	
	default String getType() {
		return type;
	}
}
