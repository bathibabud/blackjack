package com.blackjack.test.service;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.gamers.Dealer;
import com.blackjack.gamers.Player;

//Data class the prepared data for test cases
public class Busted21StrategyCalculateScoreTestCaseService {

	public static Dealer prepareDealerData() {
		List<String> dealerCards = new ArrayList<>();
		dealerCards.add("3 Diamonds");
		dealerCards.add("9 Spade");
		dealerCards.add("10 Heart");
		Dealer dealer = new Dealer(dealerCards);
		return dealer;
	}

	public static Player preparePlayerDataWithAceValueAs1() {
		List<String> playerCards = new ArrayList<>();
		playerCards.add("ACE Diamonds");
		playerCards.add("9 Spade");
		playerCards.add("10 Heart");
		Player player = new Player(playerCards);
		return player;
	}

	public static Player preparePlayerDataWithAceValueAs1And2Aces() {
		List<String> playerCards = new ArrayList<>();
		playerCards.add("ACE Diamonds");
		playerCards.add("9 Spade");
		playerCards.add("10 Heart");
		playerCards.add("ACE Club");
		Player player = new Player(playerCards);
		return player;
	}
}
