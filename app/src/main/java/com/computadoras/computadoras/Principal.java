package com.computadoras.computadoras;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements ComputerAdapter.OnComputerClickListener {
    private RecyclerView listing;
    private ArrayList<Computer> computers;
    private Resources res;
    private ComputerAdapter adapter;
    private LinearLayoutManager llm;
    private Intent i;
    private DatabaseReference databaseReference;
    private final String BD = "Computers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listing = findViewById(R.id.lstOptions);
        res = this.getResources();

        computers = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new ComputerAdapter(this.getApplicationContext(), computers, this);

        listing.setLayoutManager(llm);
        listing.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                computers.clear();

                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Computer c = snapshot.getValue(Computer.class);
                        computers.add(c);
                    }
                }
                adapter.notifyDataSetChanged();
                Data.setComputers(computers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Principal.this, CreateComputer.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onComputerClick(Computer c) {
        Intent i = new Intent(Principal.this, ComputerDetails.class);
        Bundle b = new Bundle();

        b.putString("id", c.getId());
        b.putInt("brand", c.getBrand());
        b.putInt("ram", c.getRam());
        b.putInt("color", c.getColor());
        b.putInt("type", c.getType());
        b.putInt("OS", c.getOS());
        b.putInt("image", c.getImage());

        i.putExtra("data", b);
        startActivity(i);
    }
}
