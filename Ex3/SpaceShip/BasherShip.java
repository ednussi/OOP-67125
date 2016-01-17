import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * BasherShip.java is a class representing a ship,
 * which attempts to collide with other ships. It will always accelerate,
 * and constantly turn towards the closest ship.
 * If it gets close to another ship, it will turn on its shields.
 * @author ednussi
 */
public class BasherShip extends SpaceShip {

	// Initializing parameters 
	private final boolean BASHER_ACCEL_STATE = true;
	private int _turn;
	private boolean _accelState;
	
	private final double DISTANCE_TO_SHIELD = 0.2;
	
	/**
	 * BasherShip Constructor
	 */
	public BasherShip(){
		this._turn = 0;
		this._accelState = BASHER_ACCEL_STATE;
		
	}
	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		this.startRoundShieldOff();
		
		// move logic - towards closest
		if (this.getClosestShipAngle(game) == 0){
			this._turn = 0;
		}
		if (this.getClosestShipAngle(game) > 0){
			this._turn = 1;
		}
		if (this.getClosestShipAngle(game) < 0){
			this._turn = -1;
		}
		this.getPhysics().move(this._accelState, this._turn);
		
		// shield logic
		if (this.getClosestShipDistance(game) 
				<= DISTANCE_TO_SHIELD){
			this.shieldOn();
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
