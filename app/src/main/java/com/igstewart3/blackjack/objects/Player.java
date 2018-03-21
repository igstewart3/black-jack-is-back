package com.igstewart3.blackjack.objects;

import android.support.v7.widget.RecyclerView;

import com.igstewart3.blackjack.adapters.CardDisplayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the player of the game.
 *
 * Created by ianstewart on 16/02/2018.
 */
public class Player implements Serializable
{
    // Player name
    private String name;
    // Adapter for cards.
    private CardDisplayAdapter cardDisplayAdapter;

    /**
     * Creates a new Player instance with the given name.
     *
     * @param name Player name.
     */
    public Player(String name, boolean dealer)
    {
        this.name = name;
        this.cardDisplayAdapter = new CardDisplayAdapter(dealer);
    }

    /**
     * Returns the name of the player.
     *
     * @return Player name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the current total for cards in the hand.
     *
     * @return Total of cards in hand.
     */
    public int getTotal()
    {
        List<Card> cards = cardDisplayAdapter.getCardList();
        int total = 0;
        int aceCount = 0;
        for (Card card : cards)
        {
            if(card.getSymbol().equals("A"))
                aceCount++;
            total += card.getNumber();
        }
        if(total > 21)
        {
            for(int i = 0; i < aceCount; i++)
            {
                if(total > 21)
                    total -= 10;
            }
        }
        return total;
    }

    /**
     * Adds a new card to the hand.
     *
     * @param card The card to be added to the hand.
     */
    public void addCard(Card card)
    {
        cardDisplayAdapter.addCard(card);
    }

    /**
     * Return all the cards currently in the hand.
     *
     * @return Cards currently in hand.
     */
    public List<Card> returnCards()
    {
        List<Card> toReturn = new ArrayList<>(cardDisplayAdapter.getCardList());
        for(Card card : toReturn)
            card.setHidden(false);
        cardDisplayAdapter.removeAllCards();
        return toReturn;
    }

    /**
     * Return the number of cards currently held by this player.
     *
     * @return Number of cards in hand.
     */
    public int getCardCount()
    {
        return cardDisplayAdapter.getCardList().size();
    }

    /**
     * Sets the listview this player will use to display their cards.
     *
     * @param listView The view group to display cards in.
     */
    public void setView(RecyclerView listView)
    {
        listView.setAdapter(cardDisplayAdapter);
    }

    /**
     * Shows all hidden cards.
     */
    public void showHiddenCards()
    {
        List<Card> cards = cardDisplayAdapter.getCardList();
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).isHidden())
                cardDisplayAdapter.setCardHidden(i, false);
        }
    }

    /**
     * Allows alternative display adapter to be provided if required.
     *
     * @param cardDisplayAdapter CardDisplayAdapter to use.
     */
    public void setCardDisplayAdapter(CardDisplayAdapter cardDisplayAdapter)
    {
        this.cardDisplayAdapter = cardDisplayAdapter;
    }
}
