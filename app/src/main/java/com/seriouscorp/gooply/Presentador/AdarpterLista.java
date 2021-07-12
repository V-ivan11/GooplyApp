package com.seriouscorp.gooply.Presentador;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.seriouscorp.gooply.R;
import com.seriouscorp.gooply.Modelo.EventosAgenda;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdarpterLista extends RecyclerView.Adapter<AdarpterLista.viewHoldersAdapter> {

    List<EventosAgenda>  listaeventos;
    Context context;

    public AdarpterLista(List<EventosAgenda> listaeventos, Context context) {
        this.listaeventos = listaeventos;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHoldersAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_evento,parent,false);
        viewHoldersAdapter holdersAdapter = new viewHoldersAdapter(view);
        return holdersAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoldersAdapter holder, int position) {
        EventosAgenda eventosAgenda = listaeventos.get(position);
        if (eventosAgenda.getTipoevento().equals("otro")){
            holder.imageView.setImageResource(R.drawable.ic_pastillas);
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        }else
            holder.imageView.setImageResource(R.drawable.ic_otro);

        holder.nomevento.setText(eventosAgenda.getNombreevento());
        holder.momentoevento.setText(eventosAgenda.getFecha());
        holder.cardView.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return listaeventos.size();
    }

    public class viewHoldersAdapter extends RecyclerView.ViewHolder {

        TextView nomevento, momentoevento;
        CardView cardView;
        CircleImageView imageView;

        public viewHoldersAdapter(@NonNull View itemView) {
            super(itemView);
            nomevento = itemView.findViewById(R.id.nomevento);
            momentoevento = itemView.findViewById(R.id.momentoevento);
            cardView = itemView.findViewById(R.id.cardevento);
            imageView = itemView.findViewById(R.id.imagenevento);
        }
    }
}
