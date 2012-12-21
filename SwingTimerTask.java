import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.util.TimerTask;
import java.util.Timer;

abstract class SwingTimerTask extends java.util.TimerTask {
    public abstract void doRun();
    public void run() {
	if (!EventQueue.isDispatchThread()) {
	    EventQueue.invokeLater(this);
	} else {
	    doRun();
	}
    }
}