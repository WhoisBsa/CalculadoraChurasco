package com.hemilioaraujo.saichurrasco;

import android.widget.CheckBox;

public class Calculadora {

    private static Calculadora instance;

    public Calculadora(){

    }


//    UTILIZANDO PADRÃO SINGLETON
    public static Calculadora getInstance(){
        if(instance == null)
            instance = new Calculadora();
        return instance;
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


    public double calcula_carvao(double total_carne){
        double carvao;
        carvao = 1.5 * total_carne;

        return carvao;
    }


    public double calcula_sal(double total_carne){
        double sal;
        sal = 0.03 * total_carne;

        return sal;
    }


    public int calcula_copos(int pessoas){
        int copos;
        copos = 4 * pessoas;

        return copos;
    }


    public int calcula_cerveja(int pessoas){
        int latas;
        latas = 4 * pessoas;

        return latas;
    }


    public double calcula_refrigerante(int pessoas){
        double refrigente;
        refrigente = 0.5 * pessoas;

        return refrigente;
    }
}
