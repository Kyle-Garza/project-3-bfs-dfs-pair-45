/* WORD LADDER Node.java
 * EE422C Project 3 submission by
 * <Jessica Heerboth>
 * <jrh5728>
 * <16355>
 * <Kyle Garza>
 * <kcg568>
 * <16355>
 * Slip days used: <0>
 * Git URL: https://github.com/EE422C-Fall-2018/project-3-bfs-dfs-pair-45.git
 * Fall 2018
 */


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
