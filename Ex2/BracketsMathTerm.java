/**
 * This class represents a math term between brackets
 * @author ednussi
 */
public class BracketsMathTerm extends MathTerm {
	private MathTerm _internalTerm;

	/**
	 * The constructor receives the MathTerm they will be rendered as the 
	 * term inside the brackets.
	 * @param internalTerm - The term that resides within the brackets.
	 */
	public BracketsMathTerm(MathTerm internalTerm){
		this._internalTerm = internalTerm;
	}
	
	/**
	 * Generates the latex representation of for this bracket math term.
	 * @Override toLatex in class MathTerm
	 * @return latex representation of this math term
	 */
	public java.lang.String toLatex(){
		return this.updateingTerm("\\left( " +  this._internalTerm.toLatex()
                + " \\right)");
	}
}
