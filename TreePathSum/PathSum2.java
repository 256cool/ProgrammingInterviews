class PathSum2
{
	int getHeight(TreeNode root)
	{
		if(root == null)
			return 0;
		return Math.max(getHeight(root.left),getHeight(root.right))+1;
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) 
	{
		List<List<Integer>> mainList = new ArrayList<List<Integer>>();
		List<Integer> currList = new ArrayList<Integer>();
		pathSum(root,sum,0,0,currList,mainList);
		return mainList;
    }
	
	void pathSum(TreeNode root, int sum, int level, int currSum, List<Integer> currList, List<List<Intger>> mainList)
	{
		if(root==null)
			return;
		if(root.left==null && root.right==null && currSum==sum)
		{
			List<Integr> temp = new ArrayList<Integer>(currList);
			mainList.add(temp);
			return;
		}
		currSum+=root.val;
		currList.add(level,root.val);
		pathSum(root.left,sum,level+1,currSum,currList,mainList);
		pathSum(root.right,sum,level+1,currSum,currList,mainList);
	}
}