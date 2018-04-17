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

    class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout cardFront;
        RelativeLayout cardBack;
        TextView numberTextTopLeft;
        TextView numberTextBottomRight;
        ImageView suitImage;

        ViewHolder(View v)
        {
            super(v);
            cardFront = v.findViewById(R.id.frontLayout);
            cardBack = v.findViewById(R.id.backLayout);
            numberTextTopLeft = v.findViewById(R.id.numberTextTopLeft);
            numberTextBottomRight = v.findViewById(R.id.numberTextBottomRight);
            suitImage = v.findViewById(R.id.suitImage);
        }
    }

    // Cards to display.
    private List<Card> cardList;
    // Keep track of last position.
    private int lastPosition = -1;

    public CardDisplayAdapter()
    {
        cardList = new ArrayList<>();
    }

    public void addCard(Card card)
    {
        cardList.add(card);
        notifyItemChanged(cardList.size() - 1);
    }

    public void removeAllCards()
    {
        int count = cardList.size();
        cardList.clear();
        lastPosition = -1;
        notifyItemRangeChanged(0, count);
    }

    public void revealCard(int position)
    {
        cardList.get(position).setHidden(false);
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
        return new ViewHolder(v);
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
            holder.numberTextTopLeft.setText(card.getSymbol());
            holder.numberTextBottomRight.setText(card.getSymbol());
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

            if(holder.getAdapterPosition() > lastPosition)
            {
                showBack(holder, false);

                Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_slide_from_right);
                holder.itemView.startAnimation(animation);
                lastPosition = holder.getAdapterPosition();
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
