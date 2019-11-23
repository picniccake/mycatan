package textprocess;

import java.util.Scanner;
import java.util.HashMap;

/**
 * This class parses and validates all user inputs in CLI and generates an Object holding validated command.
 * 
 * @author  L. Columpsi, IT19ta_WIN, ZHAW
 * @version v4.1, 17.11.2019
 */
public class Parser {
	private UserCommands commands;
	private Scanner scanner;

	public Parser() {
		commands = new UserCommands();
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Parse and validate typed command by user. If the command-action is available in UserCommands, 
	 * so it generates a new Command Object und returns it to Texteditor.
	 * 
     * @return Command:	Object with entered command by user. If the command is invalid, 
     * it returns an object with UNKNOWN as command action and the whole entered command as command focus.
     */
	public Command getNextCommand() {
		Commandword commandActionword = null;
		String[] focusInputs = new String[2];
		String userInput = ""; //only for help-message on invalid input
		
		if(scanner.hasNextLine()) {
			userInput = scanner.nextLine();
			Scanner parser = new Scanner(userInput);
			
			if(parser.hasNext()) {
				//Use first word as command-action and put the rest into an array. Empty array fields are possible.
	            commandActionword = commands.getCommandAction(parser.next());
	            focusInputs = parseFocusInputs(parser, focusInputs);
	        } else {
	        	commandActionword = Commandword.UNKNOWN; //unknown input, if there isn't an input for Scanner
	        }
		
		} else {
			commandActionword = commands.getCommandAction("quit");
		}
		
		if(commandActionword == Commandword.UNKNOWN) {
			//put the whole user input into focusInputs for help-message
			focusInputs[0] = userInput;
		}
		
		return new Command(commandActionword, focusInputs);
	}
	
	/**
	 * Parse typed command by user. Add the second word to focusInputs array on index 0 
	 * and add all other inputs to focusInputs Array on index 1.
	 * 
     * @return String focusInputs: Array of Strings with all user inputs after command-action word.
     */
	private String[] parseFocusInputs(Scanner parser, String[] focusInputs) {
		
		if(parser.hasNext()) {
        	focusInputs[0] = parser.next(); //command focus word
        	while(parser.hasNext()) {
        		if(focusInputs[1] != null) {
        			focusInputs[1] += " " + parser.next(); //command focus: additional parameters (incl. text)
        		} else {
        			focusInputs[1] = parser.next(); //command focus: additional parameters (incl. text)
        		}
            }
        }
		return focusInputs;
	}
	
	/**
	 * Get all user command which are available in commands enum.
	 * 
     * @return HashMap<String, String>: Hash map with all user commands mapped to their description.
     */
	public HashMap<String, String> getAllUserCommands() {
		return commands.getAll();
	}

}
