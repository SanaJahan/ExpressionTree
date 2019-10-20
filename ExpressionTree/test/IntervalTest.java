import org.junit.Before;
import org.junit.Test;


import intervals.Interval;
import intervals.Intervals;

import static org.junit.Assert.assertEquals;


/**
 * This is the tester class that contains the test cases for the Interval Tree class.
 */
public class IntervalTest {

  private Intervals expr;

  @Before
  public void setUp() {
    expr = new Interval("1,4 2,5 U");
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyStringInput() {
    expr = new Interval("");
    expr.evaluate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInput() {
    expr = new Interval(null);
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void onlySpacesInput() {
    expr = new Interval(" ");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void halfAnInterval() {
    expr = new Interval("4,");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void singleInterval() {
    expr = new Interval("4,1");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void singleIntervalWithComma() {
    expr = new Interval("4,1,");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithNoSpace() {
    expr = new Interval("4,12,1");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithNoOperator() { //doubt with this one, make more multiples
    expr = new Interval("4,1 2,1");
    expr.evaluate();
  }

  @Test
  public void multipleIntervalsWithOperatorOne() {
    expr = new Interval("0,4 1,2 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(1,2);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test (expected = IllegalArgumentException.class)
  public void multipleIntervalsWithTooManyOperators() {
    expr = new Interval("4,1 2,1 I U");
    expr.evaluate();
  }

  @Test
  public void multipleIntervalsWithOperatorTwo() {
    expr = new Interval("3,4 5,6 -7,3 U I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(3,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorThree() { //with more spaces
    expr = new Interval("-4,4 2,5 U  -1,4 I ");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(-1,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorFour() {
    expr = new Interval("3,7 2,6 4,10 I U");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(3,7);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void multipleIntervalsWithOperatorFive() {
    expr = new Interval("3,10 5,12 U 4,4 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(4,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test (expected = IllegalArgumentException.class)
  public void intervalsWithGreaterStart() {
    expr = new Interval("11,10 5,12 U 4,4 I");
    expr.evaluate();

  }

  @Test (expected = IllegalArgumentException.class)
  public void differentOperatorLetter() {
    expr = new Interval("3,10 5,12 U 4,4 J");
    expr.evaluate();
  }

  @Test (expected = IllegalArgumentException.class)
  public void lowercaseOperators() {
    expr = new Interval("11,10 5,12 u 4,4 i");
    expr.evaluate();
  }

  @Test
  public void intervalsWithSpaceInBetween() {
    expr = new Interval("7 ,10 5,12 U 4,13 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(5,12);

    assertEquals(expectedOutput,actualOutput);

  }

  @Test (expected = IllegalArgumentException.class)
  public void intervalsWithSpaceInBetweenAndGreaterStart() {
    expr = new Interval("7 ,10 5,12 U 4,4 I");
    Interval actualOutput = expr.evaluate();
    Interval expectedOutput = new Interval(5,4);

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void equalsTestOne() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,5);

    boolean actualOutput = intervalOne.equals(intervalTwo);
    boolean expectedOutput = true;

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void equalsTestTwo() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,5);

    boolean actualOutput = intervalTwo.equals(intervalOne);
    boolean expectedOutput = true;
    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void equalsTestThree() {
    Interval intervalOne = new Interval(4,5);
    Interval intervalTwo = new Interval(4,7);

    boolean actualOutput = intervalOne.equals(intervalTwo);
    boolean expectedOutput = false;

    assertEquals(expectedOutput,actualOutput);
  }

  @Test
  public void equalsTestFour() {
    Interval intervalOne = new Interval(3,5);
    Interval intervalTwo = new Interval(4,7);

    boolean actualOutput = intervalTwo.equals(intervalOne);
    boolean expectedOutput = false;

    assertEquals(expectedOutput,actualOutput);

  }
}
