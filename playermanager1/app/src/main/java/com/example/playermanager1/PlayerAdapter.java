package com.example.playermanager1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private final List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAge, tvPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvPosition = itemView.findViewById(R.id.tvPosition);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.tvName.setText(player.getName());
        holder.tvAge.setText("Tuổi: " + player.getAge());
        holder.tvPosition.setText("Vị trí: " + player.getPosition());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
}
