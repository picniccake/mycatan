package textprocess;

/**
 * Representations for all valid commandwords.
 * 
 * @author  L. Columpsi, IT19ta_WIN, ZHAW
 * @version v2.2, 17.11.2019
 */
public enum Commandword {
	//for every commandword two values: the user command and a description incl. one commandword for unknown commands
	QUIT("quit", 
			"quit: ends the texteditor session"), 
	HELP("help", 
			"help: overview of possible commands within the texteditor"), 
	RANDOM("random", 
			"random text: Inserts a random text automatically."), 
	ADD("add", 
			"add paragraph 'text': Save a paragraph and add to the end of text."),
	REPLACE("replace", 
			"replace paragraph 'number' 'text': Replace the given paragraph with given text."), 
	DELETE("delete", 
			"delete text: Delete the whole text."
			+ "<br>delete text 'number': Delete the paragraph with given paragraph-number."), 
	SHOW("show", 
			"show text: show the whole text."
			+ "<br>show text 'number': Show the paragraph with given paragraph-number."
			+ "<br>show dictionary: Show a compilation of all words, which have an occurence of more than 2."), 
	OVERVIEW("overview", 
			"overwiew text: Show an overview of the whole text with paragraph-number and amount of words."), 
	SEARCH("search", 
			"search text 'word': Search a word in the whole text."), 
	SEARCHREPLACE("searchreplace", 
			"searchreplace text 'word1' 'word2': Replace the desired word (word1) with the new word (word2)."), 
	EDIT("edit", 
			"edit width 'number': Set the desired with of the texteditor while showing text."
			+ "The number of characters has a minimum of 10."), 
	UNKNOWN("?", 
			"");
	
	//the commandword as String
	private String userCommand;
	private String commandDescription;

	/**
	 * Initialise with the corresponding commandword and it's description.
	 * @param String:	The commandword for userinput
	 * @param String:	The description of each command
	 */
	Commandword(String userCommand, String commandDescription) {
		this.userCommand = userCommand;
		this.commandDescription = commandDescription;
	}
	
	/**
	 * Returns commandword as String.
	 * @return String:	user command word
	 * 
	 */
	public String getUserCommand() {
		return userCommand;
	}
	
	/**
	 * Returns the description of commandword with possible command focus and parameters as String.
	 * @return String:	command descriptions
	 * 
	 */
	public String getDescription() {
		return commandDescription;
		}
	
	}


