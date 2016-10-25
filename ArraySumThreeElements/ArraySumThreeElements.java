import java.util.*;
class ArraySumThreeElements
{
	void printArrayIndices(int a[], int sum)
	{
		boolean found = false;
		for(int i=0; i<a.length-2; i++)
		{
			int newArrLen = a.length-i-1;
			int newArr[] = new int[newArrLen];
			int k=0;
			for(int j=0; j<newArrLen; j++)
			{
				newArr[k] = a[i+j+1];
				k++;
			}
			int remainSum = sum - a[i];
			List<Integer> list = printArraySumTwo(newArr,remainSum);
			if(list.size()==2)
			{
				list.add(a[i]);
				System.out.println(list);
			}
		}
	}
	
	List<Integer> printArraySumTwo(int a[], int sum)
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<a.length; i++)
		{
			map.put(a[i],i);
		}
		for(int i=0; i<a.length; i++)
		{
			int findVal = sum-a[i];
			if(map.containsKey(findVal) && i!=map.get(findVal))
			{
				list.add(findVal);
				list.add(a[i]);
				break;
			}
		}
		return list;
	}
	
	public static void main(String args[])
	{
		ArraySumThreeElements obj = new ArraySumThreeElements();
		int a[] = {1,3,4,5,6,8,9,12,3};
		obj.printArrayIndices(a,31);
	}
}