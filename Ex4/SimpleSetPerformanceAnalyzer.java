import java.util.*;

/**
 * A testing file for default requested HashSets and tests
 * @author ednussi
 */
public class SimpleSetPerformanceAnalyzer {

	// Initializing parameters
	static final String[] DATA1 = Ex4Utils.file2array("src//data1.txt");
	static final String[] DATA2 = Ex4Utils.file2array("src//data2.txt");
	static int testNumber = 0;
	static long timeBefore, timeAfter, timeDiffrence;
	static long[][] testResualts = new long[9][5];
	static final String[] DATA_STRUCTURES_NAMES = {"ChainedHash",
		"OpenHashSet", "TreeSet", "LinkedList", "HashSet"};
	static final int[] TEST_NUMBERS_TO_COMPARE_BEST_TIME = {0, 3, 4, 6, 7};
	static ArrayList<Integer> testsToCompareBestTime = 
			new ArrayList<Integer>();
	static final int TEST0RESUALTS = 0, TEST1RESUALTS = 1, TEST2RESUALTS = 2,
			TEST3RESUALTS = 3, TEST4RESUALTS = 4, TEST5RESUALTS = 5, 
			TEST6RESUALTS = 6, TEST7RESUALTS = 7, TEST8RESUALTS = 8;
	static final int NUMBER_OF_TESTS = 6;
	static final int INITIAL_BEST_TIME = 0;
	static final String[] resualtsNames = {
		"Time to insert all words from data1",
		"Time to insert all words from data2", 
		"Time to check Contains 'hi' after initialized with data1",
		"Time to check Contains '-13170890158' after initialized with data1",
		"Time to check Contains '23' after initialized with data2",
		"Time to check Contains 'hi' after initialized with data2",
		"Time diffrence of inserting all words from data1 to data2",
		"Time diffrence of checking Contains 'hi' and '-13170890158'" +
		" initialized with data1 ",
		"Time diffrence of checking Contains '23' and 'hi'" +
		" initialized with data2 ",
		};
	
	/**
	 * The main method which run the tests and prints the resualts
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Initializng...");
		// Initializing parameters
		for (int param: TEST_NUMBERS_TO_COMPARE_BEST_TIME)
			testsToCompareBestTime.add(param);
		SimpleSet[] dataStructures1 = dataStructures();
		SimpleSet[] dataStructures2 = dataStructures();
		
		//Test 1 - Add all words from data1 and count time
		startTest(testNumber);
		for (int i = 0; i < dataStructures1.length; i++ ){
			long timeBefore = System.nanoTime();
			testAddAllWords(DATA1 ,dataStructures1[i]);
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][i] = timeDifference;
		}
		finishTest(testNumber);
		testNumber++;

		
		//Test 2 - Add all words from data2 and count time
		startTest(testNumber);
		for (int i = 0; i < dataStructures2.length; i++ ){
			long timeBefore = System.nanoTime();
			testAddAllWords(DATA2 ,dataStructures2[i]);
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][i] = timeDifference;
		}
		finishTest(testNumber);
		testNumber++;
		
		//Test 3 - check if contains "hi" after initialized with data1
		startTest(testNumber);
		for (int i = 0; i < dataStructures1.length; i++ ){
			long timeBefore = System.nanoTime();
			dataStructures1[i].contains("hi");
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][i] = timeDifference;
		}
		finishTest(testNumber);
		testNumber++;
		
		//Test 4 - check if contains "-13170890158" after initialized 
		//with data1
		startTest(testNumber);
		for (int i = 0; i < dataStructures1.length; i++ ){
			long timeBefore = System.nanoTime();
			dataStructures1[i].contains("-13170890158");
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][i] = timeDifference;
		}
		finishTest(testNumber);
		testNumber++;
		
		//Test 5 - check if contains "23" after initialized with data2
		startTest(testNumber);
		for (int dataBase = 0; dataBase < dataStructures2.length; dataBase++ ){
			long timeBefore = System.nanoTime();
			dataStructures2[dataBase].contains("23");
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][dataBase] = timeDifference;
		}
		finishTest(testNumber);
		testNumber++;
		
		//Test 6 - check if contains "hi" after initialized with data2
		startTest(testNumber);
		for (int dataBase = 0; dataBase < dataStructures2.length; 
				dataBase++ ){
			long timeBefore = System.nanoTime();
			dataStructures2[dataBase].contains("hi");
			long timeAfter = System.nanoTime();
			long timeDifference = timeAfter - timeBefore;
			testResualts[testNumber][dataBase] = timeDifference;
		}
		finishTest(testNumber);
		//System.out.println("Finished last Test, Computing data");
		
		//computing the data for comparison results
		for (int resualt = 0; resualt < dataStructures2.length; resualt++ ){
			testResualts[TEST6RESUALTS][resualt] = 
					testResualts[TEST0RESUALTS][resualt]
					- testResualts[TEST1RESUALTS][resualt];
			testResualts[TEST7RESUALTS][resualt] = 
					testResualts[TEST2RESUALTS][resualt]
					- testResualts[TEST5RESUALTS][resualt];
			testResualts[TEST8RESUALTS][resualt] = 
					testResualts[TEST4RESUALTS][resualt]
					- testResualts[TEST5RESUALTS][resualt];
		}
		
		System.out.println("Printing Resualts");
		resualtPrinter(testResualts, DATA_STRUCTURES_NAMES, resualtsNames);
	}
	
	/**
	 * Prints a statement for starting a test
	 * @param testNumber - the number of test starting
	 */
	private static void startTest(int testNumber){
		System.out.println("Starting test Number " + testNumber);
	}

