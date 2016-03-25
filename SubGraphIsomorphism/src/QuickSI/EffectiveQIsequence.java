package QuickSI;

import org.jgrapht.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.plaf.SliderUI;

import org.jgrapht.graph.SimpleWeightedGraph;


public class EffectiveQIsequence {

	HashMap<String, Double> average_inner_vertex = new HashMap<String,Double>(); 


	public EdgeConnection SelectFirstEdge(SimpleWeightedGraph<Node, EdgeConnection> queryGraph){

		Set<EdgeConnection> edges = queryGraph.edgeSet();

		Iterator<EdgeConnection> it = edges.iterator();
		EdgeConnection firstEdge = it.next();

		while(it.hasNext()){
			EdgeConnection e = it.next();

			if(e._weight <= firstEdge._weight){
				if(e._weight == firstEdge._weight){

					int deg1 = queryGraph.degreeOf(e._source) + queryGraph.degreeOf(e._target);
					int deg2 = queryGraph.degreeOf(firstEdge._source) + queryGraph.degreeOf(firstEdge._target);

					if(deg1 > deg2){
						firstEdge = e;
					}
					else{
						if(deg1 == deg2){

						}
					}
				}
				else{
					firstEdge = e;
				}
			}
		}
		return firstEdge;
	}


	/**
	 * 
	 * This method selects the spanning edge for the Spanning tree algo
	 * @param edge_set	: contains edges of query graph
	 * @param queryGraph  
	 * @param choseVertex : vertex set Vt
	 */

	public void SelectSpanningEdge(Set<EdgeConnection> edge_set, 
			SimpleWeightedGraph<Node, EdgeConnection>queryGraph, Set<Node> choseVertex){

		Iterator<EdgeConnection> it_edgeSet = edge_set.iterator();
		Set<EdgeConnection> solution = new HashSet<EdgeConnection>();	// stores the final filtered edges
		int count=0;

		while(it_edgeSet.hasNext()){
			Set<EdgeConnection> P_prime = new HashSet<EdgeConnection>();

			EdgeConnection temp_edge = it_edgeSet.next();
			//
			count++;
			System.out.println();
			System.out.println("-------**************************---------");
			System.out.println();
			System.out.println("This is iteration : " + count );
			System.out.println("Edge Considered : " + temp_edge.toString());
			System.out.print("Vertex Set Vt : ");
			printChoseVertex(choseVertex);
			System.out.println();
			System.out.println("*** Following are the output of three filteting stages ***");
			System.out.println();
			System.out.println("#############################################");
			System.out.println();

			// First Filtering step. Filtering based on weigtes
			FilteringWithWeights(edge_set, temp_edge, P_prime);

			System.out.print("Output of first filtering step : " );
			print(P_prime);
			// second filtering step: Filtering based on Induced Graph metrics
			if(P_prime.size()>1){
				System.out.println("Second filtering step starts from here");
				Set<EdgeConnection> idg_filter = FilteringWithInduceGraph(P_prime, choseVertex, edge_set, queryGraph);
				System.out.print("Output of second filtering step : ");
				print(idg_filter);

				// Third filtering step: Filtering based on Degrees of destination vertex
				if(idg_filter.size()>1){
					System.out.println("Third filtering step starts from here");
					Set<EdgeConnection> deg_filter = FilteringWithDegrees(idg_filter, temp_edge);
					//addselectedEdgesToSolution(deg_filter,solution);
					System.out.print ("Output of third filtering step : ");
					print(deg_filter);
					addselectedEdgesToSolution(solution,deg_filter);
				}
				else{
					System.out.println("idg_filter size is < 2 so output will be same as idg_filter");
					addselectedEdgesToSolution(solution,idg_filter);
					print(idg_filter);
				}
			}
			/*else{
				if(!solution.contains(temp_edge)){
					solution.add(temp_edge);
				}
			}*/

			//System.out.println("************************************************************");
			System.out.print("Output of solution set ");
			print(solution);
		}
	}

	/**
	 * Adding edges to solution set from filtered edgeset in filter step 2 & 3
	 * @param solution
	 * @param filtered
	 */
	private void addselectedEdgesToSolution(Set<EdgeConnection> solution,
			Set<EdgeConnection> filtered) {
		// TODO Auto-generated method stub

		Iterator<EdgeConnection> it = filtered.iterator();

		while(it.hasNext()){
			EdgeConnection e = it.next();

			if(!solution.contains(e)){
				solution.add(e);
			}
		}

	}



	/**
	 * Printing
	 * @param sol
	 */
	public void print(Set<EdgeConnection> sol){
		Iterator<EdgeConnection> it = sol.iterator();
		while(it.hasNext())
			System.out.print(it.next().toString() + " , ");
		System.out.println();
	}


