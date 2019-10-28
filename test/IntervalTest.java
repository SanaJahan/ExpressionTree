import org.junit.Before;
import org.junit.Test;


import intervals.Interval;
import intervals.IntervalTree;
import intervals.Intervals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This is the tester class that contains the test cases for the Interval Tree class.
 */
public class IntervalTest {

  private Intervals expr;

  @Before
  public void setUp() {
    expr = new IntervalTree("1,4 2,5 U");
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyStringInput() {
    expr = new IntervalTree("");
    expr.evaluate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInput() {
    expr = new IntervalTree(null);
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void onlySpacesInput() {
    expr = new IntervalTree(" ");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void halfAnInterval() {
    expr = new IntervalTree("4,");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class) //should be valid
  public void singleIntervalWithGreaterStart() {
    expr = new IntervalTree("4,1");
    expr.evaluate();
  }

  @Test
  public void singleInterval() {
    expr = new IntervalTree("1,4");
    expr.evaluate();
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(1,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test (expected = IllegalArgumentException.class)
  public void doubleInterval() {
    expr = new IntervalTree("1,4 2,5");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void singleIntervalWithComma() {
    expr = new IntervalTree("4,1,");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithNoSpace() {
    expr = new IntervalTree("4,12,1");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithNoOperator() { //doubt with this one, make more multiples
    expr = new IntervalTree("1,4 1,3");
    expr.evaluate();
  }

  @Test
  public void multipleIntervalsWithOperatorOne() {
    expr = new IntervalTree("0,4 1,2 I");
    expr.evaluate();
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(1,2);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithTooManyOperators() {
    expr = new IntervalTree("1,4 0,1 I U");
    expr.evaluate();
  }

  @Test
  public void multipleIntervalsWithOperatorTwo() {
    expr = new IntervalTree("3,4 5,6 -7,3 U I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(3,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorThree() { //with more spaces
    expr = new IntervalTree("-4,4 2,5 U  -1,4 I ");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(-1,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorFour() {
    expr = new IntervalTree("3,7 2,6 4,10 I U");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(3,7);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorFive() {
    expr = new IntervalTree("3,10 5,12 U 4,4 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(4,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test (expected = IllegalArgumentException.class)
  public void intervalsWithGreaterStart() {
    expr = new IntervalTree("11,10 5,12 U 4,4 I");
    expr.evaluate();

  }

  @Test (expected = IllegalArgumentException.class)
  public void differentOperatorLetter() {
    expr = new IntervalTree("3,10 5,12 U 4,4 J");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void lowercaseOperators() {
    expr = new IntervalTree("11,10 5,12 u 4,4 i");
    expr.evaluate();
  }

  @Test
  public void intervalsWithSpaceInBetween() {
    expr = new IntervalTree("7 ,10 5,12 U 4,13 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(5,12);

    assertEquals(expectedOutput,actualOutput);

  }

  @Test (expected = IllegalArgumentException.class)
  public void intervalsWithSpaceInBetweenAndGreaterStart() {
    expr = new IntervalTree("7 ,10 5,12 U 4,4 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(5,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void equalsSymmetryTrueOne() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,5);

    boolean actualOutput = intervalOne.equals(intervalTwo);

    assertTrue(actualOutput);
  }

  @Test
  public void equalsSymmetryTrueTwo() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,5);

    boolean actualOutput = intervalTwo.equals(intervalOne);
    assertTrue(actualOutput);
  }

  @Test
  public void equalsSymmetryFalseOne() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,7);

    boolean actualOutput = intervalOne.equals(intervalTwo);

    assertFalse(actualOutput);
  }

  @Test
  public void equalsTestFalseTwo() {
    Interval intervalOne = new Interval(3,5);
    Interval intervalTwo = new Interval(4,7);

    boolean actualOutput = intervalTwo.equals(intervalOne);

    assertFalse(actualOutput);

  }

  @Test
  public void equalsReflectivity() {
    Interval intervalOne = new Interval(3,5);

    boolean actualOutput = intervalOne.equals(intervalOne);
    assertTrue(actualOutput);
  }

  @Test
  public void equalsTransitivity() {
    Interval intervalOne = new Interval(3,5);
    Interval intervalTwo = new Interval(3,5);
    Interval intervalThree = new Interval(3,5);

    boolean actualOutput1 = intervalOne.equals(intervalTwo);
    boolean actualOutput2 = intervalTwo.equals(intervalThree);
    boolean actualOutput3 = intervalOne.equals(intervalThree);

    assertTrue(actualOutput1);
    assertTrue(actualOutput2);
    assertTrue(actualOutput3);

  }

  @Test
  public void biggerExpressionTest() { //with more spaces
    expr = new IntervalTree("1,2 3,4 4,6 U 3,5 I U");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(1,5);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void treeTestOne() {
    expr = new IntervalTree("1,2 3,4 U");
    expr.textTree();
    String actualOutput = expr.textTree();
    //Have not given the expected output yet
    String expectedOutput = "";

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void treeTestTwo() {
    expr = new IntervalTree("1,2 3,4 4,6 U I");
    expr.textTree();
    String actualOutput = expr.textTree();
    //Have not given the expected output yet
    String expectedOutput = "";

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void treeTestThree() {
    expr = new IntervalTree("1,2 3,4 4,6 U 3,5 I U");
    expr.textTree();
    String actualOutput = expr.textTree();
    //Have not given the expected output yet
    String expectedOutput = "";

    assertEquals(expectedOutput,actualOutput);
  }
}
