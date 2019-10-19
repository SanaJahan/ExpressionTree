package intervals;
/**
 * Representing a single node in the expression tree.
 */
public final class TreeNode {
  String value;
  TreeNode left, right;

  /**
   * The constructor for the TreeNode.
   * @param item The string value of the current node.
   */
  TreeNode(String item) {
    value = item;
    left = right = null;
  }
}