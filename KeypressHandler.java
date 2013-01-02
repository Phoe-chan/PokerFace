import java.awt.*;
import java.awt.event.*;

public class KeypressHandler implements KeyListener {
	Game myGame;
	KeyMapper mapper;
	
	public KeypressHandler(Game theGame, KeyMapper theMapper) {
		myGame = theGame;
		mapper = theMapper;
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void keyPressed(KeyEvent e) {
		myGame.keydown(mapper.getEvent(e));
	}
	
	public void keyReleased(KeyEvent e) {
		myGame.keyup(mapper.getEvent(e));
	}
}
