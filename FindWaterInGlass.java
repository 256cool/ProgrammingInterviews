class FindWaterInGlass
{
	static double getWaterAmount(int maxRow, int glassNo, int totalWater)
	{
		int prevRow = maxRow-1;
		int totalGlassesTillPrev = prevRow*(prevRow+1)/2;
		double waterTillPrev = totalGlassesTillPrev*(totalGlassesTillPrev+1)/2;
		if(waterTillPrev >= totalWater)
			return 0;
		int glassCapacity = 0;
		/*reserved for level below - array contains water reserved by level i for level (i+1) so that glass j at  (i+1)th 
		level will get total water count from rflb[j-1]*/
		double rflb[] = new double[1]; 
		rflb[0] = (double)totalWater;
		boolean reachdGlass = false;
		for(int row=1; row<=maxRow; row++)
		{
			double tempRflb[] = new double[row+1];
			for(int i=0; i<tempRflb.length; i++)
				tempRflb[i]=0;
			for(int i=1; i<=row; i++)//iterating over all glasses in the row
			{
				glassCapacity++;
				if(row==maxRow && i==glassNo)
				{
					reachdGlass = true;
					break;
				}
				double remaining = rflb[i-1]-glassCapacity;
				tempRflb[i-1]+=remaining/2;//dividing in half to distribute water in two glassses at lower level
				tempRflb[i]+=remaining/2;
			}
			if(reachdGlass)
				break;
			rflb = tempRflb;
		}
		if(rflb[glassNo-1] >= glassCapacity)
			return glassCapacity;
		else
			return rflb[glassNo-1] > 0 ? rflb[glassNo-1] : 0;
	}
	
	public static void main(String args[])
	{
		System.out.println(getWaterAmount(3,2,5));
	}
}