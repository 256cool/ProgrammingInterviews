import java.util.*;

public class LRUCache {
    HashMap<Integer,CacheEntry> cacheMap = new HashMap<Integer,CacheEntry>();
    CacheEntry heap[]; 
    int cacheCount;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        heap = new CacheEntry[capacity];
    }
    
    public int get(int key) {
        if(cacheMap.containsKey(key))
         {
            CacheEntry e = cacheMap.get(key);
            accessedKey(e);
            return e.value;
         }
        return -1;
    }
    
    public void set(int key, int value) {
        if(heap == null)
            return;
        if(cacheMap.containsKey(key))
        {
            CacheEntry e = cacheMap.get(key);
            e.value=value;
            accessedKey(e);
        }
        else if(cacheCount<capacity)
        {
            CacheEntry e = new CacheEntry(value);
            cacheMap.put(key,e);
            insertHeap(heap,e);
        }
        else
        {
           extractMin();
		   CacheEntry e = new CacheEntry(value);
           cacheMap.put(key,e);
           insertHeap(heap,e);
        }
    }
	
	void extractMin()
	{
		int lastIndex = cacheCount-1;
		CacheEntry temp = heap[lastIndex];
		heap[0] = temp;
		heap[lastIndex]=null;
		cacheCount--;
		heap[0].arrayIndex=0;
		min_heapify(heap,0,cacheCount);
	}
    
    void accessedKey(CacheEntry e)
    {
        e.useCount++;
		min_heapify(heap,e.arrayIndex,cacheCount);
    }
	
	void min_heapify(CacheEntry heap[], int index, int maxSize)
    {
        int min = index;
        int left = 2*index+1;
        int right = 2*index+2;
        
        if(left<maxSize && heap[min].useCount>heap[left].useCount)
            min=left;
        if(right<maxSize && heap[min].useCount>heap[right].useCount)
            min=right;
        if(min != index)
        {
            CacheEntry temp = heap[index];
            heap[index] = heap[min];
            heap[min]=temp;
			heap[index].arrayIndex=index;
			heap[min].arrayIndex=min;
            min_heapify(heap,min,maxSize);
        }
    }
    
    void insertHeap(CacheEntry heap[], CacheEntry e)
    {
        heap[cacheCount] = e;
        e.arrayIndex = cacheCount;
        insertHeapUtil(heap,cacheCount);
        cacheCount++;
    }
    
    void insertHeapUtil(CacheEntry heap[], int index)
    {
        if(index<=0 || index>=heap.length)
            return;
        int root = index/2;
        if(heap[root].useCount>heap[index].useCount)
        {
            CacheEntry temp = heap[index];
            heap[index] = heap[root];
            heap[root]=temp;
            heap[root].arrayIndex=root;
            heap[index].arrayIndex=index;
            insertHeapUtil(heap,root);
        }
    }
	
	void print()
	{
		for(int i=0; i<heap.length; i++)
		{
			System.out.println(heap[i].value+" "+heap[i].useCount);
		}
	}
	
	public static void main(String args[])
	{
		LRUCache obj = new LRUCache(1);
		/*obj.set(1,-1);
		obj.set(2,-2);
		obj.set(3,-3);
		obj.set(4,-4);
		obj.set(5,-5);
		obj.set(6,-6);
		obj.set(7,-7);
		obj.set(8,-8);
		obj.get(1);
		obj.get(5);
		obj.get(8);
		obj.get(2);
		obj.get(2);
		obj.get(2);
		obj.get(3);
		obj.set(9,-9);*/
		obj.print();
	}
}

class CacheEntry
{
    int value, useCount, arrayIndex;
    
    CacheEntry(int value)
    {
        this.value = value;
        useCount++;
    }
}
