package textprocess;
import java.util.HashMap;

/**
 * This class operates as the main class and contains the main method. It uses the parsed commands from the Parser class
 * to run the command methods in the Process class. 
 * 
 * @author M. Schoch, IT19ta_WIN, ZHAW
 * @version v.1.2, 10.11.2019
 * 
 */
public class Texteditor {
	static Texteditor texteditor;
	static Parser parser;
	static Process process;
	
	
	public static void main(String[] args) {
		texteditor = new Texteditor();
	}

	public Texteditor() {
		parser = new Parser();
		process = new Process();
		boolean running = true;
		
		printWelcomeText();
		
		while(running) {
			Command command = parser.getNextCommand();
            running = runCommand(command);
		}
		
		printQuitText();
	}
	
	/**
	 * This method assembles the command from the Parser class to the command methods from the Process class.
	 * It then runs the matched method to edit the text accordingly. 
	 * 
	 * @param command
	 * 
	 */
	public boolean runCommand(Command command) {
		boolean editorRunning = true;
		String consoleMSG = null;
		
		switch(command.getAction()) {
		
		case QUIT:
			editorRunning = false;
			break;
		case HELP:
			printCommandOverview();
			break;
		case RANDOM:
			consoleMSG = process.randomText();
			break;
		case ADD:
			consoleMSG = process.addText(command.getFocus(), command.getParam().trim());
			break;
		case REPLACE:
			consoleMSG = process.replace(command.getFocus(), command.getParam().trim());
			break;
		case DELETE:
			consoleMSG = process.delete(command.getFocus(), command.getParam());
			break;
		case SHOW:
			consoleMSG = process.show(command.getFocus(), command.getParam());
			break;
		case OVERVIEW:
			consoleMSG = process.overviewText(command.getFocus());
			break;
		case SEARCH:
			consoleMSG = process.search(command.getFocus(), command.getParam().trim());
			break;
		case SEARCHREPLACE:
			consoleMSG = process.searchReplace(command.getFocus(), command.getParam().trim());
			break;
		case EDIT:
			consoleMSG = process.editWidth(command.getFocus(), command.getParam().trim());
			break;
		case UNKNOWN:
			printUnknownCommandText(command);
			break;
		default:
			printUnknownCommandText(command);
			break;
			
		}
		
		if(consoleMSG != null) {
//			process.printlnWithWidth(consoleMSG);
			System.out.println(consoleMSG);
		}
		return editorRunning;
	}


	/**
	 * This method prints a welcome text. 
	 */
	private void printWelcomeText() {
		System.out.println("Welcome to the LUMAFALE texteditor!"  + System.lineSeparator()
							+ "Please enter a command or \"help\" for an overview of all commands: " + System.lineSeparator());
		//TODO more text...
	}
	
	/**
	 * This method prints a text if the entered commands are unknown.
	 * 
	 *  @param command
	 */
	private void printUnknownCommandText(Command command) {
		String commandFocus = command.getFocus();
		
		System.out.println("The command you have entered is invalid. Please enter a valid command!" + System.lineSeparator()
				+ "The command \"help\" lists all possibilities for commands.");
		System.out.println("You have entered: " + commandFocus);
	}
	
	/**
	 * This method prints an Overview of all commands and their description.
	 */
	private void printCommandOverview() {
		HashMap<String, String> commandDescriptions = parser.getAllUserCommands();

		System.out.println("You have the following possibilities for commands:" + "" + System.lineSeparator());
		
		for(String command : commandDescriptions.keySet()) {
			String[] commandDescriptionLines = commandDescriptions.get(command).split("<br>");
			
			System.out.println("--- " + command + " ---");
			for(String commandDescriptionLine : commandDescriptionLines) {
				System.out.println(commandDescriptionLine);
			}
			System.out.println("");
		}
	}
	
	/**
	 * This method prints a quit text.
	 */
	private void printQuitText() {
		System.out.println("Thank you for choosing the LUMAFALE texteditor. See you next time!");
	}
	
}
