package verlinden.jason.airport;

import java.util.HashMap;
import java.util.Map;

public class Departures {
	Map<String, FlightInfo> flights = new HashMap<String, FlightInfo>();
	
	/**
	 * Getter for the list of departing flights.
	 * 
	 * @return The list of departing flights.
	 */
	public Map<String, FlightInfo> getFlights() {
		return flights;
	}
	
	/**
	 * Add a departure flight to the list of departures.
	 * 
	 * @param flightId
	 * 				The id of the departing flight.
	 * @param departureGate
	 * 				The gate the flight is departing from.
	 * @param destinationCity
	 * 				The destination city of the flight.
	 * @param departureTime
	 * 				The flight's departure time.
	 */
	public void addDeparture(String flightId, String departureGate, String destinationCity, String departureTime) {
		flights.put(flightId, new FlightInfo(flightId, departureGate, destinationCity, departureTime));
	}
	
	/**
	 * Get a departure based on its id.
	 * 
	 * @param id
	 * 			The id of the flight to get.
	 * @return The info for the flight.
	 */
	public FlightInfo getFlight(String id) {
		return flights.get(id);
	}
}
