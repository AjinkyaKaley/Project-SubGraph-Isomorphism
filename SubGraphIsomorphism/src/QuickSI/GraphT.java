package QuickSI;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphT {

	public static void main(String[] args) {
		
		UndirectedGraph<String, DefaultEdge> Graph = new SimpleGraph<String, DefaultEdge>
														(DefaultEdge.class);
		
		Graph.addVertex("V1");
		Graph.addVertex("V2");
		Graph.addVertex("V3");
		Graph.addVertex("V4");
		Graph.addVertex("V5");
		Graph.addVertex("V6");
		Graph.addVertex("V7");
		
		Graph.addEdge("V1", "V2");
		Graph.addEdge("V2", "V3");
		Graph.addEdge("V3", "V4");
		Graph.addEdge("V4", "V5");
		Graph.addEdge("V5", "V6");
		Graph.addEdge("V6", "V7");
		Graph.addEdge("V7", "V2");
		
		System.out.println(Graph.toString());
	}
}
