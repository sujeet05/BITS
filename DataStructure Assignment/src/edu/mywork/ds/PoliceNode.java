package edu.mywork.ds;

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

	
	/**
	 * This method add the node
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
	 * Insert node by Amount 
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
	
	/* inorder traversal */
	public void traverse_inorder(PoliceNode root)
	{
		if (root != null) {
			traverse_inorder(root.left);
			traverse_inorder(root.right);
			 
			}
	}
	/*Reorder postorder traversal */
	public PoliceNode traverse_postorder_reorder(PoliceNode root,PoliceNode newroot)
	{
		if (root != null) {
				newroot=traverse_postorder_reorder(root.left,newroot);
				newroot=traverse_postorder_reorder(root.right,newroot);
				return insertNode(newroot,root);
			}
			return newroot;
	}
	public void destroynodes(PoliceNode root){
		if(root==null)return;
		destroynodes(root.left);
		destroynodes(root.right);
		root=null;
	}
	public int getmaxAmount(PoliceNode root){
		while(root.right !=null){
			root=root.right;
		}
		return root.fineAmt;
	}
}