package com.eric_treasure;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ArrayList<Player> players = new ArrayList<Player>();
		StringBuilder builder = new StringBuilder();
		
		FileToList.createFileMap();
		var map = Map.create("src/main/java/Maps/maps.txt");
		
		FileToList.createFilePlayers();
		players = Player.fromPath("src/main/java/Maps/players.txt");
	
		for(var p1 : players) {
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
			for (var p : players) {
				p.movePlayer(map);
				System.out.println(p);
				builder.append(p.toString()).append("\n");
				if (p.continueMove()) {
					end = false;
				}
			}
			
		} while (!end);
		var player = builder.toString();
		map.print();
		System.out.println(player);
		FileToList.writeToFile(player,"src/main/java/Maps/sortie.txt");

	}

}
