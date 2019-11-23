package textprocess;

/**
 * This class generates a new Paragraph object, providing it to the Process class.
 * 
 * @author M. Schoch, IT19ta_WIN, ZHAW
 * @version v.1.2, 10.11.2019
 * 
 */

public class Paragraph{
		
	private String paragraph;
		
	public Paragraph(String paragraph) {
		this.paragraph = paragraph;
	}

/**
 * This method returns the paragraph.
 * 
 * @return paragraph
 */

	public String getParagraph() {
		return paragraph;
	}
	
	public int getSize() {
		return paragraph.split(" ").length;
	}
}