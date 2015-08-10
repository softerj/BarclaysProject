package verlinden.jason.graph;

public class Edge {

	private Node start;
	private Node end;
	private int weight;
	
	/**
	 * Getter for start node.
	 * 
	 * @return Start node.
	 */
	public Node getStart() {
		return start;
	}
	
	/**
	 * Getter for end node.
	 * 
	 * @return End node.
	 */
	public Node getEnd() {
		return end;
	}
	
	/**
	 * Getter for weight.
	 * 
	 * @return Weight.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param start
	 * 			The node the edge starts from.
	 * @param end
	 * 			The node the edge ends at.
	 * @param weight
	 * 			The distance or weight between the 2 nodes.
	 */
	public Edge(Node start, Node end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
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
		
		Edge edge1 = (Edge)obj1;
		
		if (!this.getStart().equals(edge1.getStart())) {
			return false;
		}
		
		if (!this.getEnd().equals(edge1.getEnd())) {
			return false;
		}
		
		if (this.weight != edge1.getWeight()) {
			return false;
		}
		
		return true;
	}	
}
