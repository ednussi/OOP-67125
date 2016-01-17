import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * AggressiveShip.java is a class representing a ship,
 * which pursues other ships and tries to fire at them.
 * @author ednussi
 */
public class AggressiveShip extends SpaceShip {

	// Initializing parameters 
	private final boolean AGGRESSIVE_ACCEL_STATE = true;
	private int _turn; 
	private boolean _accelState;
	
	private final double ANGLE_TO_FIRE = 0.2;
	
	/**
	 * AggressiveShip Constructor
	 */
	public AggressiveShip(){
		this._turn = 0;
		this._accelState = AGGRESSIVE_ACCEL_STATE;
		
	}


	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		
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
		
		// shooting logic
		this.increaseShotIntervalCount();
		if ((Math.abs(this.getClosestShipAngle(game)) < ANGLE_TO_FIRE)
				&& (this.getShotInterval())){
			this.fire(game);
		}
		this.chargeEnergy();
	}
	
	/**
	 * Gets the image of this ship
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
