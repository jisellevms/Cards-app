package com.jisellemartins.cards.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jisellemartins.cards.R;
import com.jisellemartins.cards.activities.MainActivity;
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
                showDialog(activity);
            }
        });

    }

    public void showDialog(Activity activity){
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_standard, null);

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
