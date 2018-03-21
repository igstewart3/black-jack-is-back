package com.igstewart3.blackjack;

import com.igstewart3.blackjack.objects.Card;
import com.igstewart3.blackjack.objects.Suit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit Tests for Card class.
 *
 * @see com.igstewart3.blackjack.objects.Card
 */
public class CardUnitTest
{
    @Test
    public void testSymbol() throws Exception
    {
        assertEquals("A", Card.CLUBS_ACE.getSymbol());
        assertEquals("K", Card.SPADES_KING.getSymbol());
        assertEquals("2", Card.DIAMONDS_TWO.getSymbol());
        assertEquals("5", Card.HEARTS_FIVE.getSymbol());
    }

    @Test
    public void testNumber() throws Exception
    {
        assertEquals(11, Card.CLUBS_ACE.getNumber());
        assertEquals(10, Card.SPADES_KING.getNumber());
        assertEquals(2, Card.DIAMONDS_TWO.getNumber());
        assertEquals(5, Card.HEARTS_FIVE.getNumber());
    }

    @Test
    public void testSuit() throws Exception
    {
        assertEquals(Suit.CLUBS, Card.CLUBS_ACE.getSuit());
        assertEquals(Suit.SPADES, Card.SPADES_KING.getSuit());
        assertEquals(Suit.DIAMONDS, Card.DIAMONDS_TWO.getSuit());
        assertEquals(Suit.HEARTS, Card.HEARTS_FIVE.getSuit());
    }

    @Test
    public void testHidden() throws Exception
    {
        Card card = Card.CLUBS_FOUR;
        assertEquals(false, card.isHidden());
        card.setHidden(true);
        assertEquals(true, card.isHidden());
    }
}