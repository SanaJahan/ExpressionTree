package expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that implements all the helper methods for the expresion tree.
 */
public class Helper {


  enum Operator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*");

    // declaring private variable for getting values
    private String operator;

    /**
     * gives the symbol for the operand.
     *
     * @return the character associated with the enum.
     */
    public String getOperator() {
      return this.operator;
    }

    // enum constructor
    Operator(String operator) {
      this.operator = operator;
    }
  }

  // A utility function to check if 'c'
  // is a valid operator.
  protected boolean isOperator(String c) {
    if (c.equals(Operator.ADD.getOperator()) || c.equals(Operator.SUBTRACT.getOperator())
            || c.equals(Operator.MULTIPLY.getOperator())) {
      return true;
    }
    return false;
  }

  // for checking the validity of the postfix expr
  protected boolean isValidExpr(String expr) {
    if (!(expr.equals("") || expr.equals(null) || expr.equals(" ")))
      return true;
    else
      return false;
  }

  // evaluate the expression
  public double calculate(TreeNode root) {
    // empty tree
    if (root == null)
      return 0;
    // leaf node i.e, an integer
    if (root.left == null && root.right == null)
      return Double.parseDouble(root.value);

    // Evaluate left subtree
    double left_val = calculate(root.left);

    // Evaluate right subtree
    double right_val = calculate(root.right);

    switch (root.value) {
      case "+":
        return left_val + right_val;
      case "-":
        return left_val - right_val;
      case "/":
        return left_val * right_val;
      default:
        return left_val / right_val;
    }
  }

  // Utility function to do inorder traversal
  protected List inOrder(TreeNode root,List infixExpr) {
    /**
     * IMPLEMENTATION OF INFIX STARTING FROM THE RIGHT SUB TREE right-root-left
     */
    if (root == null)
      return infixExpr;

    /* first recur on left child */
    inOrder(root.right,infixExpr);

    /* then print the data of node */
    infixExpr.add(root.value);

    /* now recur on right child */
    inOrder(root.left,infixExpr);
    return infixExpr;
  }

  // UTILITY FUNCTION TO DO PRE-ORDER TRAVERSAL root-right-left
  protected List preOrder(TreeNode root, List prefixExpr) {
    if (root == null)
      return prefixExpr;

    /* first print data of node */
    prefixExpr.add(root.value);
    /* then recur on left sutree */
    preOrder(root.left,prefixExpr);

    /* now recur on right subtree */
    preOrder(root.right,prefixExpr);
    return prefixExpr;
  }
}
