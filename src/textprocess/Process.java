/**
 * 
 */
package textprocess;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author L.Kurdoglu
 * version: 2.1
 *
 */
public class Process {
	private ArrayList<Paragraph> paragraphs;
	private HashSet<String> searchedWords = new HashSet<String>();
	private Pattern patt;
	private Matcher match;
	private List<String> wordsInParag = new ArrayList<String>();
	private List<String> words = new ArrayList<String>();
	private final int minWidth = 10;
	private int textWidth;
	
	public Process() {
		paragraphs = new ArrayList<>();
		textWidth = 60;
	}
	
	/* 
	 * PROCESS OF COMMAND: random text
	 * creates paragraphs from random text.
	 * 
	 * */
	public String randomText() {
		String consoleMSG = null;
		paragraphs.add(new Paragraph("An erster Stelle muss John Rosenberg genannt werden. John ist inzwischen " 
				+ "Deputy Vice Chancellor an der Deakin University, Australien."));
		paragraphs.add(new Paragraph("Es ist lediglich ein " 
				+ "unglücklicher Umstand, dass John nicht einer der Autoren dieses Buches ist. Er " 
				+ "war von Anfang an eine der treibenden Kräfte bei der Entwicklung von BlueJ. "));
		paragraphs.add(new Paragraph("Vieles von dem Material, das in" 
				+ "diesem Buch vorgestellt wird, wurde in Diskussionen mit John entwickelt"));
//
//		paragraphs.add(new Paragraph("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
//				+ "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
//				+ "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
//				+ "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."));
//		paragraphs.add(new Paragraph("Quisque quis malesuada dolor, semper vestibulum diam. "
//				+ "Vivamus quis tortor dui. Quisque suscipit sodales ex non aliquet. "
//				+ "Nulla et congue odio. Suspendisse consequat facilisis lectus, "
//				+ "sed condimentum erat vestibulum ut. Pellentesque commodo congue elementum. "
//				+ "Duis nec porttitor sapien, porta iaculis lorem. Curabitur luctus lectus mauris. "));
//		paragraphs.add(new Paragraph("Nullam interdum diam facilisis, faucibus nisl a, pretium purus. "
//				+ "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. "
//				+ "Integer metus libero, viverra eget rutrum eu, vulputate suscipit arcu. "
//				+ "Integer vitae tristique velit. Vivamus tristique luctus arcu vel faucibus. "
//				+ "Mauris ante ante, viverra nec gravida a, eleifend in diam. "
//				+ "Maecenas pharetra dolor in urna pulvinar viverra. Maecenas dapibus laoreet justo, "
//				+ "et pharetra odio dictum finibus. Nunc turpis diam, pulvinar gravida mi eu, volutpat viverra mi."));
		if (paragraphs.size() > 0) {
			consoleMSG = "Random text successfully created."
					+ System.lineSeparator()
					+ "Enter a command: ";
		} else {
			consoleMSG = "Text is not created. check the command."
					+ System.lineSeparator() + "Enter a command: ";
		}
		return consoleMSG;
	}

