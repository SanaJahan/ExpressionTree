package common;

import java.util.Stack;

public class AbstractExpressionTree {
  ExpressionTreeHelper helper;

  protected TreeNode postOrderTraversal(String postfixExpr) {
    try {
      Stack<TreeNode> stack = new Stack();
      TreeNode node, leftNode, rightNode;
      helper = new ExpressionTreeHelper();
      // Traverse through every character of
      // input expression
      if (helper.isValidExpr(postfixExpr)) {
        for (String op : postfixExpr.trim().replaceAll("\\s+,",",").split("\\s+")) {
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
      }
      node = stack.peek();
      stack.pop();
      if(!stack.isEmpty()){
        throw new IllegalArgumentException("Invalid expression");
      }
      return node;
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid expression");
    }
  }

  protected boolean isOperator(String c) {
    return c.equals(ExpressionTreeHelper.Operator.ADD.getOperator()) || c.equals(ExpressionTreeHelper.Operator.SUBTRACT.getOperator())
            || c.equals(ExpressionTreeHelper.Operator.MULTIPLY.getOperator()) || c.equals(ExpressionTreeHelper.Operator.DIVIDE.getOperator())
            || c.equals(ExpressionTreeHelper.Operator.INTERSECTION.getOperator())
            || c.equals(ExpressionTreeHelper.Operator.UNION.getOperator());
  }

  protected boolean isValidExpr(String expr) {
    return !(expr == null || expr.equals("")  || expr.isEmpty()|| expr.equals(" "));
  }
}