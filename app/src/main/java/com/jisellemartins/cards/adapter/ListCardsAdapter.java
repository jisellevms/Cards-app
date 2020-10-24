package com.jisellemartins.cards.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public ListCardsAdapter(List<Card> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = list.get(position);

        holder.descCard.setText(card.getDesc());
        
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "show dialog", Toast.LENGTH_SHORT).show();
            }
        });

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
