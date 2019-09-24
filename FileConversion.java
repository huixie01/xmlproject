package xmRadioLibrary;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;

public class FileConversion {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean id = true;
	boolean ec = true;
	boolean se = true;
	boolean location = true;
	boolean lon = true;
	boolean lat = true;
	boolean startId = true;
	Geolocation geoLoc;
	TmcLocation tmcLoc;
	TrafficIncident ti=new TrafficIncident("0","0","0");
	

	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

		//System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("id")) {
			id = true;
		}

		if (qName.equalsIgnoreCase("ec")) {
			ec = true;
		}

		if (qName.equalsIgnoreCase("se")) {
			se = true;
		}
		if (qName.equalsIgnoreCase("loc")) {
			location = true;
			if (qName.equalsIgnoreCase("lon")) {
				lon = true;
			}else if (qName.equalsIgnoreCase("lat")) {
				lat = true;
			}else if (qName.equalsIgnoreCase("startId")) {
				startId = true;
			}
		}
		System.out.println("incident: ");
		System.out.println(" -> id " + ti.id);
		System.out.println(" -> ec " + ti.ecode);
		System.out.println(" -> se " + ti.se);
		if ( ti.getLocation() != null)
			System.out.println(" -> loc " +  ti.toString() );
	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		//System.out.println("End Element :" + qName);

	}

	public void characters(char ch[], int start, int length) throws SAXException {

		
		if (id) {
			ti.id = new String(ch, start, length); 
			id = false;
		}else if (ec) {
			ti.ecode =  new String(ch, start, length);
			ec=false;
		}else if (se) {
			ti.se = new String(ch, start, length);
			se=false;
		}
		if (location) {
			String type = new String(ch, start, length);
			if ( type.equals("tmc")) {
			
				ti.setLocation(new TmcLocation("10000",'+',"0"));
			}
			else if (new String(ch, start, length).equals("geo")) {
				ti.setLocation(new Geolocation("0.0","0.0","default address"));
			}
			
		}
		
		

		

	}

     };

       saxParser.parse("myinput.xml", handler);
      
      
 
     } catch (Exception e) {
       e.printStackTrace();
     }
  
   }

}