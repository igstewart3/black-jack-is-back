package com.igstewart3.blackjack.objects;

import com.igstewart3.blackjack.utilities.Constants;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Class to represent the deck to be used in the game.
 *
 * Created by ianstewart on 16/02/2018.
 */
public class Deck implements Serializable
{
    // Secure random for shuffling.
    private static final SecureRandom secureRandom = new SecureRandom();

    // The cards contained in the deck.
    private Deque<Card> cards;

    // Cards that have already been used.
    private List<Card> usedCards;

    /**
     * Return full deck ready for playing.
     *
     * @return an instance of Deck, containing the required number of decks and shuffled.
     */
    public static Deck getFullDeck()
    {
        return new Deck();
    }

    /**
     * Creates a new Deck instance, adding the required cards and decks and shuffling the cards.
     */
    private Deck() {
        this.cards = new ArrayDeque<>();
        this.usedCards = new ArrayList<>();

        // Card values
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values     = {  2,   3,   4,   5,   6,   7,   8,   9,   10,  10,  10,  10,  11};

        // Create cards for deck
        for (int i = 0; i < Constants.DECK_COUNT; i++) {
            for (Suit suit : Suit.values()) {
                for (int j = 0; j < symbols.length; j++) {
                    this.cards.add(new Card(symbols[j], values[j], suit));
                }
            }
        }
        shuffleDeck();
    }

    /**
     * Returns the top card from the deck.
     *
     * @return the first card in the deck.
     */
    public Card getCard() throws IllegalArgumentException {
        if(cards.size() < Constants.RESHUFFLE_COUNT)
            shuffleDeck();
        return this.cards.pollFirst();
    }

    /**
     * Return used cards.
     *
     * @param returnedCards The cards to return.
     */
    public void returnCardsToDeck(List<Card> returnedCards)
    {
        usedCards.addAll(returnedCards);
    }

    /**
     * Shuffles the deck to randomise the cards.
     */
    private void shuffleDeck() {
        List<Card> temp = new ArrayList<>(cards);
        temp.addAll(usedCards);
        usedCards.clear();
        Collections.shuffle(temp, secureRandom);
        cards = new ArrayDeque<>(temp);
    }
}
