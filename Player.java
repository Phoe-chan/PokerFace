import java.awt.*;
import java.util.ArrayList;

public class Player
{
	String myName;
	ArrayList<Card> hand;
	double wallet;
	double lastBet;
	private int myXOffset = 1;
	private int myYOffset = 1;
	
	public Player(String name)
	{
		myName = name;
		wallet = 500;
		lastBet = 0;
		hand = new ArrayList<Card>(2);
	}
	
	public boolean placeBet(double value)
	{
		if (value > wallet) return false;
		if (value < lastBet) return false;
		lastBet = value;
		wallet = wallet - value;
		return true;
	}
	
	public void paint(Graphics g, int offsetX, int offsetY)
	{
		myXOffset = offsetX;
		myYOffset = offsetY + 5;
		
		g.setColor(Color.black);
		g.drawString(myName,offsetX, offsetY);
		int colNum = 0;
		int colWidth = 40;

		for(Card myCard : hand)
		{
			myCard.paint(g, (offsetX + (colNum * colWidth)), (offsetY + 5));
			colNum++;
		}
	}
	
	public int getXPosition()
	{
		return myXOffset;
	}
	
	public int getYPosition()
	{
		return myYOffset;
	}
	
	public void dealCard(Card dealtCard)
	{
		hand.add(dealtCard);
	}
	
	public void newRound()
	{
		lastBet = 0;
		hand.clear();
	}
	
	public boolean isBust()
	{
		return (wallet == 0);
	}
	
	public void spottedCheating()
	{
		//Hmmm?!
	}
}