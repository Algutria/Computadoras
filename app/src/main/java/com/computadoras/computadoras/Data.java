package com.computadoras.computadoras;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Data {

    private static String db = "Computers";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static ArrayList<Computer> computers = new ArrayList();

    public static ArrayList<Computer> getComputers() {
        return computers;
    }

    public static void saveComputer(Computer c) {
        c.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static void deleteComputer(Computer c){
        databaseReference.child(db).child(c.getId()).removeValue();
    }


    public static void setComputers(ArrayList<Computer> comp){
        computers = comp;
    }
}
