package com.eric_treasure;

import java.util.Objects;

public class Map {
	
	private final int row;
	private final int col;
	private final Item[][] items;

	public Map(int row, int col) {
		this.row = Objects.requireNonNull(row);
		this.col = Objects.requireNonNull(col);
		this.items = new Item[row][col];
	}
	
	public void print() {
		System.out.print("  ");
		for(var i = 0; i < col; i++) {
			System.out.print(i+1 + " ");
		}
		System.out.println();
		for(var i = 0; i < row; i++) {
			System.out.print(i+1);
			for(var j = 0; j < col; j++) {
				System.out.print("| ");
			}
			System.out.print("|");
			System.out.println();
		}
	}
}
