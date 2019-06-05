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
	private int nextMove;
	

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
	
	public static Player create(String path) throws IOException {
		
		var lines = FileToList.load(path);
		Player player = null;
		String[] tokens;
		String[] position;
		
		for(var line : lines) {
			tokens = line.split(" ");
			position = tokens[1].split("-");
			player = new Player(tokens[0], Integer.parseInt(position[1]), Integer.parseInt(position[0]), 
					Direction.valueOf(tokens[2]), tokens[3]);
		}
		return player;
	}
	
	public void move(Map map) {
		
		if(nextMove < moves.length()) {
			var next = moves.charAt(nextMove);
			switch(next) {
			case 'A':
				this.row = this.row + this.direction.getX();
				this.col = this.col + this.direction.getY();
				nextMove++;
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

	public int getRow() {
		return row -1;
	}

	public int getCol() {
		return col -1;
	}


}
