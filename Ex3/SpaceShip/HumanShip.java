import java.awt.Image;

import oop.ex3.GameGUI;

/**
 * HumanShip.java is a class representing a human controlled spaceship.
 * @author ednussi
 */
public class HumanShip extends SpaceShip {

	// Initializing parameters 
	private boolean HUMAN_INITALL_STATE = false;
	private int _turn; 
	private boolean _accelState;

	/**
	 * HumanShip Constructor
	 */
	public HumanShip(){
		this._turn = 0;
		this._accelState = HUMAN_INITALL_STATE;
	}
	
	/**
	 * Does the actions of this ship for this round
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		this.startRoundShieldOff();
		
		// teleport logic
		if (game.getGUI().isTeleportPressed()){
			this.teleport();
		}
		
		// movement logic
		if (game.getGUI().isUpPressed()){
			this._accelState = true;
		}
		else {
			this._accelState = false;
		}
		
		if (game.getGUI().isLeftPressed()){
			this._turn = 1;
		}
		if (game.getGUI().isRightPressed()){
			this._turn = -1;
		}
		// like school solution
		// if both pressed, or not pressed - turn forward
		if ((game.getGUI().isRightPressed()) 
				&& (game.getGUI().isLeftPressed()) 
				|| (!game.getGUI().isRightPressed()) 
				&& (!game.getGUI().isLeftPressed())){
			this._turn = 0;
		}
		
		this.getPhysics().move(this._accelState, this._turn);
		
		// shield logic
		if (game.getGUI().isShieldsPressed()){
			this.shieldOn();
		}
		
		// fire shot logic
		this.increaseShotIntervalCount();
		if ((game.getGUI().isShotPressed()) && (this.getShotInterval())){
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
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		} 
		else {
		return GameGUI.SPACESHIP_IMAGE;
		}
	}
	

}
