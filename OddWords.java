/*Liam Coffey 1206617 */
/* My take on BST OddWord Assignment*/

import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
class OddWords{
    //internal node class
    private class BSTlex{
	public String data;
	public BSTlex left;
	public BSTlex right;
	public BSTlex(String x){
	    data = x;
	    left = null;
	    right = null;
	}
    }
    private BSTlex head;
    private File FileS;
    public String outPut;
    //constructor method
    public OddWords(String File){
	FileS = new File(File);
	outPut = "";
    }
    public static void main(String[] args){
	OddWords Tree = new OddWords(args[0]);
	Tree.Scan();
	System.out.println("LEXICON:");
	Tree.ROT();
    } // end of main()
    // this method scans the files and breaks it into words and adds it to the bst
    public void Scan(){
	if(FileS.exists()){
		try{
	    //only scans for A-Z, a-z or 1-9 treats everything else as whitespace
	    Scanner sc = new Scanner(FileS).useDelimiter("[^A-Za-z1-9]+");
	    String S;
	    while(sc.hasNext()){
		S = sc.next();
		//converts each word to lowercase
		S = S.toLowerCase();
		//adds it to the bst
		add(S);
	    }
	}catch(Exception e){};}
	else{
	    System.out.println("File Not Found");
	}
    }// add method to check if head is null
    public void add(String X){
      	BSTlex temp = new BSTlex(X);
	if(head == null){
	    head = temp;
	    System.out.println(head.data+" :ADDED");
	}else{
	    //if head is not null add it to the tree
	    addToEnd(temp);
	}

    }
    // simple add method without recursion
    public void addToEnd(BSTlex temp){
            outPut = head.data+" ";	
	    BSTlex curr = head;
	    //loops around curr
	    while(curr != null){
		//if its not allreay in the bst
		if(curr.data.equals(temp.data) == false){
		    //adds it to the smaller child
		if(Compare(curr.data,temp.data) == -1 && curr.left == null){
		    outPut += temp.data+" ";
		    outPut += ":ADDED";
		    curr.left = temp;
		    System.out.println(outPut);
		    outPut = "";
		    break;
		}
		//add it to the larger child
		else if(Compare(curr.data,temp.data) == 1 && curr.right == null){
		    outPut += temp.data+" ";
		    outPut += ":ADDED";
		    curr.right = temp;
		    System.out.println(outPut);
		    outPut = "";
		    break;
		}
		//loops down the tree
		else if(Compare(curr.data,temp.data) == -1 && curr.left != null){
		    curr = curr.left;
		    outPut += curr.data+" ";
		}
		else if(Compare(curr.data,temp.data) == 1 && curr.right != null){
		    
		    curr = curr.right;
		    outPut += curr.data+" ";
		}
		}else{
		    //deletes the double ups
		    delete(temp.data);
		    break;   
		}
	    }
    }
       // This method calls deleteRec()
    private void delete(String X)
    {
        head = deleteRec(head, X);
    }
 
    /* A recursive function to remove a key in BST */
    private BSTlex deleteRec(BSTlex curr, String X)
    {
	outPut += curr.data + " ";
        /* If the tree is empty do nothing */
        if (curr == null){
	    return curr;
	}
        /* recur down the tree */
        if (Compare(X,curr.data) == 1){
            curr.left = deleteRec(curr.left, X);
	}
        else if (Compare(X,curr.data) == -1){
            curr.right = deleteRec(curr.right, X);
	}
        // if X is same as curr, then This is the node
        // to be deleted
        else
        {
	    
            // node with only one child or no child
            if (curr.left == null){
                return curr.right;
	    }
            else if (curr.right == null){
                return curr.left;
	    }
            // node with two children
            // in the right subtree)
            curr.data = minValue(curr.right);
 
            // Delete the greater
            curr.right = deleteRec(curr.right, curr.data);
	    outPut += " :REMOVED";
	    System.out.println(outPut);
	    outPut = "";
        }
 
        return curr;
    }
    
    private String minValue(BSTlex curr)
    {
        String minv = curr.data;
        while (curr.left != null)
        {
	    
            minv = curr.left.data;
            curr = curr.left;
	    }
        return minv;
    }
    //simple compare method to save on typing
    public int Compare(String X, String Y){
	//if X is greater than Y return 1
	if(X.compareTo(Y) < 0){
	    return 1;
	}
	//if X is less than Y return -1
	else if(X.compareTo(Y) > 0){
	    return -1;
	}
	//if the = return 0
	else{
	    
	    return 0;
	}
    }
    //in order Traversal of the BST
    public void ROT(){
	
	BSTlex curr = head;
	//creates a stack to save the nodes to
	Stack<BSTlex> S = new Stack<BSTlex>();
	//gives the loop something to loop on
	while(true){
	    //if curr = anthing save it to the stacl and go to the left child
	    if(curr != null){
		S.push(curr);
		curr = curr.left;
	    }else{
		//if the stack is empty break the loop
		if(S.isEmpty()){break;}
		//make curr = to the top of the stack
                curr = S.pop();
		//save that value to the output
		//output the lexicon
		System.out.println(curr.data);
		//check to see if it has any children to its right
                curr = curr.right;

               }
	}
    }

}

