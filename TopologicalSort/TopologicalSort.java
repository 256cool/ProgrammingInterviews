//DAG graphs only
import java.util.*;

class TopologicalSort
{
	void PrintToplogialOrder(List<List<Character>> adjList)
	{
		Map<Character,List<Character>> adjMap = new HashMap<Character,List<Character>>();
		for(int i=0; i<adjList.size(); i++)
		{
			char c = adjList.get(i).get(0);
			adjMap.put(c,adjList.get(i));
		}
		Stack<Character> visitedStack = new Stack<Character>();
		Map<Character,Integer> visitedMap = new HashMap<Character,Integer>();
		Stack<Character> iterateStack = new Stack<Character>();
		for(int i=0; i<adjList.size(); i++)
		{	
			if(!visitedMap.containsKey(adjList.get(i).get(0)))
			{
				visitedMap.put(adjList.get(i).get(0),1);
				iterateDFS(visitedMap,visitedStack,adjList.get(i).get(0),adjMap);
			}
		}
		System.out.println("Below is the topological order");
		while(!visitedStack.isEmpty())
		{
			System.out.print(visitedStack.pop()+" ");
		}
	}
	
	void iterateDFS(Map<Character,Integer> visitedMap, Stack<Character> visitedStack, char node, Map<Character,List<Character>> adjMap)
	{
		List<Character> currList = adjMap.get(node);
		for(int i=1; i<currList.size(); i++)
		{
			if(!visitedMap.containsKey(currList.get(i)))
			{
				visitedMap.put(currList.get(i),1);
				iterateDFS(visitedMap,visitedStack,currList.get(i),adjMap);
			}
		}
		visitedStack.push(node);
	}
	
	public static void main(String args[])
	{
		TopologicalSort obj = new TopologicalSort();
		List<Character> l1 = new ArrayList<Character>();
		l1.add('A');
		l1.add('B');
		l1.add('H');
		List<Character> l2 = new ArrayList<Character>();
		l2.add('B');
		l2.add('C');
		l2.add('F');
		List<Character> l3 = new ArrayList<Character>();
		l3.add('C');
		l3.add('F');
		List<Character> l4 = new ArrayList<Character>();
		l4.add('F');
		List<Character> l5 = new ArrayList<Character>();
		l5.add('E');
		l5.add('F');
		List<Character> l6 = new ArrayList<Character>();
		l6.add('D');
		l6.add('B');
		l6.add('E');
		List<Character> l7 = new ArrayList<Character>();
		l7.add('G');
		l7.add('H');
		List<Character> l8 = new ArrayList<Character>();
		l8.add('H');
		List<List<Character>> adjList = new ArrayList<List<Character>>();
		adjList.add(l6);
		adjList.add(l1);
		adjList.add(l2);
		adjList.add(l3);
		adjList.add(l4);
		adjList.add(l5);
		adjList.add(l7);
		adjList.add(l8);
		obj.PrintToplogialOrder(adjList);
	}
}