	/**
	 * PROCESS OF COMMAND: add 
	 * @param commandFocus 'paragraph': adds a new paragraph to the end of the paragraphs 
	 * @param text: the String text to add to paragraph
	 * @return String consoleMSG: 
	 */
	public String addText(String commandFocus, String text) {
		String consoleMSG = null;
		if (commandFocus.equals("paragraph")) {
			this.paragraphs.add(new Paragraph(text));
			consoleMSG = "paragraph successfully added."
					+ System.lineSeparator()
					+ "Enter a command: ";
		} else {
			consoleMSG = "Wrong command typed for \"add\" "
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * PROCESS OF COMMAND: overview
	 * shows all words, their frequencies within a paragraph, paragraph number
	 * 
	 * @param String commandFocus 'text': print an overview of the text
	 * @return String consoleMSG: 
	 */
	public String overviewText(String commandFocus) {
		String consoleMSG = null;
		if (commandFocus.equals("text")) {
			//print all parameter numbers and their amount of words
			System.out.println("Your text has the following paragraphs "
					+ "with the listed amount of words:");
			for(int i=0; i < paragraphs.size(); i++) {
				System.out.println("Parag. " + (i+1) + ": " 
					+ paragraphs.get(i).getSize() + " word(s)");
			}
			consoleMSG = "Enter a command: ";
		} else {
			consoleMSG = "Wrong command typed for \"overview\"."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * PROCESS OF COMMAND: search 
	 * finds the given word and prints its paragraph number
	 * and word's frequency within paragraph.
	 * 
	 * @param String commandFocus 'text'
	 * @param String parameter 'word'
	 */
	public String search(String commandFocus, String parameter) {
		String consoleMSG = null;
		if (commandFocus.equals("text") && !parameter.equals("")) {
			String[] searchword = parameter.split(" ", 2);
			consoleMSG = searchText(searchword[0]);
		} else {
			consoleMSG = "Wrong command typed for \"search\"."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * refers to process search(String commandFocus, String parameter)
	 * 
	 * search a word in text (all paragraphs) and print a list of all
	 * paragraphs with these words and their frquency in it.
	 * 
	 */
	private String searchText(String searchword) {
		String consoleMSG = null;
		int frequency = 0;
		System.out.println("search for word: " + searchword);
		for (int i = 0; i < paragraphs.size(); i++) {
			wordsInParag.clear();
			wordsInParag.addAll(cleanSplitText(paragraphs.get(i).getParagraph()));
			Boolean exists = false;
			exists = wordsInParag.contains(searchword.toLowerCase());
			if(exists) {
				frequency = Collections.frequency(wordsInParag, searchword.toLowerCase());
				System.out.println((i+1) + "		"
						+ frequency + "x");
				consoleMSG = "Enter a command: ";
			} else {
				consoleMSG = "-- The word wasn't found in text --"
						+ System.lineSeparator()
						+ "Enter a command: ";
			}
		}
		return consoleMSG;
	}

	/** 
	 * PROCESS OF COMMAND: delete
	 * delete all paragraphs
	 * 
	 * @param String commandFocus 'paragraph'
	 * @param String parameter 'paragraph number'
	 * */
	public String delete(String commandFocus, String parameter) {
		String consoleMSG = null;
		if (commandFocus.equals("text")) {
			if(parameter.trim().equals("")) {
				//delete the whole text (all paragraphs)
				paragraphs = new ArrayList<>();
				consoleMSG = "All paragraphs of text successully deleted."
						+ System.lineSeparator()
						+ "Enter a command: ";
			} else {
				consoleMSG = deleteParagraph(Integer.parseInt(parameter));
			}
		} else {
			consoleMSG = "Wrong command typed for \"delete\"."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * PROCESS OF COMMAND: delete
	 * deletes paragraph of given paragraph number.
	 * 
	 * @param paragNum: the number of paragraph which it deletes
	 * 
	 */
	public String deleteParagraph(int paragNum) {
		String consoleMSG = null;
		if(paragNum <= paragraphs.size() && paragNum > 0) {
			paragraphs.remove(paragNum - 1);
			consoleMSG = "Paragraph " + paragNum + "successfully deleted."
					+ System.lineSeparator()
					+ "Enter a command: ";
		} else {
			consoleMSG = "Invalid paragraph number: " + paragNum + System.lineSeparator()
			+ "You're able to get an overview of the whole text with \"overview text\""
			+ System.lineSeparator() + "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * PROCESS OF COMMAND: searchreplace
	 * replaces a word1 with a new word2 within all paragraphs 
	 * 
	 * @param String commandFocus 'text'
	 * @param String commandFocus 'word1 word2'
	 */
	public String searchReplace(String commandFocus, String parameter) {
		String consoleMSG = null;
		if(commandFocus.equals("text") && !parameter.equals("")) {
			String[] searchReplaceWords = parameter.split(" ",3);
			if(searchReplaceWords.length == 2) {
				consoleMSG = replaceWord(searchReplaceWords[0], searchReplaceWords[1]);
			} else {
				consoleMSG = "Invalid argument typed for 'word' in command \"searchreplace\"."
						+ System.lineSeparator()
						+ "Enter a command: ";
			}
		} else {
			consoleMSG = "Wrong command typed for \"searchreplace\"."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/** 
	 * refers to the method searchReplace(String commandFocus, String parameter).
	 * replaces a word with a new word within all paragraphs 
	 * @param word1 word for replacing
	 * @param word2 word to replace with
	 * 
	 */
	private String replaceWord(String word1, String word2) {
		String consoleMSG = null;
		patt = Pattern.compile("\\b"+word1+"\\b");
			for (int i = 0; i < paragraphs.size(); i++) {
				match = patt.matcher(paragraphs.get(i).getParagraph());
				String replacedText = match.replaceAll(word2);
				replaceParagraph(i+1, replacedText);
			}
			consoleMSG = "searched word (" + word1
					+ ") replaced with: " + word2 
					+ System.lineSeparator()
					+ "Enter a command: ";
		return consoleMSG;
	}

	/** 
	 * PROCESS OF COMMAND: replace
	 * replace a paragraph text with a new text 
	 * 
	 * @param String commandFocus 'paragraph'
	 * @param String parameter 'paragraphnumber text'
	 * paragraph number followed by text to replace with
	 * 
	 */
	public String replace(String commandFocus, String parameter) {
		String consoleMSG = null;
		if (commandFocus.equals("paragraph")) {
			String[] parameters = parameter.trim().split(" ",2);
			if(isInt(parameters[0])) {
				if (paragraphs.size() >= Integer.parseInt(parameters[0])) {
				consoleMSG = replaceParagraph(Integer.parseInt(parameters[0]), parameters[1]);
				} else {
					consoleMSG = "Wrong command format typed for \"replace\". "
							+ "Please enter a valid paragraph number."
							+ System.lineSeparator()
							+ "Enter a command: ";
				}
			} else {
				consoleMSG = "Wrong command format typed for \"replace\". "
						+ "Please enter a valid paragraph number."
						+ System.lineSeparator()
						+ "Enter a command: ";
			}
		} else {
			consoleMSG = "Wrong command typed for \"replace\"."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	/* 
	 * PROCESS OF COMMAND: replace
	 * replace a paragraph text of given paragraph number with a new text 
	 * 
	 * @param paragNum 'paragraph number'
	 * @param text 'text' new text
	 * */
	private String replaceParagraph(int paragNum, String text) {
		String consoleMSG = null;
		if(paragNum <= this.paragraphs.size() && paragNum > 0) {
			this.paragraphs.set(paragNum-1, new Paragraph(text));
			consoleMSG = "Paragraph " + paragNum + " successfully replaced."
					+ System.lineSeparator()
					+ "Enter a command: ";
		} else {
			consoleMSG = "Invalid paragraph number: " + paragNum + System.lineSeparator()
			+ "You're able to get an overview of the whole text with \"overview text\""
			+ System.lineSeparator()
			+ "Enter a command: ";
		}
		return consoleMSG;
	}

	/*
	 * PROCESS OF COMMAND: edit width 'number'
	 * set text width to given length of chars
	 * 
	 * @param String commandFocus 'width'
	 * @param parameter 'text width'
	 */
	public String editWidth(String commandFocus, String parameter) {
		String consoleMSG = null;
		if (commandFocus.equals("width")) {
			String[] parameters = parameter.trim().split(" ",2);
			if(isInt(parameters[0])) {
				consoleMSG = setWidth(Integer.parseInt(parameters[0]));
			} else {
				consoleMSG = "Wrong command format typed for \"edit\". "
							+ "Please enter a valid amount of characters for 'width'."
							+ System.lineSeparator()
							+ "Enter a command: ";
			}
		} else {
			consoleMSG = "Wrong command typed for \"edit\""
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	
	
	
	/* 
	 * refers to editWidth()
	 * save the max. amount of characters for line width 
	 * 
	 * @param width 'max. chars for a line'
	 * */
	private String setWidth(int width) {
		String consoleMSG = null;
		if(width >= minWidth) {
			textWidth = width;
			consoleMSG = "Line width successfully set to " + width +" characters."
					+ System.lineSeparator()
					+ "Enter a command: ";
		} else {
			consoleMSG = "Please enter a valid number for \"edit width\". "
						+ "The line width has a minimum of " + minWidth + " characters"
						+ System.lineSeparator()
						+ "Enter a command: ";
		}
		return consoleMSG;
	}
    
    /* 
	 * refers to showParagraph(int paragNum)
	 * prints paragraphs within a given textwidth.
	 * 
	 * @param printLine 'text of paragraph'
	 * */
	private void printlnWithWidth(String printLine) {
		if (printLine.length() > textWidth) {
			for (String line : getLines(printLine)) {
				System.out.println(line);
			}
		} else {
			System.out.println(printLine);
		}
	}

	/*
	 * refers to printlnWithWidth(). splits text into Lines with given textWidth
	 */
	private ArrayList<String> getLines(String printLine) {		
		ArrayList<String> parts = new ArrayList<>();
		String substr = "";
		String textRest = printLine;
		int spaceAfter = 0;

		while (textRest.length() > textWidth) {
			
			int spaceBefore = textRest.substring(0, Math.min(textWidth, textRest.length())).lastIndexOf(" ");
			String lastTen = textRest.substring(spaceBefore + 1, Math.min(spaceBefore + 11, textRest.length()));

			if (lastTen.contains(" ")) {
				spaceAfter = lastTen.indexOf(" ") + spaceBefore + 1;

				if (spaceAfter - spaceBefore <= 10) {
					substr = textRest.substring(0, spaceAfter);
					textRest = textRest.substring(spaceAfter).trim();
				} else if (spaceAfter - spaceBefore > 10) {
					substr = textRest.substring(0, spaceBefore);
					textRest = textRest.substring(spaceBefore).trim();
				}
			} else {
				substr = textRest.substring(0, textWidth);
				textRest = textRest.substring(textWidth).trim();
			}
			parts.add(substr.trim());
		}

		parts.add(textRest.trim());
		return parts;
	}

	/**
	 * PROCESS OF COMMAND: show 
	 * 
	 * @param commandFocus 'text': 
	 * shows whole text with paragraph numbers
	 * in a given text width.
	 * 
	 * @param commandFocus 'dictionary': 
	 * shows all words, their frequencies 
	 * more than 2 within a paragraph, paragraph number
	 * @return consoleMSG: message for user on console
	 */
	public String show(String commandFocus, String parameter) {
		String consoleMSG = null;
		if (commandFocus.equals("text")) {
			if(parameter.trim().equals("")) {
				//show the whole text (all paragraphs)
				for(int i=0; i < paragraphs.size(); i++) {
					consoleMSG = showParagraph(i+1);
				}
			} else {
				consoleMSG = showParagraph(Integer.parseInt(parameter));
			}
		} else if(commandFocus.equals("dictionary")) {
			showWords(2);
			consoleMSG = "Enter a command: ";
			//TODO use dictionary in Dictionary class...
		} else {
			consoleMSG = "Wrong command typed for \"show\""
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	
	/*
	 * refers to methods overview() and show()
	 * shows words, their frequency more thann given within a paragraph, paragraph number
	 * 
	 * @param wordfreq 'word frequency'
	 * prints all words with greater frequencies than given.
	 * */
	private void showWords(int wordfreq) {
		String word1;
		int frequency = 0;
		searchedWords.clear();
		//cleans, sorts out words from paragraph
		for (int i = 0; i < paragraphs.size(); i++) {
			searchedWords.addAll(cleanSplitText(paragraphs.get(i).getParagraph()));
		}
		Iterator<String> iter = searchedWords.iterator();
		
		System.out.println("Paragraph No:	Word:	Frequency:");
		
		while (iter.hasNext()) {
			word1 = iter.next();
			for (int i = 0; i < paragraphs.size(); i++) {
				wordsInParag.clear();
				wordsInParag.addAll(cleanSplitText(paragraphs.get(i).getParagraph()));
				frequency = Collections.frequency(wordsInParag, word1);
				if(frequency > wordfreq) {
					System.out.println(i+1 + "		" +
										word1 + "		" +
										frequency);
				}
			}
		}	
	}
	
	
	/**
	 * refers to show(String commandFocus, String parameter)
	 * 
	 * shows the paragraph with desired paragraph number.
	 *  
	 * @param paragNum: paragraph number to print
	 * @return consoleMSG: message for user on console
	 */
	private String showParagraph(int paragNum) {
		String consoleMSG = null;
		if(paragNum <= paragraphs.size() && paragNum > 0) {
//			System.out.print(paragNum + ". ");
			printlnWithWidth(paragNum + ". " + paragraphs.get(paragNum - 1).getParagraph());
			System.out.println();
			consoleMSG = "Enter a command: ";
		} else {
			consoleMSG = "Invalid paragraph number."
					+ System.lineSeparator()
					+ "Enter a command: ";
		}
		return consoleMSG;
	}
	
	
	
	/* 
	 * refers to the method showWords()
	 * cleans the text from symbols, splits words in lowercase as a list
	 * and cleans from duplicates. 
	 * @param parag 'text of a paragraph'
	 * */
	private List<String> cleanSplitText(String parag) {
		patt = Pattern.compile("[a-zA-ZÖÄÜöäü]+");
		words.clear();
			match = patt.matcher(parag.toLowerCase());
			while (match.find()) {
				words.addAll(Arrays.asList(match.group()));
			}
		return words;
	}
	
	/** 
	 * refers to methodss editWidth(), replace()
	 * 
	 * Checks a String if it's a number (int) or not.
	 * 
	 * @param String vlaue: a String for checking for number
	 * @return boolean:	true, if the value is a number
	 * 					false, if not
	 */
	private static boolean isInt(String value) {
		 try {
		    int number = Integer.parseInt(value);
		    return (number < 2147483647) && (number > -2147483648);
		 }
		 catch(NumberFormatException e) {
		   return false;
		 }
		}
	
	/**
	 * Removes all objects within the ArrayList paragraphs. Serves for testing purposes.
	 */
	public void emptyText() {
		paragraphs.removeAll(paragraphs);
	}
	
	/**
	 * Returns the selected paragraph within the ArrayList paragraphs. Serves for testing purposes
	 * 
	 * @param index
	 * @return paragraph
	 */
	public String getParagraphForTest(int index) {
		String paragraph = paragraphs.get(index).getParagraph();
		return paragraph;
	}
	
	/**
	 * Returns the text width. Serves for testing purposes.
	 * 
	 * @return paragraph
	 */
	public int getWidthForTest() {
		return textWidth;
	}
	
}
