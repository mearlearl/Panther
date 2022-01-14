package plot3Dnew;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInterface implements MouseListener{
	
	public int mpx = 0;
	public int mpy = 0;
	public boolean pressed = false;
	
		public MouseInterface(PantherFrame Frame)
		{
			
		}
	
		public void mousePressed(MouseEvent e) 
	    {
			
			if(e.getButton() == MouseEvent.BUTTON1) 
			{
				pressed = true;
				mpx = e.getX();
				mpy = e.getY();
			}
			
		}

		public void mouseReleased(MouseEvent e) 
		{
			if (e.getButton() == MouseEvent.BUTTON1) 
			{
				pressed = false;
			}
		}

		//UNSPECIFIED INTERFACE METHODS
		
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			// TODO Auto-generated method stub
		}
	
		@Override
		public void mouseExited(MouseEvent e) 
		{
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
		}

}
	class MouseMotionInterface implements MouseMotionListener
	{
		
		MouseInterface MI;
		PantherFrame frame;
		
			public MouseMotionInterface(PantherFrame frm, MouseInterface mif) 
			{
				this.frame = frm;
				this.MI = mif;
			}
		
			public void mouseDragged(MouseEvent e) 
			{
				if(MI.pressed) 
				{
					int dx = e.getX()-(MI.mpx);
					int dy = e.getY()-(MI.mpy);
					MI.mpx = e.getX();
					MI.mpy = e.getY();
					
					frame.panel.theta += ((Math.PI/24)*dx/50);
					frame.panel.phi += ((Math.PI/24)*dy/50);
					frame.panel.repaint();
				}
			}

			//UNSPECIFIED INTERFACE METHODS
			
			public void mouseMoved(MouseEvent e) 
			{
				// TODO Auto-generated method stub
			}
		
	}
	
	class MouseWheelInterface implements MouseWheelListener
	{

		PantherFrame frame;
		
			public MouseWheelInterface(PantherFrame frm) 
			{
				this.frame = frm;	
			}
		
			public void mouseWheelMoved(MouseWheelEvent e) 
			{
				
				if ((e.getWheelRotation() >= 1)) 
				{
					frame.panel.dist *= 1.1;		
					
				}else if((e.getWheelRotation() <= 1)&&(frame.panel.dist>=20)) 
				{
					frame.panel.dist /= 1.1;
				}
				
				frame.panel.repaint();
				
			}
			
	}
	

