package com.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.blackjack.gamers.Dealer;
import com.blackjack.gamers.Player;
import com.blackjack.stretagy.GameStrategy;
import com.blackjack.stretagy.impl.Busted21Strategy;

public class PlayGame {

	public static void main(String[] args) throws IOException {
		int players = 1;
		if (args.length > 0) {
			players = Integer.parseInt(args[0]);
		}

		// Define the specific strategy to adopt for playing
		// Can use object factory by taking another argument to the java class and then
		// instantiate concrete class
		GameStrategy strategy = new Busted21Strategy();
		System.out.println("Starting game with ".concat(String.valueOf(players)).concat(" players"));
		Set<String> cards = initializeCards();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Player> listPlayers = new ArrayList<>();
		Dealer dealer = new Dealer(new ArrayList<>());

		initialDeal(players, listPlayers, dealer, cards);

		listPlayers.iterator().forEachRemaining(p -> {
			String msg = "Dealing to player ".concat(String.valueOf(p.getPlayer()));
			try {
				individualPlayer(strategy, br, p, msg, cards);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		individualPlayer(strategy, br, dealer, "Dealing to computer, card: ", cards);
		announceWinners(strategy, dealer, listPlayers);
		br.close();
	}

	private static void announceWinners(GameStrategy strategy, Dealer dealer, List<Player> listPlayers) {
		listPlayers.parallelStream().forEach(p -> {
			System.out.println(strategy.winner(dealer, p).get(0));
		});
	}

	enum CardType {
		HEART, SPADE, CLUBS, DIAMONDS
	}

	private static Set<String> initializeCards() {
		Set<String> cards = new HashSet<>();
		for (int i = 0; i < 13; i++) {
			String prefix = String.valueOf(i + 1);
			switch (i + 1) {
			case 1:
				prefix = "Ace";
				break;
			case 11:
				prefix = "Jack";
				break;
			case 12:
				prefix = "Queen";
				break;
			case 13:
				prefix = "King";
				break;
			}
			for (int x = 0; x < CardType.values().length; x++) {
				cards.add(prefix.concat(" ").concat(CardType.values()[x].toString()));
			}
		}
		return cards;
	}

	private static void individualPlayer(GameStrategy strategy, BufferedReader br, Player player, String msg,
			Set<String> cards) throws IOException {
		boolean continuePlay = true;
		Random r = new Random();
		int score = 0;
		List<String> playerCards = player.getCards();
		while (continuePlay) {
			score = 0;
			int idx = r.nextInt(0, cards.size());
			String card = (String) cards.toArray()[idx];
			cards.remove(card);
			playerCards.add(card);
			System.out.print(msg.concat(", card: ").concat(playerCards.toString()));
			score = strategy.calculateScore(player);
			if (score > 21) {
				System.out.println("Busted over 21");
				player.setBusted(true);
				continuePlay = false;
			} else {
				String userInput = readInput(br, ". Hit or Stand> ");
				continuePlay = "Hit".equalsIgnoreCase(userInput) ? true : false;
			}
		}
		player.setScore(score);
	}

	private static void initialDeal(int players, List<Player> listPlayers, Dealer dealer, Set<String> cards) {
		Random r = new Random();
		for (int i = 0; i < players;) {
			Player p = new Player(new ArrayList<>());
			p.setPlayer(++i);
			addShuffledCard(cards, r, p);
			listPlayers.add(p);
			System.out.println("Dealing to player ".concat(String.valueOf(p.getPlayer())).concat(", card: ")
					.concat(p.getCards().get(0)));
		}
		addShuffledCard(cards, r, dealer);
		System.out.println("Dealing to computer, card: face down");
	}

	private static void addShuffledCard(Set<String> cards, Random r, Player p) {
		int idx = r.nextInt(0, cards.size());
		String card = (String) cards.toArray()[idx];
		cards.remove(card);
		p.getCards().add(card);
	}

	private static String readInput(BufferedReader br, String msg) throws IOException {
		System.out.print(msg);
		String userInput = br.readLine();
		return userInput;
	}
}
