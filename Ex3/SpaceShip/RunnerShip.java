import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * RunnerShip.java is a class representing a ship,
 * which attempts to run away from the fight. It always accelerates,
 * and constantly turn away from the closest ship. 
 * If it gets too close to a ship, it will try to teleport.
 * @author ednussi
 */
public class RunnerShip extends SpaceShip {

	// Initializing parameters 
	private final boolean RUNNER_ACCEL_STATE = true;
	private int _turn; //this is crap dunno how to use turn
	private boolean _accelState;
	
	private final double DISTANCE_TO_TELEPORT = 0.2;
	private final double ANGLE_TO_TELEPORT = 0.2;
	
	/**
	 * RunnerShip Constructor
	 */
	public RunnerShip(){
		this._turn = 0;
		this._accelState = RUNNER_ACCEL_STATE;
	}
	
	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		// teleport logic
		if (this.getClosestShipDistance(game) < DISTANCE_TO_TELEPORT
				&& Math.abs(this.getClosestShipAngle(game)) < ANGLE_TO_TELEPORT){
			this.teleport();
		}
		
		// move logic - farthest from closest
		if (Math.abs(this.getClosestShipAngle(game)) == Math.PI){
			this._turn = 0;
		}
		if (this.getClosestShipAngle(game) >= 0){
			this._turn = -1;
		}
		if (this.getClosestShipAngle(game) < 0){
			this._turn = 1;
		}
		this.getPhysics().move(this._accelState, this._turn);
		
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
