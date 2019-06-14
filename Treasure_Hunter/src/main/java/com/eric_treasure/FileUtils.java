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

/**
 * manipulation of files and keyboard input.
 */

public class FileUtils {
	
	protected static String destination;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static Scanner input;
	private static Scanner input2;

	/**
	 * loads the items from the file at the given path
	 * @param path the path to the file
	 * @return an arrayList of the items with their informations (position, number of treasure)
	 * @throws IOException if a problem is occurs while opening or reading the file
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
	
	/**
	 * writes the adventurer's position and his treasure number in a file
	 * @param information the information of the adventurer at every turn
	 * @param path the path to the file
	 * @throws IOException if a problem is occurs while opening or reading the file
	 */
	public static void writeToFile(String information, String path) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(new File(path)));
			try {
				
				file.write(information);
			}finally {
				file.close();
			}
		}catch(IOException ex){
            ex.printStackTrace();
		}
	}
	
	/**
	 * copy elements from one file to another
	 * @param src the path to the file source
	 * @param dest the path to the file destination
	 * @throws IOException if a problem is occurs while opening or reading or writing the file
	 */
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
	
	/**
	 * choice between keeping the map or creating a new
	 * @throws IOException if a problem is occurs while opening or reading or writing the file
	 */
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
	
	/**
	 * choice between create new Item or not
	 * @return a boolean
	 */
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
	
	/**
	 * choice between create new Adventurer or not
	 * @return a boolean
	 */
	public static boolean createAdventurerOrNot() {
		input2 = new Scanner(System.in);
		System.out.print("Do you want to create your adventurers ? O or N : ");
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
	
	/**
	 * choice between keeping adventurers and/or creating news
	 * @throws IOException if a problem is occurs while opening or reading or writing the file
	 */
	public static void createFileAdventurers() throws IOException {
		File entree = new File("src/main/java/Maps/j1.txt");
		File sortie = new File("src/main/java/Maps/players.txt");
		br = new BufferedReader(new FileReader(entree));
		bw = new BufferedWriter(new FileWriter(sortie));
		String ligne="";
		
		// Save adventurers in file or not
		while ((ligne = br.readLine()) != null){
			input = new Scanner(System.in);
			System.out.print("Do you want to save this adventurer " + ligne + " ? O or N : ");
			char myAnswer = input.next().charAt(0);
	    	if(!(myAnswer == 'O') && !(myAnswer == 'N')) {
	    		throw new IllegalStateException("malformed answer " + myAnswer);
	    	}
			if(myAnswer == 'O'){
				bw.write(ligne+"\n");
				bw.flush();
			}
		}
		// Create new adventurers
	    var begin = createAdventurerOrNot();
	    while(begin){
		    var adventurerCreate = Map.createAdventurer();
			bw.write(adventurerCreate.getName() + " " + adventurerCreate.getRow() + "-" + adventurerCreate.getCol() + 
					" " + adventurerCreate.getDirection() + " " + adventurerCreate.getMoves() + "\n");
			bw.flush();
			begin = createAdventurerOrNot();
	    }
		
		bw.close();
		br.close();
		 
	}
}

