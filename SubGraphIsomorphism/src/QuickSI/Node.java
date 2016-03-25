package QuickSI;

public class Node {

	String vertex;
	String parent_vertex;
	String label;
	
	public Node(String vertex, String label, String parent_vertex){
		this.vertex = vertex;
		this.parent_vertex = parent_vertex;
		this.label = label;
	}
	
	public void set(String parent_vertex){
		this.parent_vertex = parent_vertex;
	}
}
