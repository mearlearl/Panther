package plot3Dnew;

import java.awt.Color;
import java.awt.Graphics;

public class Point implements GraphElement
{

	public double x;
	public double y;
	public double z;
	public double tx;
	public double ty;
	public double tz;
	public double theta = 0;
	public double phi = 0;
	public double gamma = 0;
	
	
	public int px;
	public int py;
	public int size;
	
		public Point(double x, double y, double z) 
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.tx = x;
			this.ty = y;
			this.tz = z;
		}
		
	
		public void draw(Graphics g) 
		{
			
			g.setColor(new Color(50,50,100));
			g.fillOval(this.px-this.size/2,this.py-this.size/2,this.size,this.size);
			
		}
	
		public void update(PlotPanel p) 
		{
			
			/* This is a complicated procedure that determines where and how to draw points.
			 * This is the real heart of the code.  
			 * 
			 * There are two things to note:
			 * 1.  I used rotation matrices to find the new absolute position of the point
			 * 2.  Using the transformed coordinates, observer coordinates, and original coordinates
			 * 	   we can determine what pixel coordinates and pixel size correspond. 
			 * 
			 * Note: I came up with this procedure myself after taking linear algebra; it's certainly not perfect but gets the job done.
			 * The problem with sizing points based on relative distance has issues.
			 * 
			 * */
			
			double newx = tx*Math.cos(p.theta-this.theta)*Math.cos(p.phi-this.phi) - ty*Math.sin(p.theta-this.theta) + tz*Math.cos(p.theta-this.theta)*Math.sin(p.phi-this.phi);
			double newy = tx*Math.sin(p.theta-this.theta)*Math.cos(p.phi-this.phi) + ty*Math.cos(p.theta-this.theta) + tz*Math.sin(p.theta-this.theta)*Math.sin(p.phi-this.phi);
			double newz = -tx*Math.sin(p.phi-this.phi) + tz*Math.cos(p.phi-this.phi);		
			
			this.theta = p.theta;
			this.phi = p.phi;
			
			this.tx = newx;
			this.ty = newy;
			this.tz = newz;
			
			newx += p.origx;
			newy += p.origy;
			newz += p.origz;
			
			double r2 = p.dist-newx;
			double maxheight = (4+r2)/4;
			int hpix = (int) (432*newz/maxheight);
			int sizepix = (int) (40/Math.sqrt(    r2)   );
			int overpix = (int) (432*newy/maxheight);
			
			
			this.px = PlotPanel.ComputerWidth/2 +overpix;
			this.py = PlotPanel.ComputerHeight/2 -hpix;
			
			//rotate these pixels through an angle gamma;
			
			int npx = (int) (overpix*Math.cos(p.gamma)+ hpix*Math.sin(p.gamma));
			int npy = (int) (overpix*Math.sin(p.gamma)-hpix*Math.cos(p.gamma));
			
			this.gamma = p.gamma;
			
			this.px = PlotPanel.ComputerWidth/2 + npx;
			this.py = PlotPanel.ComputerHeight/2  + npy;
			
			this.size = sizepix; 
			
		}
	
	
}