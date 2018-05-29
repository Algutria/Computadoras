package com.computadoras.computadoras;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ComputerDetails extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Computer c;
    private String vRam, id;
    private int img, vBrand, vColor, vType, vOS;
    private Bundle bundle;
    private Intent i;
    private ImageView image;
    private Resources res;
    private TextView brand, ram, color, type, OS;
    private String [] opc;
    private String textBrand[], textColor[], textType[], textOS[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_details);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        brand = findViewById(R.id.lblBrand);
        ram = findViewById(R.id.lblRam);
        color = findViewById(R.id.lblColor);
        type = findViewById(R.id.lblType);
        OS = findViewById(R.id.lblOS);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        image = findViewById(R.id.computerImg);

        res = this.getResources();

        i = getIntent();
        bundle = i.getBundleExtra("data");

        id = bundle.getString("id");
        vBrand = bundle.getInt("brand");
        vRam = bundle.getString("ram");
        vColor = bundle.getInt("color");
        vType = bundle.getInt("type");
        vOS = bundle.getInt("OS");
        img = bundle.getInt("image");

        textBrand = res.getStringArray(R.array.brand_array);
        textColor = res.getStringArray(R.array.color_array);
        textType = res.getStringArray(R.array.type_array);
        textOS = res.getStringArray(R.array.os_array);

        collapsingToolbarLayout.setTitle(textBrand[vBrand]);
        image.setImageDrawable(ResourcesCompat.getDrawable(res, img,null));

        brand.setText(textBrand[vBrand]);
        ram.setText(vRam);
        color.setText(textColor[vColor]);
        type.setText(textType[vType]);
        OS.setText(textOS[vOS]);
    }

    public void delete(View v){
        String posAns, negAns;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.delete_computer));
        builder.setMessage(res.getString(R.string.are_you_sure));
        posAns = res.getString(R.string.yes);
        negAns = res.getString(R.string.no);

        builder.setPositiveButton(posAns, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Computer c = new Computer(id);
                c.delete();
                onBackPressed();

            }
        });

        builder.setNegativeButton(negAns, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(ComputerDetails.this, Principal.class);
        startActivity(i);
    }
}
