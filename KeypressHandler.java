import java.awt.*;
import java.awt.event.*;

public class KeypressHandler implements KeyListener
{
	Game myGame;
	
	public KeypressHandler(Game theGame)
	{
		myGame = theGame;
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void keyPressed(KeyEvent e)
	{
		myGame.keydown(e);
	}
	
	public void keyReleased(KeyEvent e)
	{
		myGame.keyup(e);
	}
}