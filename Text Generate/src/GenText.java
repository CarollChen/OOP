// Name: Chen Jingyuan
// uscid: chen950


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class GenText {
	
	public static final int LENGTH = 80;
	
	public static void main(String[] args) throws Exception{
	String[] cmd = args;
	String fileName, outName;
	ArrayList<String> println;
	int preLength, genLength ;
	boolean debugMode = true;//debug mode
	int debugFlag = 1;
	
	try{
		checkCmd(cmd);
	}
	catch(NumberFormatException e){
		System.out.println("prefixLength or numWords arguments are not integers");
		return;
	}
	catch(ArrayIndexOutOfBoundsException e){
		System.out.println("missing command-line arguments");
		return;
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		return;
	}
	
	if(!args[0].equals("-d")){
		debugMode = false;
		debugFlag = 0;
	}

	preLength = Integer.parseInt(args[0+debugFlag]);
	genLength = Integer.parseInt(args[1+debugFlag]);
	fileName = args[2+debugFlag];
	outName = args[3+debugFlag];
	
	
	
	File fs = new File(fileName);
	RandomTextGenerator gen = null;
	try{
		Scanner file = new Scanner(fs);
		gen = new RandomTextGenerator(file, preLength, debugMode);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		return;		
	}
	if(debugMode){
		println = debug(gen, genLength, preLength);
	}
	else{
		println = noDebug(gen, genLength, preLength);
	}
	try{
		writeTo(println, outName);
	}
	catch(Exception e){
		System.out.println("can't write to output file");
		return;
	}
	
	}
	
	private static void checkCmd(String[] cmd) throws Exception{
		int pre;
		int gen;
		int debugFlag = 1;
		if (!cmd[0].equals("-d")){
			debugFlag = 0;
		}
		pre = Integer.parseInt(cmd[0 + debugFlag]);
		gen = Integer.parseInt(cmd[1 + debugFlag]);
		if (pre < 1){
			throw new Exception("prefixLength < 1");
		}
		if(gen < 0){
			throw new Exception("numWords < 0");
		}
		if(pre>gen){
			throw new Exception("prefixLength > numWords");
		}
		String tryBound = cmd[3+debugFlag];
		
	}
	
	
	private static ArrayList<String> debug(RandomTextGenerator gen, Integer genLength, Integer preLength){
		LinkedList<String> pre = gen.getFirstRand(); 
		ListIterator<String> iter = pre.listIterator();
		ArrayList<String> printString = new ArrayList<String>();
		int i;
		System.out.print("DEBUG: chose a new initial prefix: ");
		while(iter.hasNext()){
			String n =iter.next();
			System.out.print(n + " ");
			printString.add(n);
		}
		for(i = 0; i < genLength - preLength; i ++){
			String index = "";
			ListIterator<String> iterind = pre.listIterator();
			System.out.print("\n"+"DEBUG: prefix: ");
			while(iterind.hasNext()){
				String n =iterind.next();
				index += n;
				System.out.print(n + " ");
			}
			ArrayList<String> suc = gen.getSuccessors(index);
			printSuc(suc);
			if(gen.hasNext(index)){
				String next = gen.getNext(index);
				System.out.print("\n"+"DEBUG: word generated: "+next);
				printString.add(next);
				pre.remove();
				pre.addLast(next);
			}
			else{
				pre = gen.getFirstRand();
				i++;
				ListIterator<String> iternew = pre.listIterator();
				System.out.print("\n"+"DEBUG: chose a new initial prefix: ");
				while(iternew.hasNext()){
					String n =iternew.next();
					System.out.print(n + " ");
					printString.add(n);
				}
				
			}

	}
		return printString;
	}
	private static void printSuc(ArrayList<String> suc){
		System.out.print("\n"+"DEBUG: successors: ");
		if(suc.isEmpty()){
			System.out.print("<END OF FILE>");
		}
		else{
			ListIterator<String> arrIter = suc.listIterator();
			while(arrIter.hasNext()){
				System.out.print(arrIter.next() + " ");
			}
		}
	}
	
	private static ArrayList<String> noDebug(RandomTextGenerator gen, Integer genLength, Integer preLength){
		LinkedList<String> pre = gen.getFirstRand(); 
		ListIterator<String> iter = pre.listIterator();
		ArrayList<String> printString = new ArrayList<String>();
		int i;
		while(iter.hasNext()){
			printString.add(iter.next());
		}
		for(i = 0; i < genLength - preLength; i ++){
			String index = "";
			ListIterator<String> iterind = pre.listIterator();
			while(iterind.hasNext()){
				index += iterind.next();
			}
			if(gen.hasNext(index)){
				String next = gen.getNext(index);
				printString.add(next);
				pre.remove();
				pre.addLast(next);
			}
			else{
				pre = gen.getFirstRand();
				i++;
				ListIterator<String> iternew = pre.listIterator();
				while(iternew.hasNext()){
					printString.add(iternew.next());
				}
				
			}

	}
		return printString;
	}
	
	private static void writeTo(ArrayList<String> println, String outName) throws IOException{
		F
        ileWriter fw = new FileWriter(new File(outName));
		
		 fw.write("01234567890123456789012345678901234567890123456789012345678901234567890123456789\n");
		ArrayList<String> eachLine = new ArrayList<String>();
		
		int i=0;
		
		String tmp = "";
		
		while(i < println.size()){
			String preTmp = tmp;
			tmp += println.get(i) + " ";
			
			if(tmp.length() == LENGTH || tmp.length() == (LENGTH+1)){
				i++;
				eachLine.add(tmp.substring(0,tmp.length()-1));
				tmp = "";
			}
			else if(tmp.length() > 81){
				
				tmp = preTmp;
				
				eachLine.add(tmp.substring(0,tmp.length()-1));
				tmp = "";
				
			}
			else{
				i++;
			}
		}
		
		for(String k:eachLine){
			fw.write(k+"\n");
		}
		fw.close();
	}
}
