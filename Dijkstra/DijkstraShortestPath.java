import java.util.*;

class DijkstraShortestPath
{
	void printShortestPaths(List<Vertex> graph, Vertex source)
	{
		HashMap<Character,Vertex> vertexmMap = new HashMap<Character,Vertex>();
		MinHeap<Vertex> vertexHeap = new MinHeap<Vertex>(graph.size()); 
		HashMap<Vertex,Vertex> parentMap = new HashMap<Vertex,Vertex>();
		
		for(int i=0; i<graph.size(); i++)
		{
			vertexmMap.put(graph.get(i).label,graph.get(i));
			if(graph.get(i) != source)
				vertexHeap.add(graph.get(i));
		}
		//initiate source and ADTs 
		source.distFromSource = 0;
		parentMap.put(source,null);
		Vertex curr = source;
		//Initiate distance map used in calculation
		HashMap<Vertex,Integer> currDistanceMap = new HashMap<Vertex,Integer>();
		do
		{
			for(int i=0; i<curr.neighbors.size(); i++)
			{
				Vertex currNeighbor = curr.neighbors.get(i).v;
				int edgeWeight = curr.neighbors.get(i).weight;
				int newDistanceFromSource = edgeWeight;
				if(parentMap.get(curr) != null)
				{
					newDistanceFromSource += curr.distFromSource; 
				}
				//heap contains method is O(n) 
				if(vertexHeap.contains(currNeighbor) && newDistanceFromSource < currNeighbor.distFromSource)
				{
					parentMap.put(currNeighbor,curr);
					currNeighbor.distFromSource = newDistanceFromSource;
					currDistanceMap.put(currNeighbor,newDistanceFromSource);
					vertexHeap.decreaseKey(currNeighbor);
				}
			}
			curr = vertexHeap.extract_min();
			currDistanceMap.remove(curr);
		}while(currDistanceMap.size() > 0);
		System.out.println("Shortest paths are");
		for (Vertex key : parentMap.keySet()) 
		{
			if(parentMap.get(key) != null)
				System.out.println(key.label+"-->"+parentMap.get(key).label+" Distance from Source is "+key.distFromSource);
			else
				System.out.println(key.label+"--> Null Distance from Source is "+key.distFromSource);
		}
	}
	
	public static void main(String args[])
	{
		DijkstraShortestPath obj = new DijkstraShortestPath();
		Vertex a = new Vertex('a');
		Vertex b = new Vertex('b');
		Vertex c = new Vertex('c');
		Vertex d = new Vertex('d');
		Vertex e = new Vertex('e');
		Vertex f = new Vertex('f');
		List<Vertex> graph = new ArrayList<Vertex>();
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.add(e);
		graph.add(f);
		a.neighbors.add(new Edge(5,b));
		a.neighbors.add(new Edge(9,d));
		a.neighbors.add(new Edge(2,e));
		b.neighbors.add(new Edge(2,c));
		b.neighbors.add(new Edge(5,a));
		c.neighbors.add(new Edge(2,b));
		c.neighbors.add(new Edge(3,d));
		d.neighbors.add(new Edge(3,c));
		d.neighbors.add(new Edge(9,a));
		d.neighbors.add(new Edge(2,f));
		e.neighbors.add(new Edge(3,f));
		e.neighbors.add(new Edge(2,a));
		f.neighbors.add(new Edge(2,d));
		f.neighbors.add(new Edge(3,e));
		obj.printShortestPaths(graph,a);
	}
}

class MinHeap<T extends Comparable<T>> 
{
	int capacity;
	int currCount;
	List<T> heapKeeper;
	MinHeap(int capacity)
	{
		this.capacity=capacity;
		heapKeeper = new ArrayList<T>(capacity);
	}
	
	void add(T t)
	{
		if(heapKeeper.size()<capacity)
		{
			heapKeeper.add(t);
			insert(currCount);
			currCount++;
		}
		else
		{
			System.out.println("Heap full");
		}
	}
	
	void decreaseKey(T t)
	{
		int index = -1;
		for(int i=0; i<heapKeeper.size(); i++)
		{
			if(t.compareTo(heapKeeper.get(i))==0)
			{
				index=i;
				break;
			}
		}
		if(index>-1)
		{
			insert(index);
		}
		else
		{
			System.out.println("Specified item doesn't exist in Heap");
		}
	}
	
	void insert(int index)
	{
		if(index==0)
			return;
		int root = index/2;
		if(root!=0 && index%2 == 0)
			root=root-1;
		if(heapKeeper.get(index).compareTo(heapKeeper.get(root))==-1)
		{
			T temp = heapKeeper.get(index);
			heapKeeper.set(index,heapKeeper.get(root));
			heapKeeper.set(root, temp);
			insert(root);
		}	
	}
	
	T extract_min()
	{
		if(currCount > 0)
		{
			T rtrnN = heapKeeper.get(0);
			heapKeeper.set(0,heapKeeper.get(currCount-1));
			heapKeeper.remove(currCount-1);
			currCount--;
			minHeapify(0,currCount);
			return rtrnN;
		}
		return null;
	}
	
	void minHeapify(int index, int capacity)
	{
		int min = index;
		int left = 2*index+1;
		int right = 2*index+2;
		
		if(left<capacity && heapKeeper.get(left).compareTo(heapKeeper.get(min))==-1)
			min = left;
		if(right<capacity && heapKeeper.get(right).compareTo(heapKeeper.get(min))==-1)
			min = right;
		if(min != index)
		{
			T temp = heapKeeper.get(index);
			heapKeeper.set(index, heapKeeper.get(min));
			heapKeeper.set(min, temp);
			minHeapify(min,capacity);
		}
	}
	
	void print()
	{
		for(int i=0; i<heapKeeper.size(); i++)
			System.out.print(heapKeeper.get(i)+" ");
		System.out.println();
	}
	
	boolean contains(T t)
	{
		for(int i=0; i<heapKeeper.size(); i++)
		{
			if(t==heapKeeper.get(i))
				return true;
		}
		return false;
	}
}

class Edge
{
	int weight;
	Vertex v;
	
	Edge(int weight, Vertex v)
	{
		this.weight=weight;
		this.v=v;
	}
}

class Vertex implements Comparable<Vertex>
{
	char label;
	int distFromSource = Integer.MAX_VALUE;
	List<Edge> neighbors = new ArrayList<Edge>();
	
	Vertex(char label)
	{
		this.label=label;
	}
	
	public int compareTo(Vertex v)
	{
		if(this.distFromSource > v.distFromSource)
			return 1;
		if(this.distFromSource < v.distFromSource)
			return -1;
		return 0;
	}
	
	public String toString()
	{
		return String.valueOf(this.label);
	}
}