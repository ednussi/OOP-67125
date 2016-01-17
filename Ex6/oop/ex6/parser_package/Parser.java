package oop.ex6.parser_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import oop.ex6.filescript.TypeTwoException;
import oop.ex6.filter_package.Filter;
import oop.ex6.filter_package.FilterExceptions;
import oop.ex6.filter_package.FilterFactory;
import oop.ex6.order_package.Order;
import oop.ex6.order_package.OrderExceptions;
import oop.ex6.order_package.OrderFactory;
import oop.ex6.section_package.Section;

/**
 * The only module that knows the logical order of the commands file
 * It generates the software representation of the program from the command 
 * file.
 * @author owner
 */
public class Parser {

	// Initializing parameters
	private static final String DEFUALT_ORDER_TYPE = "abs";
	private static final String[] DEFUALT_ORDER_DATA = {"abs"},
			DEFUALT_FILTER_DATA = {"all"};
	private static final int INITALIZE_LINE_NUMBER = 0;
	private static final int FILTER_TYPE = 0, FILTER_LINE_NUMBER = 1,
			ORDER_TYPE = 2, ORDER_LINE_NUMBER = 3;	
	private static Scanner _scanner;
	private static String[] _sectionData, _filterData, _orderData;
	private static int lineNum;
	private static Iterator<String[]> _rawDataIterator;

	/**
	 * Creates a rawDataIterator of the arranged sections using a given source
	 * of a command file following the special logic
	 * @param source - the command File
	 * @throws TypeTwoException
	 */
	public static void createRawDataIterator(File source) 
			throws TypeTwoException{
		try{
			_scanner = new Scanner(source);
			_rawDataIterator = arrangeSectionsData(_scanner);
			lineNum = INITALIZE_LINE_NUMBER;
		} catch (FileNotFoundException exception){
			throw new TypeTwoException();
		}
	}

	/**
	 * Returns Iterator which can iterate on the processed data from a given
	 * scanner, each time giving data of one section.
	 * @param scanner - scanner initialized with the command file
	 * @return sectionsData iterator
	 * @throws TypeTwoException
	 */
	public static Iterator<String[]> arrangeSectionsData(Scanner scanner)
			throws TypeTwoException{
		ArrayList<String[]> _sectionsData = new ArrayList<>();
		lineNum = INITALIZE_LINE_NUMBER;
		while (scanner.hasNext()){
			_sectionsData.add(arrangeOneSectionData(scanner));
		}
		return _sectionsData.iterator();
	}

	/**
	 * Uses given logic that a each command file is composed of this pattern:
	 * FILTER
	 * -filter type-
	 * ORDER
	 * -order type-
	 * creates one arranged the data for one section
	 * @param scanner - scanner initialized with the command file
	 * @return String[] - data for one section
	 * @throws TypeTwoException
	 */
	public static String[] arrangeOneSectionData(Scanner scanner)
			throws TypeTwoException {
		if (scanner.hasNext() && scanner.hasNext("FILTER")) {
			_sectionData = new String[4];
			nextLine();
			if (scanner.hasNext()) {
				_sectionData[FILTER_TYPE] = nextLine();
				_sectionData[FILTER_LINE_NUMBER] = String.valueOf(lineNum);
				if (scanner.hasNext() && scanner.hasNext("ORDER")) {
					nextLine();
					if (scanner.hasNext() && !scanner.hasNext("FILTER")) {
						_sectionData[ORDER_TYPE] = nextLine();
					} else {
						// end of file
						_sectionData[ORDER_TYPE] = DEFUALT_ORDER_TYPE; 
					}
					_sectionData[ORDER_LINE_NUMBER] = String.valueOf(lineNum); 
				} else {
					// no third line / third line isn't ORDER
					throw new TypeTwoException(); 
				}
			} else {
				// no second line
				throw new TypeTwoException(); 
			}
		} else {
			// was given empty file / first line isn't FILTER
			throw new TypeTwoException(); 								
		}
		return _sectionData;
	}

	/**
	 * The main basic parsing method which gives the final sections,
	 * one each time.
	 * @return Section - one processed Section
	 * @throws TypeTwoException
	 */
	public static Section Parse() throws TypeTwoException{
		if (_rawDataIterator.hasNext()){
			String[] sectionData = _rawDataIterator.next();
			return createSection(sectionData);
		}
		return null;
	}

	/**
	 * Creating Section method which create one section from given data  
	 * @param sectionData - section's required data in a 4 places Array
	 * place 0 - Filter type
	 * place 1 - Filter line number in the command file
	 * place 2 - Order type
	 * place 3 - Order line number in the command file
	 * @return Section - according to the Section's data
	 */
	public static Section createSection(String[] sectionData){
		Filter filter = createFilter(sectionData);
		Order order = createOrder(sectionData);
		return new Section(filter, order);
	}

	/**
	 * Inner method to create Filter, catches filter exceptions
	 * @param sectionData - the section's Data
	 * @return Filter - according to the data
	 */
	private static Filter createFilter(String[] sectionData){
		try{
			_filterData = sectionData[FILTER_TYPE].split("#");
			return FilterFactory.createFilter(_filterData);
		} catch (FilterExceptions exception){
			exception.printError(sectionData[FILTER_LINE_NUMBER]);
			try {
				return FilterFactory.createFilter(DEFUALT_FILTER_DATA);
			} catch (FilterExceptions exception2) {
			}
		}
		return null;		
	}

	/**
	 * Inner Method to create Order, catches order exceptions
	 * @param sectionData - the section's Data
	 * @return Order - according to the data
	 */
	private static Order createOrder(String[] sectionData){
		try{
			_orderData = sectionData[ORDER_TYPE].split("#");
			return OrderFactory.createOrder(_orderData);
		} catch (OrderExceptions exception){
			exception.printError(sectionData[ORDER_LINE_NUMBER]);
			try {
				return OrderFactory.createOrder(DEFUALT_ORDER_DATA);
			} catch (OrderExceptions exception2) {
			}
		}
		return null;		
	}

	/**
	 * Inner method which advance scanner and
	 * increase lineNum to reduce redundancy
	 * @return the scanner next item
	 */
	private static String nextLine(){
		lineNum++;
		return _scanner.next();
	}

	/**
	 * rawDataIterator Getter
	 * @return An Iterator which can iterate over the raw from the file
	 */
	public static Iterator<String[]> getrawDataIterator(){
		return _rawDataIterator;
	}

	/**
	 * Scanner Getter
	 * @return scanner
	 */
	public static Scanner getScanner(){
		return _scanner;
	}
}

