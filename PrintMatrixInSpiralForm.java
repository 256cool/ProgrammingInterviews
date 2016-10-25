//http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
class PrintMatrixInSpiralForm
{
	static void printSpiralMatrix(int a[][])
	{
		if(a.length == 0 && a[0].length==0)
			return;
		if(a.length == 1)
		{	
			System.out.println("Print first row");
			return;
		}
		if(a[0].length == 1)
		{	
			System.out.println("Print first Col");
			return;
		}
		int levels = (int)Math.ceil((double)a.length/2);
		int rowstart = 0;
		int rowEnd = a.length;
		int colStart = 0;
		int colEnd = a[0].length;
		//int j=0;
		for(int i=0; i<levels; i++)
		{
			//Print first row
			int j= colStart;
			while(j<colEnd)
			{
				System.out.print(a[rowstart][j]+" ");
				j++;
			}
			//print last col
			j = rowstart+1;
			while(j<rowEnd && colStart!=colEnd-1)
			{
				System.out.print(a[j][colEnd-1]+" ");
				j++;
			}
			//print last row
			j=colEnd-2;
			while(j>=colStart && rowstart!=rowEnd-1)
			{
				System.out.print(a[rowEnd-1][j]+" ");
				j--;
			}
			//print first col
			j=rowEnd-2;
			while(j>rowstart)
			{
				System.out.print(a[j][colStart]+" ");
				j--;
			}
			rowstart++;
			rowEnd--;
			colStart++;
			colEnd--;
		}
	}
	
	public static void main(String args[])
	{
		int a[][] = { {1,  2,  3,  4, 5},{6, 7,  8, 9, 10},{11, 12, 13, 14, 15},{16, 17, 18, 19, 20},{21, 22, 23, 24, 25}};
		printSpiralMatrix(a);
	}
}