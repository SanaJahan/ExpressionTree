package expression;

/**
 * Helper class that implements all the helper methods for the expresion tree.
 */
public class Helper {


  enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*');

    // declaring private variable for getting values
    private char operator;

    /**
     * gives the symbol for the operand.
     * @return the character associated with the enum.
     */
    public char getOperator() {
      return this.operator;
    }

    // enum constructor
    Operator(char operator) {
      this.operator = operator;
    }
  }

  // A utility function to check if 'c'
  // is a valid operator.
  protected boolean isOperator(char c) {
    if (c == Operator.ADD.getOperator() || c == Operator.SUBTRACT.getOperator()
            || c == Operator.MULTIPLY.getOperator()) {
      return true;
    }
    return false;
  }
}
