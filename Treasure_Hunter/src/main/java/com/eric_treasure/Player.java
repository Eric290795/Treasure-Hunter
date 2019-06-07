package com.eric_treasure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;



public class Player implements Item{
	
	private final ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	private final String name;
	private int row;
	private int col;
	private Direction direction;
	private final String moves;
	private int nextMove = 0;
	

	public Player(String name, int row, int col, Direction direction, String moves) {
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
	
	public static ArrayList<Player> fromPath(String path) throws IOException {
		
		ArrayList<Player> list_player = new ArrayList<Player>();
		var lines = FileToList.load(path);
		Player player = null;
		String[] tokens;
		String[] position;
		
		for(var line : lines) {
			tokens = line.split(" ");
			position = tokens[1].split("-");
			player = new Player(tokens[0], Integer.parseInt(position[1])-1, Integer.parseInt(position[0])-1, 
					Direction.valueOf(tokens[2]), tokens[3]);
			list_player.add(player);
		}
		return list_player;
	}
	
	public int nbTreasures() {
		var nombre = 0;
		for(var treasure : treasures) {
			nombre += treasure.getTreasure();
		}
		return nombre;
	}

	public void movePlayer(Map map) throws InterruptedException {
		
		if(continueMove()) {
			var next = moves.charAt(nextMove);
			System.out.println(next);
			switch(next) {
			case 'A':
				var new_c = this.col + this.direction.getX();
				var new_r = this.row + this.direction.getY();
				var destination = map.get(new_r, new_c);
				if(destination instanceof Treasure) {
					System.out.println("OK");
					this.treasures.add((Treasure)destination);
				}
				if(map.Move(row, col, new_r, new_c)) {
					this.row = new_r;
					this.col = new_c;
				}
				this.nextMove++;
				break;
			case 'G':
				this.direction = this.direction.gauche();
				this.nextMove++;
				break;
			case 'D':
				this.direction = this.direction.droite();
				this.nextMove++;
				break;
			default:
				throw new IllegalStateException("unknown move: " + next);	
			}
		}
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
