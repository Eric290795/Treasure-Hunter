package com.eric_treasure;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		var map = Map.create("src/main/java/Maps/map.txt", "src/main/java/Maps/j1.txt");
		map.print();
	}

}
