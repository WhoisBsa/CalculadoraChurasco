package com.hemilioaraujo.saichurrasco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numeroPessoas;
    private Button continuar;
    private EditText txtNumeroPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuar = (Button) findViewById(R.id.btnContinuar);
        txtNumeroPessoas = (EditText) findViewById(R.id.txtNumeroPessoas);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumeroPessoas.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Determine o número de pessoas!" , Toast.LENGTH_SHORT).show();
                }
                else {
                    numeroPessoas = Integer.parseInt(txtNumeroPessoas.getText().toString());

                    Intent intentActivityDefinicao = new Intent(MainActivity.this, Definicao.class);
                    intentActivityDefinicao.putExtra("numeroPessoas", numeroPessoas);

                    startActivity(intentActivityDefinicao);

//                    Toast.makeText(getApplicationContext(), "Tem texto" + numeroPessoas , Toast.LENGTH_LONG).show();
                }



            }
        });

    }
}
