package com.rpg.api_rpg.heroi_monstro.model;

public class Magia {
    private String nome;
    private Boolean tipo; // false Próprio - true Alvo;
    private int valor;
    private String status;
    public int tempoStatus;
    public int manaGasta;

    public Magia(String nome, Boolean tipo, int valor, String status, int tempoStatus, int manaGasta) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        if (status != null) {
            this.status = status;
            this.tempoStatus = tempoStatus;
        } else {
            this.status = "semStatus";
            this.tempoStatus = 0;
        }
        this.manaGasta = manaGasta;
    }

    public String atirarMagia(Combatente usuario, Combatente alvo) {
        if (this.tipo == false) {
            if (this.status == "Cura") {
                usuario.setMana(this.manaGasta)                ;
                usuario.setCura("Magia", this.valor);
            }
        }
        else{

        }
        return "";
    }
}
