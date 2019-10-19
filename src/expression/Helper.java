package expression;

/**
 * Helper class that implements all the helper methods for the expresion tree.
 */
public class Helper {


  enum Operator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*");

    // declaring private variable for getting values
    private String operator;

    /**
     * gives the symbol for the operand.
     *
     * @return the character associated with the enum.
     */
    public String getOperator() {
      return this.operator;
    }

    // enum constructor
    Operator(String operator) {
      this.operator = operator;
    }
  }

  // A utility function to check if 'c'
  // is a valid operator.
  protected boolean isOperator(String c) {
    if (c.equals(Operator.ADD.getOperator()) || c.equals(Operator.SUBTRACT.getOperator())
            || c.equals(Operator.MULTIPLY.getOperator())) {
      return true;
    }
    return false;
  }

  // for checking the validity of the postfix expr
  // empty string
// null
  // space only
  // space with only one operand and no sign
 protected boolean isValidExpr(String expr) {
    return true;
 }
}
