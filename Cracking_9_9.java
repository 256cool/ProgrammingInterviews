class Cracking_9_9
{
	void printQWays(int currRow, int colKeeper[], int QueenNum)
	{
		if(currRow >= QueenNum)
			print(colKeeper);
		for(int i=0; i<QueenNum; i++)
		{
			int colNum = i;
			if(isPossible(currRow,colNum,colKeeper))
			{
				colKeeper[currRow] = colNum;
				printQWays(currRow+1,colKeeper,QueenNum);
			}
		}
	}
	
	boolean isPossible(int maxRow, int colNum, int[]colKeeper)
	{
		for(int i=0; i<maxRow; i++)
		{
			if(colKeeper[i] == colNum)
				return false;
			int rowDis = maxRow-i;
			int colDist = Math.abs(colNum-colKeeper[i]);
			if(rowDis == colDist)
				return false;
		}
		return true;
	}
	
	void print(int a[])
	{
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	
	public static void main(String args[])
	{
		Cracking_9_9 obj = new Cracking_9_9();
		int QNum = 2;
		int []colKeeper = new int[QNum];
		obj.printQWays(0,colKeeper,QNum);
	}
}