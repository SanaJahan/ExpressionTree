package expression;

import common.AbstractExpressionTree;
import common.ExpressionTreeHelper;
import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the expression as a tree.
 */
public class ExpressionTree extends AbstractExpressionTree implements Expression {
  private ExpressionTreeHelper helper = new ExpressionTreeHelper();
  private TreeNode root;

  /**
   * constructor that takes expression in postfix form, parses it and creates an expression tree
   * accordingly.
   */
  public ExpressionTree(String postfixExpr) {
    if(helper.isValidExpr(postfixExpr)){
      root = constructTree(postfixExpr);
    }
    else {
      throw new IllegalArgumentException("Invalid expression");
    }
  }


  private TreeNode constructTree(String postfixExpr) throws IllegalArgumentException {
    return super.postOrderTraversal(postfixExpr);
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
