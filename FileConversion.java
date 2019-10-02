package xmRadioLibrary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileConversion {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
	ArrayList<TrafficIncident<Location>>	ti_list = new ArrayList<TrafficIncident<Location>> ();
	
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
	boolean tb = true;
	String loc_lon;
	String loc_lat;
	String startTm;
	String endTm;
	Geolocation geoLoc;
	TmcLocation tmcLoc;
	TrafficIncident<Location> ti=null;
	String addr;
	String loc_id;
	String offset;
	String extent;
	String type="";
	String dir;
	String tb_id;
	
			
	

	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

		System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("incident")) {
			incident = true;
			if (qName.equalsIgnoreCase("ti")) {
				if ( attributes.getQName(0).equalsIgnoreCase("table")) {
					tb = true;
					tb_id = attributes.getValue(0);
					System.out.println("table id: "+ tb_id);
				}
			}
			
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
		if (qName.equalsIgnoreCase("start")) {
			tmc = true;
			if ( attributes.getQName(0).equalsIgnoreCase("id")) {
				loc_id = attributes.getValue(0);
				System.out.println("start id: "+ loc_id);
				loc_startid=true;
			}
			if ( attributes.getQName(1).equalsIgnoreCase("dir")) {
				dir = attributes.getValue(1);
				System.out.println("loc dir: " + dir);
				loc_dir=true;
			}
			if ( attributes.getQName(2).equalsIgnoreCase("offset")) {
				offset = attributes.getValue(2);
				System.out.println("loc offset: " + offset);
				loc_offset=true;
			}
			if ( attributes.getQName(3).equalsIgnoreCase("extent")) {
				extent = attributes.getValue(3);
				System.out.println("loc extent: " + extent);
				loc_extent=true;
			}
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
			geo=false;
			tmc=false;
		
			
		}
		if (qName.equalsIgnoreCase("loc")) {
			location = false;
			
			
			
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
		if (ev_text) {
			String tmp_text = new String(ch, start, length);
			ti.setDesc(tmp_text);
			System.out.println("    desc: " + tmp_text);
			ev_text = false;
		}
			
		if (location && tmc) {
			
				if (ti.get() == null) {
					TmcLocation tmc_loc = new TmcLocation("00000",'+',"0");
					ti.set(tmc_loc);
				}
				if (loc_startid) {
					
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setStartId(loc_id);
					loc_startid=false;
				}
				if (loc_dir) {
					
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setDir(dir.charAt(0));
					loc_dir=false;
				}
				if (loc_extent) {
					
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setStartExt(extent);
					loc_extent=false;
				}
				if (loc_offset) {
					if (ti.get() instanceof TmcLocation)
						((TmcLocation)ti.get()).setOffset(offset);
					loc_offset=false;
				}
		}
		
			
		
	
		
		}
	
     };

      saxParser.parse("myinput.xml", handler);
      
      System.out.println(ti_list);
     
      OutputMapper outputMap = new OutputMapper();
      ObjectMapper mapper = new ObjectMapper();
	   try {

          // Java objects to JSON file
		   File output = new File("output.txt");
		   outputMap.convert(ti_list);

		   // Java objects to JSON string - pretty-print
		  String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputMap);

          System.out.println(jsonInString);
		   

      } catch (IOException e) {
          e.printStackTrace();
      }
       
 
     } catch (Exception e) {
       e.printStackTrace();
     }
  
   }
   
}