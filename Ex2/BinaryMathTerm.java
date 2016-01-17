/**
 *This class represents a term composed of exactly two independent math
 * terms. Like MathTerm, this class functions as a base class and 
 * is not instantiated.
 * @author ednussi
 */
public class BinaryMathTerm extends MathTerm {
	
	protected MathTerm _firstTerm = null;
	protected MathTerm _secondTerm = null;
	
	/**
	 * Constructs an new BinaryMathTerm
	 * @param firstTerm - First term
	 * @param secondTerm - Second term
	 */
	public BinaryMathTerm(MathTerm firstTerm, MathTerm secondTerm) {
		this._firstTerm = firstTerm;
		this._secondTerm = secondTerm;
	}

	/**
	 * Unimplemented in this class.
	 * @Override toLatex in class MathTerm
	 */
	public java.lang.String toLatex(){
		return "";
	}
	
}
