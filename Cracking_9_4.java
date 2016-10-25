import java.util.*;

class Cracking_9_4
{
	static void getPowerSet(int a[], List<String> l, int st, String prefix)
	{
		if(st==0)
			l.add("");
		for(int i=st; i<a.length; i++)
		{
			String s = "";
			if(prefix.length()==0)
				s = s+a[i];
			else
				s = prefix+"-"+a[i];
			l.add(s);
			getPowerSet(a,l,i+1,s);
		}
	}
	
	public static void main(String args[])
	{
		int a[] = {1,2,3,4};
		List<String> l = new ArrayList<String>();
		getPowerSet(a,l,0,"");
		System.out.println(l);
	}
}