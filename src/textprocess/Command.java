package textprocess;

/**
 * This class hold a single command. There are 4 possible command words saved as instance variables.
 * 
 * @author  L. Columpsi, IT19ta_WIN, ZHAW
 * @version v2.2, 10.11.2019
 */
public class Command {
	//instance datafields
	private Commandword action = null;	//What the command should do.
	private String focus = "";	//What the command should process.
	private String parameter = ""; //additional command parameters.

	/**
	 * Initialize user command inputs and save them to instance datafields.
     * 
     * @param Commandword:	Commandword from enum.
     * @param String[]:		Array of inputs from commandline. The command action have to be already validated.
     */
	public Command(Commandword commandword, String[] inputs) {
		action = commandword;
		
		if(inputs[0] != null) {
			focus = inputs[0];
		}
		if(inputs[1] != null) {
			parameter = inputs[1];
		}
	}
	
	/**
     * Returns the command action as Commandword (from enum). If it's invalid, it's UNKNOWN.
     * 
     * @return Commandword:	Command action word. What the command should do.
     */
	public Commandword getAction() {
		return action;
	}
	
	/**
     * Returns the command focus word.
     * 
     * @return String: Command focus word. What the command should process.
     */
	public String getFocus() {
		return focus;
	}
	
	/**
     * Returns the command parameter.
     * 
     * @return String: Command parameter which was entered by user. Can be null.
     */
	public String getParam() {
		return parameter;
	}
	
	/**
     * Returns the command parameter.
     * 
     * @return boolean: True, if the command has an additional parameter.
     */
	public boolean hasParam() {
		return (parameter != null);
	}

}
