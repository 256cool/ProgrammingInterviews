import java.util.*;

class ComputeExpressionPadavali
{
	int getAns(String expression)
	{
		Stack<Integer> number = new Stack<Integer>();
		Stack<Character> operator = new Stack<Character>();
		StringBuilder currNum = new StringBuilder();
		int ans=0;
		char MyOperator='\0';
		for(int i=0; i<expression.length(); i++)
		{
			char c = expression.charAt(i);
			if(c==' ')
				continue;
			if(c=='(' || c=='+' || c=='*' || c=='-' || c=='/')
			{	
				if(currNum.length()>0)
				{	
					number.push(Integer.valueOf(currNum.toString()));
					//System.out.println(currNum);
					currNum = new StringBuilder();
				}
				if(isHigherPrecedence(MyOperator,c))
				{
					while(operator.peek()=='(')
					operator.pop();
					int num2 = number.pop();
					int num1 = number.pop();
					ans = operatorCalc(operator.pop(),num1,num2);
					number.push(ans);
				}
				operator.push(new Character(c));
				MyOperator = c;
			}
			else if(c!=')')
			{
				currNum.append(String.valueOf(c));
			}
			else 
			{
				if(currNum.length()>0)
				{	
					//System.out.println(currNum);
					number.push(Integer.valueOf(currNum.toString()));
					currNum = new StringBuilder();
				}
				while(operator.peek()=='(')
					operator.pop();
				int num2 = number.pop();
				int num1 = number.pop();
				ans = operatorCalc(operator.pop(),num1,num2);
				number.push(ans);
			}
			//System.out.println(i);
		}
		if(currNum.length()>0)
		{	
			//System.out.println(currNum);
			number.push(Integer.valueOf(currNum.toString()));
			currNum = new StringBuilder();
		}
		while(!operator.isEmpty())
		{
			if(operator.peek()=='(')
			{
				operator.pop();
				continue;
			}
			int num2 = number.pop();
			int num1 = number.pop();
			ans = operatorCalc(operator.pop(),num1,num2);
			number.push(ans);
		}
		return ans;
	}
	
	int operatorCalc(char c, int num1, int num2)
	{
		switch(c)
		{
			case '+': return num1+num2;
			case '-': return num1-num2;
			case '*': return num1*num2;
			case '/': return num1/num2;
			default : return 0;
		}
	}
	
	boolean isHigherPrecedence(char operator1, char operator2)
	{
		if(operator1=='\0' || operator1 == '+' || operator1 == '(' || operator1 == '-')
			return false;
		if(operator1 == '*' || operator1 == '/')
		{
			if(operator2=='(')
				return false;
			else
				return true;
		}
		return false;
	}
	
	public static void main(String args[])
	{
		ComputeExpressionPadavali obj = new ComputeExpressionPadavali();
		System.out.println(obj.getAns("10 + 2 * 6"));
		System.out.println(obj.getAns("100 * 2 + 12"));
		System.out.println(obj.getAns("100 * ( 2 + 12 )"));
		System.out.println(obj.getAns("100 * ( 2 + 12 ) / 14"));
		System.out.println(obj.getAns("100 * (( 2 + 12 ) / 14)"));
		System.out.println(obj.getAns("( 2  * ( 3 - ( 4 * 5 ) ) )"));
	}
}