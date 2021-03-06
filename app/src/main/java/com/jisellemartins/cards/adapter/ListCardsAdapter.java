package com.jisellemartins.cards.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.UpdateLayout;
import com.jisellemartins.cards.db.CardDAO;
import com.jisellemartins.cards.models.Card;

import java.util.List;

public class ListCardsAdapter extends RecyclerView.Adapter<ListCardsAdapter.ViewHolder> {
    List<Card> list;
    Context context;
    Activity activity;
    private AlertDialog alerta;
    UpdateLayout updateLayout;
    CardDAO dao;

    public ListCardsAdapter(List<Card> list, Context context, Activity activity, UpdateLayout updateLayout) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        this.updateLayout = updateLayout;
        dao = new CardDAO(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Card card = list.get(position);

        holder.descCard.setText(card.getDescricao());

        holder.itemCard.setOnClickListener(v -> dialogShowData(activity, card));

        holder.itemCard.setOnLongClickListener(v -> {
            dialogDeleteCard(activity, card);
            return false;
        });

    }

    public void dialogDeleteCard(final Activity activity, final Card card) {
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_delete_card, null);
        TextView tvNameCard;
        Button btnYes, btnNo;
        tvNameCard = view.findViewById(R.id.tvNameCard);
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);


        tvNameCard.setText(card.getDescricao());
        btnYes.setOnClickListener(v -> {
            dao.delete(card.getId());
            updateLayout.updateList();
            alerta.dismiss();
            Toast.makeText(context, "Cartão excluído com sucesso", Toast.LENGTH_SHORT).show();
        });

        btnNo.setOnClickListener(v -> {
            alerta.dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }



    public void dialogShowData(final Activity activity, final Card card) {
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView descCard;
        private ConstraintLayout itemCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descCard = itemView.findViewById(R.id.descCard);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
