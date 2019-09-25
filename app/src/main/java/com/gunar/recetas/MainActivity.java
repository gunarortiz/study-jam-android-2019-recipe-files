package com.gunar.recetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.gunar.recetas.adapter.RecetaAdapter;
import com.gunar.recetas.model.Receta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Receta> comenList = new ArrayList<>();


    private RecyclerView comenRecycler;
    private RecetaAdapter comenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


        comenRecycler = (RecyclerView) findViewById(R.id.RecetaRecycler);

        comenAdapter = new RecetaAdapter(comenList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        comenRecycler.setLayoutManager(mLayoutManager);
        comenRecycler.setItemAnimator(new DefaultItemAnimator());
        comenRecycler.setAdapter(comenAdapter);
        comenRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        comenAdapter.setOnItemClickListener(new RecetaAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("receta", comenList.get(position));
                startActivity(intent);

            }

        });


        mostrar();



    }


    public void mostrar() {
        String estado = Environment.getExternalStorageState();


        if (!estado.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No hay SD Card", Toast.LENGTH_LONG).show();
            finish();
        }

        try {


            File dir = Environment.getExternalStorageDirectory();
            File pt = new File(dir.getAbsolutePath()+ File.separator + "recetas.txt");

            BufferedReader lee = new BufferedReader(new FileReader(pt));
            String res = "", linea;

//            boolean nuevo = true;
            String actual = "", ingre = "", rece = "";

            Receta receta = new Receta();

            while ((linea=lee.readLine())!=null){
//                if(nuevo){
                String nuevo = linea + ":";
                String[] separado = nuevo.split(":");

                String llave = linea.substring(0, 4);

                if(llave.equals("Titu")){
                    if(actual.equals("Prep")){
                        receta.setIngredientes(ingre);
                        receta.setPreparacion(rece);
                        comenList.add(receta);
                        receta = new Receta();

                    }
                    receta.setTitulo(separado[1]);
                    actual = "";
                }
                else if(llave.equals("Graf")){
                    String aux = separado[1].substring(1);
                    receta.setGrafico(Integer.parseInt(aux));
                }
                else if(llave.equals("Ingr")){
                    actual = "Ingr";
                    ingre = "";
                }
                else if(llave.equals("Prep")){
                    actual = "Prep";
                    rece = "";
                }
                else {
                    if(actual.equals("Ingr")){
                        ingre+=linea;
                    }
                    if(actual.equals("Prep")){
                        rece+=linea;
                    }
                }
                String hola = "asdf";
            }
            receta.setIngredientes(ingre);
            receta.setPreparacion(rece);
            comenList.add(receta);
            receta = new Receta();

            comenAdapter.notifyDataSetChanged();



        }
        catch (Exception e){

        }
    }
}
