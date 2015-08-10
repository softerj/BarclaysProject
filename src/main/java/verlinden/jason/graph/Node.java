package verlinden.jason.graph;

import java.util.HashMap;
import java.util.Map;


public class Node {

	private String name;
	private Map<String, Edge> connections = new HashMap<String, Edge>();
	
	/**
	 * Getter for name.
	 * 
	 * @return The name of the node.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the node connections.
	 * 
	 * @return The node's connections.
	 */
	public Map<String, Edge> getConnections() {
		return connections;
	}
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param name
	 * 			The name of the node.
	 */
	public Node(String name) {
		this.name = name;
	}
	
	/**
	 * Adds a edge between this node(start) and the end node(end) given the weight.
	 * Will add a reverse edge in the end node as well.
	 * 
	 * @param end
	 * 			The node to create the edge to.
	 * @param weight
	 * 			The weight of the edge.
	 */
	public void addEdge(Node end, int weight) {
		// Add connection from beginning node to end node
		this.connections.put(end.getName(), new Edge(this, end, weight));
		// Make sure we add a connection back from end node to beginning
		// node since this is a bidirectional graph
		end.connections.put(this.getName(), new Edge(end, this, weight));
	}
	
	/**
	 * Finds the edge from this node ending at the end node(end).
	 * 
	 * @param end
	 * 			The terminating node to find an edge to.
	 * @return The edge connecting the 2 nodes.
	 */
	public Edge findEdge(Node end) {
		return this.connections.get(end.getName());
	}
	
	@Override
	public boolean equals(Object obj1) {
		if (this == obj1) {
			return true;
		}
		if (obj1 == null) {
			return false;
		}
		if (getClass() != obj1.getClass()) {
			return false;
		}
		
		Node node1 = (Node)obj1;
		
		if (this.name.toUpperCase().equals(node1.getName().toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
