package com.eric_treasure;

import java.util.Objects;

public class Treasure implements Item{
	
	private int nb_treasure;

	public Treasure(int nb_treasure) {
		this.nb_treasure = Objects.requireNonNull(nb_treasure);
		
	}

	@Override
	public void print() {
		System.out.print(this.nb_treasure);
		
	}

}
