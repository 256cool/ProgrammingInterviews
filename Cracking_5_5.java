class Cracking_5_5
{
	int getBits(int a, int b)
	{
		int x = a^b;
		int count = 0;
		for(int i=0; i<32; i++)
		{
			int t = 1 << i;
			if((t & x) != 0)
				count++;
		}
		return count;
	}
	
	public static void main(String args[])
	{
		Cracking_5_5 obj = new Cracking_5_5();
		System.out.println(obj.getBits(6,9));
		System.out.println(obj.getBits(8,7));
		System.out.println(obj.getBits(1,3));
		System.out.println(obj.getBits(1,2));
	}
}