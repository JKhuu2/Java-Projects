import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import java.util.ArrayList;

class HW6Tests {
    TreeNode<Integer>b1=new TreeNode<>(5);
    BinarySearchTree tree=new BinarySearchTree(b1);
    
    @Test
    void buildFromListTest1() {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        tree=new BinarySearchTree<>();
        assertTrue(tree.buildFromList(list));
        System.out.println(tree);
    }
    @Test
    void buildFromListTest2() {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        tree=new BinarySearchTree<>();
        assertFalse(tree.buildFromList(list));
    }
    @Test
    void buildFromListTest3() {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(4);
        tree=new BinarySearchTree<>();
        assertTrue(tree.buildFromList(list));
        System.out.println(tree);
    }
    
    @Test
    void insertTest1() {
       assertTrue(tree.insert(1));
    }
    @Test
    void insertTest2() {
        assertFalse(tree.insert(5));
    }
    @Test
    void inOrderTest1() {
        tree.insert(1);
        assertEquals(tree.inOrder(),"(1)(5)");
    }
    @Test
    void inOrderTest2() {
        tree.insert(6);
        assertEquals(tree.inOrder(),"(5)(6)");
    }
    @Test
    void postOrderTest1() {
        tree.insert(4);
        tree.insert(6);
        assertEquals(tree.postOrder(),"(4)(6)(5)");
    }
    @Test
    void postOrderTest2() {
        tree.insert(0);
        tree.insert(10);
        assertEquals(tree.postOrder(),"(0)(10)(5)");
    }
    @Test
    void toStringTest1() {
        tree.insert(1);
        assertEquals(tree.toString(),"(1)(5)");
    }
    @Test
    void toStringTest2() {
        tree.insert(4);
        tree.insert(6);
        assertEquals(tree.toString(),"(4)(5)(6)");
    }
    @Test
    void sizeTest1() {
        tree.insert(1);
        assertEquals(tree.size(),2);
    }
    @Test
    void sizeTest2() {
        tree.insert(4);
        tree.insert(6);
        assertEquals(tree.size(),3);
    }
    @Test
    void heightTest1() {
        tree.insert(1);
        assertEquals(tree.height(),2);
    }
    @Test
    void heightTest2() {
        tree.insert(4);
        tree.insert(3);
        assertEquals(tree.height(),3);
    }
    @Test
    void findTest1() {
        tree.insert(1);
        assertTrue(tree.find(1));
    }
    @Test
    void findTest2() {
        assertFalse(tree.find(1));
    }
    @Test
    void deleteTest1() {
        tree.insert(1);
        System.out.println(tree.toString());
        assertTrue(tree.delete(1));
        System.out.println(tree.toString());
    }
    @Test
    void deleteTest2() {
        assertFalse(tree.delete(1));
    }
}

