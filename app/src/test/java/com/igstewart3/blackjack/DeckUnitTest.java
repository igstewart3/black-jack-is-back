package com.igstewart3.blackjack;

import com.igstewart3.blackjack.objects.Card;
import com.igstewart3.blackjack.objects.Deck;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit Tests for Deck class.
 *
 * @see com.igstewart3.blackjack.objects.Deck
 */
public class DeckUnitTest
{
    @Test
    public void testGetFullDeck() throws Exception
    {
        Deck deck = Deck.getFullDeck();
        Card card = deck.getCard();
        assertNotNull(card);
    }

    @Test
    public void testShuffleEmpty() throws Exception
    {
        Deck deck = Deck.getFullDeck();
        for(int i = 0; i < 312; i++)
            deck.getCard();
        assertNull(deck.getCard());
    }

    @Test
    public void testShuffleNotEmpty() throws Exception
    {
        Deck deck = Deck.getFullDeck();
        List<Card> cardList = new ArrayList<>();
        for(int i = 0; i < 312; i++)
        {
            cardList.add(deck.getCard());
            deck.returnCardsToDeck(cardList);
            cardList.clear();
        }
        assertNotNull(deck.getCard());
    }
}