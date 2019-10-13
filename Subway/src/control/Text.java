package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Routine;
import model.Station;

public class Text {
     public static void main(String[] args) throws IOException {
    	 if(args.length==2&&args[0].equals("-map"))
    		 Subway.Data(args[1]);
    	 else if(args.length==6&&args[0].equals("-a")&&args[2].equals("-map")&&args[4].equals("-o")) {
    	  Subway.Data(args[3]);
  		   writeRoute(args[1],args[5]);//写入station.txt
  	   }
  	   else if(args.length==7&&args[0].equals("-b")&&args[3].equals("-map")&&args[5].equals("-o")){
  		 Subway.Data(args[4]);
  		   Result(args[1],args[2],args[6]);
  		   //写入routine.txt
  	   }
  	   else {
  		    System.out.println("命令不正确");
  	   }
     }
     
     public static void writeRoute(String linename,String path) throws IOException {
   	  List<Station> route=null;
   	  int flag=1;
   	  File file=new File(path);
	   FileWriter fw =new FileWriter(file);;
   	   for(List<Station> l:Subway.lineSet){
   		    flag=1; 
   		   for(Station s:l) {
   		    if(!s.getLine().equals(linename))
   		    	flag=0;
   		   }
   		   if(flag==1) {
   			   route=l;
   	   }
   	   }
   		   if(route==null) {
   			   
   			   fw.write("路线名不正确");
   			   fw.close();
   		   }
   		   else {
   		   
   		   if(file.exists()&&file.isFile()) {
   		   try {
   			   
   			   fw.write(linename);
   			   fw.write("\r\n");
   			   for(Station s:route) {
   				   fw.write(s.getName());//将字符串写入到指定的路径下的文件中
   				   
   				   fw.write("\r\n");
   			   }
   			   
   		       fw.close();
   		       } catch (IOException e) {
   		    	   e.printStackTrace(); 
   		    	   }
   	   }
   		   else 
   			   fw.write("文件不正确");
   		       fw.close();
   		   }
   		   }

     public static void Result(String begin,String end,String path) {
     	File file=new File(path);
     	Station b;
     	Station o;
 		   FileWriter fw;
 		   try {
 			   fw=new FileWriter(file);
 			  
 			   Routine routine;
 			   b=Dijkstra.findStation(begin,Subway.lineSet);
 			   o=Dijkstra.find(end,Subway.lineSet);
 				   
 			   if(o!=null&&b!=null&&!o.getName().equals(b.getName())) {
 				  routine=Dijkstra.shortest(b,o);
 			   if(routine!=null) {
 				   if(b.getLinkStations().contains(o)) {
 					   fw.write("->乘坐"+b.getLine());
 					   fw.write("\r\n");
 					   fw.write(b.getName());
 					   fw.write("\r\n");
 					   fw.write(o.getName());
 					   fw.close();  
 				   }
 				   else {
 			   fw.write("->乘坐"+routine.getPassStations().get(1).getLine());
 			   fw.write("\r\n");
 			   fw.write(b.getName());
 			   fw.write("\r\n");
 			   for(int i=0;i<routine.getPassStations().size()-1;i++) {
 				   fw.write(routine.getPassStations().get(i).getName());
 				   fw.write("\r\n");
 				   if(!routine.getPassStations().get(i+1).getLine().equals(routine.getPassStations().get(i).getLine())) {
 					   fw.write("->换乘"+routine.getPassStations().get(i+1).getLine());
 					   fw.write("\r\n");
 				   }   
 			   }   
 			   
 			   fw.write(o.getName());
 			   fw.write("\r\n");
 			   fw.write("共经过站点数"+routine.getPassStations().size());
 		       fw.close();
 			   }
 			   }
 			  
 			   }
 			  
 			   else if(b==null) {
 				   fw.write("起点不存在");
 				   fw.close();
 			   }
 			   else if(o==null) {
 				   fw.write("终点不存在");
 				   fw.close();
 			   }
 			   else if(begin.equals(end)) {
 				   fw.write("起点和终点相同");
 				   fw.close();
 			   }
 		    
 		  
 		   
 		   }
 		      catch (IOException e) {
 		    	   e.printStackTrace(); 
 		    	   }
 			   }
     
      }

