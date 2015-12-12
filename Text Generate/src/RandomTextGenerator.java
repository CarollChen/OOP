// Name: Chen Jingyuan
// uscid: chen950
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;



public class RandomTextGenerator {


	private Random indRan;
	private HashMap<String,Prefix> fileHash = new HashMap<String,Prefix>();
	private ArrayList<String> allWordList;
	private int length;
	
	public RandomTextGenerator(Scanner file, int num, boolean debugMode) throws Exception{
		if(debugMode){
			indRan = new Random(1);
		}
		else{
			indRan = new Random();
		}
		this.length= num;
		
		allWordList = new  ArrayList<String>();
		//create a large arrayList
		while(file.hasNextLine()){
			
			String line = file.nextLine();
			
			Scanner lineSc = new Scanner(line);
			
			while(lineSc.hasNext()){
				allWordList.add(lineSc.next());
			}
			
		}
		
		if(allWordList.size() < num + 1){
			throw (new Exception("prefixLength >= number of words in sourceFile"));
		}
		else{
			
			
			for(int i=0; i < allWordList.size() - num ; i++){
				String tmp = "";
				for(int j=i ; j<i+num; j++){
					tmp+=allWordList.get(j);
				}
				if((i+num) < allWordList.size()){
					
					String nextVal = allWordList.get(i+num);
					
					if(!fileHash.containsKey(tmp)){
						
						Prefix val = new Prefix();
						val.add(nextVal);
						
						fileHash.put(tmp, val);
						
					}
					else{
						if(fileHash.get(tmp).contains(nextVal)){
							fileHash.get(tmp).increase(nextVal);
						}
						else{
							fileHash.get(tmp).add(nextVal);
						}
						
						
					}
					
				}
				
				
			}
		}
		
	}
	
	public boolean hasNext(String index){
		if(fileHash.containsKey(index)){
			return true;
		}
		else return false;
	}
	
	public String getNext(String index){
		
		if(fileHash.containsKey(index)){
			
			return fileHash.get(index).getMax();
			
		}
		
		else{
			return null;
		}
	}
	
	public ArrayList<String> getSuccessors(String index){

		if(fileHash.containsKey(index)){
			
			return fileHash.get(index).getMap();
			
		}
		
		else{
			return new ArrayList<String>();
		}
		
		

		
	}
	
	
	public LinkedList<String> getFirstRand(){
		int rand = Math.abs((indRan.nextInt()))%(allWordList.size() - length);
		LinkedList<String> first = new LinkedList<String>();
		for(int i =0; i <length; i++){
			first.add(allWordList.get(i+rand));
		}
		return first;
	}
	
}
