import java.util.*;

class Cracking_9_6
{
	static void getCombos(List<String> lst, int l, int r, int idx, char c[])
	{
		if(l==0 && r==0)
			lst.add(new String(c));
		if(l > 0)
		{
			c[idx] = '(';
			getCombos(lst,l-1,r,idx+1,c);
		}
		if(r>l)
		{
			c[idx] = ')';
			getCombos(lst,l,r-1,idx+1,c);
		}
	}
	
	public static void main(String args[])
	{
		List<String> lst = new ArrayList<String>();
		char c[] = new char[6];
		getCombos(lst,3,3,0,c);
		System.out.println(lst);
	}
}