import java.awt.*;
import java.awt.event.*;

public class KeyMapper {
	public PokerFaceEvent getEvent(KeyEvent e) {
		//todo: put some proper mapping configuration in here.

		PokerFaceEvent returnEvent = PokerFaceEvent.EMPTY;
                switch (e.getKeyChar()) {
                        case 'a':
                                returnEvent = PokerFaceEvent.NUDGE_LEFT;
                                break;
                        case 'd':
                                returnEvent = PokerFaceEvent.NUDGE_RIGHT;
                                break;
                        case 'b':
                                returnEvent = PokerFaceEvent.ACTIVATE_BALANCE;
                                break;
                        case 'n':
                                returnEvent = PokerFaceEvent.DEAL;
                                break;
                        case 'q':
                                returnEvent = PokerFaceEvent.QUIT;
                                break;
                        default:
                }
		return returnEvent;
	}
}
