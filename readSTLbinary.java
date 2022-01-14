package plot3Dnew;

import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;


public class readSTLbinary {

	  public static void read(PlotPanel panel) 
	  { 
	  
		  //Create a File chooser to find the .stl
		  
		  FileDialog myf = new FileDialog((java.awt.Frame) null);
		  myf.setVisible(true);
		  String filePath = myf.getDirectory()+myf.getFile();
		  String fixedfilePath = filePath.replace("\\", "\\\\");
		  File file = new File(fixedfilePath);
		  
		  byte[] bytes = readByteArray(file);
		  
		  ArrayList<Point> points = new ArrayList<Point>();
		  
		  int counter = 80;
		  
		  byte[] bits = {bytes[counter], bytes[counter+1], bytes[counter+2], bytes[counter+3]};
		  int tris = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getInt();
			
			counter += 4;
		
			for (int j = 1;j <= tris;j++) {
				
				//Add Vertices
				// First four bytes are normal vector
				counter+=12;
				
				// Add first vertex
				
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				float x1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				float y1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				float z1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();	
				points.add(new Point((double)x1,(double)y1,(double)z1));
		
				counter+=4;
				
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				x1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				y1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				z1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();	
				points.add(new Point((double)x1,(double)y1,(double)z1));
				
				counter+=4;
				
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				x1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				y1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				counter+=4;
				bits[0] = bytes[counter];
				bits[1]	= bytes[counter+1];
				bits[2] = bytes[counter+2];
				bits[3] = bytes[counter+3];
				z1 = ByteBuffer.wrap(bits).order(ByteOrder.LITTLE_ENDIAN).getFloat();	
				points.add(new Point((double)x1,(double)y1,(double)z1));
				
				counter += 6;
				
			}
				
		  	//Create triangle objects from points list and add them to the master list in panel
		  
			double xavg=0;
			double yavg=0;
			double zavg=0;
			
			for (int ii = 0;ii < points.size();ii++) {
				
				xavg += points.get(ii).x;
				yavg += points.get(ii).y;
				zavg += points.get(ii).z;
				
			}
			
			xavg = xavg/points.size();
			yavg = yavg/points.size();
			zavg = zavg/points.size();
			
		  	for (int i = 0;i <points.size()/3;i++ ) {
		  		
		  		points.get(i*3).tx -= xavg;
		  		points.get(i*3).ty -= yavg;
		  		points.get(i*3).tz -= zavg;
		  		points.get(i*3+1).tx -= xavg;
		  		points.get(i*3+1).ty -= yavg;
		  		points.get(i*3+1).tz -= zavg;
		  		points.get(i*3+2).tx -= xavg;
		  		points.get(i*3+2).ty -= yavg;
		  		points.get(i*3+2).tz -= zavg;
		  		
		  		Triangle t = new Triangle(points.get(i*3),points.get(i*3+1),points.get(i*3+2));
		  		t.col = new Color((int) Math.abs(points.get(i*3).x),(int) Math.abs(points.get(i*3).y),(int) Math.abs(points.get(i*3).z));
		  		panel.master.add(t);
		  		
		  	}
	  	
	  } 
	  
	  private static byte[] readByteArray(File file)
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         fileInputStream.close();
	        
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return bFile;
	   }
	 
	  
	} 
	
	
	

