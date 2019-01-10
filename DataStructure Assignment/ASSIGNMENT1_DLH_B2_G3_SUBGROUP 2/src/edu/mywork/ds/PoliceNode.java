package edu.mywork.ds;

/*
 * Class Definition to create PoliceNode Object
 * */
class PoliceNode {
	int policeId, fineAmt;
	PoliceNode left, right;
	
	public PoliceNode() {
		super();
	}
	public PoliceNode(int policeId, int fineAmt) {
		this.policeId = policeId;
		this.fineAmt = fineAmt;
		this.left =null;
		this.right=null;
	}

	
	/* 
	 * This method create police node object and create Binary tree ordered by Police ID
	 * @param current
	 * @param policeId
	 * @param fineAmt
	 * @return
	 */
	
	/*Insertion of node ....*/
	
	public PoliceNode insertNode(PoliceNode current, int policeId, int fineAmt) {
		if (current == null) {
	        return new PoliceNode(policeId,fineAmt);
	    }
		if (policeId < current.policeId) {
	        current.left = insertNode(current.left, policeId, fineAmt);
	    } else if (policeId > current.policeId) {
	        current.right = insertNode(current.right, policeId, fineAmt);
	    } else {
	        // value already exists
	    	current.fineAmt+=fineAmt;
	        return current;
	    }
		return current;
	}
	/*
	 * This methods re-order the nodes of Binary tree from Police ID to Amount.in process 
	 * this removes the nodes from the old tree (order by police ID) and new re-ordered tree
	 * (by Amount) 
	 * */
	public PoliceNode insertNode(PoliceNode root,PoliceNode current){
	   if(root==null ){
		root=current;
		current.left=null;
		current.right=null;
		return root;
	   }
	   if(current.fineAmt <= root.fineAmt){
	    	root.left= insertNode(root.left,current);
	    }
	    else{
	    	root.right =insertNode(root.right,current);
	    }
		return root;
	}
	
	/*
	 * Inorder traversal of the tree ..
	 * */
	public void traverse_inorder(PoliceNode root)
	{
		if (root != null) {
			traverse_inorder(root.left);
			traverse_inorder(root.right);
			 
			}
	}
	/*
	 * Traverse the tree in post order and reorder the nodes from police id to Amount
	 * return the root of re-ordered tree 
	 * */
	public PoliceNode traverse_postorder_reorder(PoliceNode root,PoliceNode newroot)
	{
		if (root != null) {
				newroot=traverse_postorder_reorder(root.left,newroot);
				newroot=traverse_postorder_reorder(root.right,newroot);
				return insertNode(newroot,root);
			}
			return newroot;
	}
	/*
	 * Destroy all the nodes of the tree ..Memory Clean up
	 * */
	public void destroynodes(PoliceNode root){
		if(root==null)return;
		destroynodes(root.left);
		destroynodes(root.right);
		root=null;
	}
	/*
	 * Get the Maximum key value of the node of the tree
	 * */
	public int getmaxAmount(PoliceNode root){
		while( root !=null && root.right !=null){
			root=root.right;
		}
		return root.fineAmt;
	}
}