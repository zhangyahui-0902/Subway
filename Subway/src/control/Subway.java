package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import model.Station;

public class Subway {
   

public static LinkedHashSet<List<Station>> lineSet=new LinkedHashSet<>();

public static void Data(String path) throws IOException {
	 
	   File file=new File(path);
	   if(file.exists()) {
	   FileInputStream fl = new FileInputStream(file);
	   InputStreamReader br=new InputStreamReader(fl,"UTF-8");
	   BufferedReader reader=new BufferedReader(br);
	   
	   String content="";
	   
	   for(int i=0;i<22;i++) {
		   content=reader.readLine();
		   String[] lineArr=content.split(" ");
		   List<Station> line=new ArrayList<Station>();
		   String linename=lineArr[0];
		   for(int j=1;j<lineArr.length;j++) {
					   line.add(new Station(lineArr[j],linename));
			   
		   }
		   
	    		  for(int j=0;j<line.size();j++) {
	    			  List<Station> linkedStations=line.get(j).getLinkStations();
	    	             if (j == 0) {
	    	                        linkedStations.add(line.get(j + 1));
	    	                        line.get(j).setLinkStations(linkedStations);
	    	                        
	    	              } else if (j == (line.size()-1) ) {
	    	            	  linkedStations.add(line.get(j - 1)); 
	    	            	  line.get(j).setLinkStations(linkedStations);
	    	              
	    	                       
	    	               }else {
	    	                        linkedStations.add(line.get(j+1));
	    	                        linkedStations.add(line.get(j-1));
	    	                        line.get(j).setLinkStations(linkedStations);
	    	               }		   
	   }
	    		  lineSet.add(line);
	   }
	   reader.close();
}
	   else
		   System.out.println("文件不正确");
}

}

	   

   
   
   

