package com.igstewart3.blackjack;

import android.support.v7.widget.RecyclerView;

import com.igstewart3.blackjack.adapters.CardDisplayAdapter;
import com.igstewart3.blackjack.objects.Card;
import com.igstewart3.blackjack.objects.Deck;
import com.igstewart3.blackjack.objects.Player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Tests for Deck class.
 *
 * @see Deck
 */
public class PlayerUnitTest
{
    private Player player;

    @Mock
    private CardDisplayAdapter cardDisplayAdapter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        player = new Player("Test Name");
        player.setCardDisplayAdapter(cardDisplayAdapter);
    }

    @Test
    public void testGetName() throws Exception
    {
        assertEquals("Test Name", player.getName());
    }

    @Test
    public void testAddCard() throws Exception
    {
        player.addCard(Card.CLUBS_FOUR);
        verify(cardDisplayAdapter).addCard(Card.CLUBS_FOUR);
    }

    @Test
    public void testGetCardCount() throws Exception
    {
        List<Card> mockList = mock(ArrayList.class);
        when(cardDisplayAdapter.getCardList()).thenReturn(mockList);
        when(mockList.size()).thenReturn(5);

        assertEquals(5, player.getCardCount());

        verify(cardDisplayAdapter).getCardList();
        verify(mockList).size();
    }

    @Test
    public void testGetTotal() throws Exception
    {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.CLUBS_EIGHT);
        when(cardDisplayAdapter.getCardList()).thenReturn(cards);

        assertEquals(8, player.getTotal());

        cards.add(Card.SPADES_QUEEN);
        cards.add(Card.SPADES_ACE);
        when(cardDisplayAdapter.getCardList()).thenReturn(cards);

        assertEquals(19, player.getTotal());
    }

    @Test
    public void testReturnCards() throws Exception
    {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.CLUBS_EIGHT);
        when(cardDisplayAdapter.getCardList()).thenReturn(cards);

        List<Card> retCards = player.returnCards();
        assertEquals(Card.CLUBS_EIGHT, retCards.get(0));

        verify(cardDisplayAdapter).removeAllCards();
    }

    @Test
    public void testSetView() throws Exception
    {
        RecyclerView view = mock(RecyclerView.class);
        player.setView(view);
        verify(view).setAdapter(cardDisplayAdapter);
    }

    @Test
    public void testShowHiddenCards() throws Exception
    {
        Card mockCard = mock(Card.class);
        List<Card> mockList = mock(ArrayList.class);

        when(cardDisplayAdapter.getCardList()).thenReturn(mockList);
        when(mockList.size()).thenReturn(3);
        when(mockList.get(anyInt())).thenReturn(mockCard);
        when(mockCard.isHidden()).thenReturn(true);
        when(mockCard.isHidden()).thenReturn(false);
        when(mockCard.isHidden()).thenReturn(true);

        player.showHiddenCards();

        verify(cardDisplayAdapter).revealCard(0);
        verify(cardDisplayAdapter).revealCard(2);
    }
}