	/**
	 * Edges (E-Prime) that has weights less then temp_edge
	 * @param edge_set	set of edges in query graph
	 * @param temp_edge	candidate edge
	 * @param P_prime	adding selected edges of p_prime
	 */
	public void FilteringWithWeights(Set<EdgeConnection> edge_set, EdgeConnection temp_edge,
			Set<EdgeConnection> P_prime){

		Iterator<EdgeConnection> temp_it_edgeSet = edge_set.iterator();

		while(temp_it_edgeSet.hasNext()){
			EdgeConnection e = temp_it_edgeSet.next();
			if(e._weight <= temp_edge._weight && !e.equals(temp_edge)){
				P_prime.add(e);
			}
		}
	}


	/**
	 * 3rd step of filtering, based on the degrees of destination vertex
	 * @param idg_filtered_set	set of filtered edges from filtering step 2(Induced Graph metric)
	 * @param temp_edge	candidate edge
	 * @return P_Prime_Deg
	 */
	public Set<EdgeConnection> FilteringWithDegrees(Set<EdgeConnection> idg_filtered_set,
			EdgeConnection temp_edge){

		Set<EdgeConnection> P_Prime_Deg = new HashSet<EdgeConnection>();
		Iterator<EdgeConnection> it_P_Deg = idg_filtered_set.iterator();

		while(it_P_Deg.hasNext()){
			EdgeConnection e_deg = it_P_Deg.next();

			Iterator<EdgeConnection> it_prime_deg = idg_filtered_set.iterator();

			while(it_prime_deg.hasNext()){
				EdgeConnection e_prime = it_prime_deg.next();
				if(e_deg._weight <= e_prime._weight){
					P_Prime_Deg.add(e_deg);
				}
			}
		}
		return P_Prime_Deg;
	}

	/**
	 * Filtering step 2: based on Induced Graph metrics
	 * @param P_prime	set of selected edges from filtering step 1
	 * @param choseVertex	: Vertex set Vt
	 * @param edge_set	: Edge set of query graph
	 * @param queryGraph	: graph
	 * @return idg_filtered_set
	 */
	public Set<EdgeConnection> FilteringWithInduceGraph(Set<EdgeConnection> P_prime, 
			Set<Node> choseVertex, Set<EdgeConnection> edge_set, 
			SimpleWeightedGraph<Node, EdgeConnection> queryGraph){

		Iterator<EdgeConnection> it_p_prime = P_prime.iterator();
		// set containing edges that are filtered after calculating InducedGraph values

		Set<EdgeConnection> idg_filtered_set = new HashSet<EdgeConnection>();

		while(it_p_prime.hasNext()){


			EdgeConnection candidate_edge = it_p_prime.next();
			System.out.println("Candidate Edge : "+candidate_edge._source.vertex + " " + candidate_edge._target.vertex);
			if(choseVertex.contains(candidate_edge._target)){
				continue;
			}
			System.out.print("Result of Union of Vt and candidateEdge : ");
			choseVertex.add(candidate_edge._target);
			printChoseVertex(choseVertex);

			int idg_e_v = inducedGraphCount(queryGraph.edgeSet(), choseVertex);

			System.out.println("Induced Graph Count : "+idg_e_v);

			choseVertex.remove(candidate_edge._target);

			Iterator<EdgeConnection> it_temp_P_prime = P_prime.iterator();

			boolean flag = true;

			while(it_temp_P_prime.hasNext()){
				EdgeConnection e_prime = it_temp_P_prime.next();

				if(!e_prime.equals(candidate_edge)){

					if(choseVertex.contains(e_prime._target)){
						continue;
					}
					System.out.println("e_prime edge : " + e_prime.toString());
					//System.out.print("Before Union of Vt and e_prime " );
					//printChoseVertex(choseVertex);
					//System.out.println();

					choseVertex.add(e_prime._target);
					printChoseVertex(choseVertex);
					int idg_e_prime = inducedGraphCount(queryGraph.edgeSet(), choseVertex);
					System.out.println( "Induced Graph Count : " + idg_e_prime);
					choseVertex.remove(e_prime._target);

					if(idg_e_v < idg_e_prime && !idg_filtered_set.contains(candidate_edge)){
						flag = false;
						System.out.println("Breaks : " + e_prime._source.vertex + " "+ e_prime._target.vertex);
						break;
					}
				}
			}

			if(flag){
				idg_filtered_set.add(candidate_edge);
			}
			System.out.println("-----------------------------------------------");
		}
		return idg_filtered_set;
	}

	private void printChoseVertex(Set<Node> choseVertex) {
		// TODO Auto-generated method stub
		Iterator<Node> it = choseVertex.iterator();
		while(it.hasNext()){
			System.out.print(it.next().vertex + " ");
		}
	}



