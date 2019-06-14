package com.eric_treasure;

import java.util.Objects;

/**
 * Representation of a vector with 2 dimensions.
 */

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
