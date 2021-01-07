package com.luti.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class titulosReceitas extends AppCompatActivity {

    LinearLayout ll;
    RelativeLayout.LayoutParams lp;

    Button btVoltar;

    private ProgressDialog progressDialog;

    String id="0", titulo="0", ingredientes="0", preparacao="0";
    int id_categoria;
    List<String> arrayTitulos = new ArrayList<>();
    List<String> arrayId = new ArrayList<>();
    List<String> arrayIngredi = new ArrayList<>();
    List<String> arrayModPrep = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titulos_receitas);

        id_categoria = Integer.parseInt(getIntent().getSerializableExtra("categoria").toString());

        progressDialog = new ProgressDialog(this);

        ll = findViewById(R.id.LinearLayout);
        btVoltar = findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),janelaPrincipal.class));
                finish();
            }
        });

        getTitles();

    }

    private void makeButtons(){
        final ArrayList<Button> buttons = new ArrayList<Button>();

        for(int i = 0; i < arrayId.size(); i++){
            Button button = new Button(this);
            button.setText(arrayTitulos.get(i)); //Titulo que vai vir da base de dados
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.costume_button_rounded_corners));
            button.setId(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button)view;
                    titulo = clickedButton.getText().toString();
                    Intent intent = new Intent(titulosReceitas.this,verReceitas.class);
                    intent.putExtra("titulo", arrayTitulos.get(clickedButton.getId()));
                    intent.putExtra("ingredientes", arrayIngredi.get(clickedButton.getId()));
                    intent.putExtra("preparacao", arrayModPrep.get(clickedButton.getId()));
                    intent.putExtra("id", arrayId.get(clickedButton.getId()));
                    startActivity(intent);
                    finish();
                }
            });
            buttons.add(button);
            ll.addView(button);
        }
    }

    private void getTitles(){
        progressDialog.setMessage("A obter titulos...");
        progressDialog.show();

        //GET JSON
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_TITLE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject obj = new JSONObject(response);

                    String error = obj.getString("error");
                    //JSONObject error = obj.getJSONObject("error");
                    if(error.equals("false")) {
                        JSONArray jsonarray = obj.getJSONArray("message");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            arrayTitulos.add(i, jsonobject.getString("titulo"));
                            arrayId.add(i, jsonobject.getString("id"));
                            arrayIngredi.add(i, jsonobject.getString("ingredientes"));
                            arrayModPrep.add(i, jsonobject.getString("modo_preparacao"));
                        }
                        //progressDialog.dismiss();
                        makeButtons();
                    }else{
                        Toast.makeText(getApplicationContext(), "Esta categoria não tem nenhuma receita!", Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("categoria",String.valueOf(id_categoria));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
