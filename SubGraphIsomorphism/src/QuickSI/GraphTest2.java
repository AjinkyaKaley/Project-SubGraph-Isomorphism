package QuickSI;

import org.jgrapht.*;

import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphTest2 {
	
	SimpleWeightedGraph<Node, EdgeConnection> graph;
	
	public GraphTest2(){
		this.graph = new SimpleWeightedGraph<Node, EdgeConnection>
		(EdgeConnection.class);
	}
	
	public SimpleWeightedGraph<Node, EdgeConnection> createGraph(){

		
		Node n1 = new Node("V1", "A", null);
		Node n2 = new Node("V2", "B", null);
		Node n3 = new Node("V3", "C", null);
		Node n4 = new Node("V4", "D", null);
		Node n5 = new Node("V5", "E", null);
		Node n6 = new Node("V6", "F", null);

		

		graph.addVertex(n1);
		graph.addVertex(n2);
		graph.addVertex(n3);
		graph.addVertex(n4);
		graph.addVertex(n5);
		graph.addVertex(n6);

		EdgeConnection edge12 = new EdgeConnection(n1,n2,0.1);
		EdgeConnection edge23 = new EdgeConnection(n2,n3,2.5);
		EdgeConnection edge13 = new EdgeConnection(n1,n3,0.5);
		EdgeConnection edge34 = new EdgeConnection(n3,n4,0.8);
		EdgeConnection edge35 = new EdgeConnection(n3,n5,3);
		EdgeConnection edge56 = new EdgeConnection(n5,n6,1);
		EdgeConnection edge15 = new EdgeConnection(n1,n5,2);
		EdgeConnection edge26 = new EdgeConnection(n2,n6,4);

		graph.addEdge(n1, n2,edge12);
		graph.addEdge(n2, n3,edge23);
		graph.addEdge(n1, n3,edge13);
		graph.addEdge(n3, n4,edge34);
		graph.addEdge(n3, n5,edge35);
		graph.addEdge(n5, n6,edge56);
		graph.addEdge(n1, n5,edge15);
		graph.addEdge(n2, n6,edge26);

		return graph;
	
	}
}
