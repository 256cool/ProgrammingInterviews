class AVLTreeNode
{
	int val, height;
	AVLTreeNode left, right;
	AVLTreeNode(int val)
	{
		this.val = val;
		left = null;
		right = null;
	}
}

class AVLTree
{
	AVLTreeNode root;
	
	AVLTree()
	{
		root = null;
	}
	
	void insert(int val)
	{
		root = insert(root,val);
	}
	
	int getBalanceFactor(AVLTreeNode root)
	{
		int leftHeight = -1, rightHeight = -1;
		if(root.left != null)
			leftHeight = root.left.height;
		if(root.right != null)
			rightHeight = root.right.height;
		return leftHeight-rightHeight;
	}
	
	int getHeight(AVLTreeNode root)
	{
		if(root == null)
			return -1;
		return root.height;
	}
	
	AVLTreeNode rotateLeft(AVLTreeNode root)
	{
		AVLTreeNode newRoot = root.right;
		AVLTreeNode temp = newRoot.left;
		newRoot.left = root;
		root.right = temp;
		root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
		newRoot.height = Math.max(getHeight(newRoot.left),getHeight(newRoot.right))+1;
		return newRoot;
	}
	
	AVLTreeNode rotateRight(AVLTreeNode root)
	{
		AVLTreeNode newRoot = root.left;
		AVLTreeNode temp = newRoot.right;
		newRoot.right = root;
		root.left = temp;
		root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
		newRoot.height = Math.max(getHeight(newRoot.left),getHeight(newRoot.right))+1;
		return newRoot;
	}
	
	AVLTreeNode insert(AVLTreeNode root, int val)
	{
		if(root == null)
		{
			root = new AVLTreeNode(val);
			root.height = 0;
			return root;
		}
		if(val <= root.val)
		{
			root.left = insert(root.left, val);
		}
		else 
		{
			root.right = insert(root.right,val);
		}
		root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
		int balance = getBalanceFactor(root);
		if(balance > 1 && val < root.left.val)//LeftLeft case
			root = rotateRight(root);
		if(balance > 1 && val > root.left.val)//LeftRight Case
		{
			root.left = rotateLeft(root.left);
			root = rotateRight(root);
		}
		if(balance < -1 && val > root.right.val)//RightRight case
			root = rotateLeft(root);
		if(balance < -1 && val < root.right.val)//RightLeft Case
		{
			root.right = rotateRight(root.right);
			root = rotateLeft(root);
		}
		return root;
	}
	
	void printPreOrder(AVLTreeNode root)
	{
		if(root != null)
		{
			System.out.print(root.val+" ");
			printPreOrder(root.left);
			printPreOrder(root.right);
		}
	}
	
	public static void main(String args[])
	{
		AVLTree obj = new AVLTree();
		obj.insert(10);
		obj.insert(20);
		obj.insert(30);
		obj.insert(40);
		obj.insert(50);
		obj.insert(25);
		obj.printPreOrder(obj.root);
	}
}