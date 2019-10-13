package model;

import java.util.ArrayList;
import java.util.List;

public class Routine {
      private Station begin;
      private Station end;
      private int distance=0;
      private List<Station> passStations=new ArrayList<>();
	public Station getBegin() {
		return begin;
	}
	public void setBegin(Station begin) {
		this.begin = begin;
	}
	public Station getEnd() {
		return end;
	}
	public void setEnd(Station end) {
		this.end = end;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public List<Station> getPassStations() {
		return passStations;
	}
	public void setPassStations(List<Station> passStations) {
		this.passStations = passStations;
	}
    public Routine(Station begin,Station end) {
    	this.begin=begin;
    	this.end=end;
    	
    }
    public Routine() {
    	
    }
    public String toString() {
        return "Result{" +
                "star=" + begin +
                ", end=" + end +
                ", distance=" + distance +
                ", passStations=" + passStations +
                '}';
    }
}
