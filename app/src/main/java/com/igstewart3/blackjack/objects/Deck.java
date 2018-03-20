package com.igstewart3.blackjack.objects;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static final List<Card> unshuffledDeck = Arrays.asList(
            Card.SPADES_ACE,
            Card.SPADES_KING,
            Card.SPADES_QUEEN,
            Card.SPADES_JACK,
            Card.SPADES_TEN,
            Card.SPADES_NINE,
            Card.SPADES_EIGHT,
            Card.SPADES_SEVEN,
            Card.SPADES_SIX,
            Card.SPADES_FIVE,
            Card.SPADES_FOUR,
            Card.SPADES_THREE,
            Card.SPADES_TWO,
            Card.HEARTS_ACE,
            Card.HEARTS_KING,
            Card.HEARTS_QUEEN,
            Card.HEARTS_JACK,
            Card.HEARTS_TEN,
            Card.HEARTS_NINE,
            Card.HEARTS_EIGHT,
            Card.HEARTS_SEVEN,
            Card.HEARTS_SIX,
            Card.HEARTS_FIVE,
            Card.HEARTS_FOUR,
            Card.HEARTS_THREE,
            Card.HEARTS_TWO,
            Card.CLUBS_ACE,
            Card.CLUBS_KING,
            Card.CLUBS_QUEEN,
            Card.CLUBS_JACK,
            Card.CLUBS_TEN,
            Card.CLUBS_NINE,
            Card.CLUBS_EIGHT,
            Card.CLUBS_SEVEN,
            Card.CLUBS_SIX,
            Card.CLUBS_FIVE,
            Card.CLUBS_FOUR,
            Card.CLUBS_THREE,
            Card.CLUBS_TWO,
            Card.DIAMONDS_ACE,
            Card.DIAMONDS_KING,
            Card.DIAMONDS_QUEEN,
            Card.DIAMONDS_JACK,
            Card.DIAMONDS_TEN,
            Card.DIAMONDS_NINE,
            Card.DIAMONDS_EIGHT,
            Card.DIAMONDS_SEVEN,
            Card.DIAMONDS_SIX,
            Card.DIAMONDS_FIVE,
            Card.DIAMONDS_FOUR,
            Card.DIAMONDS_THREE,
            Card.DIAMONDS_TWO);

    private static final SecureRandom secureRandom = new SecureRandom();

    // Number of decks used in game.
    private static final int deckNumber = 6;

    // Number of cards at which to shuffle deck.
    private static final int RESHUFFLE_COUNT = 50;

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
    private Deck()
    {
        this.cards = new ArrayDeque<>();
        this.usedCards = new ArrayList<>();
        for (int i = 0; i < deckNumber; i++)
            this.cards.addAll(unshuffledDeck);
        shuffleDeck();
    }

    /**
     * Returns the top card from the deck.
     *
     * @return the first card in the deck.
     */
    public Card getCard() throws IllegalArgumentException
    {
        if(cards.size() < RESHUFFLE_COUNT)
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
    private void shuffleDeck()
    {
        List<Card> temp = new ArrayList<>(cards);
        temp.addAll(usedCards);
        usedCards.clear();
        Collections.shuffle(temp, secureRandom);
        cards = new ArrayDeque<>(temp);
    }
}
