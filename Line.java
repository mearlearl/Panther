package plot3Dnew;

import java.awt.Color;
import java.awt.Graphics;

public class Line implements GraphElement
{

	public Point p1;
	public Point p2;
	public Color col = null;
	
		public Line(Point p1,Point p2) 
		{
			
			this.p1 = p1;
			this.p2 = p2;
			
		}
	
		public Line(Point p1,Point p2,Color C) 
		{
			this.col = C;
			this.p1 = p1;
			this.p2 = p2;	
		}
	
		public void draw(Graphics g) {
			
			g.setColor(Color.BLACK);
			
			if (col != null) {
				g.setColor(col);
			}
			
			g.drawLine(p1.px,p1.py,p2.px,p2.py);
			g.drawLine(p1.px+1,p1.py+1,p2.px-1,p2.py-1);
			g.drawLine(p1.px-1,p1.py-1,p2.px+1,p2.py+1);
		}
		
		public void update(PlotPanel p) {
			
			p1.update(p);
			p2.update(p);
			
		}
	
}