package com.eric_treasure;

import java.util.Objects;

public class Vect {
	
	private final int x; 
	private final int y;

	public Vect(int x, int y) {
		this.x = Objects.requireNonNull(x);
		this.y = Objects.requireNonNull(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
