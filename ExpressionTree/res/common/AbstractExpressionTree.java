package common;

import java.util.Stack;
public class AbstractExpressionTree {

  private ExpressionTreeHelper helper = new ExpressionTreeHelper();

  public TreeNode postOrderTraversal(String postfixExpr){
    try {
      Stack<TreeNode> stack = new Stack();
      TreeNode node, leftNode, rightNode;
      // Traverse through every character of
      // input expression
      for (String op : postfixExpr.trim().split("\\s+")) {
        // If operand, simply push into stack
        if (!helper.isOperator(op)) {
          node = new TreeNode(op);
          stack.push(node);
        } else { // operator
          node = new TreeNode(op);
          // Pop two top nodes
          // Store top
          rightNode = stack.pop();      // Remove top
          leftNode = stack.pop();
          //  make them children
          node.right = rightNode;
          node.left = leftNode;
          // Add this subexpression to stack
          stack.push(node);
        }
      }
      //  only element will be root of expression
      // tree
      node = stack.peek();
      stack.pop();
      if(!stack.isEmpty()){
        throw new IllegalArgumentException("Invalid expression");
      }
      return node;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid expression");
    }
  }
}
