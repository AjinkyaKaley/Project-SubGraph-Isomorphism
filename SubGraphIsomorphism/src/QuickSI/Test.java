package QuickSI;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {


	public static void main(String[] args) {
		
		HashMap<Node, ArrayList<Node>> Graph = new HashMap<Node, ArrayList<Node>>();
		
		Node n1 = new Node("V1", "A", null);
		Node n2 = new Node("V2", "B", null);
		Node n3 = new Node("V3", "C", null);
		Node n4 = new Node("V4", "D", null);
		Node n5 = new Node("V5", "E", null);
		Node n6 = new Node("V6", "F", null);
		Node n7 = new Node("V7", "G", null);
		Node n8 = new Node("V8", "H", null);
		Node n9 = new Node("V9", "I", null);
		
		ArrayList<Node> temp = new ArrayList<Node>();
		temp.add(n4);
		temp.add(n2);
		Graph.put(n1,temp);
		
		temp = new ArrayList<Node>();
		temp.add(n1);
		temp.add(n3);
		temp.add(n5);
		Graph.put(n2, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n2);
		temp.add(n6);
		Graph.put(n3, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n1);
		temp.add(n5);
		Graph.put(n4, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n4);
		temp.add(n6);
		temp.add(n2);
		temp.add(n8);
		Graph.put(n5, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n5);
		temp.add(n3);
		temp.add(n9);
		Graph.put(n6, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n4);
		temp.add(n8);
		Graph.put(n7, temp);
		
		temp = new ArrayList<Node>();
		temp.add(n5);
		temp.add(n7);
		temp.add(n9);
		Graph.put(n8,temp);
		
		temp = new ArrayList<Node>();
		temp.add(n6);
		temp.add(n8);
		Graph.put(n9, temp);
		
		QISequence qi = new QISequence();
		qi.dfs(Graph, n1);
	}
}
