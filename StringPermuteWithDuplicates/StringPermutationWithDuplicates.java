import java.util.*;

class StringPermutationWithDuplicates
{
	void permuteString(String s)
	{
		TreeMap<Character,Integer> t = new TreeMap<Character,Integer>();
		for(int i=0; i<s.length(); i++)
		{
			if(t.containsKey(s.charAt(i)))
			{
				int newKey = t.get(s.charAt(i))+1;
				t.put(s.charAt(i),newKey);
			}
			else
				t.put(s.charAt(i),1);
		}
		char charArr[] = new char[t.size()];
		int countArr[] = new int[t.size()];
		Set set = t.entrySet();
		Iterator itr = set.iterator();
		int i=0;
		while(itr.hasNext()) 
		{
			Map.Entry me = (Map.Entry)itr.next();
			charArr[i] = (char)me.getKey();
			countArr[i] = (int)me.getValue();
			i++;
		}
		char opArr[] = new char[s.length()];
		permuteStringUtil(charArr,countArr,opArr,0,s.length());
	}
	
	void permuteStringUtil(char charArr[], int countArr[],char opArr[], int level, int totalLen)
	{
		if(level == totalLen)
		{
			for(int i=0; i<opArr.length; i++)
			{
				System.out.print(opArr[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0; i<charArr.length; i++)
		{
			if(countArr[i]>0)
			{	
				opArr[level]=charArr[i];
				countArr[i]--;
				permuteStringUtil(charArr,countArr,opArr,level+1,totalLen);
				countArr[i]++;
			}
		}
	}
	
	public static void main(String args[])
	{
		StringPermutationWithDuplicates obj = new StringPermutationWithDuplicates();
		obj.permuteString("ABC");
	}
}