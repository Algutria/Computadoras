package com.computadoras.computadoras;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateComputer extends AppCompatActivity {
    private String [] brand, color, type, OS;
    private Spinner sBrand, sColor, sType, sOs;
    private EditText txtRam;
    private TextInputLayout ramBox;
    private Resources res;
    private ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_computer);

        sBrand = findViewById(R.id.cmbBrand);
        sColor = findViewById(R.id.cmbColor);
        sType = findViewById(R.id.cmbType);
        sOs = findViewById(R.id.cmbOS);

        txtRam = findViewById(R.id.txtRam);
        ramBox = findViewById(R.id.ramBox);

        res = this.getResources();

        loadImages();

        brand = res.getStringArray(R.array.brand_array);
        ArrayAdapter<String> brandAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, brand);
        sBrand.setAdapter(brandAdapter);

        color = res.getStringArray(R.array.color_array);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, color);
        sColor.setAdapter(colorAdapter);

        type = res.getStringArray(R.array.type_array);
        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, type);
        sType.setAdapter(TypeAdapter);

        OS = res.getStringArray(R.array.os_array);
        ArrayAdapter<String> osAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, OS);
        sOs.setAdapter(osAdapter);
    }

    public void loadImages(){
        images = new ArrayList<>();
        images.add(R.drawable.images);
        images.add(R.drawable.images1);
        images.add(R.drawable.images2);
        images.add(R.drawable.images3);
    }

    public void save(View v){
        if(isValid()) {
            Computer c = new Computer(Integer.parseInt(txtRam.getText().toString()), sBrand.getSelectedItemPosition(), sColor.getSelectedItemPosition(),
                    sType.getSelectedItemPosition(), sOs.getSelectedItemPosition(), Methods.randomImage(images));

            c.save();

            Snackbar.make(v, res.getString(R.string.saved_successfully), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            clear();
        }
    }

    public void clear(View v){
        clear();
    }

    public void clear(){
        txtRam.setText("");

        sBrand.setSelection(0);
        sType.setSelection(0);
        sColor.setSelection(0);
        sOs.setSelection(0);

        txtRam.requestFocus();
    }

    public boolean isValid(){
        if (txtRam.getText().toString().isEmpty()){
            txtRam.requestFocus();
            txtRam.setError(res.getString(R.string.cant_be_blank));
            return false;
        }

        return true;
    }
}
