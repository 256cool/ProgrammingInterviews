import java.util.*;

class ExpressionEvaluationAgain
{
	int getAnswer(String expression)
	{
		if(expression==null || expression.length()<=0)
			return 0;
		Stack<Integer> numStack = new Stack<Integer>();
		Stack<Character> operatorStack = new Stack<Character>();
		char prevOperator = '\0';
		StringBuffer numKeeper = new StringBuffer();
		for(int i=0; i<expression.length(); i++)
		{
			char currChar = expression.charAt(i);
			if(currChar >= '0' && currChar <= '9')
				numKeeper.append(currChar);
			else if(currChar == ' ')
			{
				if(numKeeper.length()>0)
				{
					numStack.push(Integer.valueOf(numKeeper.toString()));
					numKeeper = new StringBuffer();
				}
			}
			else 
			{
				if(currChar == ')' || isHigherPrecedence(prevOperator,currChar))
				{
					int b = numStack.pop();
					int a = numStack.pop();
					numStack.push(evaluate(a,b,operatorStack.pop()));
					if(currChar != ')')
						operatorStack.push(currChar);
				}
				else if(currChar != '(')
				{
					operatorStack.push(currChar);
				}
				prevOperator = currChar;
			}
		}
		if(numKeeper.length()>0)
			numStack.push(Integer.valueOf(numKeeper.toString()));
		while(!operatorStack.isEmpty())
		{
			int b = numStack.pop();
			int a = numStack.pop();
			numStack.push(evaluate(a,b,operatorStack.pop()));
		}
		return numStack.peek();
	}
	
	int evaluate(int a, int b, char operator)
	{
		switch(operator)
		{
			case '+' : return a+b;
			case '-' : return a-b;
			case '*' : return a*b;
			case '/' : return a/b;
		}
		return 0;
	}
	
	boolean isHigherPrecedence(char c1, char c2)
	{
		if((c1=='*' || c1=='/' )&& c2!='(')
			return true;
		return false;
	}
	
	public static void main(String args[])
	{
		ExpressionEvaluationAgain obj = new ExpressionEvaluationAgain();
		System.out.println(obj.getAnswer("100 * 2 + 12"));
		System.out.println(obj.getAnswer("100 * ( 2 + 12 )"));
		System.out.println(obj.getAnswer("100 * ( 2 + 12 ) / 14"));
		System.out.println(obj.getAnswer("( 2 * ( 3 - ( 4 * 5 ) ) )"));
	}
}