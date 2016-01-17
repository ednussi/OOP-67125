import java.awt.Image;
import oop.ex3.*;

/**
 * SpaceShip.java is an abstract class.
 * It is the base class for the other spaceships.
 * @author ednussi
 */

public abstract class SpaceShip {
	
	// Initializing parameters 
	private int _maxEnergy;
	private int _currentEnergy;
	private int _healthLevel;
	private boolean _shield;
	private SpaceShipPhysics _SpaceShipPhysics;
	private int _shotIntervalCount;
	
	protected final int INITIAL_MAX_ENERGY = 200;
	protected final int INITIAL_START_ENERGY = 200;
	protected final int INITIAL_HEALTH_LEVEL = 20;
	protected final boolean INITIAL_SHIELD_POSITION = false;
	protected final int TELEPORT_COST = 150;
	protected final int SHIELD_COST = 3;
	protected final int SHOT_COST = 20;
	protected final int REDUCE_ENERGY_FROM_HIT = 10;
	protected final int REDUCE_ENERGY_FROM_COLLISION = 10;
	protected final int REDUCE_HEALTH_FROM_HIT = 1;
	protected final int REDUCE_HEALTH_FROM_COLLISION = 1;
	protected final int ADDITIONAL_ENERGY_FROM_BASHING = 20;
	protected final int SHOT_INTERVAL_MINIMUM = 8;
	protected final int REPLANISHED_ENERGY_PER_ROUND = 1;
	
	/**
	 * SpaceShip Constructor
	 */
	protected SpaceShip(){
		this._maxEnergy = INITIAL_MAX_ENERGY;
		this._currentEnergy = INITIAL_START_ENERGY;
		this._healthLevel = INITIAL_HEALTH_LEVEL;
		this._SpaceShipPhysics = new SpaceShipPhysics();
		this._shield = INITIAL_SHIELD_POSITION;
		this._shotIntervalCount = SHOT_INTERVAL_MINIMUM;
	}
	   
