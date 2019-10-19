package expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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
    helper = new Helper();
    if(helper.isValidExpr(postfixExpr)){
      root = constructTree(postfixExpr);
    }
     else {
      throw new IllegalArgumentException("Invalid expression");
    }
  }


  private TreeNode constructTree(String postfixExpr) throws IllegalArgumentException {

    try {

        Stack<TreeNode> stack = new Stack();
        TreeNode node, leftNode, rightNode;
        helper = new Helper();
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


  @Override
  public double evaluate() {
    return helper.calculate(root);
  }

  @Override
  public String infix() {
    List<String> infixExpr = new ArrayList<>();

    Collection<String> s = Collections.singleton(Arrays.asList(helper.inOrder(root, infixExpr)).stream()
            .map(Object::toString)
            .collect(Collectors.joining(" ")).replace(",", ""));
    return s.toString().replace("[", "").replace("]", "");
  }


  @Override
  public String schemeExpression() {
    List<String> prefixExpr = new ArrayList<>();
    Collection<String> s = Collections.singleton(Arrays.asList(helper.preOrder(root, prefixExpr)).stream()
            .map(Object::toString)
            .collect(Collectors.joining(" ")).replace(",", ""));
    return s.toString().replace("[", "").replace("]", "").replace("( ", "(").replace(" )", ")");
  }
}
