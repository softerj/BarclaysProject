package verlinden.jason.airport;

import java.util.ArrayList;
import java.util.List;

import verlinden.jason.airport.BagInfo;
import verlinden.jason.airport.FlightInfo;

public class DenverAirport {
	
	private ConveyerSystem conveyerSystem = new ConveyerSystem();
	private Departures departures = new Departures();
	private Luggage luggage = new Luggage();
	
	public static final String ARRIVAL_END_GATE = "BaggageClaim";
	
	/**
	 * Add a link between 2 nodes in the conveyer system with a weight of time.
	 * 
	 * @param startGate
	 * 				The name of the first gate.
	 * @param endGate
	 * 				The name of the second gate.
	 * @param time
	 * 				The time or weight of the connection.
	 */
	public void addConveyerSystemConnection(String startGate, String endGate, int time) {
		conveyerSystem.addConnection(startGate, endGate, time);
	}
	
	/**
	 * Add a departing flight's information keyed of the flight id.
	 * 
	 * @param flightId
	 * 				The id of the flight.
	 * @param gate
	 * 				The gate the flight is departing from.
	 * @param destination
	 * 				The city code the flight is going to.
	 * @param departTime
	 * 				The time the flight is departing.
	 */
	public void addDeparture(String flightId, String gate, String destination, String departTime) {
		departures.addDeparture(flightId, gate, destination, departTime);
	}
	
	/**
	 * Add a new piece of luggage.
	 * 
	 * @param bagId
	 * 			The id of the bag.
	 * @param startGate
	 * 			The name of the gate the bag is starting from.
	 * @param flightId
	 * 			The id of the flight this bag belongs to.
	 */
	public void addLuggage(String bagId, String startGate, String flightId) {
		luggage.addBag(bagId, startGate, flightId);
	}
	
	/**
	 * Prints the the shortest routes for each piece of luggage to console.
	 */
	public List<String> getAllLuggageRoutes() {
		List<String> routes = new ArrayList<String>();
		
		for (BagInfo bi : luggage.getLuggage()) {
	    	String path = null;
	    	String start = bi.getEntryPoint();
	    	String end;
	    	
	    	FlightInfo fi = departures.getFlight(bi.getFlightId());
	    	if (fi == null) {
	    		// This is an arrival bag since we didn't find any departure info
	    		// This bag goes to the baggage claim
	    		end = ARRIVAL_END_GATE;
	    	} else {
	    		end = fi.getFlightDepartureGate();
	    	}
	    	
	    	try {
	    		path = conveyerSystem.getRoute(start, end);			    	
		    	if (path == null) {
		    		path = " No path found from " + start + " to " + end + "!";
		    	}
		    	
		    	routes.add(bi.getId() + path);
	    	} catch(IllegalArgumentException iae) {
	    		// This will get hit if the start and end nodes are the same
	    		// or if one/both nodes are null
	    		routes.add(bi.getId() + " " + iae.getMessage());		    		
	    	}
	    }
		
		return routes;
	}
}
