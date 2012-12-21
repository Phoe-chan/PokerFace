import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class GameWindow extends JPanel{
		//Constants
		public static int Height = 480;
		public static int Width = 640;

		//Game Variables
		static Game myGame; //this will control the entire game

		//Double Buffering variables.
		Image offscreenImage;
		Graphics offscr;

    // Create a constructor method
    public GameWindow(){
        super();
    }

	/**
	 * Return the window size, doesn't do anything it would seem.
	 */
	public Dimension getPreferredSize() {
	    Insets insets = getInsets();
	    int width = Width + insets.left + insets.right;
	    int height = Height + insets.top + insets.bottom;
	    return new Dimension(width, height);
	}

    public void paintComponent(Graphics g){
    	offscreenImage = createImage(Width, Height);
    	offscr = offscreenImage.getGraphics();
    	offscr.clearRect(0,0,Width,Height);
		
		myGame.paint(offscr);
		
    	g.drawImage(offscreenImage,0,0,this);
		g.drawLine(0,0,Width,Height);
		g.drawLine(0,Height,Width,0);
    }

	public void init() {
	}

    public static void main(String arg[]){
        JFrame fframe = new JFrame("Texas");
        fframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fframe.setSize(Width+17,Height+39);

        GameWindow ppanel = new GameWindow();
        ppanel.setFocusable(true);
        ppanel.requestFocusInWindow();
		ppanel.setSize(Width,Height);
		
		myGame = new Game(arg, ppanel);  //create the game based on the parameters passed through.
		
		ppanel.addKeyListener(new KeypressHandler(myGame));
	 	   					
        fframe.setContentPane(ppanel);
        fframe.setVisible(true);
    }
	    
	/** 
	 * The frame rate is based on an estimate of the time it takes to paint the 
	 * frame. Our estimate is based on the average elapsed time required to 
	 * paint the frame to an offscreen image plus 10%.  The extra 10% is 
	 * to try and guard against making the frame rate so fast that 
	 * slight variations in the performance of paintComponent will make the 
	 * TimerTask fall behind.  
	 
	 throwing exceptions, commented until needed.
	 
	public long computeFrameRate() {
	    Graphics g = createImage(Width, Height).getGraphics();
	    long dt = 0;
	    paintComponent(g);
	    for(int i = 0; i < 10; i++) {
		long startTime = System.currentTimeMillis();
		paintComponent(g);
		dt += System.currentTimeMillis() - startTime;
	    }
	    return (long)((float)(dt / 10) * 1.1f);
	}
	*/
	
	//Testing toy functionality
	int myNum = 0;

    public void addNumber(int nNumber)
    {
    	myNum = myNum + nNumber;
    	repaint();
    }
}
