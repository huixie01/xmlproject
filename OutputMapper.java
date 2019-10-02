package xmRadioLibrary;

import java.util.ArrayList;
import java.util.List;

public class OutputMapper {
	public List<Event> locations;
	public OutputMapper() {
		locations = new ArrayList<Event>();
		
	}
	
	public void convert(List<TrafficIncident<Location>> ti_list) {
		for (int i=0; i<ti_list.size(); i++) {
			TrafficIncident ti = ti_list.get(i);
			
			if (ti.get() instanceof TmcLocation) {
				TmcEvent event= new TmcEvent();
				TmcLocation tmc_loc = (TmcLocation)ti.get();				
				
			    event.tmc = tmc_loc;
			    
			    event.setEvent(ti.getId(), ti.getDesc(), ti.getSeverity(), ti.getEventCode(), ti.getStartTime(), ti.getEndTime());
			    locations.add(event);
			}
				
			if (ti.get() instanceof Geolocation) {
				GeoEvent event = new GeoEvent();
				Geolocation geo_loc = (Geolocation)ti.get();
			
				event.geo = geo_loc;
				event.setEvent(ti.getId(), ti.getDesc(), ti.getSeverity(), ti.getEventCode(), ti.getStartTime(), ti.getEndTime());
				locations.add(event);
			}
			
			
			
		
			
			
		}
	}
	
	
	
}
