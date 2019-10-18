import org.junit.Before;

import expression.Expression;
import expression.ExpressionTree;

/**
 * unit tests for the operations performed in the expression tree class.
 */
public class ExpressionTreeTest {

  private Expression postfixExpr;

  @Before
  public void setUp() {
    postfixExpr = new ExpressionTree("1.2 5.4 *   -4.5 + ");
  }

  // type of inputs to be handled
  // empty string
  // null
  // space only
  // space with only one operator and no sign
  // space with one operand and no operator
  // an infix expr
  // a prefix expr
  // a postfix valid expr with lots of trailing spaces here and there
  // a postfix good looking expr with one operator and two operand
  // a postfix with one operator and three operand
  // a postfix with 2 operators and one operand
  // a perfect postfix expr

}
