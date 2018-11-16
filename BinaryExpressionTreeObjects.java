/* $Id$ */

import structure5.*;

/**
class to evaluate simple expression trees using structure's
BinaryTree implementation 
<BR> 
Each node in the tree holds either a binary operator (stored as a
Character and restricted to +, -, *, /) or an Integer representing 
its value.

@author Jim Teresco, terescoj@cs.williams.edu, jteresco@mtholyoke.edu
 */
public class BinaryExpressionTreeObjects {

    /** some constants to define the operators */
    public static final Character PLUS = new Character('+');
    public static final Character MINUS = new Character('-');
    public static final Character TIMES = new Character('*');
    public static final Character DIVIDE = new Character('/');

    /**
    method to check if the given BinaryTree has an operator
    at its root

    @param root a BinaryTree whose root value may be an operator
    @return true if the value is a valid operator, false otherwise
     */
    protected static boolean isOperator(BinaryTree<Object> root) {
        Object rootvalue = root.value();
        return (rootvalue.equals(PLUS) || rootvalue.equals(MINUS) || 
            rootvalue.equals(TIMES) || rootvalue.equals(DIVIDE));
    }

    /**
    method to evaluate an expression stored in a BinaryTree

    @param root a BinaryTree whose root value is an operator or an Integer
    @return an int representing the computed value of this tree
     */
    public static int evaluate(BinaryTree<Object> root) {

        Object rootvalue = root.value();

        /* is it just a value? */
        if (rootvalue instanceof Integer) 
            return ((Integer)rootvalue).intValue();

        /* is it a operator? */
        if (isOperator(root)) {
            int left = evaluate(root.left());
            int right = evaluate(root.right());
            if (rootvalue.equals(PLUS)) return left+right;
            if (rootvalue.equals(MINUS)) return left-right;
            if (rootvalue.equals(TIMES)) return left*right;
            if (rootvalue.equals(DIVIDE)) return left/right;
        }

        /* it's something we don't know how to deal with */
        Assert.fail("Unknown value found, giving up");
        return 0;
    }

    /** main method to set up an answer */
    public static void main(String[] args) {

        /* build and evaluate the binary tree for ((4+3)*(10-5))/2 */
        /* we will build it from the bottom up */
        BinaryTree<Object> four = new BinaryTree<Object>(new Integer(4));
        BinaryTree<Object> three = new BinaryTree<Object>(new Integer(3));
        BinaryTree<Object> ten = new BinaryTree<Object>(new Integer(10));
        BinaryTree<Object> five = new BinaryTree<Object>(new Integer(5));
        BinaryTree<Object> two = new BinaryTree<Object>(new Integer(2));

        /* build the actual tree structure */
        BinaryTree<Object> plus = new BinaryTree<Object>(PLUS, four, three);
        BinaryTree<Object> minus = new BinaryTree<Object>(MINUS, ten, five);
        BinaryTree<Object> times = new BinaryTree<Object>(TIMES, plus, minus);
        BinaryTree<Object> divide = new BinaryTree<Object>(DIVIDE, times, two);

        /* what does it look like? */
        System.out.println(divide.treeString());

        /* what's the answer */
        System.out.println(evaluate(divide));
    }
}
