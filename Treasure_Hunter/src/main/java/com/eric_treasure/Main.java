package com.eric_treasure;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		var map = FileToList.load("src/main/java/Maps/map.txt");
		System.out.println(map);
	}

}
