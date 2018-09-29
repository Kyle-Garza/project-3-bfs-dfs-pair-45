package assignment3;

import java.util.*;
import java.io.*;

public class Node {
	
	boolean check[];
	String word;
	int size;
	
	
	public Node (String word) {
		this.size = word.length();
		this.check = new boolean[size];
		this.word = word;
	}
	
	public Node (Node node) {
		this.size = node.size;
		this.check = node.check;
		this.word = node.word;
	}
	
	public Node chooseNextNode(Node node) {
		Node updated = new Node(node);
		
		
		
		return updated;
	}
	
	public ArrayList<String> dfs (Node st, Node end, ArrayList<String> list){
		
		
		return list;
	}
	
}
