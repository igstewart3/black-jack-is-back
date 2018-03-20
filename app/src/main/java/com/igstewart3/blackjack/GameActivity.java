package com.igstewart3.blackjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igstewart3.blackjack.objects.Card;
import com.igstewart3.blackjack.objects.Deck;
import com.igstewart3.blackjack.objects.Player;
import com.igstewart3.blackjack.utilities.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main Game Activity.
 */
public class GameActivity extends AppCompatActivity
{
    private enum Outcome
    {
        WIN("YOU WIN!"),
        LOSE("YOU LOSE!"),
        DRAW("DRAW!"),
        BUST("BUST!");

        private static Map<String, Outcome> mappings = new HashMap<>();
        static
        {
            for(Outcome outcome : Outcome.values())
                mappings.put(outcome.getOutcomeText(), outcome);
        }
        private String outcomeText;
        Outcome(String outcomeText)
        {
            this.outcomeText = outcomeText;
        }

        public String getOutcomeText()
        {
            return outcomeText;
        }

        public static Outcome forOutcomeText(String outcomeText)
        {
            return mappings.get(outcomeText);
        }
    }

    /**
     * Timer task implementation for the dealer to stagger adding it's cards.
     */
    private class DealerTimerTask extends TimerTask
    {
        private boolean running = true;

        @Override
        public boolean cancel()
        {
            running = false;
            return super.cancel();
        }

        @Override
        public void run()
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if(running)
                        dealerTurn();
                }
            });
        }
    }

    private static final int DEALER_STAY = 17;
    private static final int BLACK_JACK  = 21;

    // Views
    private TextView hitButton = null;
    private TextView stayButton = null;
    private RelativeLayout outcomeLayout = null;
    private TextView outcomeText = null;

    // Deck to use
    private Deck deck = null;
    private Player dealer = null;
    private Player player = null;

    // Timers
    private Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get UI elements
        outcomeLayout = (RelativeLayout) findViewById(R.id.outcome_layout);
        outcomeText = (TextView) findViewById(R.id.outcome_text);
        hitButton = (TextView) findViewById(R.id.hit_button);
        stayButton = (TextView) findViewById(R.id.stay_button);

        // Get card layout view groups
        RecyclerView dealerLayout = (RecyclerView) findViewById(R.id.dealer_layout);
        RecyclerView playerLayout = (RecyclerView) findViewById(R.id.player_layout);

        if(savedInstanceState == null)
        {
            // Setup deck
            deck = Deck.getFullDeck();

            // Setup players
            dealer = new Player(Constants.DEALER, true);
            player = new Player("Player", false);

            // Attach UIs
            dealer.setView(dealerLayout);
            player.setView(playerLayout);

            // Start dealing
            dealInitialCards();
        }
        else
        {
            // Restore deck
            deck = (Deck) savedInstanceState.getSerializable(Constants.DECK);

            // Restore players
            dealer = (Player) savedInstanceState.getSerializable(Constants.DEALER);
            player = (Player) savedInstanceState.getSerializable(Constants.PLAYER);

            // Set UIs
            dealer.setView(dealerLayout);
            player.setView(playerLayout);

            String outcomeString = savedInstanceState.getString(Constants.OUTCOME);
            if(outcomeString != null)
            {
                // Disable user buttons
                enableButtons(false);

                // Show outcome
                showOutcome(Outcome.forOutcomeText(outcomeString));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constants.DECK, deck);
        outState.putSerializable(Constants.PLAYER, player);
        outState.putSerializable(Constants.DEALER, dealer);
        if(outcomeLayout != null && outcomeLayout.getVisibility() == View.VISIBLE)
        {
            outState.putString(Constants.OUTCOME, outcomeText.getText().toString());
        }
    }

    /**
     * Called when the user wants to draw another card.
     *
     * @param view The calling view.
     */
    public void hitMe(View view)
    {
        // Add next card in deck.
        player.addCard(getCard(false));
        if(player.getTotal() > BLACK_JACK)
        {
            stay(null);
        }
    }

    /**
     * Called when the user is staying (either bust, or finished).
     *
     * @param view The calling view.
     */
    public void stay(View view)
    {
        // Disable game buttons
        enableButtons(false);

        // Dealer turn to play
        dealerPlay(view != null);
    }

    /**
     * Restarts a new game from the start.
     *
     * @param view The calling view.
     */
    public void restart(View view)
    {
        outcomeLayout.setVisibility(View.GONE);

        deck.returnCardsToDeck(dealer.returnCards());
        deck.returnCardsToDeck(player.returnCards());

        dealInitialCards();
    }

    /**
     * Deals the initial cards to the players.
     */
    public void dealInitialCards()
    {
        for(int i = 0; i < 2; i++)
        {
            dealer.addCard(getCard(i == 0));
            player.addCard(getCard(false));
        }

        // Enable game buttons
        enableButtons(true);
    }

    /**
     * Starts the dealers turn.
     *
     * @param playOutHand True if the dealer needs to deal cards to himself to finish the round.
     */
    public void dealerPlay(boolean playOutHand)
    {
        dealer.showHiddenCards();

        if(playOutHand)
        {
            timer = new Timer();
            timer.schedule(new DealerTimerTask(), 1000, 1000);
        }
        else
        {
            showOutcome(Outcome.BUST);
        }
    }

    /**
     * Plays the dealers turn, adding cards until either all the players are defeated, the dealer
     * is bust or 17 is reached (dealer stays at 17).
     */
    public void dealerTurn()
    {
        if(dealer.getTotal() >= DEALER_STAY)
        {
            timer.cancel();
            endGame();
        }
        else
        {
            dealer.addCard(getCard(false));
        }
    }

    /**
     * Gets a new card from the deck.
     *
     * @param hidden Whether the card value should be hidden from the user.
     *
     * @return Next Card from deck.
     */
    private Card getCard(boolean hidden)
    {
        Card card = deck.getCard();
        card.setHidden(hidden);
        return card;
    }

    /**
     * End this round, should display appropriate outcome to user.
     */
    public void endGame()
    {
        Outcome outcome;
        int playerTotal = player.getTotal();
        int dealerTotal = dealer.getTotal();

        if(playerTotal <= BLACK_JACK && (playerTotal > dealerTotal || dealerTotal > BLACK_JACK))
            outcome = Outcome.WIN;
        else if(playerTotal > BLACK_JACK || playerTotal < dealerTotal)
            outcome = Outcome.LOSE;
        else
            outcome = Outcome.DRAW;

        showOutcome(outcome);
    }

    /**
     * Display the outcome of the round.
     *
     * @param outcome Outcome to display to the user.
     */
    public void showOutcome(Outcome outcome)
    {
        outcomeText.setText(outcome.getOutcomeText());
        outcomeLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Set whether the user buttons are enabled.
     *
     * @param enable True to enable, otherwise false.
     */
    private void enableButtons(boolean enable)
    {
        hitButton.setEnabled(enable);
        stayButton.setEnabled(enable);
    }
}
