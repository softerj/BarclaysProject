package verlinden.jason.airport;

import java.util.ArrayList;
import java.util.List;

public class Luggage {
	
	List<BagInfo> luggage = new ArrayList<BagInfo>();
	
	/**
	 * Getter for luggage.
	 * 
	 * @return A BagInfo list.
	 */
	public List<BagInfo> getLuggage() {
		return luggage;
	}
	
	/**
	 * Add a new BagInfo object.
	 * 
	 * @param bagId
	 * 			The id of the bag to add.
	 * @param startGate
	 * 			The name of the starting gate.
	 * @param flightId
	 * 			The id of the flight the bag is for.
	 */
	public void addBag(String bagId, String startGate, String flightId) {
		luggage.add(new BagInfo(bagId, startGate, flightId));
	}
}
