package plot3Dnew;

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlotPanel extends JPanel
{
		
		public static final int ComputerWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		public static final int ComputerHeight  = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		public ArrayList<GraphElement> master = new ArrayList<GraphElement>();
			
		public double DomUp = 3;
		public double DomDown = -3;
		public double DomL = -5;
		public double DomR = 5;
		public double pf = 10;
		
		public double windowL =  DomL;
		public double windowR = DomR;
		public double windowUp = DomUp;
		public double windowDown = DomDown;
		
		public Point Origen = new Point(0,0,0);
		
		public double dist = 50;
		
		public double origx = 0;
		public double origy = 0;
		public double origz = 0;
		
		public double theta = 0;
		public double phi = 0;
		public double gamma = 0;
		public double reftheta = 0;
		public double refphi = 0;
	
			public PlotPanel()
			{
				this.setVisible(true);
				this.setBounds(0,0,ComputerWidth,ComputerHeight);
			}
			
		
			public void paint(Graphics g)
			{
				super.paint(g);
				paintBackground(g);
				Action(g);
			}
			
			public void Action (Graphics g) 
			{
				for(GraphElement p : master) 
				{
					p.update(this);
					p.draw(g);
				}
			}
	
			public void paintBackground(Graphics g) {
				
				for (int i = 1; i < 100;i++){
				
					g.setColor(new Color(100-i,125-i,150-i));
					g.fillOval(-1000+20*i,-500+15*i,(int) (ComputerWidth-100*i+500)+1000,(int) (ComputerHeight-100*0.5*i+300)+1000);
					
				}
				
			}
	
			public void execute(){
					
				// Add what ever GraphElements you want in this block to the environment
				
				try {
					
					readSTLbinary.read(this);
			
				}catch(Exception ep) {
						
				}
		
				master.add(Origen);
				
			
			}
	
}