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
			Event event = new Event();
			event._id = ti.getId();
			
			event.description = ti.getDesc();
			event.type=ti.getClass().getName();
		
			if (ti.get() instanceof TmcLocation) {
				TmcLocation tmc_loc = (TmcLocation)ti.get();
			    event.tmc = tmc_loc;
			}
				
			if (ti.get() instanceof Geolocation) {
				Geolocation geo_loc = (Geolocation)ti.get();
				event.geo = geo_loc;
			}
			locations.add(event);
		}
	}
	
	public List<Event> getEvents() {
		return locations;
	}
	
}
