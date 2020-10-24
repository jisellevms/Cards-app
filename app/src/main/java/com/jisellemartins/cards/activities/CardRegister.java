package com.jisellemartins.cards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jisellemartins.cards.R;


public class CardRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_register);
        getSupportActionBar().hide();

    }
}