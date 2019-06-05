package com.eric_treasure;

public enum Direction {
	
	N(new Vect(0, -1)),
	E(new Vect(1, 0)),
	S(new Vect(0, 1)),
	O(new Vect(-1, 0));
	
	private final Vect vect;

	Direction(Vect vect) {
		this.vect = vect;
	}
	
	
	public Direction droite() {
		return values()[(ordinal() + 1) % values().length];
	}
	
	public Direction gauche() {
		return values()[(ordinal() - 1) % values().length];
	}
	
	public int getX() {
		return vect.getX();
	}
	
	public int getY() {
		return vect.getY();
	}

}
