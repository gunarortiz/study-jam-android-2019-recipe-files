package com.gunar.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gunar.recetas.model.Receta;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Receta receta = (Receta) getIntent().getExtras().getSerializable("receta");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.banner);

        if(receta.getGrafico() == 1){
            linearLayout.setBackgroundResource(R.drawable.a);
        }
        if(receta.getGrafico() == 2){
            linearLayout.setBackgroundResource(R.drawable.b);
        }
        if(receta.getGrafico() == 3){
            linearLayout.setBackgroundResource(R.drawable.c);
        }
        if(receta.getGrafico() == 4){
            linearLayout.setBackgroundResource(R.drawable.d);
        }

        setTitle(receta.getTitulo());

        TextView ingre = (TextView) findViewById(R.id.ingre);
        TextView prepa = (TextView) findViewById(R.id.prepa);

        ingre.setText(receta.getIngredientes());
        prepa.setText(receta.getPreparacion());


    }
}
