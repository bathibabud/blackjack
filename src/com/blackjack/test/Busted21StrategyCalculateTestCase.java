package com.blackjack.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.blackjack.gamers.Dealer;
import com.blackjack.gamers.Player;
import com.blackjack.strategy.GameStrategy;
import com.blackjack.strategy.impl.Busted21Strategy;
import com.blackjack.test.service.Busted21StrategyCalculateScoreTestCaseService;

//Class to explicitly test the calculateScore method in Busted21Strategy
public class Busted21StrategyCalculateTestCase {

	/**
	 * Calculate score with actual face
	 */
	@Test
	public void test_calculateScoreWithActualFace() {
		Dealer dealer = Busted21StrategyCalculateScoreTestCaseService.prepareDealerData();
		GameStrategy strategy = new Busted21Strategy();
		int score = strategy.calculateScore(dealer);
		assertTrue("The score is not calculated properly", score == 22);
	}

	/**
	 * Calculate score with ACE value as 1
	 */
	@Test
	public void test_calculateScoreWithAceMin() {
		Player player = Busted21StrategyCalculateScoreTestCaseService.preparePlayerDataWithAceValueAs1();
		GameStrategy strategy = new Busted21Strategy();
		int score = strategy.calculateScore(player);
		assertTrue("The score is not calculated properly", score == 20);
	}

	/**
	 * Calculate score with actual value as 1
	 */
	@Test
	public void test_calculateScoreWithAceMinAnd2Aces() {
		Player player = Busted21StrategyCalculateScoreTestCaseService.preparePlayerDataWithAceValueAs1And2Aces();
		GameStrategy strategy = new Busted21Strategy();
		int score = strategy.calculateScore(player);
		assertTrue("The score is not calculated properly", score == 21);
	}
}
