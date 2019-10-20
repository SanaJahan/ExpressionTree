package res;

import java.util.List;

import intervals.Interval;
import intervals.TreeNode;

/**
 * This class contains all the helper methods required to implement an Expression tree,
 * and an interval tree.
 */
public class ExpressionTreeHelper extends AbstractExpressionTreeClass {
  enum Operator {
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

  /**
   * isOperator checks if the current node value is an operator string.
   * @param c Can be either of the enumerations.
   * @return True or False.
   */
  protected boolean isOperator(String c) {
    return super.isOperator(c);
  }

  /**
   * Checks is the current expression string is valid or not.
   * @param expr The postfix expression string.
   * @return True or False.
   */
  protected boolean isValidExpr(String expr) {
    return super.isValidExpr(expr);
  }

  /**
   * This calculate method is for evaluating an Expression tree.
   * @param root This is the current root of the tree or sub tree considered.
   * @return The double precision value result of the computation.
   */
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
   * This is a utility function for performing inorder traversal on the Expression tree.
   * @param root The current root of the tree or subtree considered.
   * @param infixExpr The infix expression.
   * @return A list of the nodes added during the inorder traversal.
   */
  protected List inOrder(TreeNode root,List infixExpr) {
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

  /**
   * This is a utility function for performing preorder traversal on the Expression tree.
   * @param root The current root of the tree or subtree considered.
   * @param prefixExpr The prefix expression.
   * @return A list of the nodes added during the preorder traversal.
   */
  protected List preOrder(TreeNode root, List prefixExpr) {
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

  /**
   * This helper method performs the evaluation of the Interval tree.
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
}
