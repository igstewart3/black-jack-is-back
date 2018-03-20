package com.igstewart3.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Activity to display the main menu to the user.
 *
 * Created by ianstewart on 20/03/2018.
 */
public class MenuActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * Starts the main blackjack game.
     *
     * @param view Calling view
     */
    public void mainGame(View view)
    {
        Intent mainGameIntent = new Intent(this, GameActivity.class);
        startActivity(mainGameIntent);
    }
}
