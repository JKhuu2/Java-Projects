import java.util.ArrayList;

/**
 * Binary Search Tree Class
 * 
 * The head class for a binary search tree implementation.
 * 
 * @author jtk2eh
 * @param <Comparable> Type of data to store in the binary tree
 */
public class BinarySearchTree<T extends Comparable<T>> {

	/**
	 * A reference pointer to the root of the tree
	 */
	private TreeNode<T> root;

	/**
	 * Default constructor
	 * 
	 * Creates a binary tree object with null root note (empty tree)
	 */
	public BinarySearchTree() {
		this(null);
	}

	/**
	 * Constructor
	 * 
	 * Creates a binary tree object with the given node as root
	 * 
	 * @param newRoot The root of the tree
	 */
	public BinarySearchTree(TreeNode<T> newRoot) {
		this.root = newRoot;
	}
	
	/**
	 * Get the root of the tree
	 * 
	 * @return The root of the tree
	 */
	public TreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the root of the tree
	 * 
	 * @param root  The new root of this tree
	 */
	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}
	
	/***
	 * find the size of the tree; number of nodes
	 * @return int - size of the tree
	 */
	public int size() {
	    if (root!=null) {
	        return root.size();
	    }
	    else {
	        return 0;
	    }
	}
	
	/***
	 * finds the height; longest path from root to leaf
	 * @return int - height of the tree
	 */
	public int height() {
	    if (root!=null) {
	        return root.height();
	    }
	    else {
	        return 0;
	    }
	}
	
	/**
	 * Find if an element exists
	 * 
	 * Checks to see if the value val appears in the
	 * tree (recursively).  Returns true if it appears
	 * and false otherwise.
	 * 
	 * @param val The value to find
	 * @return True if the tree contains the value, false otherwise
	 */
	public boolean find(T val) {
	    if(root==null) {
	        return false;
	    }
	    return root.find(val);
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
	    if(root==null) {
	        root=new TreeNode<T>();
	        root.setData(val);
	        return true;
	    }
	    return root.insert(val);
	}
	
	/***
	 * inOrder()
	 * 
	 * String that represents the data held in the tree starting with left
     * child followed by the node followed by right child
	 * 
	 * @return string: values in order "(0)(1)"
	 */
	public String inOrder() {
	    if(root==null) {
	        return "";
	    }
	    return root.inOrder();
	}
	
	/***
	 * postOrder()
	 * 
	 * String that represents the data held at each node starting with all the nodes
     * of the left child, right child, and then the root
	 * 
	 * @return String: values in postOrder "(-1)(1)(0)"
	 */
	public String postOrder() {
	    if(root==null) {
            return "";
        }
	    return root.postOrder();
	}
    /**
     * Delete an element from the tree
     * 
     * Deletes val from the tree if it appears, returning
     * true on success and false otherwise
     * 
     * @param val The value to delete
     * @return True on success, false otherwise
     */
	
    public boolean delete(T val) {
        TreeNode<T> parent=this.root;
        TreeNode<T> current=null;
        BinarySearchTree<T> newTree=null;
        TreeNode<T> next=null;
        T data=null;
        if(val.compareTo(parent.getData())<0) {
            current=parent.getLeft();
            if(current==null) {
                return false;
            }
            data=current.getData();
            if(data==val&&current.getRight()==null) {
                parent.setLeft(current.getLeft());
                return true;
            }
            else if(data==val && current.getLeft()==null) {
                parent.setLeft(current.getRight());
                return true;
            }
            else {
                newTree=new BinarySearchTree<T>(current);
                newTree.delete(val);
            }
        }
        else if(val.compareTo(parent.getData())>0) {
            current=parent.getRight();
            if(current==null) {
                return false;
            }
            data=current.getData();
            if(data==val&&current.getLeft()==null) {
                parent.setRight(current.getRight());
                return true;
            }
            else if(data==val && current.getRight()==null) {
                parent.setRight(current.getLeft());
                return true;
            }
            else {
                newTree=new BinarySearchTree<T>(current);
                newTree.delete(val);
            }
        }
        else if(val.compareTo(parent.getData())==0) {
            if(parent.getLeft()==null&&parent.getRight()==null) {
                parent=null;
            }
//            else if (parent.getLeft()==null) {
//                newTree=new BinarySearchTree<T>(parent);
//                TreeNode<T> successor=newTree.getSuccessor();
//                if(successor==parent) {
//                    next=parent.getRight();
//                    data=next.getData();
//                    if(next.getLeft()==null&&next.getRight()==null) {
//                        next=null;
//                        parent.setData(data);
//                        return true; 
//                    }
//                    else if(next.getLeft()==null && next.getRight()!=null) {
//                        parent.setRight(next.getRight());
//                        parent.setData(data);
//                        return true;
//                    }
//                }
//                else {
//                    next=successor.getLeft();
//                    data=next.getData();
//                    if(next.getLeft()==null && next.getRight()==null) {
//                       successor.setLeft(null);
//                    }
//                    else if(next.getRight()!=null) {
//                        successor.setLeft(next.getRight());
//                    }
//                    parent.setData(data);
//                    return true;
//                }
//            }
//            else if(parent.getRight()==null) {
//                newTree=new BinarySearchTree<T>(parent);
//                TreeNode<T> successor=newTree.getLeftSuccessor();
//                TreeNode<T> child=null;
//                if(successor==parent) {
//                    next=parent.getLeft();
//                    data=next.getData();
//                    if(next.getLeft()==null && next.getRight()==null) {
//                        next=null;
//                        parent.setData(data);
//                        return true;
//                    }  
//                    else if(next.getRight()==null) {
//                        child=next.getLeft();
//                        parent.setLeft(child);
//                        parent.setData(data);
//                        return true;
//                    }
//            }
//                else {
//                    child=successor.getRight();
//                    data=child.getData();
//                    if(child.getRight()==null && child.getLeft()==null) {
//                        child=null;
//                        parent.setData(data);
//                        return true;
//                    }
//                    if(child.getLeft()!=null) {
//                        successor.setRight(child.getLeft());
//                        parent.setData(data);
//                        return true;
//                    }
//                }
//            }
            else {
                newTree=new BinarySearchTree<T>(parent);
                TreeNode<T> successor=newTree.getSuccessor();
                if(successor==parent) {
                    next=parent.getRight();
                    data=next.getData();
                    if(next.getLeft()==null&&next.getRight()==null) {
                        parent.setRight(null);
                        parent.setData(data);
                    }
                    else if(next.getLeft()==null && next.getRight()!=null) {
                        parent.setRight(next.getRight());
                        parent.setData(data);
                    }
                }
                else {
                    next=successor.getLeft();
                    data=next.getData();
                    if(next.getLeft()==null && next.getRight()==null) {
                       successor.setLeft(null);
                    }
                    else if(next.getRight()!=null) {
                        successor.setLeft(next.getRight());
                    }
                    parent.setData(data);
                }
        }
    }
        return true;
    }
    
    //getting left-most node on the right subtree
    public TreeNode<T> getSuccessor(){
        TreeNode<T> parent=this.root;
        TreeNode<T> current=parent.getRight();
        TreeNode<T> child=null;
        if(current.getLeft()==null && current.getRight()==null) {
            return parent;
        }
        if(current.getRight()==null) {
            return parent;
        }
        else {
            child=current.getLeft();
            while(child.getLeft()!=null) {
                current=child;
                child=child.getLeft();
            }
            return current;
        }
    }
    
    //getting right-most node in left subtree
    public TreeNode<T> getLeftSuccessor(){
        TreeNode<T> parent=this.root;
        TreeNode<T> current=parent.getLeft();
        TreeNode<T> child=null;
        if(current.getLeft()==null && current.getRight()==null) {
            return parent;
        }
        else if(current.getRight()==null) {
            return parent;
        }
        else {
            child=current.getRight();
            while (child.getRight()!=null) {
                current=child;
                child=child.getRight();
            }
            return current;
        }
    }
	
	/**
	 * Build from a list
	 * 
	 * Build the tree from the given list, overwriting any tree data
	 * previously stored in this tree.  Should read from beginning to
	 * end of the list and repeatedly call insert() to build the tree.
	 * 
	 * @param list The list from which to build the tree
	 * @return True if successfully built, false otherwise
	 */
	public boolean buildFromList(ArrayList<T> list) {
	    if (this.root!=null) {
	        this.root=null;
	    }
	    for (int i=0; i<list.size(); i++) {
	        if (this.insert(list.get(i))==false){
	            return false;
	        }
	    }
	    return true;
	}
	
	   
    /**
     * toString method
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return root.inOrder();
    }
	
	/**
	 * Main method
	 * 
	 * For testing, etc
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
	    //insert and InOrder testing
	    BinarySearchTree<Integer> tree=new BinarySearchTree<>();
	    TreeNode<Integer> b1=new TreeNode<>(0);
	    tree.setRoot(b1);
	    tree.insert(1);
	    System.out.println(tree.inOrder());
	    
	    //postorder testing
	    tree.insert(-1);
	    System.out.println(tree.postOrder());
	    
	    //size testing
	    System.out.println(tree.size());
	    
	    //height testing
	    System.out.println(tree.height());
	    
	    //delete testing
	    System.out.println(tree.delete(-1));
	    System.out.println(tree.toString());
	    System.out.println(tree.delete(1));
	    System.out.println(tree.toString());
	    tree.insert(3);
	    tree.insert(4);
	    tree.insert(5);
	    tree.delete(4);
	    System.out.println(tree.toString());
	    
	    BinarySearchTree<Integer> bst=new BinarySearchTree<Integer>();
	    bst.insert(5);
	    bst.insert(3);
	    bst.insert(1);
	    System.out.println(bst.size());
	    System.out.println(bst.inOrder());
	}
}
