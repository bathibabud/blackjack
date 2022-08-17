package com.blackjack.stretagy;

import java.util.List;

import com.blackjack.gamers.Player;

//Using Polymorphism to apply different strategies
public interface GameStrategy {
	List<String> winner(Player p1, Player p2);

	int calculateScore(Player p);
}
