package com.eric_treasure;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Representation of the Map board.
 */

public class Map {
	
	private final int rows;
	private final int cols;
	private final Item[][] items;
	private static Scanner input;

	public Map(int rows, int cols) {
		this.rows = Objects.requireNonNull(rows);
		this.cols = Objects.requireNonNull(cols);
		this.items = new Item[rows][cols];
	}
	
	
	/**
	 * displays the grid of the map
	 */
	public void print() {
		System.out.print("  ");
		for(var i = 0; i < cols; i++) {
			System.out.print(i+1 + " ");
		}
		System.out.println();
		for(var i = 0; i < rows; i++) {
			System.out.print(i+1);
			for(var j = 0; j < cols; j++) {
				System.out.print("|");
				if(this.items[i][j] == null) {
					System.out.print(" ");
				}
				else {
					this.items[i][j].print();
				}
			}
			System.out.print("|");
			System.out.println();
		}
	}
	
	public void set(int r, int c, Item item) {
		if(r < 0 || r >= this.rows || c < 0 || c >= this.cols) {
			throw new IndexOutOfBoundsException();
		}
		if(item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		this.items[r][c] = item;
	}
	
	public Item get(int row, int col) {
		if(row < 0 || row >= this.rows || col < 0 || col >= this.cols) {
			throw new IndexOutOfBoundsException();
		}
		return items[row][col];
	}
	
	/**
	 * move the adventurer on the map by changing his new position
	 * @param old_r his old row position
	 * @param old_c his old column position
	 * @param new_r his new row position
	 * @param new_c his new column position
	 * @return a boolean if the adventurer moves or not
	 */
	public boolean Move(int old_r, int old_c, int new_r, int new_c) {
		if(new_r < 0 || new_r >= this.rows || new_c < 0 || new_c >= this.cols) {
			return false;
		}
		
		var destination = items[new_r][new_c];
		if((destination != null) && !(destination instanceof Treasure)) {
			return false;
		}
		
		var source = items[old_r][old_c];
		items[new_r][new_c] = source;
		items[old_r][old_c] = null;
		return true;
		
	}
	
	
	/**
	 * create the map of the game with items
	 * @param path the path to the file
	 * @return the map with Treasures and Mountains
	 * @throws IOException if a problem is occurs while opening or reading the file
	 */
	public static Map create(String path) throws IOException {
		
		var lines = FileUtils.load(path);
		Map map = null;
		String[] tokens;
		String[] position;
		
		for(var line : lines) {
			tokens = line.split(" ");
			if(map == null){
				map = new Map(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[1]));
			}
			else {
				position = tokens[1].split("-");
				var col = Integer.parseInt(position[0]) - 1;
				var row = Integer.parseInt(position[1]) - 1;
				
				switch (tokens[0]) {
				case "T" :
					map.set(row, col, new Treasure(Integer.parseInt(tokens[2])));
					break;
				case "M" :
					map.set(row, col, new Mountain());
					break;
				default :
					throw new IllegalStateException("malformed line " + line);
				}
			}
		}
		return map;
	}
	
	/**
	 * create an adventurer by the user
	 * @return an instance of Adventurer class.
	 */
	
	public static Adventurer createAdventurer() {
		input = new Scanner(System.in);
    	
    	// Getting name input
    	System.out.print("Enter name: ");
    	String myName = input.next();
    	System.out.println("Name entered = " + myName);
    	
    	// Getting row input
    	System.out.print("Enter row position : ");
    	int myRow = input.nextInt();
    	System.out.println("Row entered = " + myRow);
    	
    	// Getting column input
    	System.out.print("Enter col position : ");
    	int myCol = input.nextInt();
    	System.out.println("Row entered = " + myCol);
    	
    	// Getting direction, input
    	System.out.print("Enter direction N, O, E or S: ");
    	String str = input.next();
    	Direction myDirection = Direction.valueOf(str);
    	System.out.println("Text entered = " + myDirection);
    	
    	// Getting moves input
    	System.out.print("Enter moves A, G or D: ");
    	String myMoves = input.next();
    	System.out.println("Text entered = " + myMoves);
    	
    	Adventurer player = new Adventurer(myName, myRow, myCol, myDirection, myMoves);
    	
    	return player;
	}
	
	/**
	 * create a map by the user
	 * @return a string with items without adventurers
	 */
	public static String createMap() {
		input = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
    	
    	// Getting row input
    	System.out.print("Enter row map : ");
    	int myRow = input.nextInt();
    	System.out.println("Row entered = " + myRow);
    	
    	// Getting column input
    	System.out.print("Enter col map : ");
    	int myCol = input.nextInt();
    	System.out.println("Row entered = " + myCol);
    	
    	builder.append("C " + myRow + " " + myCol).append("\n");
    	
    	var createOrNot = FileUtils.createItemOrNot();
    	while(createOrNot) {
    		System.out.print("What type of item do you want ? T or M : ");
    		char myItem = input.next().charAt(0);
    		if(!(myItem == 'T') && !(myItem == 'M')) {
        		throw new IllegalStateException("malformed answer " + myItem);
        	}
    		if(myItem == 'T') {
    			System.out.print("Enter row treasure : ");
    	    	int myTreasureRow = input.nextInt();
    	    	System.out.println("Row entered = " + myTreasureRow);
    	    	System.out.print("Enter col treasure : ");
    	    	int myTreasureCol = input.nextInt();
    	    	System.out.println("Row entered = " + myTreasureCol);
    	    	System.out.print("Enter number of treasure : ");
    	    	int myNbTreasure = input.nextInt();
    	    	System.out.println("Row entered = " + myNbTreasure);
    	    	
    	    	builder.append("T " + myTreasureRow + "-" + myTreasureCol + " " + myNbTreasure).append("\n");
    		}
    		else{
    			System.out.print("Enter row mountain : ");
    	    	int myMountainRow = input.nextInt();
    	    	System.out.println("Row entered = " + myMountainRow);
    	    	System.out.print("Enter col mountain : ");
    	    	int myMountainCol = input.nextInt();
    	    	System.out.println("Row entered = " + myMountainCol);
    	    	
    	    	builder.append("M " + myMountainRow + "-" + myMountainCol).append("\n");
    		}
    		createOrNot = FileUtils.createItemOrNot();
    	}
    	var map = builder.toString();
		return map;
	}
}
