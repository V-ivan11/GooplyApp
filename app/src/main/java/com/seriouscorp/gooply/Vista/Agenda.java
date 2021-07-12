package com.seriouscorp.gooply.Vista;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seriouscorp.gooply.R;
import com.seriouscorp.gooply.Modelo.EventosAgenda;
import com.seriouscorp.gooply.Presentador.AdarpterLista;

import java.util.ArrayList;

public class Agenda extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ConstraintLayout layout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Eventos/"+user.getUid());

    public Agenda() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        layout = view.findViewById(R.id.layoutcargandoa);
        RecyclerView recyclerView;
        final ArrayList<EventosAgenda> eventosAgenda = new ArrayList<>();
        final AdarpterLista adarpterLista = new AdarpterLista(eventosAgenda, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    eventosAgenda.removeAll(eventosAgenda);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        EventosAgenda agenda = snapshot.getValue(EventosAgenda.class);
                        eventosAgenda.add(agenda);
                    }
                    layout.setVisibility(View.INVISIBLE);
                    adarpterLista.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "No existen eventos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);

        recyclerView = view.findViewById(R.id.listaagenda);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adarpterLista);

        return view;
    }
}