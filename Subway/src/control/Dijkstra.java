package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import model.Routine;
import model.Station;
import control.Subway;
public class Dijkstra {
	public static HashMap<HashMap<Station,Station>,Integer> distanceMap=new HashMap<>();
	private static List<Station> analysisList = new ArrayList<>();
      public static Station findStation(String name,LinkedHashSet<List<Station>> lineSet) {
    	  
    	  Station ss=null;
    	  for(List<Station> l:lineSet) {
    		  
    		  for(Station s:l) {
    			  if(s.getName().equals(name)) {
    				  ss=s;
    				
    				  
    			  }
    		  }
    		  
    	  }
    	  return ss;
      }
      public static boolean search(String name,LinkedHashSet<List<Station>> lineSet) {
    	  int flag=0;
    	  for(List<Station> l:lineSet) {
    		  flag=0;
    		  for(Station s:l) {
    			  if(s.getName().equals(name)) {
    				  flag=1;
    				  break;
    			  }
    				  
    		  }
    		  if(flag==1)
    			  break;
    	  }
    	  if(flag==1)
    		  return true;
    	  else return false;
      }
      public static boolean sear(String name,LinkedHashSet<List<Station>> lineSet) {
    	  int flag=0;
    	  for(List<Station> l:lineSet) {
    		  flag=0;
    		  for(Station s:l) {
    			  if(s.getName().equals(name)) {
    				  flag=1;
    				  break;
    			  }
    				  
    		  }
    		  if(flag==1)
    			  break;
    	  }
    	  if(flag==1)
    		  return true;
    	  else return false;
      }
public static Station find(String name,LinkedHashSet<List<Station>> lineSet) {
    	  
    	  Station ss=null;
    	  for(List<Station> l:lineSet) {
    		  
    		  for(Station s:l) {
    			  if(s.getName().equals(name)) {
    				  ss=s;
    				
    				  
    			  }
    		  }
    		  
    	  }
    	  return ss;
      }
private static List<Station> getLinkStations(Station station) {
    List<Station> linkedStaions = new ArrayList<Station>();
    for (List<Station> line : Subway.lineSet) {
        for (int i = 0; i < line.size(); i++) {
            if (station.equals(line.get(i))) {
                if (i == 0) {
                    linkedStaions.add(line.get(i + 1));
                } else if (i == (line.size() - 1)) {
                    linkedStaions.add(line.get(i - 1));
                } else {
                    linkedStaions.add(line.get(i + 1));
                    linkedStaions.add(line.get(i - 1));
                }
            }
        }
    }
    return linkedStaions;
}
      private static HashMap<Station, Routine> resultMap = new HashMap<>();
      private static Station getNextStation() {
          int min = Integer.MAX_VALUE;
          Station rets = null;
          Set<Station> stations = resultMap.keySet();
          for (Station station : stations) {
              if (analysisList.contains(station)) {
                  continue;
              }
              Routine result = resultMap.get(station);
              if (result.getDistance() < min) {
                  min = result.getDistance();
                  rets = result.getEnd();
              }
          }
          return rets;
      }
    
      public static Routine shortest(Station star, Station end) {
          if (!analysisList.contains(star)) {
              analysisList.add(star);
          }
          if (resultMap.isEmpty()) {
              List<Station> linkStations = getLinkStations(star);
              for (Station station : linkStations) {
            	  Routine routine = new Routine();
            	  routine.setBegin(star);
            	  routine.setEnd(station);
                  
                  int distance = 1;
                  routine.setDistance(distance);
                  routine.getPassStations().add(station);
                  resultMap.put(station, routine);
              }
          }
          Station parent = getNextStation();
          if (parent == null) {
        	  Routine routine = new Routine();
        	  routine.setDistance(0);
        	  routine.setBegin(star);
        	  routine.setEnd(end);
              return resultMap.put(end, routine);
          }
          if (parent.equals(end)) {
              return resultMap.get(parent);
          }
          List<Station> childLinkStations = getLinkStations(parent);
          for (Station child : childLinkStations) {
              if (analysisList.contains(child)) {
                  continue;
              }
             int distance = 1+resultMap.get(parent).getDistance();
              if (parent.getName().equals(child.getName())) {
                  distance = 0;
              }
              
              
              List<Station> parentPassStations = resultMap.get(parent).getPassStations();
              Routine childResult = resultMap.get(child);
              if (childResult != null) {
                  if (childResult.getDistance() > distance) {
                      childResult.setDistance(distance);
                      childResult.getPassStations().clear();
                      childResult.getPassStations().addAll(parentPassStations);
                      childResult.getPassStations().add(child);
                  }
              } else {
                  childResult = new Routine();
                  childResult.setDistance(distance);
                  childResult.setBegin(star);
                  childResult.setEnd(child);
                  childResult.getPassStations().addAll(parentPassStations);
                  childResult.getPassStations().add(child);
              }
              resultMap.put(child, childResult);
          }
          analysisList.add(parent);
          shortest(star, end);
          return resultMap.get(end);
      }
   
   
   
    
		   }
    


