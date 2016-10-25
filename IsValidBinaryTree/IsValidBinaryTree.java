class IsValidBinaryTree
{
	boolean isValidBST(TreeNode root)
	{
		return ValidBSTUtil(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	boolean ValidBSTUtil(TreeNode root, int minVal, int maxVal)
	{
		if(root == null)
			return true;
		if(root.value <= minVal || root.value >= maxVal)
			return false;
		return (ValidBSTUtil(root.left,minVal,root.val) && ValidBSTUtil(root.right,root.val,maxVal));
	}
}