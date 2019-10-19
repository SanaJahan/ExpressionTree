package expression;

import java.util.Arrays;
import java.util.Stack;

/**
 * This class represents the expression as a tree.
 */
public class ExpressionTree implements Expression {
  private Helper helper;
  private TreeNode root;

  /**
   * constructor that takes expression in postfix form, parses it and creates an expression tree
   * accordingly.
   */
  public ExpressionTree(String postfixExpr) {
    root = constructTree(postfixExpr);
  }


  private TreeNode constructTree(String postfixExpr) throws IllegalArgumentException {

    try {
      Stack<TreeNode> stack = new Stack();
      TreeNode node, leftNode, rightNode;
      helper = new Helper();
      // Traverse through every character of
      // input expression
      for (String op : postfixExpr.split("\\s+")) {
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

      return node;
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid expression");
    }

  }


  @Override
  public double evaluate() {
    return 0;
  }

  @Override
  public String infix() {
    return null;
  }

  @Override
  public String schemeExpression() {
    return null;
  }
}