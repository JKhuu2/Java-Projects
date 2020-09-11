
/**
 * Binary Tree Node
 * 
 * Tree node that has two children: left and right
 * 
 * @author jtk2eh
 * @param <Comparable> The type of data this tree node stores
 */
public class TreeNode<T extends Comparable<T>> {
	
	/**
	 * Reference pointer to the left subtree
	 */
	private TreeNode<T> left;
	
	/**
	 * Reference pointer to the right subtree
	 */
	private TreeNode<T> right;
	
	/**
	 * Data stored at this node
	 */
	private T data;
	
	/**
	 * Default Constructor
	 * 
	 * Creates a binary tree node with null data and null children
	 */
	public TreeNode(){
		this(null,null,null);
	}
	
	/**
	 * Data-only Constructor
	 * 
	 * Creates a binary tree node with the given data and null children
	 * 
	 * @param theData The data to store at this node
	 */
	public TreeNode(T theData){
		this(theData,null,null);
	}
	
	
	/**
	 * Full Constructor
	 * 
	 * Creates a binary tree node with the given data and child reference pointers
	 * 
	 * @param theData The data to store at this node
	 * @param leftChild A reference pointer to the left subtree
	 * @param rightChild A reference pointer to the right subtree
	 */
	public TreeNode(T theData, TreeNode<T> leftChild, TreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}


	/**
	 * Left Child/Subtree getter
	 * 
	 * @return A reference pointer to the root of the left subtree
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Left Child/Subtree Setter
	 * 
	 * @param left A reference pointer to the new left subtree's root node
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

    /**
     * Right Child/Subtree getter
     * 
     * @return A reference pointer to the root of the right subtree
     */
	public TreeNode<T> getRight() {
		return right;
	}

    /**
     * Right Child/Subtree Setter
     * 
     * @param left A reference pointer to the new right subtree's root node
     */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	/**
	 * Get the data at this node
	 * 
	 * @return The data stored at this node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data at this node
	 * 
	 * @param data The data to be stored at this node
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
	    return ""+this.getData()+"";
	}
	
	/***
	 * size()
	 * 
	 * @return int returns size of tree
	 */
	public int size() {
        int leftSize=0, rightSize=0;
        if (this.left!=null) {
            leftSize=this.left.size();
        }
        if (this.right!=null) {
            rightSize=this.right.size();
        }
        return 1+leftSize+rightSize;
    }
	
	/***
	 * height()
	 * 
	 * @return int returns height of the tree
	 */
	public int height() {
	    int leftHeight=0, rightHeight=0;
	    if(this.left!=null) {
	        leftHeight=this.left.height();
	    }
	    if(this.right!=null) {
	        rightHeight=this.right.height();
	    }
	    return Math.max(leftHeight, rightHeight)+1;
	}
	
	/***
	 * Find an element
	 * 
	 * Determines if the value "val" appears within the Binary SearchTree.
	 * 
	 * @param val
	 * @return true if it exists,false otherwise
	 */
	public boolean find (T val) {
	    TreeNode<T> next=null;
        if(this.getData()==val) {
            return true;
        }
        else if(val.compareTo(this.getData())<0) {
            next=this.left;
        }
        else {
            next=this.right;
        }
        if(next==null) {
            return false;
        }
        return next.find(val);
	}
	
	/**
     * Insert an element
     * 
     * Inserts val into the tree where it should appear, returning
     * true on success and false otherwise
     * 
     * @param val The value to insert
     * @return True on success, false otherwise
     */
	public boolean insert(T val) {
	    TreeNode<T> next=null;
	    if(this.getData()==val) {
	        return false;
	    }
	    else if(val.compareTo(this.getData())<0) {
	        next=this.left;
	    }
	    else if(val.compareTo(this.getData())>0){
	        next=this.right;
	    }
	    if(next==null) {
	        if(val.compareTo(this.getData())<0) {
	            TreeNode<T> node=new TreeNode<T>();
	            node.setData(val);
	            this.setLeft(node);
	            return true;
	        }
	        else {
	            TreeNode<T> node=new TreeNode<T>();
	            node.setData(val);
	            this.setRight(node);
	            return true;
	        } 
	    }
	    return next.insert(val);
	}
	
	/***
     * inOrder()
     * 
     * String that represents the data held in the tree starting with left
     * child followed by the node followed by right child
     * 
     * @return String: values in order "(0)(1)"
     */
	public String inOrder() {
	    String order="";
	    if(this!=null) {
	        if(this.left!=null) {
	            order+=this.left.inOrder();
	        }
	        order+="("+this.getData()+")";
	        if(this.right!=null) {
	            order+=this.right.inOrder();
	        }
	    }
	    return order;
	}
	
	/***
     * postOrder()
     * 
     * String that represents the data held at each node starting with all the nodes
     * of the left child, right child, and then the root
     * 
     * @return string: values in postorder "(-1)(1)(0)"
     */
	public String postOrder() {
	    String order="";
	    if(this!=null) {
	        if(this.left!=null) {
	            order+=this.left.postOrder();
	        }
	        if(this.right!=null) {
	            order+=this.right.postOrder(); 
	        }
	        order+="("+this.getData()+")";
	    }
	    return order;
	}
	
	/**
	 * Main method
	 * 
	 * For main method testing, etc
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
	    //inOrder() and insert testing
	    TreeNode<Integer>root=new TreeNode<Integer>(0);
	    System.out.println(root.insert(1));
	    System.out.println(root.getRight());
	    System.out.println(root.inOrder());
	    System.out.println(root.insert(-1));
	    System.out.println(root.inOrder());
	    
	    //postOrder testing
	    System.out.println(root.postOrder());
	    
	    //size testing
	    System.out.println(root.size());
	    
	    //height testing
	    System.out.println(root.height());
	}

}
