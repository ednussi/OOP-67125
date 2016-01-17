package oop.ex7.main;

import oop.ex7.fileReader.FileReader;
import oop.ex7.nodes.CastingRulesTable;
import oop.ex7.nodes.DecelerationTypeTable;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.scopeNodes.EnvironmentNode;
import oop.ex7.parser.LineParser;
import oop.ex7.semanticAnalyzer.SemanticAnalyzer;

import java.io.File;
import java.util.ArrayList;

/**
 * main class supposed to run the show and lunch the main compnets of the code
 * check.
 */
public class Sjavac {
	private static final String CORRECT_CODE_FLAG = "0";
	private static final int ALLOWED_PROGRAM_ARGUMENTS = 1;
	private static final int DEFAULT_LINE_NUMBER = 0;
	private static final int SOURCE_FILE_ARGUMENT_INDEX = 0;

	/**
	 * The main method which runs the project  
	 * @param args - the requested paths as arguments
	 */
	public static void main(String[] args) {
		try{
			File classFile = startCompilerProcedure(args);

			DecelerationTypeTable.tableInitializer();
			CastingRulesTable.tableInitializer();

			FileReader.readFile(classFile);

			ArrayList<SimpleNode> parsedSourceFileStructure = LineParser
					.scanLines();

			EnvironmentNode fileCodeNodeTreeRoot = new EnvironmentNode(
					parsedSourceFileStructure);

			SemanticAnalyzer.VerifyCode(fileCodeNodeTreeRoot);

			System.out.println(CORRECT_CODE_FLAG);
		} catch (MasterTypeOneException | MasterTypeTwoException exception){
			exception.printExceptionMessage();
		}
	}

	/**
	 * starts the procdure by checking the arguments and creating a file object
	 * for the read file to be processed.
	 * @param args
	 * @return handle to the file
	 * @throws IncorrectProgramUsage incase wrong arguments - missing file,
	 * more
	 * then one file in the path line and etc.
	 */
	private static File startCompilerProcedure(String[] args)
			throws IncorrectProgramUsage {
		try{
			if (args.length != ALLOWED_PROGRAM_ARGUMENTS) {
				throw new IncorrectProgramUsage(DEFAULT_LINE_NUMBER);
			}
			return new File(args[SOURCE_FILE_ARGUMENT_INDEX]);
		} catch (NullPointerException exception){
			throw new IncorrectProgramUsage(DEFAULT_LINE_NUMBER);
		}

	}
}
