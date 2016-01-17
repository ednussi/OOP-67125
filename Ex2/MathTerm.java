/**
 *This class represents the base class for all other MathTerms classes
 * It defines the interface for all other math terms which 
 * extends it and implement the method "toLatex" 
 * This class is instantiated only by classes which extends it.
 * @author ednussi
 */
public class MathTerm extends java.lang.Object{

	private MathTerm _exponentTerm;
	private boolean _isBarred;
	private boolean _isNegated;
	
	/**
	 * Default constructor.
	 */
	public MathTerm(){
		this._exponentTerm = null;
		this._isBarred = false;
		this._isNegated = false;
	}
	
	/**
	 * This method gets a math term to be placed as an exponent for 
	 * the current math term.
	 * @param exponentTerm -  The MathTerm to be placed as an exponent 
	 * of the current term.
	 */
	public void setExponentTerm(MathTerm exponentTerm){
		this._exponentTerm = exponentTerm;
	}

	/**
	 * Returns the exponent math term.
	 * @return The exponent MathTerm of this term.
	 */
	public MathTerm getExponentTerm(){
		return this._exponentTerm;
	}
	
	/**
	 * Setting whether this MathTerm should be barred or not.
	 * @param isBarred - true if we want this term to be barred.
	 */
	public void setIsBarred(boolean isBarred){
		this._isBarred = isBarred;
	}

	/**
	 * isBarred getter.
	 * @return returns whether this math term was set to be barred.
	 */
	public boolean getIsBarred(){
		return this._isBarred;
	}

	/**
	 * Sets whether this math term should be negated.
	 * @param isNegated - true if we want this term to be negated
	 */
	public void setIsNegated(boolean isNegated){
		this._isNegated = isNegated;
	}
	
	/**
	 * isNegated getter.
	 * @return returns whether this math term was set to be negated.
	 */
	public boolean getIsNegated(){
		return this._isNegated;
	}

    /**
     * Creates the final math term with the additional options.
     * Implementing the hierarchy of the operations of
     * exponent, barred and negated.
     * @param _updatingTerm - the current term to be checked
     *        and added the MathTerm Metheods
     * @return the new updated string
     */
    protected String updateingTerm(String _updatingTerm) {
        if (this.getExponentTerm() != null){
            _updatingTerm += "^{ " + this.getExponentTerm().toLatex() + " }";
        }
        if (this.getIsBarred() == true){
            _updatingTerm = "\\overline{ " + _updatingTerm + " }";
        }

        if (this.getIsNegated() == true){
            _updatingTerm = "\\neg" + "{ " + _updatingTerm +" }";
        }

        return _updatingTerm;
    }
	/**
	 * This method is implemented in the MathTerm derivatives
	 */
	public java.lang.String toLatex(){
		return ""; 
	}

}

