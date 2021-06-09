package com.luti.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class janelaPrincipal extends AppCompatActivity {

    private Button  btEntradas, btSopas, btPratoInicial, btCremes, btSobremesas, btMolhos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.janela_principal);

//        btCancel = findViewById(R.id.btCancel);
        btEntradas = findViewById(R.id.btEntradas);
        btSopas = findViewById(R.id.btSopas);
        btPratoInicial = findViewById(R.id.btPratoInicial);
        btCremes = findViewById(R.id.btCremes);
        btSobremesas = findViewById(R.id.btSobremesas);
        btMolhos = findViewById(R.id.btMolhos);


//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                finish();
//            }
//        });
        btEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 2);
                startActivity(intent);
                finish();
            }
        });
        btSopas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 3);
                startActivity(intent);
                finish();
            }
        });
        btPratoInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 4);
                startActivity(intent);
                finish();
            }
        });
        btSobremesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 5);
                startActivity(intent);
                finish();
            }
        });
        btMolhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 6);
                startActivity(intent);
                finish();
            }
        });
        btCremes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(janelaPrincipal.this,titulosReceitas.class);
                intent.putExtra("categoria", 7);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
