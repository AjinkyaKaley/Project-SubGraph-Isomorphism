package QuickSI;

import java.util.HashMap;
import java.util.*;

public class QISequence {

	
	/**
	 * This method runs a DFS traversal on query graph starting at the given
	 * root.
	 * it maintains two hashsets for seen vertexes and one for seen edges respectively
	 *  
	 * @param adjMatrix
	 * @param root
	 */
	public void dfs(HashMap<Node,ArrayList<Node>> adjMatrix, Node root){
	
		// stack to store the neighbouring nodes
		Stack<Node> _stack = new Stack<Node>();
		HashSet<String> visited_vertex = new HashSet<String>();
		HashSet<String> seen_edges = new HashSet<String>();
		int T_count=0;	// to print trees
		
		_stack.push(root);
		
		Node prev_node = null;
		
		while(!_stack.isEmpty()){
			
			Node temp = _stack.pop();
			
			char u = prev_node==null ? 0 : temp.parent_vertex.charAt(1); 
			int j = Character.getNumericValue(u)==-1?0:Character.getNumericValue(u);
			
			System.out.println("T" + T_count++ +" " + " " + j + " "+temp.label + " "+temp.vertex);
			
			if(!visited_vertex.contains(temp.vertex)){
		
				// adding edge as seen entry
				if(prev_node!=null){
					String s = prev_node.vertex + temp.vertex;
					char[] t= s.toCharArray();
					Arrays.sort(t);
					String y = new String(t);
					seen_edges.add(new String(t));
				}
				
				visited_vertex.add(temp.vertex);
				
				// getting childrens
				ArrayList<Node> neighbors = adjMatrix.get(temp);
				
				// DEG
				if(neighbors.size()>2){
					System.out.println("Deg : " + neighbors.size());
				}
				
				for(Node children : neighbors){
					
					if(!visited_vertex.contains(children.vertex)){
						children.set(temp.vertex);
						_stack.push(children);
					}
					else{
						// not seen edges
						String _edge = temp.vertex+children.vertex;
						char[] x = _edge.toCharArray();
						
						Arrays.sort(x);
					
						if(!seen_edges.contains(new String(x))){
							System.out.println("Edge: " + children.vertex);
						}
					}
				}
			}
			prev_node = temp;	
		}
	}
	
}
