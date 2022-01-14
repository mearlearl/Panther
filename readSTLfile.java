package plot3Dnew;

import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;


public class readSTLfile {

	  public static void read(PlotPanel panel) 
	  { 
	  
		  //Create a File chooser to find the .stl
		  
		  FileDialog myf = new FileDialog((java.awt.Frame) null);
		  myf.setVisible(true);
		  String filePath = myf.getDirectory()+myf.getFile();
		  String fixedfilePath = filePath.replace("\\", "\\\\");
		  File file = new File(fixedfilePath);
		  
		  BufferedReader br = null;
		  
		  //Required try catch
		  
		  try {
			  br = new BufferedReader(new FileReader(file));
		  } catch (FileNotFoundException e) {
			  e.printStackTrace();
		  } 
	  
		  
		  String st; 
	  
		  ArrayList<Point> points = new ArrayList<Point>();
		  
		  try {
			  
			  //This reads the stl looking for lines that define vertices.  It then reads the coordinates
			  //New points are created and stored in points List
			  
			  while ((st = br.readLine()) != null) {
		    
				  if (st.contains("vertex")) {
				
			  		st = st.replaceAll("vertex","");
			  		st = st.replaceAll(" ",",");
			  		st = st.substring(9,st.length());
			  		
			  		String stz = st.substring(  st.lastIndexOf(',')+1 , st.length() );
			  		st = st.substring(0,st.lastIndexOf(',') );
			  		String sty = st.substring(  st.lastIndexOf(',')+1 , st.length() );
			  		st = st.substring(0,st.lastIndexOf(',') );
		  		
				  		if (st.charAt(1) == '.') {
				  			st = "0"+st;
				  		}
		  		
			  		double[] coords = new double[3]; 
			  		coords[0] = Double.parseDouble(st)/10.0-1.0;
			  		coords[1] = Double.parseDouble(sty)/10.0;
			  		coords[2] = (Double.parseDouble(stz)/10.0 - 5.0);
			  		
			  		points.add(new Point(coords[0],coords[1],coords[2]));
			  		
				  }
			   }
			  
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		  	
		  	//Create triangle objects from points list and add them to the master list in panel
		  
		  	for (int i = 0;i <points.size()/3;i++ ) {
		  		
		  		Triangle t = new Triangle(points.get(i*3),points.get(i*3+1),points.get(i*3+2));
		  		t.col = new Color((int) Math.abs(points.get(i*3).x)*10,(int) Math.abs(points.get(i*3).y)*10,(int) Math.abs(points.get(i*3).z*10));
		  		panel.master.add(t);
		  		
		  	}
	  	
	  } 
	 
	  
	} 
	
	
	