    /**
     * Does the actions of the ship for a specific round. 
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
	protected abstract void doAction(SpaceWars game);
    

    /**
     * This method is called every time a collision with this ship occurs 
     */
	protected void collidedWithAnotherShip(){
    	if (this._shield == false){
    		this.reduceMaxEnergy(REDUCE_ENERGY_FROM_COLLISION);
        	this.reduceHealth(REDUCE_HEALTH_FROM_COLLISION);
    	} 
    	else {
    		this._currentEnergy += ADDITIONAL_ENERGY_FROM_BASHING;
    		this._maxEnergy += ADDITIONAL_ENERGY_FROM_BASHING;
    	}
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
	protected void reset(){
		this._maxEnergy = INITIAL_MAX_ENERGY;
		this._currentEnergy = INITIAL_START_ENERGY;
		this._healthLevel = INITIAL_HEALTH_LEVEL;
		this._SpaceShipPhysics = new SpaceShipPhysics();
		this._shield = INITIAL_SHIELD_POSITION;
    }

    /**
     * Checks if this ship is dead.
     * @return true if the ship is dead. false otherwise.
     */
	protected boolean isDead() {
    	if (this._healthLevel == 0) {
    		return true;
    	}
    	else {
        return false;
    	}
    }

    /**
     * Gets the physics object that controls this ship.
     * @return the physics object that controls the ship.
     */
	protected SpaceShipPhysics getPhysics() {
        return this._SpaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
	protected void gotHit() {
    	if (this._shield == false){
    		this.reduceMaxEnergy(REDUCE_ENERGY_FROM_HIT);
    		this.reduceHealth(REDUCE_HEALTH_FROM_HIT);
    	}  	
    }

    /**
     * Gets the image of this ship - returns the image of the 
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * @return the image of this ship.
     */
	protected abstract Image getImage();

    /**
     * Attempts to fire a shot.
     * @param game the game object.
     */
	protected void fire(SpaceWars game) {
    	if (this._currentEnergy >= SHOT_COST){
        	game.addShot(this._SpaceShipPhysics);
        	this.reduceEnergy(SHOT_COST);
        	this._shotIntervalCount = 0; 
    	}
    }

    /**
     * Attempts to turn on the shield.
     */
	protected void shieldOn() {
    	if (this._currentEnergy >= SHIELD_COST){
    	this.reduceEnergy(SHIELD_COST);
    	this._shield = true;
    	}
    }

    /**
     * Attempts to teleport.
     */
	protected void teleport() {
    	if (this._currentEnergy >= TELEPORT_COST){
    		this.reduceEnergy(TELEPORT_COST);
    		this._SpaceShipPhysics = new SpaceShipPhysics();
    	}
    }
    
    /**
     * Reduces to a non negative your energy
     * @param i - the energy to be reduced
     */
    private void reduceEnergy(int i) {
    	if (this._currentEnergy < i){
    		this._currentEnergy = 0;
    	}
    	else {
    		this._currentEnergy -= i;
    	}
    }
    
    /**
     * Reduces to a non negative your max energy
     * updates current energy if necessary (current <= max)
     * @param i - the energy to be reduced
     */
    private void reduceMaxEnergy(int i) {
    	if (this._maxEnergy < i){
    		this._maxEnergy = 0;
    		this._currentEnergy = 0;
    	}
    		else {
    			if (this._currentEnergy < this._maxEnergy - i){
    			this._maxEnergy -= i;
    			}
    		else {
    			this._maxEnergy -= i;
    			this._currentEnergy = this._maxEnergy;
    	}
    		}
    }
    
    /**
     * Reduces to a non negative your health
     * @param i - the health to be reduced
     */
    private void reduceHealth(int i) {
    	if (this._healthLevel < i){
    		this._healthLevel = 0;
    	}
    		else {
    			this._healthLevel -= i;
    		}
    }
    
    /**
     * charges energy each round
     */
    protected void chargeEnergy(){
    	if ((this._maxEnergy > 0) && (this._currentEnergy < this._maxEnergy)){
    		if (this._currentEnergy + REPLANISHED_ENERGY_PER_ROUND 
    				>= this._maxEnergy ){
    			this._currentEnergy = this._maxEnergy;
    		}
    		else {
        		this._currentEnergy += REPLANISHED_ENERGY_PER_ROUND;
    		}
    	}
    }
    
    /**
     * turns shield off each round
     */
    protected void startRoundShieldOff(){
    	this._shield = false;
    }
    
    /**
     * Increases Shot interval count each round
     */
    protected void increaseShotIntervalCount(){
    	this._shotIntervalCount++;
    }
    
    /**
     * Checks if the time interval between shots has passes
     * @return true if it has, otherwise false
     */
    protected boolean getShotInterval(){
    	if (this._shotIntervalCount >= SHOT_INTERVAL_MINIMUM){
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    /**
     * Checks what is the distance from the closest ship
     * @param game the game object to which this ship belongs.
     * @return the distance from the closest ship 
     */
    protected double getClosestShipDistance(SpaceWars game){
    	return this.getPhysics().distanceFrom(
    			game.getClosestShipTo(this).getPhysics());
    }
    
    /**
     * Checks what is the angle from the closest ship
     * @param game the game object to which this ship belongs.
     * @return the angle from the closest ship
     */
    protected double getClosestShipAngle(SpaceWars game){
    	return this.getPhysics().angleTo(
				game.getClosestShipTo(this).getPhysics());
    }

    /**
     * Max energy getter
     * @return the max energy
     */
    protected int get_maxEnergy() {
		return _maxEnergy;
	}

    /**
     * Current energy getter
     * @return the current energy
     */
    protected int get_currentEnergy() {
		return _currentEnergy;
	}

    /**
     * HealthLevel getter
     * @return the healthLevel
     */
    protected int get_healthLevel() {
		return _healthLevel;
	}

    /**
     * shield state getter
     * @return the shield state
     */
    protected boolean get_shield() {
		return _shield;
	}
    
}