package intervals;

/**
 * This class contains helper methods required to perform certain operations on the tree.
 */
public class IntervalOperationHelper {

  /**
   * This enum type has two enumerations namely INTERSECTION and UNION.
   */
  enum IntervalOperator {
    INTERSECTION("I"),UNION("U");
    private String operator;

    public String getOperator() {
      return this.operator;
    }

    /**
     * A constructor for the enum type class.
     * @param operator I or U
     */
    IntervalOperator(String operator) {
      this.operator = operator;
    }
  }

  /**
   * This helper method performs the evaluation of the tree.
   * @param root This is the current root in the tree or subtree considered.
   * @return The calculated Interval after Intersection or Union operation.
   * @throws IllegalArgumentException Thrown if the interval or operator are invalid.
   */
  public Interval calculate(TreeNode root) throws IllegalArgumentException{
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
    Interval left_val = calculate(root.left);

    // Evaluate right subtree
    Interval right_val = calculate(root.right);

    switch (root.value) {
      case "I":
        return left_val.intersect(right_val);
      case "U":
        return left_val.union(right_val);
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * This method chacks if the current string value of a anode is an operator.
   * @param c The I or U operator.
   * @return True or False.
   */
  protected boolean isOperator(String c) {
    if (c.equals(IntervalOperator.INTERSECTION.getOperator())
            || c.equals(IntervalOperator.UNION.getOperator())) {
      return true;
    }
    return false;
  }

  /**
   * isValidExpr checks if the expression given, is valid or not.
   * @param expr The postfix expression given as a string.
   * @return True or False.
   */
  protected boolean isValidExpr(String expr) {
    if (!(expr.equals("") || expr.equals(null) || expr.equals(" ")))
      return true;
    else
      return false;
  }
}
