class MakeChange
{
	int getWays(int count, int sum, int a[])
	{
		if(count == 0)
			return 1;
		int i=0;
		int result = 0;
		while(i*a[count] <=sum)
		{
			if(i*a[count] == sum)
				result++;
			else
				result+=getWays(count-1,sum-(i*a[count]),a);
			i++;
		}
		return result;
	}
	
	public static void main(String args[])
	{
		MakeChange obj = new MakeChange();
		int a[] = {1,10,20,50,100};
		System.out.println(obj.getWays(3,100,a));
	}
}