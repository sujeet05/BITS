package edu.mywork.ds;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


class PoliceTree {
	
	PoliceNode root;
	
	/*
	 *This function inserts an entry <id, amount> into the police tree ordered 
	 *by police id. If the Police id is already found in the tree, then this
	 *function adds up to the existing amount to get the total
	 * amount to be stored against him. This function returns the updated tree 
	 * This method insert the node(PoliceNode) into tree by police id and fined amount
	 * @param root
	 * @param policeId
	 * @param amount
	 * @return
	 */
	
	public PoliceTree insertByPoliceId(PoliceTree root, int policeId, int amount) {
		if(root.root == null) {
			root.root = new PoliceNode(policeId, amount);
			 root.root.insertNode(null, policeId, amount);
			 return root;
		}
		 root.root.insertNode(root.root, policeId, amount);
		 return root;
	}
	/*
	 * This function reorders the Binary Tree on the basis of fine amount, 
	 * instead of police id. This function removes the nodes from the original 
	 * PoliceTree, and puts it in a new tree ordered by fine amount. Note that
	 * if the fine amount is equal to the amount in node j, then they will be
	 * inserted to the left of the node j.
	 * This function returns the root node of the new tree.
	 * */
	PoliceTree reorderByFineAmount(PoliceTree root){
		/* traverse the tree in post order and reorder the node */
		
		PoliceTree reorderedTree =new PoliceTree();
		root.root.traverse_inorder(root.root);
		reorderedTree.root=root.root.traverse_postorder_reorder(root.root,reorderedTree.root);
		return reorderedTree;
	}
	/*This function prints the policemen who have earned more 
	 * than 90% of largest fine amount collected by an individual
	 *  on to a file called "bonus.txt".
	 * This method create bonus file by Policemen id if he eligible
	 * @param root
	 * @param maxFine
	 * @param policeIdList
	 */
	
	public void printBonusPolicemen(PoliceTree root){
		PoliceTree reorderedTree=reorderByFineAmount(root);
		PoliceNode traverse = reorderedTree.root;
		int maxFine= reorderedTree.root.getmaxAmount(reorderedTree.root);
		List<String> policeIdList = new ArrayList<String>();
		printBonusPolicemen(traverse,maxFine,policeIdList);
		Path path;
		try {
			path = Paths.get("bonus.txt");
			Files.write(path, policeIdList, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printBonusPolicemen(PoliceNode root, int maxFine, List<String> policeIdList) {
		if (root != null) {
			printBonusPolicemen(root.right, maxFine, policeIdList);
			if(root.fineAmt >= maxFine*.9) {
				policeIdList.add(root.policeId + ", ");
			}
			printBonusPolicemen(root.left, maxFine, policeIdList);
		}
	}
	
	/**
	 * @param root
	 * This function is meant for debugging purposes.This function prints the 
	 * contents of the PoliceTree in-order
	 */
	public void printPoliceTree (PoliceTree root) {
		root.root.traverse_inorder(root.root);
	}
	/*
	 * This function is a cleanup function that destroys all the 
	 * nodes in the police tree
	 * traverse the tree in postorder and set the node equals to null
	 */
	void destroyPoliceTree (PoliceTree root){
		root.root.destroynodes(root.root);
	}
	
}