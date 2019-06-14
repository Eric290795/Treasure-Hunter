package com.eric_treasure;


/**
 * Representation of a direction with a vector.
 */

public enum Direction {
	
	N(new Vect(0, -1)),
	E(new Vect(1, 0)),
	S(new Vect(0, 1)),
	O(new Vect(-1, 0));
	
	private final Vect vect;

	Direction(Vect vect) {
		this.vect = vect;
	}
	
	/**
	 * give the next direction if the adventurer turn right
	 * @return a next direction
	 */
	public Direction right() {
		return values()[(ordinal() + 1) % values().length];
	}
	
	/**
	 * give the next direction if the adventurer turn left
	 * @return a next direction
	 */
	public Direction left() {
		return values()[(ordinal() - 1) % values().length];
	}
	
	public int getX() {
		return vect.getX();
	}
	
	public int getY() {
		return vect.getY();
	}

}
