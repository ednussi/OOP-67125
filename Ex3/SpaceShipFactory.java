
/**
 * SpaceShipFactory.java is a class designed to turn 
 * your desired input into a workable array
 * @author ednussi
 */
public class SpaceShipFactory {
	
	//Initialize parameters
	private final static String AggresiveShip = "a";
	private final static String RunnerShip = "r";
	private final static String BasherShip = "b";
	private final static String SpecialShip = "s";
	private final static String HumanShip = "h";
	private final static String DrunkardShip = "d";
	
	/**
	 * A method used by the supplied driver to create all the spaceship 
	 * objects according to the command line arguments.
	 * @param args - String[] array with arguments as the desired
	 * space ships in game, according to the rules given:
	 * "r"-RunnerShip, "s"-SpecialShip, "a"-AggressiveShip,
	 * "d"-DrunkardShip, "h"-HumanShip, "b"-BasherShip
	 * @return spaceShips - array filled with the desired ships to be created
	 */
    public static SpaceShip[] createSpaceShips(String[] args) {
    	
    	SpaceShip[] spaceShips = new SpaceShip[args.length];
    	int slot = 0;

    	for (String argument:args){
    		switch(argument){
    		case RunnerShip:
    			spaceShips[slot] = new RunnerShip();
    			slot++;
    			break;
    		case BasherShip:
    			spaceShips[slot] = new BasherShip();
    			slot++;
    			break;
    		case AggresiveShip:
    			spaceShips[slot] = new AggressiveShip();
    			slot++;
    			break;
    		case DrunkardShip:
    			spaceShips[slot] = new DrunkardShip();
    			slot++;
    			break;
    		case SpecialShip:
    			spaceShips[slot] = new SpecialShip();
    			slot++;
    			break;
    		case HumanShip:
    			spaceShips[slot] = new HumanShip();
    			slot++;
    			break;
    		}		
    	}
    	return spaceShips;
    }
}
