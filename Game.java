import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;

public class Game implements Runnable
{
	//Display Window.
	GameWindow myWindow;

	//timer
	private final java.util.Timer timer = new java.util.Timer();

	//rng
	private Random randomGenerator = new Random();
	
	//Game Objects
	Dealer dealingPlayer;
	ArrayList<Player> seats;
	ArrayList<Card> sharedHand;
	Player humanPlayer;
	Player currentPlayer;
	ArrayList<Pot> roundsPot;
	BalanceNode theNode;
	int movementFactor;


	public Game(String arg[], GameWindow inwindow)
	{
		movementFactor = 0;
		this.myWindow = inwindow;
		seats = new ArrayList<Player>();
		roundsPot = new ArrayList<Pot>();
		sharedHand = new ArrayList<Card>();
		
		humanPlayer = new Player("Player");
		currentPlayer = humanPlayer;
		dealingPlayer = new Dealer(0,0,humanPlayer);
		seats.add(humanPlayer);
		seats.add(new Player("Foe1"));
		seats.add(new Player("Foe2"));
		seats.add(new Player("Foe3"));
		seats.add(new Player("Foe4"));

		newRound();

		dealingPlayer.startDeal(seats.get(2), new Card(0,13));
		
		run();
	}
	
	public void run()
	{
		long frameRate = (1000/40);
		SwingTimerTask gameTimer = new SwingTimerTask(){
			public void doRun(){
				gameTick();
			}
		};
		SwingTimerTask secondTimer = new SwingTimerTask(){
			public void doRun(){
				secondTick();
			}
		};
		timer.schedule(gameTimer, 0, frameRate);
		timer.schedule(secondTimer, 0, 1000);
	}
	
	public void gameTick()
	{
		if (theNode != null)
		{
			theNode.nudge(movementFactor);
			theNode.roll();
		}
		dealingPlayer.animate();
		myWindow.repaint();
	}
	
	public void secondTick()
	{
		if (theNode != null)
		{
			int randomInt = randomGenerator.nextInt(100);
			if (randomInt < theNode.getFailChance())
			{
				theNode = null;
				humanPlayer.spottedCheating();
			}
		}
	}
	
	public void keydown(KeyEvent keyVal)
	{
		switch (keyVal.getKeyChar())
		{
			case 'a':
				movementFactor = -3;
				break;
			case 'd':
				movementFactor = 3;
				break;
			case 'b':
				theNode = new BalanceNode(-1, false);
				break;
			case 'n':
				newRound();
				dealingPlayer.startDeal(seats.get(2), new Card(3,3));
				break;
			case 'q':
				System.exit(0);
				//handler to quit out here.
			default:
		}
	}

	public void keyup(KeyEvent keyVal)
	{
		switch (keyVal.getKeyChar())
		{
			case 'a':
				movementFactor = 0;
				break;
			case 'd':
				movementFactor = 0;
				break;
			default:
		}
	}
	
	private int getNodePosition()
	{
		if (theNode != null)
			return theNode.getNodePosition();
		return 0;
	}
	
	private boolean inBalanceMode()
	{
		if(theNode != null)
			return true;
		return false;
	}
	
	//Draw all of the game components to the supplied graphics object.
    public void paint(Graphics g){
		int offsetX = 10;
		int offsetY = 20;
		int colNum = 0;
		int rowNum = 0;
		int colWidth = (int)Math.round(myWindow.Width / 4);
		int rowHeight = 200;
		for(Player player : seats)
		{
			player.paint(g, offsetX + (colNum * colWidth), offsetY + (rowNum * rowHeight));
			colNum++;
			if(colNum > 3)
			{
				colNum = 0;
				rowNum++;
			}
		}
		
		dealingPlayer.paint(g);
		
		if(inBalanceMode())
			theNode.paint(g, (int)(myWindow.Width / 2), (int)(myWindow.Height * .90));
	}
	
	private void newRound()
	{
		roundsPot.clear();
		Pot mainPot = new Pot(seats);
		roundsPot.add(mainPot);
		sharedHand.clear();
		for(Player player : seats)
		{
			player.newRound();
		}
	}
}
