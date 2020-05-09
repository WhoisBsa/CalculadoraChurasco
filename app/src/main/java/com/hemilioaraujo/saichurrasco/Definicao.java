package com.hemilioaraujo.saichurrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.hemilioaraujo.saichurrasco.Calculadora;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Definicao extends AppCompatActivity {
    NumberFormat casas_decimais = new DecimalFormat("#.##");
    Calculadora calculadora = Calculadora.getInstance();

    private SeekBar seekHomens, seekMulheres, seekCriancas, seekBebeCerveja, seekBebeRefri;
    private CheckBox cheeckBoi, checkPorco, checkFrango, checkLinguica;
    private Button btnCalcular;
    private Bundle dadosMainActivity;
    private int numeroPessoas, ajusteSeek;
    private TextView valorHomens, valorMulheres, valorCriancas, valorBebeCerveja, valorBebeRefri;

    private TextView textViewTotal;
    private TextView textViewBoi;
    private TextView textViewPorco;
    private TextView textViewFrango;
    private TextView textViewLinguica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicao);

        dadosMainActivity = getIntent().getExtras();
        numeroPessoas = dadosMainActivity.getInt("numeroPessoas");
        ajusteSeek = 0;

        seekHomens = (SeekBar) findViewById(R.id.seekHomens);
        seekMulheres = (SeekBar) findViewById(R.id.seekMulheres);
        seekCriancas = (SeekBar) findViewById(R.id.seekCriancas);
        seekBebeCerveja = (SeekBar) findViewById(R.id.seekBebeCerveja);
        seekBebeRefri = (SeekBar) findViewById(R.id.seekBebeRefri);

        seekHomens.setMax(numeroPessoas);
        seekMulheres.setMax(numeroPessoas);
        seekCriancas.setMax(numeroPessoas);
        seekBebeCerveja.setMax(numeroPessoas);
        seekBebeRefri.setMax(numeroPessoas);

        valorHomens = (TextView) findViewById(R.id.valorHomens);
        valorMulheres = (TextView) findViewById(R.id.valorMulheres);
        valorCriancas = (TextView) findViewById(R.id.valorCriancas);
        valorBebeCerveja = (TextView) findViewById(R.id.valorBebeCerveja);
        valorBebeRefri = (TextView) findViewById(R.id.valorBebeRefri);

        cheeckBoi = (CheckBox) findViewById(R.id.checkBoi);
        checkPorco = (CheckBox) findViewById(R.id.checkPorco);
        checkFrango = (CheckBox) findViewById(R.id.checkFrango);
        checkLinguica = (CheckBox) findViewById(R.id.checkLinguica);

        textViewTotal = (TextView) findViewById(R.id.textViewTotal);
        textViewBoi = (TextView) findViewById(R.id.textViewBoi);
        textViewPorco = (TextView) findViewById(R.id.textViewPorco);
        textViewFrango = (TextView) findViewById(R.id.textViewFrango);
        textViewLinguica = (TextView) findViewById(R.id.textViewLinguica);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] total_de_cada_carne;
                double total_carne = calculadora.calcula_total_carne(seekHomens.getProgress(), seekMulheres.getProgress(), seekCriancas.getProgress());

               total_de_cada_carne = calculadora.calcula_total_cada_tipo_carne(cheeckBoi, checkPorco, checkFrango, checkLinguica, total_carne);

                textViewTotal.setText(String.valueOf(casas_decimais.format(total_carne)));
                textViewBoi.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[0])));
                textViewPorco.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[1])));
                textViewFrango.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[2])));
                textViewLinguica.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[3])));

                Intent intentResultados = new Intent(Definicao.this, Resultatdos.class);
//                QUANTIDADE DE PESSOAS
                intentResultados.putExtra("qtd_homem", seekHomens.getProgress());
                intentResultados.putExtra("qtd_mulher", seekMulheres.getProgress());
                intentResultados.putExtra("qtd_crianca", seekCriancas.getProgress());
//                QUANTIDADE DE CARNES
                intentResultados.putExtra("qtd_carne", total_carne);
                intentResultados.putExtra("qtd_boi", total_de_cada_carne[0]);
                intentResultados.putExtra("qtd_porco", total_de_cada_carne[1]);
                intentResultados.putExtra("qtd_frango", total_de_cada_carne[2]);
                intentResultados.putExtra("qtd_linguica", total_de_cada_carne[3]);
//                QUANTIDADE DE BEBIDAS
                intentResultados.putExtra("qtd_cerveja", calculadora.calcula_cerveja(seekBebeCerveja.getProgress()));
                intentResultados.putExtra("qtd_refrigerante", calculadora.calcula_refrigerante(seekBebeRefri.getProgress()));
//                QUANTIDADE DE PERIFÉRICOS
                intentResultados.putExtra("qtd_carvao", calculadora.calcula_carvao(total_carne));
                intentResultados.putExtra("qtd_sal", calculadora.calcula_sal(total_carne));
                intentResultados.putExtra("qtd_copo", calculadora.calcula_copos(dadosMainActivity.getInt("numeroPessoas")));

                startActivity(intentResultados);
            }
        });


        seekHomens.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorHomens.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ajusteSeek = numeroPessoas - seekHomens.getProgress();
                seekMulheres.setMax(ajusteSeek);
                ajusteSeek = numeroPessoas - seekHomens.getProgress() - seekMulheres.getProgress();
                seekCriancas.setMax(ajusteSeek);
            }
        });

        seekMulheres.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorMulheres.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ajusteSeek = numeroPessoas - seekHomens.getProgress() - seekMulheres.getProgress();
                seekCriancas.setMax(ajusteSeek);
            }
        });

        seekCriancas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorCriancas.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBebeCerveja.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorBebeCerveja.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBebeRefri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorBebeRefri.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
