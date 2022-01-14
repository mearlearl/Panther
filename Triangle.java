package plot3Dnew;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle implements GraphElement 
{

	public Point p1;
	public Point p2;
	public Point p3;
	
	public Color col;
	
		public Triangle(Point pp1,Point pp2,Point pp3) 
		{
			this.p1 = pp1;
			this.p2 = pp2;
			this.p3 = pp3;
			
		}
	
		public Triangle(Point pp1,Point pp2,Point pp3,Color C) 
		{
			this.p1 = pp1;
			this.p2 = pp2;
			this.p3 = pp3;
			this.col = C;
			
		}
	
		public void update(PlotPanel p) 
		{
			p1.update(p);
			p2.update(p);
			p3.update(p);
		}
		
		public void draw(Graphics g) 
		{
			
			g.setColor(Color.WHITE);
			
			int[] xx = {p1.px,p2.px,p3.px};
			int[] yy = {p1.py,p2.py,p3.py};
			
			if (col != null)
			{
				g.setColor(new Color((float)(col.getRed()/255.0),(float)(col.getGreen()/255.0),(float)(col.getBlue()/255.0),0.4f));
			}
			
			g.fillPolygon(xx,yy,3);
			g.setColor(Color.BLACK);
			
		}
	
}
