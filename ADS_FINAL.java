/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads_project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class ADS_FINAL {
    ArrayList<Integer> keys;
    class Node 
	{ 
		int roll; 
                int marks;
                int sem;
		Node left, right; 

		public Node(int item,int mark,int sem) 
		{ 
			roll = item; 
                        marks = mark;
                        this.sem = sem;
			left = right = null; 
		} 
                @Override
    protected void finalize() throws Throwable{
        System.out.println("Roll number: "+this.roll+" from Semester:"+this.sem+" is deleted and collected by garbage collector ");
    }
	} 

	// Root of BST 
	Node root; 

	// Constructor 
	ADS_FINAL() 
	{ 
		root = null; 
	}
        void insert(int roll,int marks,int sem,int flag) 
	{ 
                if(flag==1)
		root = insertByRoll(root, roll , marks,sem); 
                else
                root = insertBySem(root, roll , marks,sem); 
                
	} 
        Node insertByRoll(Node root, int roll, int marks,int sem) 
	{ 

		/* If the tree is empty, return a new node */
		if (root == null) 
		{ 
			root = new Node(roll,marks,sem); 
			return root; 
		} 

		/* Otherwise, recur down the tree */
		if (roll < root.roll) 
			root.left = insertByRoll(root.left, roll,marks,sem); 
		else if (roll > root.roll) 
			root.right = insertByRoll(root.right, roll,marks,sem); 
                else
                {
                        if(sem<root.sem)
                            root.left = insertByRoll(root.left, roll,marks,sem);
                        else if(sem>root.sem)
                            root.right = insertByRoll(root.right, roll,marks,sem);
                     
                }
		/* return the (unchanged) node pointer */
		return root; 
	} 
        Node insertBySem(Node root, int roll, int marks,int sem) 
	{ 

		/* If the tree is empty, return a new node */
		if (root == null) 
		{ 
			root = new Node(roll,marks,sem); 
			return root; 
		} 

		/* Otherwise, recur down the tree */
		if (sem < root.sem) 
			root.left = insertBySem(root.left, roll,marks,sem); 
		else if (sem > root.sem) 
			root.right = insertBySem(root.right, roll,marks,sem); 
                else
                {
                        if(roll<root.roll)
                            root.left = insertByRoll(root.left, roll,marks,sem); 
                        else if (roll>root.roll)
                            root.right = insertByRoll(root.right, roll,marks,sem);
                }
		/* return the (unchanged) node pointer */
		return root; 
	}
        void myinorder() 
	{   
                System.out.println("Roll\tSem\tMarks");
                System.out.println("----------------------------------------------------");
		inorder(root); 
	} 
        void inorder(Node root) 
	{ 
		if (root != null) 
		{ 
			inorder(root.left); 
			System.out.println(root.roll + "\t" +root.sem+"\t"+root.marks); 
			inorder(root.right); 
		} 
	} 
        void inorder1(Node root , int limit,int flag) 
	{ 
		if (root != null) 
		{ 
			inorder1(root.left,limit,flag); 
                      
                        if(root.marks<=limit) 
                        {
                            if(flag==1)
                            keys.add(root.roll);
                            else
                            keys.add(root.sem);
                        }
			inorder1(root.right,limit,flag); 
		} 
	} 
        void deleteByMarks(int limit,int flag)
        {
                keys = new ArrayList<Integer>();
                inorder1(root,limit,flag);
               // System.out.println(keys.size());
               if(keys.size()==0)
                    System.out.println("Zero nodes to be deleted");
               else{
               for(int i=0;i<keys.size();i++)
                   deleteKey(keys.get(i),limit);
               
               }
               System.gc();
        }
        void deleteKey(int roll,int limit) 
	{ 
		root = deleteRec(root, roll,limit); 
	} 

	
	Node deleteRec(Node root, int roll,int marks) 
	{ 
		/* Base Case: If the tree is empty */
		if (root == null) return root; 

		/* Otherwise, recur down the tree */
		if (roll < root.roll) 
			root.left = deleteRec(root.left, roll,marks); 
		else if (roll > root.roll) 
			root.right = deleteRec(root.right, roll,marks); 

		// if roll is same as root's roll, then This is the node 
		// to be deleted 
		else
		{ 
                    {
			// node with only one child or no child 
			if (root.left == null) 
				return root.right; 
			else if (root.right == null) 
				return root.left; 

			// node with two children: Get the inorder successor (smallest 
			// in the right subtree) 
			root.roll = minValue(root.right); 

			// Delete the inorder successor 
			root.right = deleteRec(root.right, root.roll,marks); 
                    }
		} 

		return root; 
	} 

	int minValue(Node root) 
	{ 
		int minv = root.roll; 
		while (root.left != null) 
		{ 
			minv = root.left.roll; 
			root = root.left; 
		} 
		return minv; 
	} 
        public static void main(String[] arg)
        {
                ADS_FINAL tree = new ADS_FINAL();
                int flag ;
                Scanner sc= new Scanner(System.in);
                boolean fl = false;
                System.out.println("Structuring on basis of: [1=ROLL ,2=SEMESTER]");
                flag = sc.nextInt();
                
                tree.insert(2,60,3,flag);
                tree.insert(1,40,2,flag);
                tree.insert(10,80,4,flag);
                tree.insert(2,50,4,flag);
                tree.insert(10,90,1,flag);
                tree.insert(9,81,1,flag);
                tree.insert(3,65,1,flag);
                tree.insert(4,39,4,flag);
                tree.myinorder();
                System.out.println("Performing deletions for nodes having mar"
                        + "ks less than 41");
                tree.deleteByMarks(41,flag);
             //   System.gc();
                tree.myinorder();
        }
}
