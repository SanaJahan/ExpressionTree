package intervals;

import tree.AbstractExpressionTree;
import tree.ExpressionTreeHelper;
import tree.TreeNode;

/**
 * This class contains the evaluate and textTree methods implemented from the Intervals interface.
 */
public class IntervalTree extends AbstractExpressionTree implements Intervals {
  private TreeNode root;
  public ExpressionTreeHelper helper = new ExpressionTreeHelper();

  /**
   * This is the constructor that takes the postfix expression as a string,
   * parses it and constructs the Interval Tree.
   * @param postfixExpr The postfix expression taken as a string.
   */
  public IntervalTree(String postfixExpr) throws IllegalArgumentException {
    root = super.postOrderTraversal(postfixExpr);
  }

  /**
   * Evaluate this expression of intervals and return the resulting interval.
   * @return the result as an Interval object.
   */
  @Override
  public Interval evaluate() {
    return helper.calculateInterval(root);
  }

  /**
   * Return a string-representation of this interval.
   * @return a string of the format start,end.
   */
  @Override
  public String textTree() {
    return helper.generateTextTree(root);
  }
}
