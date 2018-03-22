package com.igstewart3.blackjack.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igstewart3.blackjack.R;
import com.igstewart3.blackjack.objects.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Display adapter for card Recycler view.
 *
 * Created by ianstewart on 17/03/2018.
 */
public class CardDisplayAdapter extends RecyclerView.Adapter<CardDisplayAdapter.ViewHolder> implements Serializable
{

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout cardFront;
        public RelativeLayout cardBack;
        public TextView numberText;
        public ImageView suitImage;

        public ViewHolder(View v)
        {
            super(v);
            cardFront = (RelativeLayout)v.findViewById(R.id.frontLayout);
            cardBack = (RelativeLayout)v.findViewById(R.id.backLayout);
            numberText = (TextView)v.findViewById(R.id.numberText);
            suitImage = (ImageView)v.findViewById(R.id.suitImage);
        }
    }

    // Cards to display.
    private List<Card> cardList;
    // True if this is the dealer's adapter.
    private boolean dealerAdapter = false;
    // Keep track of last position.
    private int lastPosition = -1;

    public CardDisplayAdapter(boolean dealerAdapter)
    {
        cardList = new ArrayList<Card>();
        this.dealerAdapter = dealerAdapter;
    }

    public void addCard(Card card)
    {
        cardList.add(card);
        notifyItemChanged(cardList.size() - 1);
    }

    public void removeCard(int position)
    {
        cardList.remove(position);
        notifyItemChanged(position);
    }

    public void removeAllCards()
    {
        int count = cardList.size();
        cardList.clear();
        lastPosition = -1;
        notifyItemRangeChanged(0, count);
    }

    public void setCardHidden(int position, boolean hidden)
    {
        cardList.get(position).setHidden(hidden);
        notifyItemChanged(position);
    }

    public List<Card> getCardList()
    {
        return cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Card card = cardList.get(position);
        if(card.isHidden())
        {
            showBack(holder, true);
        }
        else
        {
            holder.numberText.setText(card.getSymbol());
            switch (card.getSuit())
            {
                case SPADES:
                {
                    holder.suitImage.setImageResource(R.drawable.spades);
                    break;
                }
                case HEARTS:
                {
                    holder.suitImage.setImageResource(R.drawable.clubs);
                    break;
                }
                case CLUBS:
                {
                    holder.suitImage.setImageResource(R.drawable.hearts);
                    break;
                }
                case DIAMONDS:
                {
                    holder.suitImage.setImageResource(R.drawable.diamonds);
                    break;
                }
            }

            if(position > lastPosition)
            {
                showBack(holder, false);

                Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_slide_from_right);
                holder.itemView.startAnimation(animation);
                lastPosition = position;
            }
            else
            {
                AnimatorSet outSet = (AnimatorSet) AnimatorInflater.loadAnimator(holder.itemView.getContext(), R.animator.animation_flip_out);
                AnimatorSet inSet = (AnimatorSet) AnimatorInflater.loadAnimator(holder.itemView.getContext(), R.animator.animation_flip_in);

                outSet.setTarget(holder.cardBack);
                inSet.setTarget(holder.cardFront);

                outSet.start();
                inSet.start();
            }

        }
    }

    @Override
    public int getItemCount()
    {
        return cardList.size();
    }

    private void showBack(ViewHolder viewHolder, boolean showBack)
    {
        viewHolder.cardFront.setAlpha(showBack ? 0.0f : 1.0f);
        viewHolder.cardBack.setAlpha(showBack ? 1.0f : 0.0f);
    }

}
