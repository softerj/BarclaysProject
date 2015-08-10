package verlinden.jason.airport;

import verlinden.jason.graph.WeightedGraph;

public class ConveyerSystem {
	
	private WeightedGraph wg = new WeightedGraph();

	/**
	 * Add a connection between 2 node names.
	 * 
	 * @param beginNodeName
	 * 				The name of the beginning node.
	 * @param endNodeName
	 * 				The name of the ending node.
	 * @param time
	 * 				The time it takes to go between the nodes.
	 */
	public void addConnection(String beginNodeName, String endNodeName, int time) {
		wg.addEdge(beginNodeName, endNodeName, time);
	}
	
	/**
	 * Get the a route beginning with the node with the name beginNodeName and ending
	 * with the node with the name endNodeName.
	 * 
	 * @param beginNodeName
	 * 				The name of the node the route will start from.
	 * @param endNodeName
	 * 				The name of the node the route will end at.
	 * @return The shortest path and weight as a string.
	 */
	public String getRoute(String beginNodeName, String endNodeName) {
		return wg.getShortestPathString(beginNodeName, endNodeName);
	}	
}