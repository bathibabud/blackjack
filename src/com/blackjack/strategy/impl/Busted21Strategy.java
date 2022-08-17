package com.blackjack.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

import com.blackjack.gamers.Player;
import com.blackjack.strategy.GameStrategy;

//Specific class that tests busted strategy
public class Busted21Strategy implements GameStrategy {

	@Override
	public List<String> winner(Player p1, Player p2) {
		int p1Score = p1.isBusted() ? -1 : p1.getScore();
		String p1Msg = "Dealer has ".concat(String.valueOf(p1.getScore()));

		StringBuffer sb = new StringBuffer("Scoring player ");
		sb.append(p2.getPlayer());
		sb.append(" has ");
		sb.append(p2.getScore());
		sb.append(". ");
		sb.append(p1Msg);
		List<String> result = new ArrayList<>();
		if (p2.isBusted()) {
			result.add(sb.toString().concat(". Player ").concat(String.valueOf(p2.getPlayer())).concat(" busted"));
			result.add("0");
		} else if (p1Score == -1 || p1Score < p2.getScore()) {
			result.add(sb.toString().concat(". Player ").concat(String.valueOf(p2.getPlayer())).concat(" wins"));
			result.add("1");
		} else {
			result.add(sb.toString().concat(". Dealer wins over Player ").concat(String.valueOf(p2.getPlayer())));
			result.add("0");
		}
		return result;
	}

	// Need to add algorithm to calculate with different combinations of ACE based
	// on the max value
	@Override
	public int calculateScore(Player p) {
		List<String> cards = p.getCards();
		ToIntFunction<? super String> mapperMax = s -> {
			int value = 0;
			String val = s.split(" ")[0];
			switch (val.toUpperCase()) {
			case "KING":
			case "QUEEN":
			case "JACK":
				value = 10;
				break;
			case "ACE":
				value = 11;
				break;
			default:
				value = Integer.parseInt(val);
			}
			return value;
		};

		ToIntFunction<? super String> mapperMin = s -> {
			int value = 0;
			String val = s.split(" ")[0];
			switch (val.toUpperCase()) {
			case "KING":
			case "QUEEN":
			case "JACK":
				value = 10;
				break;
			case "ACE":
				value = 1;
				break;
			default:
				value = Integer.parseInt(val);
			}
			return value;
		};

		int max = cards.stream().mapToInt(mapperMax).reduce((i, sum) -> sum + i).getAsInt();
		int min = cards.stream().mapToInt(mapperMin).reduce((i, sum) -> sum + i).getAsInt();

		return max <= 21 && max >= min ? max : min;
	}

}
