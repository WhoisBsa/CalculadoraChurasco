package com.hemilioaraujo.saichurrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Resultatdos extends AppCompatActivity {
    NumberFormat casas_decimais = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultatdos);

        TextView qtd_homem = (TextView) findViewById(R.id.qtd_homem);
        TextView qtd_mulher = (TextView) findViewById(R.id.qtd_mulher);
        TextView qtd_crianca = (TextView) findViewById(R.id.qtd_crianca);
        TextView qtd_carne = (TextView) findViewById(R.id.qtd_carne);
        TextView qtd_boi = (TextView) findViewById(R.id.qtd_boi);
        TextView qtd_porco = (TextView) findViewById(R.id.qtd_porco);
        TextView qtd_frango = (TextView) findViewById(R.id.qtd_frango);
        TextView qtd_linguica = (TextView) findViewById(R.id.qtd_linguica);
        TextView qtd_cerveja = (TextView) findViewById(R.id.qtd_cerveja);
        TextView qtd_refrigerante = (TextView) findViewById(R.id.qtd_refrigerante);
        TextView qtd_carvao = (TextView) findViewById(R.id.qtd_carvao);
        TextView qtd_sal = (TextView) findViewById(R.id.qtd_sal);
        TextView qtd_copo = (TextView) findViewById(R.id.qtd_copo);

        Bundle dados_activity_definicao = getIntent().getExtras();

        qtd_homem.setText(String.valueOf(dados_activity_definicao.getInt("qtd_homem")));
        qtd_mulher.setText(String.valueOf(dados_activity_definicao.getInt("qtd_mulher")));
        qtd_crianca.setText(String.valueOf(dados_activity_definicao.getInt("qtd_crianca")));
        qtd_cerveja.setText(String.valueOf(dados_activity_definicao.getInt("qtd_cerveja")));
        qtd_copo.setText(String.valueOf(dados_activity_definicao.getInt("qtd_copo")));
        
        
        qtd_boi.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_boi")
                        )
                )
        );

        qtd_porco.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_porco")
                        )
                )
        );

        qtd_frango.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_frango")
                        )
                )
        );

        qtd_linguica.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_linguica")
                        )
                )
        );

        qtd_refrigerante.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_refrigerante")
                        )
                )
        );

        qtd_carvao.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_carvao")
                        )
                )
        );

        qtd_sal.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_sal")
                        )
                )
        );

        qtd_carne.setText(
                String.valueOf(
                        casas_decimais.format(
                                dados_activity_definicao.getDouble("qtd_carne")
                        )
                )
        );
    }
}
