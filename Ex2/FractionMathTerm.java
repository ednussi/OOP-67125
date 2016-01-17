/**
 * This class represents a special case of binary math term. 
 * It is rendered as a fraction
 * @author ednussi
 */
public class FractionMathTerm extends BinaryMathTerm {
	
	private MathTerm _firstTerm;
	private MathTerm _secondTerm;
	
	/**
	 * Constructs a new Fraction term.
	 * @param firstTerm - Term on the numerator ("Mone").
	 * @param secondTerm - Term on the denominator ("Mechane").
	 */
	public FractionMathTerm(MathTerm firstTerm, MathTerm secondTerm){
		super(firstTerm, secondTerm);
		this._firstTerm = firstTerm;
		this._secondTerm = secondTerm;
	}
	
	/**
	 * Generates the latex representation of this fraction math term.
	 * @Override toLatex in class BinaryMathTerm
	 * @return latex representation of this math term
	 */
	public java.lang.String toLatex(){
		return  this.updateingTerm("\\frac{ "  + this._firstTerm.toLatex() + " }{ "
	+ this._secondTerm.toLatex() + " }");
	}
}
