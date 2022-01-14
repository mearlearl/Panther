package plot3Dnew;

import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PantherFrame extends JFrame
{

	private static int ComputerWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static int ComputerHeight  = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public PlotPanel panel;
	
		public PantherFrame()
		{
			
			/*
			 *This is a straightforward JFrame constructor.
			 *A lot of variables are set as public static which is intentional
			*/
			
			this.setTitle("Panther Project");
			this.setVisible(true);
			this.setBounds(0,0,ComputerWidth, ComputerHeight);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Add an instance of PlotPanel 
			
			panel = new PlotPanel();
			this.add(panel);
	
			// Add the Mouse Listeners
			
			MouseInterface mi = new MouseInterface(this);
			this.addMouseListener(mi);
			this.addMouseMotionListener(new MouseMotionInterface(this,mi));
			this.addMouseWheelListener(new MouseWheelInterface(this));
		}
	

		public void paint(Graphics g)
		{
		 super.paint(g);
		}
	
}

