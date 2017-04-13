import java.util.*;
/**
 * 612 PBE06 Binary Search Tree
 * Prof Kang
 */

class Node {
    Node left;
    Node right;
    int value;
    public Node(int value) {
        this.value =value;
    }
}

public class BST {
    private Node root;

    public void insert(Node node, int value) {
        if (value < node.value){
            if (node.left != null) insert(node.left, value);
            else{
                node.left = new Node(value);
                System.out.println("Inserted "+value+" to left node" +  node.value);
            }
        } else if (value > node.value){
            if (node.right != null) insert(node.right, value);
            else{
                node.right = new Node(value);
                System.out.println("Inserted "+value+" to right node"+node.value);
            }
        }
    }

    public void printInOrder(Node node) {
        if(node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }
    public Node search(Node n, int key) {
        //To be completed
        return null;
    }

    public static void main(String[] args) {

      BST bst = new BST();
      Node rootnode = new Node(35);
      bst.insert(rootnode, 20);
      bst.insert(rootnode, 85);
      bst.insert(rootnode, 30);
      bst.insert(rootnode, 45);
      
      System.out.println("traversed");
      bst.printInOrder(rootnode);
      /*
      Node n = bst.search(rootnode, 45);
      //Node n = bst.search(rootnode, 100);
      if(n != null) System.out.println("\n" + n.value);
      else System.out.println("\n no match");
   */
    }
}