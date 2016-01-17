/**
 * This class represents a mathematical sum. 
 * It comprised of 3 MathTerms: 
 * - Term beneath the sigma sign.
 * - Term above the sigma sign.
 * - Term being summed.
 * @author ednussi
 */
public class SumMathTerm extends MathTerm {

	private MathTerm _subTerm;
	private MathTerm _superTerm;
	private MathTerm _sumTerm;
	
	/**
	 * Instantiate a new SumMathTerm
	 * @param subTerm - The term beneath the sigma.
	 * @param superTerm - The term above the sigma.
	 * @param sumTerm - The summed term.
	 */
	public SumMathTerm(MathTerm subTerm, MathTerm superTerm, 
			MathTerm sumTerm){
		this._subTerm = subTerm;
		this._superTerm = superTerm;
		this._sumTerm = sumTerm;
	}
	
	/**
	 * Generates the latex representation of this sum math term. 
	 * @Override toLatex in class BinaryMathTerm
	 * @return latex representation of the operation.
	 */
	public java.lang.String toLatex(){
		return this.updateingTerm("\\sum_{ " + this._subTerm.toLatex() + " }^{ "
	+ this._superTerm.toLatex() + " }{ " + this._sumTerm.toLatex() + " }");
	}

}
