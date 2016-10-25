class NoOfKs1ToN
{
	int getNumber(int num, int k)
	{
		int digitLen = (int)Math.log10(num)+1;
		if(num == 0)
			return 0;
		if(digitLen == 1 && num < k)
			return 0;
		if(digitLen == 1)
			return 1;
		int minNoDigitLen = (int)Math.pow(10,digitLen-1);
		int msb = num/minNoDigitLen;
		int fullNo = msb * minNoDigitLen;
		int retNum = 0;
		retNum = retNum+(msb*getNumSum(digitLen-1));
		retNum+=getNumber(num-fullNo,k);
		if(k < msb)
		{
			retNum+=minNoDigitLen;
		}
		else if(k == msb)
		{
			retNum+=(num-fullNo+1);
		}
		return retNum;
	}
	
	int getNumSum(int digitLen)
	{
		if(digitLen==1)
			return 1;
		int sum = (getNumSum(digitLen-1)*10)+(int)Math.pow(10,digitLen-1);
		return sum;
	}
	
	public static void main(String args[])
	{
		NoOfKs1ToN obj = new NoOfKs1ToN();
		System.out.println(obj.getNumber(520,4));
	}
}