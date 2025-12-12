package com.rpg.api_rpg.heroi_monstro.model;

public interface Combatente {
    String atacar(Combatente alvo, String ataque);
    String receberDano(int dano);
    int getVida();
    String getNome();
    String getStatus();
    String setCura(String metodo);
    String setCura(String metodo, int valor);
    String setMana(int valor);

} 