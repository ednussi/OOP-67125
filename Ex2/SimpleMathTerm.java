/**
 * This class represents a math term which is either a 
 * single letter variable (x,y,a,b,etc..) 
 * or a number (may be a floating point number).
 * @author ednussi
 */
public class SimpleMathTerm extends MathTerm {
	
	private boolean _hasPrecision;
	private String _termName;
	private int _precisionDigits;
	private String _currentMathTerm;
	
	/**
	 * Constructs a new instance given a simple term "name" 
	 * (which can be a variable or a number).
	 * @param termName  - A string of either a single letter variable 
	 * (x,y,z,a,b..) or a number (may be a floating point number).
	 */
	public SimpleMathTerm(java.lang.String termName){
		this._termName = termName;
		this._currentMathTerm = termName;
		this._hasPrecision = false;
	}
	
	/**
	 * Checks the given name. And determines whether it's numeric.
	 * @return true if this term represents a number.
	 */
	public boolean isNumeric(){
		return this._termName.matches("-?\\d+(\\.\\d+)?");
	}
	
	/**
	 * Sets the number of digits of precision in case this term 
	 * represents a number.
	 * @param precisionDigits  - Number of digits right of the 
	 * floating point on the latex representation.
	 */
	public void setPrecisionDigits(int precisionDigits){
		this._precisionDigits = precisionDigits;
		this._hasPrecision = true;
	}
	
	/**
	 * Generates the latex representation of the this simple math term.
	 * @Override toLatex in class MathTerm
	 * @return latex representation of the operation.
	 */
	public java.lang.String toLatex(){
		if (this.isNumeric()){
			if (this._hasPrecision){
				
				if (this._precisionDigits == 0){
					this._currentMathTerm = 
							this._currentMathTerm.substring(0,
									this._currentMathTerm.indexOf("."));
				}
				else {
				this._currentMathTerm = 
						this._currentMathTerm.substring(0,
								this._currentMathTerm.indexOf(".")
								+ this._precisionDigits + 1);
				}
			}
		}

		return this.updateingTerm(this._currentMathTerm);
	}
}
	

