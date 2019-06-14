package com.eric_treasure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representation of an adventurer.
 */

public class Adventurer implements Item{
	
	private final ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	private final String name;
	private int row;
	private int col;
	private Direction direction;
	private final String moves;
	private int nextMove = 0;
	private int stop = 0;
	

	public Adventurer(String name, int row, int col, Direction direction, String moves) {
		this.name = Objects.requireNonNull(name);
		this.row = Objects.requireNonNull(row);
		this.col = Objects.requireNonNull(col);
		this.direction = Objects.requireNonNull(direction);
		this.moves = Objects.requireNonNull(moves);
	}

	@Override
	public void print() {
		switch (direction) {
		case N:
			System.out.println("↑");
			break;
		case E:
			System.out.print("→");
			break;		
		case S:
			System.out.print("↓");
			break;		
		case O:
			System.out.print("←");
			break;
		}
	}
	
	/**
	 * loads adventurers from the file at the given path
	 * @param path the path to the final file
	 * @return an arrayList of adventurers
	 * @throws IOException if a problem is occurs while opening or reading the file
	 */
	public static ArrayList<Adventurer> fromPath(String path) throws IOException {
		
		ArrayList<Adventurer> list_adventurer = new ArrayList<Adventurer>();
		var lines = FileUtils.load(path);
		Adventurer adventurer = null;
		String[] tokens;
		String[] position;
		
		for(var line : lines) {
			tokens = line.split(" ");
			position = tokens[1].split("-");
			adventurer = new Adventurer(tokens[0], Integer.parseInt(position[1])-1, Integer.parseInt(position[0])-1, 
					Direction.valueOf(tokens[2]), tokens[3]);
			list_adventurer.add(adventurer);
		}
		return list_adventurer;
	}
	
	/**
	 * give the number of treasure picked up by the adventurer
	 * @return number the number of treasure
	 */
	public int nbTreasures() {
		var number = 0;
		for(var treasure : treasures) {
			number += treasure.getTreasure();
		}
		return number;
	}

	/**
	 * move adventurer if it's possible
	 * @param map the map game
	 */
	public void moveAdventurer(Map map){
		
		if(continueMove()) {
			var next = moves.charAt(nextMove);
			System.out.println(next);
			System.out.println(nextMove);
			switch(next) {
			case 'A':
				// if find treasure wait 1sec
				if(stop == 1) {
					stop = 0;
					this.nextMove++;
					break;
				}
				var new_c = this.col + this.direction.getX();
				var new_r = this.row + this.direction.getY();
				var destination = map.get(new_r, new_c);
				// wait if an another player is in destination
				if(destination instanceof Adventurer) {
					break;
				}
				if(destination instanceof Treasure) {
					this.treasures.add((Treasure)destination);
					stop = 1;
				}
				if(map.Move(row, col, new_r, new_c)) {
					this.row = new_r;
					this.col = new_c;
				}
				if(stop == 0) {
					this.nextMove++;
				}
				System.out.println("stop vaut : " + stop);
				break;
			case 'G':
				this.direction = this.direction.left();
				this.nextMove++;
				System.out.println("stop vaut : " + stop);
				break;
			case 'D':
				this.direction = this.direction.right();
				this.nextMove++;
				System.out.println("stop vaut : " + stop);
				break;
			default:
				throw new IllegalStateException("unknown move: " + next);	
			}
			/*Thread.sleep(1000);*/
		}
}
		
	
	public String getMoves() {
		return moves;
	}

	public boolean continueMove() {
		return nextMove < moves.length();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public String toString() {
		return (this.name + " est à la position : " + (this.row+1) + "-" + (this.col+1) + 
				", avec une direction : " + this.direction + ", avec un nombre de trésor de : "
				+ this.nbTreasures());
	}
	
	


}
