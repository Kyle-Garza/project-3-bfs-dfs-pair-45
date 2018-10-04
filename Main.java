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

		// TODO some code

		//local copy so DFS and BFS can have there own visited set
		Set<String> visited = new HashSet<String>();
		Queue<String> path = new LinkedList<String>();
		boolean foundLadder = DFS(start, end, visited, path);
		//if no path return ladder with just start and end
		if (!foundLadder)
		{
			ArrayList<String> noLadder = new ArrayList<String>();
			noLadder.add(start);
			noLadder.add(end);
			return noLadder;
		}
		//convert LinkedList path into arrayList, then return it
		ArrayList<String> yesLadder = new ArrayList<String>(path);
		return yesLadder; 
	}
	
	//helper function to do the recursion
	private static boolean DFS(String start, String end, Set<String> visited, Queue<String> path)
	{
		//if start is not in VISITED SET
			//start to VISITED
			//add start to PATH
				//for every neighbor of start
					//if neighbor is not in VISITED SET
						//recursive call boolean found = DFS(params)
						//if (FOUND) return true
				//out of for loop
				//remove start from PATH
				//return false;
		if (!visited.contains(start))
		{
			visited.add(start);
			path.add(start);
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
		return false;
	}
	
	//helper function that returns array of Neighbors to start
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
				if (dict.contains(word) && !visited.contains(word))
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
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		Iterator<String> it = ladder.iterator();
		while(it.hasNext()) 
		{
			System.out.println(it.next());
		}
	}
	// TODO
	// Other private static methods here


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
