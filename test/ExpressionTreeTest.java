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
    assertEquals(4,postfixExpr.evaluate(),6);
  }

  // space with only one operand and no sign
  @Test
  public void shouldHandleOneNegativeOperand() {
    postfixExpr = new ExpressionTree(" -4");
    assertEquals(-4,postfixExpr.evaluate(),6);
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

    assertEquals(4.0,postfixExpr.evaluate(),6);
    String expected = "( 4 - ( 5 + 3 ) )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(- (+ 3 5) 4)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }

  // a postfix good looking expr with one operator and two operand
  @Test
  public void shouldHandlePostfixExprWithOneOperatorTwoOperand() {
    postfixExpr = new ExpressionTree("2 5 + ");

    String expected = "( 5 + 2 )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(+ 2 5)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());
  }

  // a postfix with one operator and three operand
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleOneOperatorAndThreeOperand() {
    postfixExpr = new ExpressionTree(" 2 5 3 + ");
    postfixExpr.evaluate();
  }

  // a postfix with 2 operators and one operand
  @Test(expected = IllegalArgumentException.class)
  public void shouldHandleTwoOperatorsAndOneOperand() {
    postfixExpr = new ExpressionTree(" 2 + - ");
  }

  // a perfect postfix expr
  @Test
  public void shouldHandleValidPostfixExpr() {


    // scheme expression
    String schemeExpr = "(+ (* 1.2 5.4) -4.5)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());

    //infix expression
    String expected = "( -4.5 + ( 5.4 * 1.2 ) )";
    assertEquals(expected, postfixExpr.infix());

    //evaluate postfix expression
    assertEquals(1.9800000000000004,postfixExpr.evaluate(),0);
  }

  // postfix expr with divide
  @Test
  public void shouldHandleExprWithAllOperators() {
    postfixExpr = new ExpressionTree(" 4 5 + 2 * 6 /");

    assertEquals(3.0, postfixExpr.evaluate(),0);

    String expected = "( 6 / ( 2 * ( 5 + 4 ) ) )";
    assertEquals(expected, postfixExpr.infix());

    // scheme expression
    String schemeExpr = "(/ (* (+ 4 5) 2) 6)";
    assertEquals(schemeExpr,postfixExpr.schemeExpression());

  }

  @Test
  public void treeTestThree() {
    postfixExpr = new ExpressionTree("2 3 +");
    postfixExpr.textTree();
    String actualOutput = postfixExpr.textTree();
    //Have not given the expected output yet
    String expectedOutput = "+\n"
            + "|\n"
            + "|\n"
            + "|___2\n"
            + "|\n"
            + "|___3";

    assertEquals(expectedOutput,actualOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTreeWithOneOperand() {
    postfixExpr = new ExpressionTree("2 3");
    postfixExpr.infix();
  }



}
