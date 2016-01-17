/**
 * This class represents a simple operation between two other terms.
 * For example, "a+b", "c*d" or "c=d","x
 * @author ednussi
 */
public class SimpleBinaryOpMathTerm extends BinaryMathTerm {
	
	private MathTerm _firstTerm;
	private MathTerm _secondTerm;
	private char _sign;
	
	/**
	 * Instantiate a new SimpleBinaryOpMathTerm.
	 * @param firstTerm - The first term of the binary operation.
	 * @param secondTerm - The second term of the binary operation.
	 * @param sign - The operation sign.
	 */
	public SimpleBinaryOpMathTerm(MathTerm firstTerm, 
			MathTerm secondTerm, char sign) {
		super(firstTerm, secondTerm);
		this._sign = sign;
		this._firstTerm = firstTerm;
		this._secondTerm = secondTerm;

	}	
	
	/**
	 * Generates the latex representation of this arithmetic 
	 * operation math term.
	 * @Override toLatex in class BinaryMathTerm
	 * @return latex representation of the operation.
	 */
	public java.lang.String toLatex(){
		// in case sign is '*' needs special implementation
		if (this._sign == '*') {
			return this.updateingTerm(this._firstTerm.toLatex() + " \\cdot "
		+ this._secondTerm.toLatex());
		}
		else {
			return this.updateingTerm(this._firstTerm.toLatex() + this._sign
					+ this._secondTerm.toLatex());
		}
	}

}
