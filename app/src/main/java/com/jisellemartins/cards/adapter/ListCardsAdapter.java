package com.jisellemartins.cards.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.models.Card;

import java.util.List;

public class ListCardsAdapter extends RecyclerView.Adapter<ListCardsAdapter.ViewHolder> {
    List<Card> list;
    Context context;
    Activity activity;
    private AlertDialog alerta;

    public ListCardsAdapter(List<Card> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Card card = list.get(position);

        holder.descCard.setText(card.getDescricao());
        
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirmKey(activity, card);
            }
        });

    }

    public void dialogConfirmKey(final Activity activity, final Card card){
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_confirm_key, null);
        final EditText etKeyConfirm = view.findViewById(R.id.etKeyConfirm);
        view.findViewById(R.id.btnExibir).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String keyConfirm = etKeyConfirm.getText().toString();

                if (!keyConfirm.isEmpty() && !(keyConfirm == null)){
                    if (keyConfirm.equals(card.getKey())){
                        alerta.dismiss();
                        dialogShowData(activity, card);
                    }else{
                        Toast.makeText(activity, "chave incorreta", Toast.LENGTH_SHORT).show();
                        alerta.dismiss();
                    }
                }else {
                    Toast.makeText(activity, "Preencha o campo para ter acesso aos dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public void dialogShowData(final Activity activity, final Card card){
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_show_data, null);
        TextView tvShowNumber, tvShowCod, tvShowValidity, tvShowPassword;
        tvShowNumber = view.findViewById(R.id.tvShowNumber);
        tvShowCod = view.findViewById(R.id.tvShowCod);
        tvShowValidity = view.findViewById(R.id.tvShowValidity);
        tvShowPassword = view.findViewById(R.id.tvShowPassword);

        tvShowNumber.setText(card.getNumber() + "");
        tvShowCod.setText(card.getCodSecutiry());
        tvShowValidity.setText(card.getValidity());
        tvShowPassword.setText(card.getPassword());

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView descCard;
        private ConstraintLayout itemCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descCard = itemView.findViewById(R.id.descCard);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
