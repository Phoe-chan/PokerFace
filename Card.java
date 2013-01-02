import java.awt.*;

public class Card {
	int mySuit;
	int myValue;
	
	public Card(int suit, int value) {
		mySuit = suit;
		myValue = value;
	}
	
	public void paint(Graphics g, int offsetX, int offsetY) {
		g.setColor(Color.black);
		g.drawLine(offsetX+8,offsetY+0,offsetX+8,offsetY+50);
		g.drawLine(offsetX+8,offsetY+50,offsetX+40,offsetY+50);
		g.drawLine(offsetX+40,offsetY+50,offsetX+40,offsetY+0);
		g.drawLine(offsetX+40,offsetY+0,offsetX+8,offsetY+0);
		g.drawString(getDisplayFace(myValue), offsetX+20, offsetY+30);
		g.setColor(getDisplayColour(mySuit));
		g.drawString(getDisplaySuit(mySuit),(offsetX+10), offsetY+10);
		g.drawString(getDisplaySuit(mySuit),(offsetX+30), offsetY+50);
	}
	
	public int getSuit() {
		return mySuit;
	}
	
	public int getValue() {
		return myValue;
	}
	
	private String getDisplayFace(int thisValue) {
		switch (thisValue) {
			case 13:  return "K";
			case 12:  return "Q";
			case 11:  return "J";
			case 1:  return "A";
			default: return Integer.toString(mySuit);
		}
	}
	
	private String getDisplaySuit(int thisSuit) {
		switch (thisSuit) {
			case 0:  return "H";
			case 1:  return "D";
			case 2:  return "S";
			case 3:  return "C";
			default: return "?";
		}
	}
	
	private Color getDisplayColour(int thisSuit) {
		switch (thisSuit) {
			case 0:  return Color.red;
			case 1:  return Color.red;
			default: return Color.black;
		}
	}
}
