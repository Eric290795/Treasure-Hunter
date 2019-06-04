package com.eric_treasure;

import java.util.ArrayList;
import java.util.Objects;

public class Player implements Item{
	
	private final ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	private final String name;
	private final String direction;

	public Player(String name, String direction) {
		this.name = Objects.requireNonNull(name);
		this.direction = Objects.requireNonNull(direction);
	}

	@Override
	public void print() {
		switch (direction) {
		case "N":
			System.out.println("↑");
			break;
		case "W":
			System.out.print("→");
			break;		
		case "S":
			System.out.print("↓");
			break;		
		case "E":
			System.out.print("←");
			break;
		}
		
		
	}

}
