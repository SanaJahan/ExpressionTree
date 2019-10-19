import org.junit.Before;
import org.junit.Test;

import expression.Expression;
import expression.ExpressionTree;

import static org.junit.Assert.assertEquals;

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
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleEmptyString() {
    postfixExpr = new ExpressionTree("");
  }

  // null
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleNullPassToConstructor() {
    postfixExpr = new ExpressionTree(null);
  }

  // space only
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleSpaceAsStringParameterToConstructor() {
    postfixExpr = new ExpressionTree(" ");
  }

  // space with only one operand and no sign
  @Test
  public void shouldHandleOneOperand() {
    postfixExpr = new ExpressionTree(" 4");
  }

  // space with only one operand and no sign
  @Test
  public void shouldHandleOneNegativeOperand() {
    postfixExpr = new ExpressionTree(" -4");
  }

  // space with one operand and one operator
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleOneOperandAndOneOperator() {
    postfixExpr = new ExpressionTree(" 4 +");
  }

  // an infix expr
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleInfixExpr() {
    postfixExpr = new ExpressionTree(" 4 + 3 ");
  }

  // a prefix expr
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandlePrefixExpr() {
    postfixExpr = new ExpressionTree(" + 4 3 ");
  }

  // a postfix valid expr with lots of trailing spaces here and there
  @Test
  public void shouldHandleValidPostfixWithSpaces() {
    postfixExpr = new ExpressionTree(" 3    5   + 4 - ");

    String expected = "( 4 - ( 3 + 5 ) )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(- (+ 4 5))";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }

  // a postfix good looking expr with one operator and two operand
  @Test
  public void shouldHandlePostfixExprWithOneOperatorTwoOperand() {
    postfixExpr = new ExpressionTree("2 5 + ");

    String expected = "( 2 + 5 )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(+ 2 5)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }

  // a postfix with one operator and three operand
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleOneOperatorAndThreeOperand() {
    postfixExpr = new ExpressionTree(" 2 5 3 + ");
  }

  // a postfix with 2 operators and one operand
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleTwoOperatorsAndOneOperand() {
    postfixExpr = new ExpressionTree(" 2 + - ");
  }

  // a perfect postfix expr
  @Test
  public void shouldHandleValidPostfixExpr() {
    String expected = "( -4.5 + ( 1.2 * 5.4 ) )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(+ (* 1.2 5.4) -4.5)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }

  // postfix expr with divide
  @Test
  public void shouldHandleExprWithAllOperators() {
    postfixExpr = new ExpressionTree(" 4 5 + 2 * 6 /");

    String expected = "( 6 / ( 2 * ( 4 + 5 ) ) )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(/ (* (+ 4 5) 2) 6)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }



}
