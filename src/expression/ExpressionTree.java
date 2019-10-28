package expression;

import tree.AbstractExpressionTree;
import tree.ExpressionTreeHelper;
import tree.TreeNode;

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
    if (super.isValidExpr(postfixExpr)) {
      root = super.postOrderTraversal(postfixExpr);
    } else {
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

    Collection<String> s = getInfixExprList(helper.inOrder(root, infixExpr));
    return s.toString().replace("[", "")
            .replace("]", "");
  }

  @Override
  public String schemeExpression() {
    List<String> prefixExpr = new ArrayList<>();
    Collection<String> s = getInfixExprList(helper.preOrder(root, prefixExpr));
    return s.toString().replace("[", "")
            .replace("]", "")
            .replace("( ", "(")
            .replace(" )", ")");
  }

  @Override
  public String textTree() {
    return helper.generateTextTree(root);
  }

  private Collection<String> getInfixExprList(List list) {
    return Collections.singleton(Arrays.asList(list).stream()
            .map(Object::toString)
            .collect(Collectors.joining(" ")).replace(",", ""));
  }

}
