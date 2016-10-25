import java.io.*;
import java.util.*;

class QuoraOntology
{
	public static void main(String args[])
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int topicNo = Integer.parseInt(br.readLine());
			String flatTreeLine = br.readLine();
			TreeOperator obj = new TreeOperator();
			obj.createTreeFromFlatString(flatTreeLine,null);
			//obj.printTree();
			int quesNo = Integer.parseInt(br.readLine());
			for(int i=0; i<quesNo; i++)
			{
				String currLine = br.readLine();
				String str[] = currLine.split(":");
				TreeNode topicTreeNode = obj.treeMap.get(str[0].trim());
				//System.out.println(str[0].trim()+" "+topicTreeNode);
				topicTreeNode.myTrie.add(str[1].trim());
			}
			int queryNo = Integer.parseInt(br.readLine());
			int opArr[] = new int[queryNo];
			QuoraOntology qObj = new QuoraOntology();
			for(int i=0; i<queryNo; i++)
			{
				String currLine = br.readLine();
				int separatioIndex = -1;
				for(int j=0; j<currLine.length(); j++)
				{
					if(currLine.charAt(j)==' ')
					{
						separatioIndex = j;
						break;
					}
				}
				if(separatioIndex > -1)
				{
					String topic = currLine.substring(0,separatioIndex);
					String query = currLine.substring(separatioIndex+1);
					TreeNode topicTreeNode = obj.treeMap.get(topic);
					//System.out.println(topic+" "+topicTreeNode);
					opArr[i] = qObj.getQueryCount(query.trim(),topicTreeNode);
				}
			}
			for(int i=0; i<opArr.length; i++)
				System.out.println(opArr[i]);
			/*TrieOperator trieObj = new TrieOperator();
			trieObj.add("Where is She");
			trieObj.add("Where is he");
			System.out.println(trieObj.presentCount("Where is she"));*/
		}
		catch(Exception e)
		{
			System.out.println(e);
            e.printStackTrace();
		}
	}
	
	int getQueryCount(String qry, TreeNode node)
	{
		int count = 0;
		count += node.myTrie.presentCount(qry);
		for(int i=0; i<node.childrenList.size(); i++)
		{
			count += getQueryCount(qry,node.childrenList.get(i));
		}
		return count;
	}
}

class TrieNode
{
	HashMap<Character,TrieNode> nodeMap = new HashMap<Character,TrieNode>();
	HashMap<Character,Integer> countMap = new HashMap<Character,Integer>();
	boolean isEnd;
}

class TrieOperator
{
	private TrieNode myRoot = new TrieNode();
	void add(String sentence)
	{
		TrieNode root = myRoot;
		for(int i=0; i<sentence.length(); i++)
		{
			char curr = sentence.charAt(i);
			if(curr==' ')
				continue;
			TrieNode temp = null;
			if(!root.nodeMap.containsKey(curr))
			{
				temp = new TrieNode();
				root.nodeMap.put(curr,temp);
				root.countMap.put(curr,1);
			}
			else
			{
				temp = root.nodeMap.get(curr);
				int prevCount = root.countMap.get(curr);
				root.countMap.put(curr,prevCount+1);
			}
			if(i==sentence.length()-1)
				temp.isEnd = true;
			root = temp;
		}
	}
	
	int presentCount(String query)
	{
		int currCount=0;
		TrieNode root = myRoot;
		for(int i=0; i<query.length();i++)
		{
			char currChar = query.charAt(i);
			if(currChar==' ')
				continue;
			if(root.nodeMap.containsKey(currChar))
			{
				currCount = root.countMap.get(currChar);
				TrieNode temp = root.nodeMap.get(currChar);
				root = temp;
			}
			else
				return 0;
		}
		return currCount;
	}
}

class TreeOperator
{
	private TreeNode myRoot;
	HashMap<String,TreeNode> treeMap = new HashMap<String,TreeNode>();
	
	TreeNode createTreeFromFlatString(String strTree)
	{
		createTreeFromFlatString(strTree,null);
		return myRoot;
	}
	
	String createTreeFromFlatString(String strTree, TreeNode root)
	{	
		//System.out.println(strTree);
		int blankIndex = strTree.indexOf(' ');
		TreeNode curr = null;
		boolean containsChild = false;
		if(blankIndex == -1 && strTree.length()>0)
		{
			curr = new TreeNode(strTree);
			treeMap.put(strTree,curr);
			if(root==null)
				myRoot = curr;
			else
				root.childrenList.add(curr);
			strTree = null;
		}
		else if(blankIndex != -1)
		{
			String nodeName = strTree.substring(0,blankIndex);
			curr = new TreeNode(nodeName);
			treeMap.put(nodeName,curr);
			strTree = strTree.substring(blankIndex+1);
			if(root==null)
				myRoot = curr;
			else
				root.childrenList.add(curr);
			if(strTree.charAt(0)=='(')
			{
				containsChild = true;
				strTree = strTree.substring(2);
			}
			while(containsChild)
			{	
				strTree = createTreeFromFlatString(strTree,curr);
				if(strTree.charAt(0)==')')
				{	
					if(strTree.length()>2)
						strTree = strTree.substring(2);
					break;
				}
			}
		 }
		 return strTree;
	}
	
	void printTree()
	{
		if(myRoot == null)
			return;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		System.out.println(myRoot.topicName);
		q.add(myRoot);
		int levelSize=1;
		while(!q.isEmpty())
		{
			int newLevelSize=0;
			while(levelSize>0)
			{
				TreeNode curr=q.remove();
				newLevelSize += curr.childrenList.size();
				for(int i=0; i<curr.childrenList.size();i++)
				{
					q.add(curr.childrenList.get(i));
					System.out.print(curr.childrenList.get(i).topicName+":childernCount="+curr.childrenList.get(i).childrenList.size()+" ");
				}
				levelSize--;
			}
			System.out.println();
			levelSize=newLevelSize;
		}
	}
		
}

class TreeNode
{
	String topicName;
	ArrayList<TreeNode> childrenList;
	TrieOperator myTrie = new TrieOperator();
	TreeNode(String topicName)
	{
		this.topicName = topicName;
		childrenList = new ArrayList<TreeNode>();
	}
}