import static org.junit.Assert.*;

import oop.ex3.SpaceShipPhysics;

import org.junit.Test;
/**
 * JUNIT Testers for each spaceship (according to what is created)
 * and SpaceShip super class
 * @author ednussi
 */
public class SpaceShipTesters {

	// HumanShip as default but works with other as well
	SpaceShip _testShip = new HumanShip();

	/**
	 * Test charging energy at the end of each round
	 */
	@Test
	public void testChargeEnergy() {
		if (_testShip.get_currentEnergy() == _testShip.get_maxEnergy()){
			int maxEnergyBeforeCharge = _testShip.get_maxEnergy();
			int currentEnergyBeforeCharge = _testShip.get_currentEnergy();
			_testShip.chargeEnergy();
			assertEquals(maxEnergyBeforeCharge, 
					_testShip.get_maxEnergy());
			assertEquals(currentEnergyBeforeCharge, 
					_testShip.get_currentEnergy());
			
		} 
		else if (_testShip.get_currentEnergy() 
				+ _testShip.REPLANISHED_ENERGY_PER_ROUND > 
		_testShip.get_maxEnergy()){
			assertEquals(_testShip.get_maxEnergy(), 
					_testShip.get_currentEnergy());
		}
		else {
			int currentEnergyBeforeCharge = _testShip.get_currentEnergy();
			assertEquals(currentEnergyBeforeCharge 
					+ _testShip.REPLANISHED_ENERGY_PER_ROUND, 
					_testShip.get_currentEnergy());
		}
	}
	
	/**
	 * Test collisions with another ship while the shield is off
	 */
	@Test
	public void testCollidedWithAnotherShipShieldOff() {
		_testShip.startRoundShieldOff();
		int maxEnergyBeforeCollide = _testShip.get_maxEnergy();
		int healthLevelBeforeCollide = _testShip.get_healthLevel();
		_testShip.collidedWithAnotherShip();
		assertEquals(maxEnergyBeforeCollide 
				- _testShip.REDUCE_ENERGY_FROM_COLLISION,
				_testShip.get_maxEnergy());
		assertEquals(healthLevelBeforeCollide 
				- _testShip.REDUCE_HEALTH_FROM_COLLISION,
				_testShip.get_healthLevel());
	}
	
	/**
	 * Test collisions with another ship while the shield is on
	 */
	@Test
	public void testCollidedWithAnotherShipShieldOn() {
		if (_testShip.get_currentEnergy() >= _testShip.SHIELD_COST){
		_testShip.shieldOn();
		int maxEnergyBeforeCollide = _testShip.get_maxEnergy();
		int currentEnergyBeforeCollide = _testShip.get_currentEnergy();
		int healthLevelBeforeCollide = _testShip.get_healthLevel();
		_testShip.collidedWithAnotherShip();
		assertEquals(maxEnergyBeforeCollide  
				+ _testShip.ADDITIONAL_ENERGY_FROM_BASHING,
				_testShip.get_maxEnergy());
		assertEquals(currentEnergyBeforeCollide
				+ _testShip.ADDITIONAL_ENERGY_FROM_BASHING,
				_testShip.get_currentEnergy());
		assertEquals(healthLevelBeforeCollide,
				_testShip.get_healthLevel());
		}
	}

	/**
	 * Test the reset method
	 */
	@Test
	public void testReset() {
		_testShip.shieldOn();
		_testShip.gotHit();
		SpaceShipPhysics beforeReset = _testShip.getPhysics();
		_testShip.reset();
		assertEquals(_testShip.INITIAL_MAX_ENERGY ,
				_testShip.get_maxEnergy());
		assertEquals(_testShip.INITIAL_START_ENERGY,
				_testShip.get_currentEnergy());
		assertEquals(_testShip.INITIAL_HEALTH_LEVEL,
				_testShip.get_healthLevel());
		assertEquals(_testShip.INITIAL_SHIELD_POSITION,
				_testShip.get_shield());
		if (beforeReset == _testShip.getPhysics()){
			fail("Did not create new SpaceShipPhysics in reset");
		}
	}

	/**
	 * Test the is dead method - while dead
	 */
	@Test
	public void testIsDead() {
		while (_testShip.get_healthLevel() != 0){
			_testShip.gotHit();
		}
		assertEquals(true, _testShip.isDead());
	}
	
	/**
	 * Test the is dead method - while alive
	 */
	@Test
	public void testIsAlive() {
		if (_testShip.get_healthLevel() >= 0){
			assertEquals(false, _testShip.isDead());
		}
	}
	
	/**
	 * Test getting hit while shield is off
	 */
	@Test
	public void testGotHitShieldOff() {
		if ((_testShip.get_maxEnergy() >= _testShip.REDUCE_ENERGY_FROM_HIT)
				&& (_testShip.get_healthLevel() 
						>= _testShip.REDUCE_HEALTH_FROM_HIT)){
			int healthBeforeHit = _testShip.get_healthLevel();
			int energyBeforeHit = _testShip.get_currentEnergy();
			_testShip.gotHit();
			assertEquals(healthBeforeHit - _testShip.REDUCE_HEALTH_FROM_HIT,
					_testShip.get_healthLevel());
			assertEquals(energyBeforeHit - _testShip.REDUCE_ENERGY_FROM_HIT,
					_testShip.get_currentEnergy());
		}
	}
	
	/**
	 * Test getting hit while shield is on
	 */
	@Test
	public void testGotHitShieldOn() {
		if (_testShip.get_currentEnergy() >= _testShip.SHIELD_COST){
			_testShip.shieldOn();
			int healthBeforeHit = _testShip.get_healthLevel();
			int energyBeforeHit = _testShip.get_currentEnergy();
			_testShip.gotHit();
			assertEquals(healthBeforeHit, _testShip.get_healthLevel());
			assertEquals(energyBeforeHit, _testShip.get_currentEnergy());
		}
	}

	/**
	 * Test activating shield
	 */
	@Test
	public void testShieldOn() {
		if (_testShip.get_currentEnergy() >= _testShip.SHIELD_COST ){
			_testShip.shieldOn();
		assertEquals(true, _testShip.get_shield());
		}
	}
	
	/**
	 * Test activating shield when you have no energy
	 */
	@Test
	public void testShieldOnWithNoEnergy() {
		while (_testShip.get_currentEnergy() >= _testShip.SHIELD_COST){
			_testShip.shieldOn();
		}
		_testShip.startRoundShieldOff();
		_testShip.shieldOn();
		assertEquals(false, _testShip.get_shield());
	}

	/**
	 * Test if returns the right instance
	 */
	@Test
	public void testGetPhysicsInstance() {
		if ((_testShip.getPhysics() instanceof SpaceShipPhysics) == false){
			fail("Did not return the right instance");
		}
	}
	
	/**
	 * Test if ship teleported
	 */
	@Test
	public void testTeleport() {
		SpaceShipPhysics beforeTeleport = _testShip.getPhysics();
		_testShip.teleport();
		if (beforeTeleport == _testShip.getPhysics()){
			fail("Did not teleport");
		}
	
	}
	
	/**
	 * Test if ship teleported when has no energy to
	 */
	@Test
	public void testTeleportWithNoEnergy() {
		while (_testShip.get_currentEnergy() >=  _testShip.TELEPORT_COST){
			_testShip.teleport();
		}
		SpaceShipPhysics beforeTeleport = _testShip.getPhysics();
		_testShip.teleport();
		assertEquals(beforeTeleport, _testShip.getPhysics());
	}

}


