package com.luti.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class verReceitas extends AppCompatActivity {

    TextView txTitulo, txIngredientes, txPreparacao;
//    Button btCancel;
    private String id;
    String id_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_receitas);
        txTitulo = findViewById(R.id.txTitulo);
        txIngredientes = findViewById(R.id.txIngredientes);
        txPreparacao = findViewById(R.id.txPreparacao);
//        btCancel=findViewById(R.id.btCancel);


        if(getIntent().getExtras() != null) {
            txTitulo.setText(getIntent().getSerializableExtra("titulo").toString());
            txIngredientes.setText(getIntent().getSerializableExtra("ingredientes").toString());
            txPreparacao.setText(getIntent().getSerializableExtra("preparacao").toString());
            id=getIntent().getSerializableExtra("id").toString();
            id_categoria=getIntent().getSerializableExtra("id_categoria").toString();
        }
//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),janelaPrincipal.class));
//                finish();
//            }
//        });


    }

    public void onBackPressed() {
        Intent intent = new Intent(verReceitas.this,titulosReceitas.class);
        intent.putExtra("categoria", Integer.parseInt(id_categoria));
        startActivity(intent);
        finish();
    }
}
