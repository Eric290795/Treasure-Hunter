package com.eric_treasure;

import java.io.IOException;
import java.util.Objects;

public class Map {
	
	private final int rows;
	private final int cols;
	private final Item[][] items;

	public Map(int rows, int cols) {
		this.rows = Objects.requireNonNull(rows);
		this.cols = Objects.requireNonNull(cols);
		this.items = new Item[rows][cols];
	}
	
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
	
	public static Map create(String path) throws IOException {
		
		var lines = FileToList.load(path);
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
	
}
