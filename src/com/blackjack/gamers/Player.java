package com.blackjack.gamers;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<String> cards = new ArrayList<>();
	// Defined variable isBusted and score to improve performance
	// Cache the data during execution
	private boolean isBusted = false;
	private int score = 0;
	private int player = 0;

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player(List<String> cards) {
		super();
		this.cards = cards;
	}

	public boolean isBusted() {
		return isBusted;
	}

	public void setBusted(boolean isBusted) {
		this.isBusted = isBusted;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

}
