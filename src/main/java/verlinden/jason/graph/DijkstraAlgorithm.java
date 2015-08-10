package verlinden.jason.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
	
	private Set<Node> completeNodes;
	private Set<Node> incompleteNodes;
	private Map<Node, Node> prevNodes;
	private Map<Node, Integer> distance;
	
	/**
	 * Executes the Dijkstra algorithm finding the shortest path
	 * starting at the beginning node beginNode and ending at the node
	 * endNode. Constructs lists of paths from beginNodes to other nodes.
	 * 
	 * @param beginNode
	 * 				The node to start the path from.
	 * @param endNode
	 * 				The node we are trying to end the path at.
	 * @return A LinkedList of nodes representing the path from beginNode to endNode.
	 * 		   Will return null if no path is found.
	 */
	public LinkedList<Node> run(Node beginNode, Node endNode) {
		// Do some data validation
		if (beginNode == null || endNode == null) {
			throw new IllegalArgumentException("Start and end nodes cannot be null!");
		}
		
		if (beginNode.equals(endNode)) {
			throw new IllegalArgumentException("Start and end nodes cannot be the same!");
		}
		
		// Initialize our collections
		completeNodes = new HashSet<Node>();
		incompleteNodes = new HashSet<Node>();
		prevNodes = new HashMap<Node, Node>();
		distance = new HashMap<Node, Integer>();
		
		// Initialize the incomplete nodes with the beginning nodes since
		// that is where we will start evaluating from
		distance.put(beginNode, 0);
		incompleteNodes.add(beginNode);
		
		// Start visiting incomplete nodes
		while (incompleteNodes.size() > 0) {
			// Find the smallest weight amongst the neighbors
			Node closestNode = findMinimumNode(incompleteNodes);
			completeNodes.add(closestNode);
			incompleteNodes.remove(closestNode);
			findShortestDistances(closestNode);
		}
		
		return getPath(endNode);
	}
	
	/**
	 * Get a List of nodes that represents the shortest path given
	 * the beginNode passed into run() and endNode.
	 * 
	 * @param endNode
	 * 				The node we are ending at.
	 * @return	A list of nodes representing the shortest path.
	 */
	private LinkedList<Node> getPath(Node endNode) {
	    LinkedList<Node> path = new LinkedList<Node>();
	    Node prevNode = endNode;
	    
	    // check if a path exists
	    if (prevNodes.get(prevNode) == null) {
	      return null;
	    }
	    
	    path.add(prevNode);
	    while (prevNodes.get(prevNode) != null) {
	      prevNode = prevNodes.get(prevNode);
	      path.add(prevNode);
	    }	    
	    Collections.reverse(path);
	    
	    return path;
	  }
	
	/**
	 * Give a node, find the closest neighbor. Will add the closest neighbor to
	 * to the list of nodes to be evaluated. 
	 * 
	 * @param node
	 * 			The node to get the closest neighbor relative to.
	 */
	private void findShortestDistances(Node node) {
		List<Node> neighbors = getNeighbors(node);
		
		for (Node neighbor : neighbors) {
			int cumulativeDistance = getDistance(node) + getDistance(node, neighbor);
			
			if (getDistance(neighbor) > cumulativeDistance) {
				distance.put(neighbor, cumulativeDistance);
				prevNodes.put(neighbor, node);
				incompleteNodes.add(neighbor);
			}
		}
	}
	
	/**
	 * Get the distance or weight between 2 nodes.
	 * 
	 * @param beginNode
	 * 				The first node.
	 * @param endNode
	 * 				The second node.
	 * @return The weight or distance between the 2 nodes.
	 */
	private int getDistance(Node beginNode, Node endNode) {
		return beginNode.getConnections().get(endNode.getName()).getWeight();
	}
	
	/**
	 * Given a node, get all of it's  neighbors. That is, those nodes that
	 * the given node has connections to.
	 * 
	 * @param node
	 * 			The node to get the neighbors for.
	 * @return A List of nodes which are neighbors of the given node.
	 */
	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		Set<String> edgeKeys = node.getConnections().keySet();
		Iterator<String> edgeItr = edgeKeys.iterator();
		
		// Loop through edges and get all the end nodes which are node's neighbors
		while (edgeItr.hasNext()) {
			Edge edge = node.getConnections().get(edgeItr.next());
			Node neighbor = edge.getEnd();
			
			if (!isComplete(neighbor)) {
				neighbors.add(neighbor);
			}
		}
		
		return neighbors;
	}
	
	/**
	 * Find the node with the lowest weight from the list of nodes compared
	 * against the lowest weight we have already found.
	 * 
	 * @param nodes
	 * 			The nodes to find the lowest weighted node from.
	 * @return	The node with the lowest weight.
	 */
	private Node findMinimumNode(Set<Node> nodes) {
		Node minNode = null;
		int minWeight = 0;
		
		for (Node node : nodes) {
			if (minNode == null) {
				minNode = node;
				minWeight = getDistance(node);
			} else {
				int weight = getDistance(node);
				if (weight < minWeight) {
					minNode = node;
					minWeight = weight;
				}
			}
		}
		
		return minNode;
	}
	
	/**
	 * Has this node already been evaluated completely?
	 * 
	 * @param node
	 * 			The node to check.
	 * @return	True if it has been evaluated completely, False if not.
	 */
	private boolean isComplete(Node node) {
		return completeNodes.contains(node);
	}
	
	/**
	 * Retrieves the shortest weight/distance we have found for a give node.
	 * 
	 * @param node
	 * 			The node to do the lookup on.
	 * @return Return the weight/distance.
	 */
	private int getDistance(Node node) {
		Integer d = distance.get(node);
		
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}
}
