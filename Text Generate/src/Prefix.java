// Name: Chen Jingyuan
// uscid: chen950

import java.util.ArrayList;
import java.util.HashMap;


public class Prefix {
	
	private int max;
	private HashMap<String, Integer> freMap;
	private String maxString;
	
	
	public Prefix(){
		max = 0;
		freMap = new HashMap<String, Integer>();
		maxString = "";
	}
	
	public boolean contains(String in){
		return(freMap.containsKey(in));
	}
	
	public void increase(String in){ 
		int tmp = freMap.get(in) + 1;
		if (max < tmp){
			max = tmp;
			maxString = in;
		}
		freMap.replace(in, tmp - 1, tmp);
	}
	
	public void add(String in){
		if (max == 0){
			max = 1;
			maxString = in;
		}
		freMap.put(in, 1);
	}
	
	public String getMax(){
		return maxString;
	}
	
	public ArrayList<String> getMap(){
		ArrayList<String> map = new ArrayList<String>();
		if(freMap.isEmpty()){
			return map;
		}
		else{
			for(String i:freMap.keySet()){
				map.add(i);
			}
			return map;
		}
		
	}
}
