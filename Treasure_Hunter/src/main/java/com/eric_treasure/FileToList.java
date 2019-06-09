package com.eric_treasure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileToList {
	
	protected static String destination;

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
	
	public static void writeToFile(String s) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(new File("src/main/java/Maps/sortie.txt")));
			try {
				
				file.write(s);
			}finally {
				file.close();
			}
		}catch(IOException ex){
            ex.printStackTrace();
		}
	}
	

}
