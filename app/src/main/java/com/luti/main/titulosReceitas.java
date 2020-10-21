package com.luti.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class titulosReceitas extends AppCompatActivity {

    LinearLayout ll;
    RelativeLayout.LayoutParams lp;

    String id="0", titulo="0", ingredientes="0", preparacao="0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titulos_receitas);

        ll = findViewById(R.id.LinearLayout);

        final ArrayList<Button> buttons = new ArrayList<Button>();

        for(int i = 0; i < 10; i++){
            Button button = new Button(this);
            button.setText(String.valueOf(i)); //Titulo que vai vir da base de dados
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.costume_button_rounded_corners));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button)view;
                    titulo = clickedButton.getText().toString();
                    Intent intent = new Intent(titulosReceitas.this,verReceitas.class);
                    intent.putExtra("titulo", titulo);
                    intent.putExtra("ingredientes", ingredientes);
                    intent.putExtra("preparacao", preparacao);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            });
            buttons.add(button);
            ll.addView(button);
        }

    }
}
