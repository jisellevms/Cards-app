package com.jisellemartins.cards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.UpdateLayout;
import com.jisellemartins.cards.adapter.ListCardsAdapter;
import com.jisellemartins.cards.db.CardDAO;
import com.jisellemartins.cards.models.Card;
import com.jisellemartins.cards.util.Keys;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UpdateLayout {
    private Button btnAddCard;
    private RecyclerView rvCards;
    private CardDAO dao;
    private AlertDialog alerta;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private TextView tvEmptyList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        linkLayout();
        dao = new CardDAO(getApplicationContext());
        preferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();


        dialogConfirmKey(this);


        btnAddCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CardRegister.class)));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showList();
        if (dao.selectAll().size() > 0) tvEmptyList.setVisibility(View.GONE);
        else tvEmptyList.setVisibility(View.VISIBLE);
    }

    public void linkLayout() {
        btnAddCard = findViewById(R.id.btnAddCard);
        rvCards = findViewById(R.id.rvCards);
        tvEmptyList = findViewById(R.id.tvEmptyList);
    }

    public void showList() {
        ListCardsAdapter adapter = new ListCardsAdapter(dao.selectAll(), getApplicationContext(), this, this);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvCards.setLayoutManager(llm);
        rvCards.setHasFixedSize(true);
        rvCards.setAdapter(adapter);
    }

    public void dialogConfirmKey(final Activity activity) {
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_confirm_key, null);


        final EditText etKeyConfirm = view.findViewById(R.id.etKeyConfirm);
        final Button btn = view.findViewById(R.id.btnExibir);
        final Button btn1 = view.findViewById(R.id.btn1);
        final Button btn2 = view.findViewById(R.id.btn2);
        final Button btn3 = view.findViewById(R.id.btn3);
        final Button btn4 = view.findViewById(R.id.btn4);
        final Button btn5 = view.findViewById(R.id.btn5);
        final Button btn6 = view.findViewById(R.id.btn6);
        final Button btn7 = view.findViewById(R.id.btn7);
        final Button btn8 = view.findViewById(R.id.btn8);
        final Button btn9 = view.findViewById(R.id.btn9);

        if (getPreferencesBoolean(Keys.SET_KEY)){
            btn.setText("Salvar chave");
        }else{
            btn.setText("Exibir");
        }

        btn1.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "1"));
        btn2.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "2"));
        btn3.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "3"));
        btn4.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "4"));
        btn5.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "5"));
        btn6.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "6"));
        btn7.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "7"));
        btn8.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "8"));
        btn9.setOnClickListener(v -> etKeyConfirm.setText(etKeyConfirm.getText().toString() + "9"));




        btn.setOnClickListener(arg0 -> {
            if (etKeyConfirm.getText().toString().isEmpty()){
                Toast.makeText(activity, "Preencha o campo", Toast.LENGTH_SHORT).show();
            }else{
                if (getPreferencesBoolean(Keys.SET_KEY)) {
                    setPreferencesString(Keys.KEY, etKeyConfirm.getText().toString());
                    setPreferencesBoolean(Keys.SET_KEY, false);
                    alerta.dismiss();
                    showList();
                }else{
                    String key = getPreferencesString(Keys.KEY);
                    if (etKeyConfirm.getText().toString().equals(key)){
                        alerta.dismiss();
                        showList();
                    }else{
                        Toast.makeText(activity, "Chave incorreta", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        alerta = builder.create();
        alerta.setCancelable(false);
        alerta.show();
    }

    public void setPreferencesString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setPreferencesBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getPreferencesString(String key) {
        return preferences.getString(key, "");
    }

    public boolean getPreferencesBoolean(String key) {
        return preferences.getBoolean(key, true);
    }

    @Override
    public void updateList() {
        new Handler().postDelayed(() -> showList() ,1000);
        onResume();
    }
}