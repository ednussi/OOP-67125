import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * SpecialShip.java is a class representing a ship,
 * which was trained in the special SpaceShip center and was rewarded
 * with best score in all simulations.
 * @author ednussi
 */
public class SpecialShip extends SpaceShip {
	
	// Initializing parameters 
	private boolean SPECIL_INITALL_STATE = true;
	private int _turn; 
	private boolean _accelState;
	
	private final double DISTANCE_TO_TELEPORT = 0.05;
	private final double ANGLE_TO_TELEPORT = 0.95 * Math.PI;
	private final double DISTANCE_TO_SHIELD = 0.1;
	private final double ANGLE_TO_FIRE = 0.05;
	
	/**
	 * SpecialShip Constructor 
	 */
	public SpecialShip(){
		this._turn = 0;
		this._accelState = SPECIL_INITALL_STATE;
	}
	
	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		this.startRoundShieldOff();
	
		// teleport logic
		if (this.getClosestShipDistance(game) < DISTANCE_TO_TELEPORT
				&& Math.abs(this.getClosestShipAngle(game))
				> ANGLE_TO_TELEPORT){
			this.teleport();
		}

		// movement logic
		if (this.getClosestShipAngle(game) == 0){
			this._turn = 0;
		}
		if (this.getClosestShipAngle(game) > 0){
			this._turn = 1;
		}
		if (this.getClosestShipAngle(game) < 0){
			this._turn = -1;
		}
		// the move
		this.getPhysics().move(this._accelState, this._turn);
		
		//shield logic
		if (this.getClosestShipDistance(game) <= DISTANCE_TO_SHIELD){
			this.shieldOn();
		}
		
		// fire shot logic
		this.increaseShotIntervalCount();
		if ((Math.abs(this.getClosestShipAngle(game)) <= ANGLE_TO_FIRE)
				&& (this.getShotInterval())){
			this.fire(game);
		}
		this.chargeEnergy();
	}

	/**
	 * Gets the image of this ship - with or without the shield
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		if (this.get_shield()) {
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		} 
		else {
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
		}
	}
}
