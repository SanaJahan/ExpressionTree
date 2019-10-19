package expression;

/**
 * Representing a single node in the expression tree.
 */
public final class TreeNode {
  String value;
  TreeNode left, right;

  TreeNode(String item) {
    value = item;
    left = right = null;
  }
}
