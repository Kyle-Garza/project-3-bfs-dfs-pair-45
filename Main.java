/* WORD LADDER Main.java
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

public class Main {
	static char[] alpha;
	static Set<String> dict;
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dict = makeDictionary();
		alpha = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
	}
	

	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> start = new ArrayList<String>();
		//read first input
		String input1 = keyboard.next();
		if (input1.equals("/quit"))
		{
			//return empty arrayList
			return new ArrayList<String>();
		}
		//get second input if not "/quit"
		String input2 = keyboard.next();
		start.add(input1);
		start.add(input2);
		return start;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {

		//local copy so DFS and BFS can have there own visited set
		Set<String> visited = new HashSet<String>();
		ArrayList<String> path = new ArrayList<String>();
		
		//does all the meat of the work
		boolean foundLadder = DFS(start, end, visited, path);
		
		//if no path return ladder with just start and end
		if (!foundLadder)
		{
			ArrayList<String> noLadder = new ArrayList<String>();
			noLadder.add(start);
			noLadder.add(end);
			System.out.println("no word ladder can be found between " + start + " and " + end);
			return noLadder;
		}
		return path; 
	}
	
	/*DFS: helper function to do the recursion*/
	private static boolean DFS(String start, String end, Set<String> visited, ArrayList<String> path)
	{
		/*Call FIND with start node, value, empty set, empty ArrayList of strings. Returns T/F

		  FIND: Given node, value, set of visited nodes, path; returns true/false
			  If node == null
				  return false
			  Add node to visited nodes set
			  Add node to path <- different from above version
			  If node == value
				  return true
			  else
				  For every neighbor of node not in visited-nodes-set
					  boolean found = FIND(neighbor, value) 
					  if (found) return true;
				  remove node from path <- different from above version
				  return false
		 */
		
		visited.add(start);
		path.add(start);
		
		/*We found the ladder*/
		if (start.equals(end))
		{
			return true;
		}
		else 
		{
			//helper function that returns an arrayList of valid neighbors
			ArrayList<String> neighbors = getNeighbors(start,visited);
			for(String nextWord: neighbors)
			{
				boolean found = DFS(nextWord, end, visited, path);
				if (found)
				{
					return true;
				}
			}
			path.remove(start);
			return false;
		}
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
    	/*
    	  Search for a node by traversing the graph from a starting node.
	      Returns not found if node is not reachable or not in graph.
			
		  Keep track of discovered nodes in a Set S.
		  Each node has a parent node field.
			
		  Add the starting node to a Queue.
		  Set parent of starting node to null.
			
		  while the queue is not empty:
		  	Dequeue the head of the queue.
			IF head == value, return found.
			FOR EACH neighbor of head
				IF neighbor has not been discovered
					mark neighbor discovered (or add to Set S).
					mark neighbor's parent to be head (if parent != null)
					add neighbor to queue.
			}
		  return not found.
		*/
    	
    	//local copy so DFS and BFS can have there own visited set
		Set<String> visited = new HashSet<String>();
		
		//change this to the right thing, just wanted an Queue object
		//to write the skeleton of the loop
		Queue<Vertex> object = new LinkedList<Vertex>();
		while(!object.isEmpty())
		{
			//removes Head and returns head object
			Vertex curWord = object.remove();
			if (curWord.word == end)
			{
				//return true; 
				//TODO: Might need to make a helper BFS function
			}
			visited.add(curWord.word);
			ArrayList<String> neighbors = getNeighbors(curWord.word,visited);
			for(String nextWord: neighbors)
			{
				visited.add(nextWord);
				Vertex curNeighbor = new Vertex(); //edit this with right constructor
				if (curWord.parent != null)
				{
					curNeighbor.parent = curWord;
				}
				object.add(curNeighbor);
			}
		}
		return null; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		Iterator<String> it = ladder.iterator();
		while(it.hasNext()) 
		{
			System.out.println(it.next());
		}
	}
	
	//private static methods here
	
	/*helper function that returns array of Neighbors to start*/
	private static ArrayList<String> getNeighbors(String start, Set<String> visited)
	{
		ArrayList<String> neighbors = new ArrayList<String>();
		//brute force check all combinations and see if in dict and NOT visited
		StringBuilder word = new StringBuilder(start);
		for (int k = 0; k < start.length(); k++)
		{
			for (int i = 0; i < alpha.length; i++)
			{
				word.setCharAt(k, alpha[i]);
				String compareWord = word.toString();
				boolean inDict = dict.contains(compareWord.toUpperCase());
				boolean inVisited = visited.contains(compareWord.toLowerCase());
				if (inDict && !inVisited)
				{
					//if in dict and not visited add to neighbors
					neighbors.add(word.toString());
				}
			}
			//Reinitialize start when switching to next letter
			word = new StringBuilder(start);
		}
		return neighbors;
	}

	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
