/**
 * 
 */
package textprocess;
import java.util.HashMap;

/**
 * This class saves all words from a paragraph in a HashMap and outputs all words,
 * which occure more often than twice.
 * 
 * @author Fabian Schellenberg
 * @version v.1.0, 06.11.2019
 *
 */

public class Dictionary {
	static HashMap<String, Integer> dictionary = new HashMap<>();
	
	/**
	 * 
	 * No construction actions needed
	 * 
	 */
	public Dictionary() {
		
	}
	
	/**
	 * 
	 * This method splits the words from a paragraph
	 * fills them into a HashMap.
	 * It also replaces punctuation marks (. , ! :) with empty character.
	 * 
	 * @param paragraph
	 */
	public static void fillDictionary(Paragraph paragraph) { 
		
	    String paragraphText = paragraph.getParagraph().replaceAll("(.|,|!|:)\\s+", "");
	    String[] splitWords = paragraphText.split(" ");
		
		for ( String word : splitWords ) {
			Integer wordCounter = dictionary.get(word);
			if ( wordCounter == null ) {
				wordCounter = 0;
			}
			dictionary.put(word, wordCounter + 1);
		}
	}
	
	/**
	 * This method prints the words from the dictionary, which occure more often
	 * than twice
	 * 
	 * @return
	 */
	public void printDictionary() { 
		for (String word : dictionary.keySet()) {
			int wordCounter = 0;
			if ( wordCounter >= 2 ) {
				System.out.println("The word: " + word + " occures" + wordCounter + " times");
			}
		}
	}
}