package com.luti.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class janelaPrincipal extends AppCompatActivity {

    private Button btCancel, btEntradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.janela_principal);

        btCancel = findViewById(R.id.btCancel);
        btEntradas = findViewById(R.id.btEntradas);


        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        btEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),titulosReceitas.class));
                finish();
                //btEntradasClick();
            }
        });
    }

    private void btEntradasClick(){

        Intent intent = new Intent(janelaPrincipal.this,verReceitas.class);
        intent.putExtra("titulo", "Arroz");
        intent.putExtra("ingredientes", "Arroz");
        intent.putExtra("preparacao", "Arroz");
        startActivity(intent);
        finish();
    }
}
