import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUNIT Testers for SpaceShip Factory
 * @author ednussi
 */
public class SpaceShipFactoryTests {

	/**
	 * Test if returns the right instance - RunnerShip
	 */
	@Test
	public void testFactoryIsOfRunnerInstance() {
		String[] testRunner = {"r", "r"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof RunnerShip) == false ){
				fail("Is not of instance RunnerShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - BasherShip
	 */
	@Test
	public void testFactoryIsOfBasherInstance() {
		String[] testRunner = {"b", "b"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof BasherShip) == false ){
				fail("Is not of instance BasherShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - AggresiveShip
	 */
	@Test
	public void testFactoryIsOfAggressiveInstance() {
		String[] testRunner = {"a", "a"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof AggressiveShip) == false ){
				fail("Is not of instance AggressiveShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - DrunkardShip
	 */
	@Test
	public void testFactoryIsOfDrunkardInstance() {
		String[] testRunner = {"d", "d"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof DrunkardShip) == false ){
				fail("Is not of instance DrunkardShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - SpecialShip
	 */
	@Test
	public void testFactoryIsOfSpecialInstance() {
		String[] testRunner = {"s", "s"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof SpecialShip) == false ){
				fail("Is not of instance SpecialShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - HumanShip
	 */
	@Test
	public void testFactoryIsOfHumanInstance() {
		String[] testRunner = {"h", "h"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		for (SpaceShip argument : spaceShipArray){
			if ((argument instanceof HumanShip) == false ){
				fail("Is not of instance HumanShip");
			}
		}
	}
	
	/**
	 * Test if returns the right instance - all kinds
	 */
	@Test
	public void testFactoryIsOfCombinedInstance() {
		String[] testRunner = {"r", "b", "a", "d", "s", "h"};
		SpaceShip[] spaceShipArray = 
				SpaceShipFactory.createSpaceShips(testRunner);
		int slot = 0;
		for (SpaceShip argument : spaceShipArray){
			switch(slot){
			case 0:
				if ((argument instanceof RunnerShip) == false ){
					fail("Is not of instance RunnerShip");
				}
					slot++;
					break;
			case 1:
				if ((argument instanceof BasherShip) == false ){
					fail("Is not of instance BasherShip");
				}
					slot++;
					break;
			case 2:
				if ((argument instanceof AggressiveShip) == false ){
					fail("Is not of instance AggressiveShip");
				}
					slot++;
					break;
			case 3:
				if ((argument instanceof DrunkardShip) == false ){
					fail("Is not of instance DrunkardShip");
				}
					slot++;
					break;
			case 4:
				if ((argument instanceof SpecialShip) == false ){
					fail("Is not of instance SpecialShip");
				}
					slot++;
					break;
			case 5:
				if ((argument instanceof HumanShip) == false ){
					fail("Is not of instance HumanShip");
				}
					slot++;
					break;	
			}
		}
	}
}
