package plot3Dnew;

import java.awt.Graphics;

public interface GraphElement {

	public default void draw(Graphics g) {}
	
	public default void update(PlotPanel p) {}
	
}
