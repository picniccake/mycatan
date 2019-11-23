package textprocess;

import java.util.HashMap;

/**
 * This class validates the first word of user input. 
 * This word is named as "command action" in Command class.
 * 
 * @author  L. Columpsi, IT19ta_WIN, ZHAW
 * @version v2.0, 07.11.2019
 */
public class UserCommands {
	HashMap<String, Commandword> commandActionCommandwords;
	HashMap<String, String> commandDescriptions;
	
	/**
     * Save valid user commands with enum Commandword into HashMap.
     */
	public UserCommands() {
		commandActionCommandwords = new HashMap<>();
		commandDescriptions = new HashMap<>();
		
        for(Commandword commandword : Commandword.values()) {
            if(commandword != Commandword.UNKNOWN) {
                commandActionCommandwords.put(commandword.getUserCommand(), commandword);
                commandDescriptions.put(commandword.getUserCommand(), commandword.getDescription());
            }
        }
	}
	
	
	/**
     * Find and returns the corresponding commandword to user command.
     * 
     * @param	String: The word which is to find
     * @return	Commandword: The corresponding commandword or UNKNOWN if it wasn't found in enum
     */
	public Commandword getCommandAction(String commandAction) {
		Commandword commandword = commandActionCommandwords.get(commandAction);
        if(commandword != null) {
            return commandword;
        }
        else {
            return Commandword.UNKNOWN;
        }
	}
	
	/**
     * Returns a HashMap with all user commands and their description.
     * 
     * @return HashMap:	user command String with description String
     */
	public HashMap<String, String> getAll() {
		return commandDescriptions;
	}

}
