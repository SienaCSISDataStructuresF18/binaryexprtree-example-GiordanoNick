import structure5.*;

/**
   class to evaluate simple expression trees using structure's
   BinaryTree implementation 
   <BR> 
   Each node in the tree holds a String that is either a binary operator
   (restricted to "+", "-", "*", "/") or can be treated as an integer value.
   
   @author Jim Teresco, terescoj@cs.williams.edu, jteresco@mtholyoke.edu,
   jteresco@siena.edu
*/
public class BinaryExpressionTree {

    /** some constants to define the operators */
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String TIMES = "*";
    public static final String DIVIDE = "/";

    /**
    method to check if the given BinaryTree has an operator
    at its root

    @param root a BinaryTree whose root value may be an operator
    @return true if the value is a valid operator, false otherwise
     */
    protected static boolean isOperator(BinaryTree<String> root) {
        String rootvalue = root.value();
        return (rootvalue.equals(PLUS) || rootvalue.equals(MINUS) || 
            rootvalue.equals(TIMES) || rootvalue.equals(DIVIDE));
    }

    /**
    method to evaluate an expression stored in a BinaryTree

    @param root a BinaryTree whose root value is an operator or an Integer
    @return an int representing the computed value of this tree
     */
    public static int evaluate(BinaryTree<String> root) {

        String rootvalue = root.value();

        /* is it a operator? */
        if (isOperator(root)) {
            int left = evaluate(root.left());
            int right = evaluate(root.right());
            if (rootvalue.equals(PLUS)) return left+right;
            if (rootvalue.equals(MINUS)) return left-right;
            if (rootvalue.equals(TIMES)) return left*right;
            if (rootvalue.equals(DIVIDE)) return left/right;
        }

        /* is it just a value? */
        try {
            int value = Integer.parseInt(rootvalue);
            return value;
        }
        catch (NumberFormatException e) {
            Assert.fail("Expected String representing an operator or an integer, found " + rootvalue);
        }

        /* it's something we don't know how to deal with */
        Assert.fail("Unknown value found, giving up");
        return 0;
    }

    /** main method to set up an answer */
    public static void main(String[] args) {

        /* build and evaluate the binary tree for ((4+3)*(10-5))/2 */
        /* we will build it from the bottom up */
        BinaryTree<String> four = new BinaryTree<String>("4");
        BinaryTree<String> three = new BinaryTree<String>("3");
        BinaryTree<String> ten = new BinaryTree<String>("10");
        BinaryTree<String> five = new BinaryTree<String>("5");
        BinaryTree<String> two = new BinaryTree<String>("2");

        /* build the actual tree structure */
        BinaryTree<String> plus = new BinaryTree<String>(PLUS, four, three);
        BinaryTree<String> minus = new BinaryTree<String>(MINUS, ten, five);
        BinaryTree<String> times = new BinaryTree<String>(TIMES, plus, minus);
        BinaryTree<String> divide = new BinaryTree<String>(DIVIDE, times, two);

        /* what does it look like? */
        System.out.println(divide.treeString());

        /* what's the answer */
        System.out.println(evaluate(divide));
    }
}
