package oop.ex6.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import oop.ex6.parser_package.Parser;
import oop.ex6.section_package.Section;

/**
 * The main module which runs it all.
 * Calls the parsing module, and iterate the different sections, printing
 * warnings as it goes. In each section traverse files in the source directory,
 * filter them and prints them in the relevant order.
 * @author owner
 */
public class MyFileScript {
	
	private static final int SOURCE_DIR = 0, COMMAND_FILE_DIR = 1;
	
	/**
	 * MyfileScript main method which manages the entire project.
	 * It gets the args from the user and uses them in order to return
	 * the desirable output
	 * @param args - two args:
	 * A directory name in the form of a path - can be either absolute or 
	 * relative which contains the files to be filtered and ordered
	 * A file name in a form of a path which contains the command file
	 */
	public static void main(String[] args){
		try{
		if (args.length == 2){
			File filesSource = new File(args[SOURCE_DIR]);
			File commandFile = new File(args[COMMAND_FILE_DIR]);
			Parser.createRawDataIterator(commandFile);
			while (Parser.getrawDataIterator().hasNext()){
				sectionPrinter(Parser.Parse(), filesSource);			
			}
			Parser.getScanner().close();	
			} else {
			throw new TypeTwoException(); //didn't type correct amount of args
			}
		} catch (TypeTwoException exception){
			exception.printErrorMessage();
			}
		}
	
	/**
	 * Section Printer method - takes a given section and prints its
	 * After filtering and ordering according to the requirements of the 
	 * section
	 * @param section - A Section 
	 * @param filesSource
	 */
	private static void sectionPrinter (Section section, File filesSource){
		ArrayList<File> filteredAndOrederedFiles = new ArrayList<>();
		File[] filesList = filesSource.listFiles();
		for (File file : filesList){ //filers the files in the directory
			if (section.get_filter().isFiltered(file)){
				filteredAndOrederedFiles.add(file);
			}
		}
		// orders the files
		Collections.sort(filteredAndOrederedFiles, section.get_order()); 
		for (File file : filteredAndOrederedFiles){
			if (!file.isDirectory()){
				System.out.println(file.getName());
			}
		}
		
	}
}
