class LongestIncreasingSubsequence
{
	//Complexity of this method is O(n^2)
	void printNumMoreComplexity(int arr[])
	{
		int increasingSubSeqCounter[] = new int[arr.length];
		for(int i=0; i<increasingSubSeqCounter.length; i++)
			increasingSubSeqCounter[i] = 1;
		for(int i=1; i<arr.length; i++)
		{
			for(int j=0; j<i; j++)
			{
				if(arr[i] > arr[j] && increasingSubSeqCounter[i] < increasingSubSeqCounter[j]+1)
					increasingSubSeqCounter[i] = increasingSubSeqCounter[j]+1;
			}
		}
		int max=increasingSubSeqCounter[0];
		for(int i=1; i<increasingSubSeqCounter.length; i++)
		{
			if(increasingSubSeqCounter[i]>max)
				max=increasingSubSeqCounter[i];
		}
		System.out.println(max);
	}
	
	public static void main(String args[])
	{
		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
		//int arr[] = {3,4,-1,0,6,2,3};
		//int arr[] = {1,2,3,4,5,6,7};
		int arr[] = {5,4,3,2,1};
		obj.printNumMoreComplexity(arr);
	}
}
