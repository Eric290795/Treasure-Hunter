package com.eric_treasure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileToList {
	
	protected static String destination;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static Scanner input;
	private static Scanner input2;

	/**
	 * loads the items from the file at the given path
	 * @param path the path to the file
	 * @return an arraylist of the items
	 * @throws IOException if a problem is occured while opening or reading the file
	 */
	public static ArrayList<String> load(String path) throws IOException {
		ArrayList<String> tokens = new ArrayList<>();
		if (path == null) {
			throw new IllegalArgumentException("parameter 'path' cannot be null");
		}
		File file = new File(path);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			try {
				String line = null;
				while((line = bufferedReader.readLine()) != null) {
					tokens.add(line);
				}
			}finally {
				bufferedReader.close();
			}
		}catch(IOException ex){
            ex.printStackTrace();
		}
		return tokens;
	}
	
	public static void writeToFile(String s, String path) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(new File(path)));
			try {
				
				file.write(s);
			}finally {
				file.close();
			}
		}catch(IOException ex){
            ex.printStackTrace();
		}
	}
	
	public static void copyFile(String src, String dest) {
		FileInputStream instream = null;
		FileOutputStream outstream = null;
	 
	    try{
	    	File infile =new File(src);
	    	File outfile =new File(dest);
	 
	    	instream = new FileInputStream(infile);
	    	outstream = new FileOutputStream(outfile);
	 
	    	byte[] buffer = new byte[1024];
	 
	    	int length;
	    	/*copying the contents from input stream to
	    	 * output stream using read and write methods
	    	 */
	    	while ((length = instream.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}

	    	//Closing the input/output file streams
	    	instream.close();
	    	outstream.close();

	    	System.out.println("File copied successfully!!");
	 
	    	}catch(IOException ioe){
	    		ioe.printStackTrace();
	    	}
	}
	
	public static void createFileMap() throws IOException {
		File entree = new File("src/main/java/Maps/map.txt");
		File sortie = new File("src/main/java/Maps/maps.txt");
		br = new BufferedReader(new FileReader(entree));
		bw = new BufferedWriter(new FileWriter(sortie));
		String ligne="";
	
		while ((ligne = br.readLine()) != null){
			System.out.println(ligne);
		}
		input = new Scanner(System.in);
		System.out.print("Do you want to save this map ? O or N : ");
		char myAnswer = input.next().charAt(0);
    	if(!(myAnswer == 'O') && !(myAnswer == 'N')) {
    		throw new IllegalStateException("malformed answer " + myAnswer);
    	}
		if(myAnswer == 'O'){
			copyFile("src/main/java/Maps/map.txt","src/main/java/Maps/maps.txt");
		}
		else{
			var map = Map.createMap();
			bw.write(map);
			bw.flush();
		}
	}
	
	public static boolean createItemOrNot() {
		input2 = new Scanner(System.in);
		System.out.print("Do you want to create new item ? O or N : ");
		char myAnswer = input2.next().charAt(0);
	    input2.nextLine();
	    if(!(myAnswer == 'O') && !(myAnswer == 'N')) {
    		throw new IllegalStateException("malformed answer " + myAnswer);
    	}
	    if(myAnswer == 'O'){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public static boolean createPlayerOrNot() {
		input2 = new Scanner(System.in);
		System.out.print("Do you want to create your players ? O or N : ");
	    /*String myAnswer = input2.next();*/
		char myAnswer = input2.next().charAt(0);
	    input2.nextLine();
	    if(!(myAnswer == 'O') && !(myAnswer == 'N')) {
    		throw new IllegalStateException("malformed answer " + myAnswer);
    	}
	    if(myAnswer == 'O'){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public static void createFilePlayers() throws IOException {
		File entree = new File("src/main/java/Maps/j1.txt");
		File sortie = new File("src/main/java/Maps/players.txt");
		br = new BufferedReader(new FileReader(entree));
		bw = new BufferedWriter(new FileWriter(sortie));
		String ligne="";
		
		// Save players in file or not
		//OK
		while ((ligne = br.readLine()) != null){
			input = new Scanner(System.in);
			System.out.print("Do you want to save this player " + ligne + " ? O or N : ");
			char myAnswer = input.next().charAt(0);
	    	if(!(myAnswer == 'O') && !(myAnswer == 'N')) {
	    		throw new IllegalStateException("malformed answer " + myAnswer);
	    	}
			if(myAnswer == 'O'){
				bw.write(ligne+"\n");
				bw.flush();
			}
		}
		// Create new players
	    var begin = createPlayerOrNot();
	    while(begin){
		    var playerCreate = Map.createPlayer();
			bw.write(playerCreate.getName() + " " + playerCreate.getRow() + "-" + playerCreate.getCol() + 
					" " + playerCreate.getDirection() + " " + playerCreate.getMoves() + "\n");
			bw.flush();
			begin = createPlayerOrNot();
	    }
		
		bw.close();
		br.close();
		 
	}
}

