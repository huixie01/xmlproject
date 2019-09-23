package xmRadioLibrary;

import java.io.File;

import javax.xml.stream.XMLInputFactory;

public class FileConversion {

	static XMLInputFactory xmlif = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			xmlif = XMLInputFactory.newInstance();
			/*
			FileInputStream fis = new FileInputStream(filename);
			XMLStreamReader xmlr = xmlif.createFilterReader(xmlif.createFilteredReader(
					reader, filter));
					*/
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		
	}

}
