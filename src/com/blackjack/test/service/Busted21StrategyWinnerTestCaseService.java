package com.blackjack.test.service;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.gamers.Dealer;
import com.blackjack.gamers.Player;

public class Busted21StrategyWinnerTestCaseService {

	public static Dealer prepareDealerData() {
		List<String> dealerCards = new ArrayList<>();
		Dealer dealer = new Dealer(dealerCards);
		dealer.setScore(21);
		return dealer;
	}

	public static Player PreparePlayerData() {
		List<String> playerCards = new ArrayList<>();
		Player player = new Player(playerCards);
		player.setScore(21);
		player.setPlayer(1);
		return player;
	}

	public static Dealer prepareBustedDealerData() {
		List<String> dealerCards = new ArrayList<>();
		Dealer dealer = new Dealer(dealerCards);
		dealer.setScore(24);
		dealer.setBusted(true);
		return dealer;
	}
}
