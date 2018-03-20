package com.igstewart3.blackjack.objects;

/**
 * Class to represent cards.
 *
 * Created by ianstewart on 16/02/2018.
 */
public class Card extends Object
{
    /* Spades */
    public static final Card SPADES_ACE   = new Card("A",  11, Suit.SPADES);
    public static final Card SPADES_KING  = new Card("K",  10, Suit.SPADES);
    public static final Card SPADES_QUEEN = new Card("Q",  10, Suit.SPADES);
    public static final Card SPADES_JACK  = new Card("J",  10, Suit.SPADES);
    public static final Card SPADES_TEN   = new Card("10", 10, Suit.SPADES);
    public static final Card SPADES_NINE  = new Card("9",  9,  Suit.SPADES);
    public static final Card SPADES_EIGHT = new Card("8",  8,  Suit.SPADES);
    public static final Card SPADES_SEVEN = new Card("7",  7,  Suit.SPADES);
    public static final Card SPADES_SIX   = new Card("6",  6,  Suit.SPADES);
    public static final Card SPADES_FIVE  = new Card("5",  5,  Suit.SPADES);
    public static final Card SPADES_FOUR  = new Card("4",  4,  Suit.SPADES);
    public static final Card SPADES_THREE = new Card("3",  3,  Suit.SPADES);
    public static final Card SPADES_TWO   = new Card("2",  2,  Suit.SPADES);

    /* Hearts */
    public static final Card HEARTS_ACE   = new Card("A",  11, Suit.HEARTS);
    public static final Card HEARTS_KING  = new Card("K",  10, Suit.HEARTS);
    public static final Card HEARTS_QUEEN = new Card("Q",  10, Suit.HEARTS);
    public static final Card HEARTS_JACK  = new Card("J",  10, Suit.HEARTS);
    public static final Card HEARTS_TEN   = new Card("10", 10, Suit.HEARTS);
    public static final Card HEARTS_NINE  = new Card("9",  9,  Suit.HEARTS);
    public static final Card HEARTS_EIGHT = new Card("8",  8,  Suit.HEARTS);
    public static final Card HEARTS_SEVEN = new Card("7",  7,  Suit.HEARTS);
    public static final Card HEARTS_SIX   = new Card("6",  6,  Suit.HEARTS);
    public static final Card HEARTS_FIVE  = new Card("5",  5,  Suit.HEARTS);
    public static final Card HEARTS_FOUR  = new Card("4",  4,  Suit.HEARTS);
    public static final Card HEARTS_THREE = new Card("3",  3,  Suit.HEARTS);
    public static final Card HEARTS_TWO   = new Card("2",  2,  Suit.HEARTS);

    /* Clubs */
    public static final Card CLUBS_ACE   = new Card("A",  11, Suit.CLUBS);
    public static final Card CLUBS_KING  = new Card("K",  10, Suit.CLUBS);
    public static final Card CLUBS_QUEEN = new Card("Q",  10, Suit.CLUBS);
    public static final Card CLUBS_JACK  = new Card("J",  10, Suit.CLUBS);
    public static final Card CLUBS_TEN   = new Card("10", 10, Suit.CLUBS);
    public static final Card CLUBS_NINE  = new Card("9",  9,  Suit.CLUBS);
    public static final Card CLUBS_EIGHT = new Card("8",  8,  Suit.CLUBS);
    public static final Card CLUBS_SEVEN = new Card("7",  7,  Suit.CLUBS);
    public static final Card CLUBS_SIX   = new Card("6",  6,  Suit.CLUBS);
    public static final Card CLUBS_FIVE  = new Card("5",  5,  Suit.CLUBS);
    public static final Card CLUBS_FOUR  = new Card("4",  4,  Suit.CLUBS);
    public static final Card CLUBS_THREE = new Card("3",  3,  Suit.CLUBS);
    public static final Card CLUBS_TWO   = new Card("2",  2,  Suit.CLUBS);

    /* Diamonds */
    public static final Card DIAMONDS_ACE   = new Card("A",  11, Suit.DIAMONDS);
    public static final Card DIAMONDS_KING  = new Card("K",  10, Suit.DIAMONDS);
    public static final Card DIAMONDS_QUEEN = new Card("Q",  10, Suit.DIAMONDS);
    public static final Card DIAMONDS_JACK  = new Card("J",  10, Suit.DIAMONDS);
    public static final Card DIAMONDS_TEN   = new Card("10", 10, Suit.DIAMONDS);
    public static final Card DIAMONDS_NINE  = new Card("9",  9,  Suit.DIAMONDS);
    public static final Card DIAMONDS_EIGHT = new Card("8",  8,  Suit.DIAMONDS);
    public static final Card DIAMONDS_SEVEN = new Card("7",  7,  Suit.DIAMONDS);
    public static final Card DIAMONDS_SIX   = new Card("6",  6,  Suit.DIAMONDS);
    public static final Card DIAMONDS_FIVE  = new Card("5",  5,  Suit.DIAMONDS);
    public static final Card DIAMONDS_FOUR  = new Card("4",  4,  Suit.DIAMONDS);
    public static final Card DIAMONDS_THREE = new Card("3",  3,  Suit.DIAMONDS);
    public static final Card DIAMONDS_TWO   = new Card("2",  2,  Suit.DIAMONDS);

    private String symbol;
    private int number;
    private Suit suit;
    private boolean hidden;

    /**
     * Creates a card instance with a given symbol, value and suit.
     *
     * @param symbol The symbol to display.
     * @param number The value of the card.
     * @param suit The suit of the card.
     */
    private Card(String symbol, int number, Suit suit)
    {
        this.symbol = symbol;
        this.number = number;
        this.suit = suit;
        this.setHidden(false);
    }

    /**
     * Returns the symbol for the card.
     *
     * @return The card symbol to display.
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * Returns the numeric value of the card.
     *
     * @return The numeric value of the card.
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * Returns the suit of the card.
     *
     * @return The suit of the card.
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Returns whether the card back should be displayed.
     *
     * @return True if card back to be displayed, false for card front.
     */
    public boolean isHidden()
    {
        return hidden;
    }

    /**
     * Sets whether the card back should be displayed.
     *
     * @param hidden True to display back, false for front.
     */
    public void setHidden(boolean hidden)
    {
        this.hidden = hidden;
    }
}
