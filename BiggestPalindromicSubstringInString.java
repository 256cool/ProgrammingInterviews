class BiggestPalindromicSubstringInString
{
	static String getPaliSubString(String str)
	{
		if(str==null || str.length()<=1)
			return str;
		int i=0, j=str.length()-1;
		int st = -1, end = -1;
		while(i<j)
		{
			char charI = str.charAt(i);
			char charJ = str.charAt(j);
			if(charI==charJ)
			{
				if(st==-1 && end ==-1)
				{
					st = i;
					end = j;
				}
			}
			else
			{
				if(i>0 && str.charAt(i-1)==charJ)
				{
					
					st = --i;
					end = j;
				}
				else if(j<str.length()-1 && str.charAt(j+1)==charI)
				{
					st = i;
					end = ++j;
				}
				else
				{
					st = -1;
					end = -1;
				}
			}
			i++;
			j--;
		}
		if(st!=-1 && end!=-1)
			return str.substring(st,end+1);
		else
			return str.substring(0,1);
	}
	
	public static void main(String args[])
	{
		System.out.println(getPaliSubString("ABCADCBA"));
	}
}