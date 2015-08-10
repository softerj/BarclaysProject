package verlinden.jason.airport;

public class FlightInfo {
	private String id;
	private String gate;
	private String destination;
	private String departTime;
	
	/**
	 * Getter for the flight id.
	 * 
	 * @return The id of the flight.
	 */
	public String getFlightId() {
		return id;
	}
	
	/**
	 * Getter for the flight departure gate.
	 * 
	 * @return The gate of departure.
	 */
	public String getFlightDepartureGate() {
		return gate;		
	}
	
	/**
	 * Getter for the destination city.
	 * 
	 * @return The destination city.
	 */
	public String getDestinationCity() {
		return destination;
	}
	
	/**
	 * Getter for the flight departure time.
	 * 
	 * @return The time of flight departure.
	 */
	public String getDepartureTime() {
		return departTime;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param flightId
	 * 				Id of the flight.
	 * @param flightGate
	 * 				Departure gate.
	 * @param destinationCity
	 * 				Destination city.
	 * @param departureTime
	 * 				Departure time.
	 */
	public FlightInfo(String id, String gate, String destination, String departTime) {
		this.id = id;
		this.gate = gate;
		this.destination = destination;
		this.departTime = departTime;
	}
}
