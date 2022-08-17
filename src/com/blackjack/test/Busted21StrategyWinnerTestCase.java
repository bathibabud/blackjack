package com.blackjack.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.blackjack.gamers.Dealer;
import com.blackjack.gamers.Player;
import com.blackjack.stretagy.GameStrategy;
import com.blackjack.stretagy.impl.Busted21Strategy;
import com.blackjack.test.service.Busted21StrategyWinnerTestCaseService;

//Class to test explicitly the winner method in Busted21Strategy
public class Busted21StrategyWinnerTestCase {

	/**
	 * Dealer has 21 and player has 21 Dealer wins
	 */
	@Test
	public void test_DealerPlayerScoresMatch() {
		Dealer dealer = Busted21StrategyWinnerTestCaseService.prepareDealerData();
		Player player = Busted21StrategyWinnerTestCaseService.PreparePlayerData();

		GameStrategy strategy = new Busted21Strategy();
		List<String> result = strategy.winner(dealer, player);
		assertTrue(Integer.parseInt(result.get(1)) == 0);
		assertTrue(result.get(0).contains("Dealer wins "));
	}

	/**
	 * Dealer has busted and player has 3 Player wins
	 */
	@Test
	public void test_DealerBusted() {
		Dealer dealer = Busted21StrategyWinnerTestCaseService.prepareBustedDealerData();

		Player player = Busted21StrategyWinnerTestCaseService.PreparePlayerData();

		GameStrategy strategy = new Busted21Strategy();
		List<String> result = strategy.winner(dealer, player);
		assertTrue(Integer.parseInt(result.get(1)) == 1);
		assertTrue(result.get(0).contains("Player 1 wins"));
	}

}
