package com.eric_treasure;

import java.io.IOException;
import java.util.ArrayList;




public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		var map = Map.create("src/main/java/Maps/map.txt");
		/*players = Player.fromPath("src/main/java/Maps/j1.txt");*/
		
		
		/*for(var p1 : players) {
			map.set(p1.getRow(), p1.getCol(), p1);
		}*/
		
		var players = new ArrayList<Player>();
		players.add(new Player("John",0,0,Direction.E,"AADADAGA"));
		map.set(0, 0, players.get(0));
		
		var end = true;
		do {
			end = true;
			map.print();
			for (var p : players) {
				p.move(map);
				if (p.continueMove()) {
					end = false;
				}
			}
			
		} while (!end);
		map.print();
	}

}
