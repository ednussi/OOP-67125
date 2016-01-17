import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * DrunkardShip.java is a class representing a ship,
 * which has random probability to make each action 
 * turn / accelerate / shield / fire / teleport
 * @author ednussi
 */
public class DrunkardShip extends SpaceShip {

	// Initializing parameters 
	private boolean DRUNKARD_INITALL_STATE = true;
	private int _turn; 
	private boolean _accelState;
	private double _drunklogic;
	
	/**
	 * DrunkardShip Constructor
	 */
	public DrunkardShip(){
		this._turn = 0;
		this._accelState = DRUNKARD_INITALL_STATE;
		this._drunklogic = 0;
		
		
	}
	
	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		this.startRoundShieldOff();

		// teleport logic
		if (this.drunkLogic() >= 9){
			this.teleport();
		}
		
		// movement logic
		this._drunklogic = this.drunkLogic();
		if (this._drunklogic <= 5){
			this._turn = 1;
		}
		else {
			this._turn = -1;
		}
		if ((this._drunklogic <= 2.5) || (this._drunklogic >= 7.5)){
			this._accelState = true;
		}
		else {
			this._accelState = false;
		}
		this.getPhysics().move(this._accelState, this._turn);
		
		//shield logic
		if (this.drunkLogic() <= 3){
			this.shieldOn();
		}
		
		// fire shot logic
		this.increaseShotIntervalCount();
		this._drunklogic = this.drunkLogic();
		if (((this._drunklogic <= 3) || (this._drunklogic >= 7)) 
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
	
	private double drunkLogic(){
		this._drunklogic = Math.random() * 10;
		return this._drunklogic;
	}

}
