package com.igstewart3.blackjack;

import com.igstewart3.blackjack.objects.Card;
import com.igstewart3.blackjack.objects.Suit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit Tests for Card class.
 *
 * @see com.igstewart3.blackjack.objects.Card
 */
public class CardUnitTest
{
    private Card diamondCard = null;
    private Card clubCard = null;
    private Card heartCard = null;
    private Card spadeCard = null;

    @Before
    public void setUp() throws Exception
    {
        diamondCard = new Card("D", 16, Suit.DIAMONDS);
        clubCard = new Card("C", 15, Suit.CLUBS);
        heartCard = new Card("H", 14, Suit.HEARTS);
        spadeCard = new Card("S", 13, Suit.SPADES);
    }

    @Test
    public void testSymbol() throws Exception
    {
        assertEquals("D", diamondCard.getSymbol());
        assertEquals("C", clubCard.getSymbol());
        assertEquals("H", heartCard.getSymbol());
        assertEquals("S", spadeCard.getSymbol());
    }

    @Test
    public void testNumber() throws Exception
    {
        assertEquals(16, diamondCard.getNumber());
        assertEquals(15, clubCard.getNumber());
        assertEquals(14, heartCard.getNumber());
        assertEquals(13, spadeCard.getNumber());
    }

    @Test
    public void testSuit() throws Exception
    {
        assertEquals(Suit.DIAMONDS, diamondCard.getSuit());
        assertEquals(Suit.CLUBS, clubCard.getSuit());
        assertEquals(Suit.HEARTS, heartCard.getSuit());
        assertEquals(Suit.SPADES, spadeCard.getSuit());
    }

    @Test
    public void testHidden() throws Exception
    {
        assertEquals(false, diamondCard.isHidden());
        diamondCard.setHidden(true);
        assertEquals(true, diamondCard.isHidden());
    }
}