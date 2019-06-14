package com.eric_treasure;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>();
		StringBuilder builder = new StringBuilder();
		
		FileUtils.createFileMap();
		var map = Map.create("src/main/java/Maps/maps.txt");
		
		FileUtils.createFileAdventurers();
		adventurers = Adventurer.fromPath("src/main/java/Maps/players.txt");
	
		for(var p1 : adventurers) {
			System.out.println(p1.getCol());
			System.out.println(p1.getRow());
			map.set(p1.getRow(), p1.getCol(), p1);
			System.out.println(p1.getName());
			System.out.println(p1.getDirection());
		}
		
		var end = true;
		do {
			end = true;
			map.print();
			for (var a : adventurers) {
				a.moveAdventurer(map);
				System.out.println(a);
				builder.append(a.toString()).append("\n");
				if (a.continueMove()) {
					end = false;
				}
			}
			
		} while (!end);
		var adventurer = builder.toString();
		map.print();
		System.out.println(adventurer);
		FileUtils.writeToFile(adventurer,"src/main/java/Maps/sortie.txt");

	}

}