	/**
	 * This method counts the number of edges in Induced graph formed by vertexes in Vt
	 * @param edgeSet
	 * @param Vt
	 * @return count : number of edges
	 */
	public int inducedGraphCount(Set<EdgeConnection> edgeSet, Set<Node> Vt){

		int count = 0;
		ArrayList<Node> aux_Vt = new ArrayList<Node>(Vt);

		for(int i=0;i<aux_Vt.size();i++){
			Node t1 = aux_Vt.get(i);

			for(int j=i+1;j<aux_Vt.size();j++){

				Node one , two;
				Node t2 = aux_Vt.get(j);

				if(t1.vertex.compareTo(t2.vertex)<0){
					one = t1;
					two = t2;
				}
				else{
					one = t2;
					two = t1;
				}

				EdgeConnection e = new EdgeConnection(one,two);

				if(edgeSet.contains(e)){
					count++;
				}
			}
		}
		return count;
	}

	
	/**
	 * Calculating inner support for vertex
	 * @param vertex_avg
	 * @param graphDatabase
	 */
	public void calculate_average_inner_support_vertex(HashMap<String,Double> vertex_avg, 
			ArrayList<Graph<Node,EdgeConnection>> graphDatabase){

		String Label;
		Set<String> s = vertex_avg.keySet();
		Iterator<String> it = s.iterator();

		while(it.hasNext()){

			Label = (String) it.next();
			int count = 0;
			int total_vertex_count=0;

			for(Graph<Node,EdgeConnection> g : graphDatabase){
				Set<Node> v_set = g.vertexSet();
				int count_in_this_graph = 0;

				Iterator<Node> its = v_set.iterator();

				while(its.hasNext()){

					Node temp = its.next();

					if(Label.equals(temp.label)){
						count_in_this_graph++;
					}
				}

				if(count_in_this_graph!=0){
					total_vertex_count+=count_in_this_graph;
					count++;
				}
			}

			double avg = total_vertex_count/count;
			vertex_avg.put(Label,avg);
		}
	}


	public HashMap<String,Double> getMapLables(SimpleWeightedGraph<Node, EdgeConnection> graph){
		HashMap<String,Double> result = new HashMap<String,Double>();
		Set<Node> vertices = graph.vertexSet();
		Iterator<Node> it = vertices.iterator();

		while(it.hasNext()){
			Node n = (Node) it.next();

			if(!result.containsKey(n.label)){
				result.put(n.label, 0.0);
			}
		}
		return result;
	}


	
	public static SimpleWeightedGraph<Node,EdgeConnection> createQueryGraph(){

		Node n1 = new Node("V1", "N", null);
		Node n2 = new Node("V2", "C", null);
		Node n3 = new Node("V3", "C", null);
		Node n4 = new Node("V4", "C", null);
		Node n5 = new Node("V5", "C", null);
		Node n6 = new Node("V6", "C", null);
		Node n7 = new Node("V7", "C", null);
		Node n8 = new Node("V8", "C", null);

		SimpleWeightedGraph<Node, EdgeConnection> graph = new SimpleWeightedGraph<Node, EdgeConnection>
		(EdgeConnection.class);

		graph.addVertex(n1);
		graph.addVertex(n2);
		graph.addVertex(n3);
		graph.addVertex(n4);
		graph.addVertex(n5);
		graph.addVertex(n6);
		graph.addVertex(n7);
		graph.addVertex(n8);

		EdgeConnection edge12 = new EdgeConnection(n1,n2,1.4);

		EdgeConnection edge23 = new EdgeConnection(n2,n3,5.1);
		EdgeConnection edge28 = new EdgeConnection(n2,n8,5.1);
		EdgeConnection edge34 = new EdgeConnection(n3,n4,5.1);
		EdgeConnection edge45 = new EdgeConnection(n4,n5,5.1);
		EdgeConnection edge56 = new EdgeConnection(n5,n6,5.1);
		EdgeConnection edge67 = new EdgeConnection(n6,n7,5.1);
		EdgeConnection edge72 = new EdgeConnection(n7,n2,5.1);

		graph.addEdge(n1, n2,edge12);
		graph.addEdge(n2, n3,edge23);
		graph.addEdge(n2, n7,edge28);
		graph.addEdge(n3, n4,edge34);
		graph.addEdge(n4, n5,edge45);
		graph.addEdge(n5, n6,edge56);
		graph.addEdge(n6, n7,edge67);
		graph.addEdge(n7, n2,edge72);

		return graph;
	}

	public static void main(String[] args) {
		EffectiveQIsequence q = new EffectiveQIsequence();
		GraphTest2 Graph = new GraphTest2();
		SimpleWeightedGraph<Node, EdgeConnection> queryGraph = Graph.createGraph();
		EdgeConnection e = q.SelectFirstEdge(queryGraph);
		Set<EdgeConnection> edgeset_temp = new HashSet<EdgeConnection>(queryGraph.edgeSet());
		edgeset_temp.remove(e);
		Set<Node> choseVertex = new HashSet<Node>();
		//EdgeConnection temp13 = new EdgeConnection(new Node("V1","A",null), new Node("V3","C",null));
		//edgeset_temp.remove(temp13);

		choseVertex.add(e._source);
		choseVertex.add(e._target);
		//choseVertex.add(temp13._target);

		q.SelectSpanningEdge(edgeset_temp, queryGraph, choseVertex);

	

	}

}
