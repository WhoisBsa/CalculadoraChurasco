package com.hemilioaraujo.saichurrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Definicao extends AppCompatActivity {
    NumberFormat casas_decimais = new DecimalFormat("#.##");

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
                double total = calcula_total_carne(seekHomens.getProgress(), seekMulheres.getProgress(), seekCriancas.getProgress());

               total_de_cada_carne = calcula_total_cada_tipo_carne(cheeckBoi, checkPorco, checkFrango, checkLinguica, total);

                textViewTotal.setText(String.valueOf(casas_decimais.format(total)));
                textViewBoi.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[0])));
                textViewPorco.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[1])));
                textViewFrango.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[2])));
                textViewLinguica.setText(String.valueOf(casas_decimais.format(total_de_cada_carne[3])));
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


    public double calcula_total_carne(int homens, int mulheres, int criancas){
        double HOMEM_COME = 0.4;
        double MULHER_COME = 0.3;
        double CRIANCA_COME = 0.15;

        double total = (homens * HOMEM_COME) + (mulheres * MULHER_COME) + (criancas * CRIANCA_COME);

        return total;
    }


    public double[] calcula_total_cada_tipo_carne(CheckBox boi, CheckBox porco, CheckBox frango, CheckBox linguica, double total_carne){
        double total_carne_boi = total_carne * 0.45;
        double total_carne_porco = total_carne * 0.25;
        double total_carne_frango = total_carne * 0.2;
        double total_carne_linguica = total_carne * 0.2;

        double soma_das_porcentagens = 0;
        double diferenca = 0;
        int itens_selecionados = 0;

        //        VERIFICA SE O ITEM ESTÁ SELECIONADO E SOMA AO TOTAL RELATIVO A PORCENTAGEM DEFINIDA
        if (boi.isChecked()){
            soma_das_porcentagens += total_carne_boi;
            itens_selecionados += 1;
        } else {
            total_carne_boi = 0;
        }

        if (porco.isChecked()){
            soma_das_porcentagens += total_carne_porco;
            itens_selecionados += 1;
        }else {
            total_carne_porco = 0;
        }

        if (frango.isChecked()){
            soma_das_porcentagens += total_carne_frango;
            itens_selecionados += 1;
        }else {
            total_carne_frango = 0;
        }

        if (linguica.isChecked()){
            soma_das_porcentagens += total_carne_linguica;
            itens_selecionados += 1;
        }else {
            total_carne_linguica = 0;
        }

        //        SE A SOMA RELATIVA AS PORCENTAGENS FOR MAIOR
        if (soma_das_porcentagens > total_carne){
            diferenca = soma_das_porcentagens - total_carne;

            if (boi.isChecked()){
                total_carne_boi -= diferenca / itens_selecionados;
            }

            if (porco.isChecked()){
                total_carne_porco -= diferenca / itens_selecionados;
            }

            if (frango.isChecked()){
                total_carne_frango -= diferenca / itens_selecionados;
            }

            if (linguica.isChecked()){
                total_carne_linguica -= diferenca / itens_selecionados;
            }
        }
        //        SE A SOMA RELATIVA AS PORCENTAGENS FOR MENOR
        else if (soma_das_porcentagens < total_carne){
            diferenca = total_carne - soma_das_porcentagens;

            if (boi.isChecked()){
                total_carne_boi += diferenca / itens_selecionados;
            }

            if (porco.isChecked()){
                total_carne_porco += diferenca / itens_selecionados;
            }

            if (frango.isChecked()){
                total_carne_frango += diferenca / itens_selecionados;
            }

            if (linguica.isChecked()){
                total_carne_linguica += diferenca / itens_selecionados;
            }
        }

        else {

        }


        double[] peso_de_cada_carne = {total_carne_boi, total_carne_porco, total_carne_frango, total_carne_linguica};

        return peso_de_cada_carne;
    }


}
