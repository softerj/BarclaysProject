package verlinden.jason.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WeightedGraph {
	
	private Map<String, Node> nodes = new HashMap<String, Node>();
	
	/**
	 * Getter for nodes.
	 * 
	 * @return The nodes in the graph keyed by their name.
	 */
	public Map<String, Node> getNodes() {
		return nodes;
	}
	
	/**
	 * Add an edge to graph between 2 nodes. Will check if the nodes exist and
	 * add the edge to one or both if they do. If they do not exist, this will first
	 * create the nodes and then add the edge. Adds it bi-directional for both nodes.
	 * 
	 * @param beginNodeName
	 * 				The name of the node to start with.
	 * @param endNodeName
	 * 				The name of the node to end with.
	 * @param weight The weight of the edge.
	 */
	public void addEdge(String beginNodeName, String endNodeName, int weight) {
		Node beginNode = new Node(beginNodeName);
		Node endNode = new Node(endNodeName);
		
		// See if we already have these nodes in our collection. If we do, retrieve
		// them from the collection so we can set an edge, othewerise add them
		if (hasNode(beginNodeName)) {
			beginNode = getNode(beginNodeName);
		} else {
			addNode(beginNode);
		}
		
		if (hasNode(endNodeName)) {
			endNode = getNode(endNodeName);
		} else {
			addNode(endNode);
		}
		
		// Add the edge between the two nodes
		beginNode.addEdge(endNode, weight);
	}
		
	/**
	 * Will find the shortest path and total weight and then convert it to a string in
	 * the form: node1 node2 ... nodeN : totalWeight
	 * 
	 * @param beginNodeName
	 * 				The node to start the path from.
	 * @param endNodeName
	 * 				The node to end the path at.
	 * @return A string representation of the shortest path and total weight.
	 */
	public String getShortestPathString(String beginNodeName, String endNodeName) {
		StringBuffer sb = new StringBuffer();
		int totalWeight = 0;
		
		// Get the shortest path
		LinkedList<Node> path = findShortestPath(beginNodeName, endNodeName);
		
		// Check to see if we found a path
		if (path == null) {
			return null;
		}
		
		// Write the shortest path out along with the total weight of the path
		for (int i = 0; i < path.size(); i++) {
			Node node = path.get(i);
			sb.append(" " + node.getName());
			
			// Get the edge between current node and next node to get the weight
			if (i != path.size() - 1) {
				Edge edge = node.findEdge(path.get(i + 1));
				totalWeight += edge.getWeight();
			}
		}
		
		// Append the total weight to the node list
		sb.append(" : " + totalWeight);
		
		return sb.toString();
	}
	
	/**
	 * Find the shortest path between 2 nodes given the name of the start node
	 * and the name of the end node utilizing Dijkstra's Algorithm.
	 * 
	 * @param beginNodeName
	 * 				The name of the node to begin from.
	 * @param endNodeName
	 * 				The name of the node to end at.
	 * @return The linked list containing the nodes in order of the shortest path.
	 */
	private LinkedList<Node> findShortestPath(String beginNodeName, String endNodeName) {
		Node beginNode = getNode(beginNodeName);
		Node endNode = getNode(endNodeName);
		
		DijkstraAlgorithm da = new DijkstraAlgorithm();
		LinkedList<Node> path = da.run(beginNode, endNode);
		
		return path;
	}

	/**
	 * Add a new node to the graph's node collection keyed by the node's
	 * name in uppercase.
	 * 
	 * @param node The node to add.
	 */
	private void addNode(Node node) {
		nodes.put(node.getName().toUpperCase(), node);
	}	
	
	/**
	 * Checks to see if the graph already has a node.
	 * 
	 * @param name
	 * 			The name of the node to check for.
	 * @return True if the graph has the node and False if it does not.
	 */
	private boolean hasNode(String name) {
		return nodes.containsKey(name.toUpperCase());	
	}
	
	/**
	 * Retrieves a node from the graph.
	 * 
	 * @param name 
	 * 			The name of the node to get.
	 * @return The node.
	 */
	private Node getNode(String name) {
		return nodes.get(name.toUpperCase());
	}
}
