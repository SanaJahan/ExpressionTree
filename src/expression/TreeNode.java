package expression;

/**
 * Representing a single node in the expression tree.
 */
public class TreeNode {
  char value;
  TreeNode left, right;

  TreeNode(char item) {
    value = item;
    left = right = null;
  }
}
