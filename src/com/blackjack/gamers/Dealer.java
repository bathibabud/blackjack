package com.blackjack.gamers;

import java.util.List;

//Dealer is also a player but a specialized one
//Hence extended from Player to inherit behaviour
public class Dealer extends Player {

	public Dealer(List<String> cards) {
		super(cards);
	}

}
