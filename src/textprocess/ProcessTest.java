/**
 * 
 */
package textprocess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains all automated tests of the class Process.java.
 * This is not a complete test. Chosen methods are be tested manually.
 * 
 * @author M. Schoch, F. Schellenberg
 * version: 1.1
 */
class ProcessTest {
		
	static Process process;
	static String[] paragraphTestArray = {"Dies ist der Test Paragraph 1, um die Funktionalität des Texteditors zu überprüfen",
										"Dies ist der Test Paragraph 2, um die Funktionalität des Texteditors zu überprüfen",
										"Dies ist der Test Paragraph 3, um die Funktionalität des Texteditors zu überprüfen"};
	Process processTest = new Process();
	
	/**
	 * Setup before each test method
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		processTest.emptyText();
		for(int i = 0; i < 3; i++)
			processTest.addText("paragraph", paragraphTestArray[i]);
	}

	/**
	 * Tear down after each test method
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		processTest.emptyText();
	}
	
	/**
	 * Testing of addText()
	 * Tests consoleMSG if adding a paragraph is successful
	 */
	@Test
	void addTextTest() {
		String consoleMSGTest = processTest.addText("paragraph", paragraphTestArray[0]);
		assertEquals("paragraph successfully added."
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of addText()
	 * Tests consoleMSG if the commandFocus input is unknown
	 */
	@Test
	void addTextTestFail() {
		String consoleMSGTest = processTest.addText("fail", paragraphTestArray[0]);
		assertEquals("Wrong command typed for \"add\" " 
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of addText()
	 * Tests if adding a new paragraph to ArrayList is successful
	 */
	@Test 
	void addTextTestParagraphAdded(){
		processTest.addText("paragraph", paragraphTestArray[0]);
		assertEquals(paragraphTestArray[0], processTest.getParagraphForTest(0));
	}
	
	/**
	 * Testing of overviewText()
	 * Tests consoleMSG if the commandFocus input is unknown
	 */
	@Test 
	void overviewTextTestFail(){
		String consoleMSGTest = processTest.overviewText("fail");
		assertEquals("Wrong command typed for \"overview\"." 
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of search() 
	 * Tests consoleMSG if the searched word is successfully found.
	 */
	@Test
	void searchTest() {
		String consoleMSGTest = processTest.search("text", "Dies");
		assertEquals("Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of search() 
	 * Tests consoleMSG if the commandFocus input is unknown
	 */
	@Test
	void searchTestFailFocus() {
		String consoleMSGTest = processTest.search("fail", "Hello");
		assertEquals("Wrong command typed for \"search\"." 
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of search() 
	 * Tests consoleMSG if the Parameter input (searching word) is not found
	 * 	 */
	@Test
	void searchTestFailParameter() {
		String consoleMSGTest = processTest.search("text", "Hello");
		assertEquals("-- The word wasn't found in text --"
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of editWidth() 
	 * Tests consoleMSG if text width is successfully edited
	 * 	 */
	@Test
	void editWidthTest() {
		String consoleMSGTest = processTest.editWidth("width", "20");
		assertEquals("Line width successfully set to 20 characters."
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of editWidth() 
	 * Tests consoleMSG if the commandFocus input is unknown
	 * 	 */
	@Test
	
	void editWidthTestFailFocus(){
		String consoleMSGTest = processTest.editWidth("fail", "30");
		assertEquals("Wrong command typed for \"edit\""
				+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of editWidth() 
	 * Tests consoleMSG if defined minimum width of 10 is deceeded
	 * 	 */
	@Test
	void editWidthTestFailMinWidth() {
		String consoleMSGTest = processTest.editWidth("width", "5");
		assertEquals("Please enter a valid number for \"edit width\". "
						+ "The line width has a minimum of 10 characters"
						+ System.lineSeparator() + "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Testing of editWidth() 
	 * Tests if editing text width is successful
	 * 	 */
	@Test
	void editWidthTestWidthChanged() {
		int widthTest = 50;
		processTest.editWidth("width", Integer.toString(widthTest));
		assertEquals(widthTest, processTest.getWidthForTest());
	}
	
	/**
	 * Testing of editWidth() 
	 * Tests if editing text width has not changed if the defined minimum text width of 10 is deceeded.
	 * 	 */
	@Test 
	void editWidthTestWidthChangedFail(){
		int widthTest = 5;
		processTest.editWidth("width", Integer.toString(widthTest));
		assertEquals(60, processTest.getWidthForTest());
	}

	/**
	 * Testing of randomText() 
	 * Tests if the random paragraph is successfully added to the ArrayList paragraphs.
	 * 	 */
	@Test
	void randomTextTestAdded() {
		processTest.randomText();
		assertEquals("Quisque quis malesuada dolor, semper vestibulum diam. "
				+ "Vivamus quis tortor dui. Quisque suscipit sodales ex non aliquet. "
				+ "Nulla et congue odio. Suspendisse consequat facilisis lectus, "
				+ "sed condimentum erat vestibulum ut. Pellentesque commodo congue elementum. "
				+ "Duis nec porttitor sapien, porta iaculis lorem. Curabitur luctus lectus mauris. ", 
				processTest.getParagraphForTest(4));
	}
	
	/**
	 * Tests for correct error message when a wrong paragraph number
	 * is given.
	 */
	@Test
	void showParagraphTestFail() {
		String paragNumTest = "4";
		String consoleMSGTest = processTest.show("text", paragNumTest);
		assertEquals("Invalid paragraph number."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Tests if search and replace is working.
	 * Checks for the correct reply message and if the new word
	 * got written to the ArrayList
	 */
	@Test 
	void searchreplaceTest() {
		int paragNumTest = 1;
		String replaceWords = new String("Paragraph Absatz"); 
		String consoleMSGTest = processTest.searchReplace("text", replaceWords);
	    String getParagraphForTest = processTest.getParagraphForTest(paragNumTest);
	    assertEquals("searched word (Paragraph) replaced with: Absatz"
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
        assertEquals("Dies ist der Test Absatz 2, um die Funktionalität des Texteditors zu überprüfen", getParagraphForTest);
	}

	/**
	 * Tests if search and replace does nothing when only one word is given
	 */	
	@Test
	void replaceWordTestFail() {
		int paragNumTest = 2;
		String replaceWords = new String("Paragraph"); 
		String consoleMSGTest = processTest.searchReplace("text", replaceWords);
	    String getParagraphForTest = processTest.getParagraphForTest(paragNumTest);
	    assertEquals("Invalid argument typed for 'word' in command \"searchreplace\"."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
	    assertEquals(paragraphTestArray[2], getParagraphForTest);
	    
	}

	/**
	 * Tests if replace paragraph is working.
	 * Checks for the correct reply message and if the new word
	 * got written to the ArrayList
	 */
	@Test
	void replaceTest() {
		int paragNumTest = 2;
		String newParagraph = "2 In dem 3 Paragraphen steht nun ein komplett anderer Text drin."; 
		String consoleMSGTest = processTest.replace("paragraph", newParagraph);
	    String getParagraphForTest = processTest.getParagraphForTest(1);
	    assertEquals("Paragraph " + paragNumTest + " successfully replaced."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
        assertEquals("In dem 3 Paragraphen steht nun ein komplett anderer Text drin.", getParagraphForTest);
	}
	
	/**
	 * Tests if replace paragraph fails when a wrong paragraph number is given
	 */
	@Test
	void replaceTestFail() {
		String newParagraph = "5 In dem 5 Paragraphen steht nun ein komplett anderer Text drin."; 
		String consoleMSGTest = processTest.replace("paragraph", newParagraph);
	    assertEquals("Wrong command format typed for \"replace\". "
				+ "Please enter a valid paragraph number."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
	}
	/**
	 * Tests if delete text is working.
	 */
	@Test
	void deleteTest() {
		String consoleMSGTest = processTest.delete("text", "");
        assertEquals("All paragraphs of text successully deleted."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Tests if delete text is failing when wrong command word is given
	 * and checks that the text is still present.
	 */	
	@Test
	void deleteTestFail() {
		int paragNumTest = 2;
		String consoleMSGTest = processTest.delete("file", "");
	    String getParagraphForTest = processTest.getParagraphForTest(paragNumTest);
        assertEquals("Wrong command typed for \"delete\"."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
        assertEquals(paragraphTestArray[paragNumTest], getParagraphForTest);
	}
	
	/**
	 * Tests if delete paragraph is working
	 * and checks that the paragraph got deleted from the ArrayList
	 */	
	@Test
	void deleteParagraphTest() {
		int paragNumTest = 1;
		String consoleMSGTest = processTest.deleteParagraph(paragNumTest);
	    String getParagraphForTest = processTest.getParagraphForTest(paragNumTest);
	    assertEquals("Paragraph " + paragNumTest + "successfully deleted."
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
        assertEquals(paragraphTestArray[2], getParagraphForTest);
	}
	
	/**
	 * Tests if delete paragraph is failing when a wrong
	 * paragraph number is given
	 */	
	@Test
	void deleteParagraphTestFail() {
		int paragNumTest = 5;
		String consoleMSGTest = processTest.deleteParagraph(paragNumTest);
        assertEquals("Invalid paragraph number: " + paragNumTest 
        		+ System.lineSeparator()
        		+ "You're able to get an overview of the whole text with \"overview text\""
        		+ System.lineSeparator()
        		+ "Enter a command: ", consoleMSGTest);
	}
	
	/**
	 * Tests if show text with paragraph number doesn't work
	 * when a wrong paragraph number is given, which doesn't exists.
	 */	
	@Test
	void showTextFail() {
		String paragNumTest = "5";
		String consoleMSGTest = processTest.show("text", paragNumTest);
        assertEquals("Invalid paragraph number." 
        		+ System.lineSeparator()
        		+ "Enter a command: ", consoleMSGTest);
	}

	/**
	 * Tests for correct error message if a wrong focus word is given
	 * for show text 
	 */	
	@Test
	void showFail() {
		String consoleMSGTest = processTest.show("fail", paragraphTestArray[0]);
		assertEquals("Wrong command typed for \"show\""
				+ System.lineSeparator()
				+ "Enter a command: ", consoleMSGTest);
	}
		
}
