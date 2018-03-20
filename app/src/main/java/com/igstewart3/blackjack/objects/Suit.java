package com.igstewart3.blackjack.objects;

/**
 * Created by ianstewart on 16/02/2018.
 */

public enum Suit
{
    DIAMONDS("D"),
    CLUBS("C"),
    HEARTS("C"),
    SPADES("S");

    private String suitTag;
    Suit(String tag)
    {
        this.suitTag = tag;
    }

    public String getSuitTag()
    {
        return suitTag;
    }
}
