package xmRadioLibrary;


import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;

public class FileConversion {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean incident = false;
	boolean ev = true;
	boolean id = true;
	boolean ec = true;
	boolean se = true;
	boolean location = false;
	boolean lon = false;
	boolean lat = false;
	boolean stime = false;
	boolean etime = false;
	boolean startId = false;
	boolean geo = false;
	boolean tmc = false;
	boolean loc_startid = false;
	boolean loc_extent = false;
	boolean loc_dir = false;
	boolean loc_offset = false;
	boolean loc_addr = false;
	boolean ev_valid = false;
	boolean ev_text = false;
	String loc_lon;
	String loc_lat;
	String startTm;
	String endTm;
	Geolocation geoLoc;
	TmcLocation tmcLoc;
	TrafficIncident<Location> ti=null;
	String addr;
	String type="";
	ArrayList<TrafficIncident<Location>>	ti_list;
			
	

	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

		System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("incident")) {
			incident = true;
			ti_list = new ArrayList<TrafficIncident<Location>> ();
		}
		if (qName.equalsIgnoreCase("ev")) {
			ev = true;
			
		}
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
			if ( attributes.getQName(0).equalsIgnoreCase("type")) {
				type = attributes.getValue(0);
				System.out.println("loc type: "+ type);
				
			}
		}
		if (qName.equalsIgnoreCase("geo")) {
			geo = true;
			if ( attributes.getQName(0).equalsIgnoreCase("lon")) {
				 loc_lon = attributes.getValue(0);
				System.out.println("loc lon: "+ loc_lon);
				lon=true;
			}
			if ( attributes.getQName(1).equalsIgnoreCase("lat")) {
				loc_lat = attributes.getValue(1);
				System.out.println("loc lat: " + loc_lat);
				lat=true;
			}
		}
		if (qName.equalsIgnoreCase("tmc")) {
			tmc = true;
		}
		
		if (qName.equalsIgnoreCase("id")) {
			startId = true;
		}
		
		if (qName.equalsIgnoreCase("addr")) {
			loc_addr = true;
		}
		if (qName.equalsIgnoreCase("valid")) {
			ev_valid = true;
			if ( attributes.getQName(0).equalsIgnoreCase("start")) {
				startTm = attributes.getValue(0);
				System.out.println("start time: "+ startTm);
				stime=true;
			}
			if ( attributes.getQName(1).equalsIgnoreCase("end")) {
				endTm = attributes.getValue(1);
				System.out.println("end time: " + endTm);
				etime=true;
			}
		}
		if (qName.equalsIgnoreCase("text")) {
			ev_text = true;
		}
		
		
		
	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		System.out.println("End Element :" + qName);
		//reset the location type when incident element is done.

		if (qName.equalsIgnoreCase("incident")) {
			incident = false;
			ti_list = null;
		}
		if (qName.equalsIgnoreCase("ev")) {
			System.out.println("incident id: " + ti.id);
			System.out.println(" 	-> ecode: " + ti.ecode);
			System.out.println("    -> se: " + ti.se);
			if (ti.get() != null)
				System.out.println("    -> loc: " + ti.get().toString());
			ti_list.add(ti);
			
			ti=null;
			ev=false;
		
			
		}
		if (qName.equalsIgnoreCase("loc")) {
			location = false;
			
			geo=false;
			tmc=false;
		}
		
		

	}

	
	
	public void characters(char ch[], int start, int length) throws SAXException {

		if (ev) {
			ti=new TrafficIncident<Location>("0","0","0");
			ev=false;
		}
		if (id) {
			ti.id = new String(ch, start, length); 
			id = false;
		}
		if (ec) {
			ti.ecode =  new String(ch, start, length);
			ec=false;
		}
		if (se) {
			ti.se = new String(ch, start, length);
			se=false;
		}
		if (location && geo) {
			
			
				if (ti.get() == null)  {
					Geolocation tmp_loc = new Geolocation(loc_lon,loc_lat,"default");
					ti.set(tmp_loc);
				}
				if (lon) {
					//String tmp_lon;
					//tmp_lon = new String(ch, start, length);
					((Geolocation)ti.get()).setLon(loc_lon);
					lon = false;
				}
				if (lat) {
					//String tmp_lat;
				//	tmp_lat = new String(ch, start, length);
					((Geolocation)ti.get()).setLat(loc_lat);
					lat = false;
				}
				if (loc_addr ) {
					addr = new String(ch, start, length);
					((Geolocation)ti.get()).setAddr(addr);
					loc_addr = false;
				}
				
				
				System.out.println(ti.toString());
				
				
		}
		if (ev_valid && stime ) {
			ti.setStartTime(startTm);
			stime = false;
		}
		if (ev_valid && etime) {
			ti.setEndTime(endTm);
			etime = false;
		}
		if (location && tmc) {
			
				if (ti.get() == null) {
					TmcLocation tmc_loc = new TmcLocation("00000",'+',"0");
					ti.set(tmc_loc);
				}
				if (loc_startid) {
					String tmp_startid;
					tmp_startid = new String(ch, start, length);
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setStartId(tmp_startid);
					loc_startid=false;
				}
				if (loc_dir) {
					String tmp_dir;
					tmp_dir = new String(ch, start, length);
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setDir(tmp_dir.charAt(0));
					loc_dir=false;
				}
				if (loc_extent) {
					String tmp_ext;
					tmp_ext = new String(ch, start, length);
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setStartExt(tmp_ext);
					loc_extent=false;
				}
		}
		
			
		
	
		
		}
	
     };

       saxParser.parse("myinput.xml", handler);
      
       
 
     } catch (Exception e) {
       e.printStackTrace();
     }
  
   }

