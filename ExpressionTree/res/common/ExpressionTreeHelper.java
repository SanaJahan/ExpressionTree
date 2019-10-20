package common;

import java.util.List;
import java.util.Optional;

import intervals.Interval;


/**
 * Helper class that implements all the helper methods for the expresion tree.
 */
public class ExpressionTreeHelper {

    protected enum Operator {
      ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), INTERSECTION("I"), UNION("U");

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
    public boolean isOperator(String c) {
      return c.equals(Operator.ADD.getOperator()) || c.equals(Operator.SUBTRACT.getOperator())
              || c.equals(Operator.MULTIPLY.getOperator()) ||
              c.equals(Operator.DIVIDE.getOperator()) ||
              c.equals(Operator.UNION.getOperator()) || c.equals(Operator.INTERSECTION.getOperator());
    }

    // for checking the validity of the postfix expr
    public boolean isValidExpr(String expr) {
      return !(expr == null || expr.isBlank()  || expr.isEmpty()|| expr.equals(" "));
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
        case "*":
          return left_val * right_val;
        case "/":
          return left_val / right_val;
        default:
          throw new IllegalArgumentException("Invalid expression");
      }
    }

  /**
   * This helper method performs the evaluation of the tree.
   * @param root This is the current root in the tree or subtree considered.
   * @return The calculated Interval after Intersection or Union operation.
   * @throws IllegalArgumentException Thrown if the interval or operator are invalid.
   */
  public Interval calculateInterval(TreeNode root) throws IllegalArgumentException{
    String[] parse;
    int start,end;
    // empty tree
    if (root == null) {
      return new Interval(0, 0);
    }
    // leaf node i.e, an integer
    if (root.left == null && root.right == null) {
      parse = root.value.split(",");
      if (parse.length == 2) {
        start = Integer.parseInt(parse[0]);
        end = Integer.parseInt(parse[1]);
        return new Interval(start, end);
      }
      else {
        throw new IllegalArgumentException();
      }
    }
    // Evaluate left subtree
    Interval left_val = calculateInterval(root.left);

    // Evaluate right subtree
    Interval right_val = calculateInterval(root.right);

    switch (root.value) {
      case "I":
        return left_val.intersect(right_val);
      case "U":
        return left_val.union(right_val);
      default:
        throw new IllegalArgumentException();
    }
  }
    // Utility function to do inorder traversal
    public List inOrder(TreeNode root, List infixExpr) {
      if (root == null)
        return infixExpr;

      if(!(root.left==null && root.right==null))
        infixExpr.add("(");

      /* first recur on right child */
      inOrder(root.right,infixExpr);


      /* then print the data of node */
      infixExpr.add(root.value);


      /* now recur on left child */
      inOrder(root.left,infixExpr);
      if(!(root.left==null && root.right==null))
        infixExpr.add(")");
      return infixExpr;
    }

    // UTILITY FUNCTION TO DO PRE-ORDER TRAVERSAL root-right-left
    public List preOrder(TreeNode root, List prefixExpr) {
      if (root == null)
        return prefixExpr;
      if(!(root.left==null && root.right==null))
        prefixExpr.add("(");
      /* first print data of node */
      prefixExpr.add(root.value);
      /* then recur on left subtree */
      preOrder(root.left,prefixExpr);

      /* now recur on right subtree */
      preOrder(root.right,prefixExpr);
      if(!(root.left==null && root.right==null))
        prefixExpr.add(")");
      return prefixExpr;
    }

}
