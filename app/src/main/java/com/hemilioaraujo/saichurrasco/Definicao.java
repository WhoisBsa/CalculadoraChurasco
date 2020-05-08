package com.hemilioaraujo.saichurrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class Definicao extends AppCompatActivity {

    private SeekBar seekHomens, seekMulheres, seekCriancas, seekBebeCerveja, seekBebeRefri;
    private CheckBox boi, porco, frango, linguica;
    private Button calcular;
    private Bundle dadosMainActivity;
    private int numeroPessoas, ajusteSeek;
    private TextView valorHomens, valorMulheres, valorCriancas, valorBebeCerveja, valorBebeRefri;

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

        boi = (CheckBox) findViewById(R.id.checkBoi);
        porco = (CheckBox) findViewById(R.id.checkPorco);
        frango = (CheckBox) findViewById(R.id.checkFrango);
        linguica = (CheckBox) findViewById(R.id.checkLinguica);

        calcular = (Button) findViewById(R.id.btnCalcular);


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
