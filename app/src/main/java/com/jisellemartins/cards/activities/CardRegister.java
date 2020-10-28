package com.jisellemartins.cards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.db.CardDAO;
import com.jisellemartins.cards.models.Card;


public class CardRegister extends AppCompatActivity {
    private ImageView imgBack;
    private Button btnSave;
    private EditText etNumber, etValidity, etCod, etPassword, etDesc, etKey;

    private CardDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_register);
        getSupportActionBar().hide();
        linkLayout();
        dao = new CardDAO(getApplicationContext());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = new Card();
                card.setNumber(Integer.parseInt(String.valueOf(etNumber.getText())));
                card.setValidity(etValidity.getText().toString());
                card.setCodSecutiry(etCod.getText().toString());
                card.setPassword(etPassword.getText().toString());
                card.setDescricao(etDesc.getText().toString());
                card.setKey(etKey.getText().toString());
                dao.insert(card);
                Toast.makeText(CardRegister.this, "Cartão registrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void linkLayout(){
        imgBack = findViewById(R.id.imgBack);
        btnSave = findViewById(R.id.btnSave);
        etNumber = findViewById(R.id.etNumber);
        etValidity = findViewById(R.id.etValidity);
        etCod = findViewById(R.id.etCod);
        etPassword = findViewById(R.id.etPassword);
        etDesc = findViewById(R.id.etDesc);
        etKey = findViewById(R.id.etKey);
    }
}