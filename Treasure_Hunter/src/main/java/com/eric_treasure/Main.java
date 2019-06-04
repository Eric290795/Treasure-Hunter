package com.eric_treasure;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		var map = FileToList.load("src/main/java/Maps/map.txt");
		var j1 = FileToList.load("src/main/java/Maps/j1.txt");
		System.out.println(map);
		System.out.println(j1);
		var maap = new Map(6,5);
		maap.print();
	}

}
