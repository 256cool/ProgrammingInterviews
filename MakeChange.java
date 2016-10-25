class MakeChange
{
	static int getWays(int amount)
	{
		if(amount < 0)
			return 0;
		if(amount==1 || amount==0)
			return 1;
		int ways = 1 + getWays(amount-5) + getWays(amount-10) + getWays(amount-25);
		return ways;
	}
	
	public static void main(String args[])
	{
		System.out.println(getWays(3));
		System.out.println(getWays(5));
		System.out.println(getWays(25));
	}
}