	/**
	 * Prints a statement for finishing a test
	 * @param testNumber - the number of test finished
	 */
	private static void finishTest(int testNumber){
		System.out.println("Finished test Number " + testNumber);
		testNumber++;
		if (testNumber >= NUMBER_OF_TESTS){
			System.out.println("Finished last Test, Computing data");
		}
	}
	
	/**
	 * Constructs a SimpleSet[] of the requested data structures to test
	 * @return - a SimpleSet[] of the requested data structures to test
	 */
	private static SimpleSet[] dataStructures(){
		SimpleSet[] dataStructures = {
                new ChainedHashSet(),
                new OpenHashSet(),
                new CollectionFacadeSet(new TreeSet<String>()),
                new CollectionFacadeSet(new LinkedList<String>()),
                new CollectionFacadeSet(new HashSet<String>())
				};
		return dataStructures;
		}  
	
	/**
	 * Adds all elements  from a given String[] to a given data structure.
	 * @param data - the String[] array of data to add
	 * @param dataStructure - the requested data structure to add the 
	 * elements to.
	 */
	private static void testAddAllWords(String[] data, 
			SimpleSet dataStructure){
		for (String item : data){
			dataStructure.add(item);
		}
	}
	
	/**
	 * Compares which is the best time
	 * @param testResualts - a long[][] array which contains the data,
	 * each test resualt in a certain long[], and the resualts inside it.
	 * @param testToCompare - the index of the requested test to compare
	 */
	private static void bestResualt(long[][] testResualts, int testToCompare){
		long bestTime = testResualts[testToCompare][INITIAL_BEST_TIME];
		for (long resualt : testResualts[testToCompare]){
			if (bestTime > resualt){
				bestTime = resualt;
			}
		}
		System.out.println("Best time is: " + bestTime);
	}
	
	/**
	 * Prints the results of test saved into a long[][] array
	 * @param testResualts - the long[][] array the results are saved on
	 * @param dataStructuresNames - String[] containing the names of 
	 * the dataStrctures tested
	 * @param resualtsNames - String[] containing the names of the tests 
	 */
	private static void resualtPrinter(long[][] testResualts, 
			String[] dataStructuresNames, String[] resualtsNames){
		int resualtName = 0;
		for (long[] singleTestResualt : testResualts){
			System.out.println("Resualts for " +
		resualtsNames[resualtName] + " are:");
			int hashName = 0;
			for (long resualt: singleTestResualt){
				System.out.println(dataStructuresNames[hashName]
						+ " " + resualt);
				hashName++;
			}
			if (testsToCompareBestTime.contains(resualtName)){
				bestResualt(testResualts, resualtName);
			}
			resualtName++;
		}
	}
}
