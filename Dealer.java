import java.awt.*;

public class Dealer
{
	private Player currentDealer = null;
	private Card myCard = null;
	private Player myTarget = null;
	private int cardXPos = 0;
	private int cardYPos = 0;
	private int originX = 0;
	private int originY = 0;
	
	public Dealer(int startX, int startY, Player theDealer)
	{
		originX = startX;
		originY = startY;
		currentDealer = theDealer;
	}
	
	public void animate()
	{
		if(myTarget != null)
		{
			if(cardXPos < myTarget.getXPosition()) cardXPos += 5;
			if(cardYPos < myTarget.getYPosition()) cardYPos += 5;
			if(cardXPos > myTarget.getXPosition()) cardXPos -= 5;
			if(cardYPos > myTarget.getYPosition()) cardYPos -= 5;

			if	(
				(cardYPos > myTarget.getYPosition()) &&
				Math.abs(cardYPos / myTarget.getYPosition()) 
				< 	(
					(myTarget.getYPosition() + 6) / myTarget.getYPosition()
					) 
				)
				cardYPos =  myTarget.getYPosition();

			if	(
				(cardYPos < myTarget.getYPosition()) &&
				Math.abs(cardYPos / myTarget.getYPosition()) 
				> 	(
					(myTarget.getYPosition() + 6) / myTarget.getYPosition()
					) 
				)
				cardYPos =  myTarget.getYPosition();

			if	(
				(cardYPos > myTarget.getYPosition()) &&
				Math.abs(cardXPos / myTarget.getXPosition()) 
				< 	(
					(myTarget.getXPosition() + 6) / myTarget.getXPosition()
					) 
				)
				cardXPos =  myTarget.getXPosition();

			if	(
				(cardYPos < myTarget.getYPosition()) &&
				Math.abs(cardXPos / myTarget.getXPosition()) 
				> 	(
					(myTarget.getXPosition() + 6) / myTarget.getXPosition()
					) 
				)
				cardXPos =  myTarget.getXPosition();

			if((cardXPos == myTarget.getXPosition()) && (cardYPos < myTarget.getYPosition()))
			{
				myTarget.dealCard(myCard);
				cardXPos = 0;
				cardYPos = 0;
				myTarget = null;
				myCard = null;
				//Need a callback here to tell the game it can do its next event.
			}
		}
	}
	
	public void paint(Graphics g)
	{
		myCard.paint(g, cardXPos, cardYPos);
	}
	
	private void setNextDealer(Player newDealer)
	{
		currentDealer = newDealer;
	}
	
	public void startDeal(Player destination, Card dealtCard)
	{
		myCard = dealtCard;
		cardXPos = originX;
		cardYPos = originY;
		myTarget = destination;
	}

}
