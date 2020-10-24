package com.jisellemartins.cards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.adapter.ListCardsAdapter;
import com.jisellemartins.cards.models.Card;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddCard;
    private List<Card> listCard = new ArrayList<>();
    private RecyclerView rvCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnAddCard = findViewById(R.id.btnAddCard);
        rvCards = findViewById(R.id.rvCards);

        for (int a = 0; a < 20; a++){
            Card card = new Card();

            card.setDesc("Cartão " + a);

            listCard.add(card);
        }

        ListCardsAdapter adapter = new ListCardsAdapter(listCard, getApplicationContext());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvCards.setLayoutManager(llm);
        rvCards.setHasFixedSize(true);
        rvCards.setAdapter(adapter);


        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardRegister.class));
            }
        });
    }
}