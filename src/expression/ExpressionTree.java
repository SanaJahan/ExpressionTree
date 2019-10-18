package expression;

/**
 * This class represents the expression as a tree.
 */
public class ExpressionTree implements Expression {
 private String postfixExpr;

  /**
   * constructor that takes expression in postfix form, parses it and creates an expression tree accordingly.
   */
  public ExpressionTree(String postfixExpr) {


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
