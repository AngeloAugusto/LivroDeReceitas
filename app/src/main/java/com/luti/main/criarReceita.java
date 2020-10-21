package com.luti.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class criarReceita extends AppCompatActivity {
    private EditText nReceita, ingredientes, preparacao;
    private Spinner categoria, sub_categoria;
    private Button btCreate, btCancel;
    private ProgressDialog progressDialog;
    private TextView tvSubCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_receitas);

        nReceita =  findViewById(R.id.nReceita);
        ingredientes =  findViewById(R.id.ingredientes);
        preparacao = findViewById(R.id.preparacao);

        categoria =  findViewById(R.id.cbCategoria);
        sub_categoria =  findViewById(R.id.cbSubCategoria);

        btCreate = findViewById(R.id.btEdit);
        btCancel =  findViewById(R.id.btCancel);

        tvSubCate = findViewById(R.id.tvSubCat);

        progressDialog = new ProgressDialog(this);

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!isEmpty(nReceita) && !isEmpty(ingredientes) && !isEmpty(preparacao))
                    createReceita();
                else
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();

            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        sub_categoria.setVisibility(View.INVISIBLE);
        tvSubCate.setVisibility(View.INVISIBLE);


        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedItem= categoria.getSelectedItemPosition() + 2;
                System.out.println(selectedItem);
                if(selectedItem==4){
                    sub_categoria.setVisibility(View.VISIBLE);
                    tvSubCate.setVisibility(View.VISIBLE);
                }else{
                    if(sub_categoria.getVisibility()!=View.INVISIBLE && categoria.getVisibility()!=View.INVISIBLE) {
                        sub_categoria.setVisibility(View.INVISIBLE);
                        tvSubCate.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }



    private void createReceita(){
        final String nomeReceita = nReceita.getText().toString().trim();
        final String ingredi = ingredientes.getText().toString().trim();
        final String prepara = preparacao.getText().toString().trim();
        final String id_categoria= String.valueOf(categoria.getSelectedItemPosition() + 2);
        String id_sub_cat= "1";

        if(id_categoria.equals("4")){
            id_sub_cat= String.valueOf(sub_categoria.getSelectedItemPosition() + 2);
        }

        final String id_sub_categoria= id_sub_cat;

        progressDialog.setMessage("A criar receita...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    clearAll();

                    //TODO: uando criar ir para a página de de ver receitas

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
                params.put("nome_receita",nomeReceita);
                params.put("ingredientes",ingredi);
                params.put("modo_preparacao",prepara);
                params.put("categoria",id_categoria);
                params.put("sub_categoria",id_sub_categoria);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    private void clearAll(){
        nReceita.setText("");
        ingredientes.setText("");
        preparacao.setText("");
        categoria.setSelection(0);
        sub_categoria.setSelection(0);
    }
}
