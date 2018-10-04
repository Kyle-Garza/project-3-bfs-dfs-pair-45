/* WORD LADDER Vertex.java
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

public class Vertex {
	String word;
	public int index;
	public Vertex parent = null;
	private List<Vertex> adj = new LinkedList<Vertex>();

	/*TODO: whatever stuff we need for Vertex*/
	public Vertex() 
	{
		
	}
	
	public void addEdge(Vertex v) {
		adj.add(v);
	}
}

class Graph {
	List<Vertex> graph;

	Queue<Vertex> q = new LinkedList<Vertex>();
	/*TODO: whatever stuff we need for graph*/
	public Graph()
	{
		
	}
}