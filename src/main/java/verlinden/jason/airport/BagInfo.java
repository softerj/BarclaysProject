package verlinden.jason.airport;

public class BagInfo {
	private String id;
	private String entryPoint;
	private String flightId;
	
	/**
	 * Getter for the bag id.
	 * 
	 * @return The bag id.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Getter for the bag starting gate.
	 * 
	 * @return The start gate.
	 */
	public String getEntryPoint() {
		return entryPoint;
	}
	
	/**
	 * Getter for the id of the flight this bag belongs to.
	 * 
	 * @return The flight id.
	 */
	public String getFlightId() {
		return flightId;
	}
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param bagId
	 * 				The id of the bag or luggage.
	 * @param entryPoint
	 * 				The name of the entry point gate.
	 * @param flightId
	 * 				The id of the flight this bag belongs to.
	 */
	public BagInfo(String bagId, String entryPoint, String flightId) {
		this.id = bagId;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}
}
