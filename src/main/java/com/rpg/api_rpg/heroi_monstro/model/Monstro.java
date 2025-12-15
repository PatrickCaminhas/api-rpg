package com.rpg.api_rpg.heroi_monstro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monstro implements Combatente {
    private String nome;
    private int vida = 120;
    private int forca = 6;
    private int mana = 0;

    public Monstro() {
        List<String> nomesMonstros = new ArrayList<String>(List.of("HobGoblin", "Orc", "Slime", "Lobo Cinzento"));
        Random aleatorio = new Random();
        this.nome = nomesMonstros.get(aleatorio.nextInt(4));
    }

    @Override
    public String atacar(Combatente alvo, String ataque) {
        if (alvo.getVida() <= 0) {
            return alvo.getNome() + "está morto!";
        }
        Random aleatorio = new Random();
        int dado = aleatorio.nextInt(21);
        if (ataque == "Normal") {
            if (dado == 20) {
                alvo.receberDano(this.forca * 2);
                return "Dano Critico! Dano: " + (this.forca * 2) + " pontos de vida";
            } else if (dado >= 15 && dado <= 19) {
                alvo.receberDano((int) Math.round(this.forca * 1.5));
                return "Ataque forte! Dano: " + (int) Math.round(this.forca * 1.5) + " pontos de vida";
            } else if (dado >= 10 && dado <= 14) {
                alvo.receberDano(this.forca);
                return "Ataque normal. Dano: " + this.forca + " pontos de vida";
            } else if (dado >= 4 && dado <= 9) {
                alvo.receberDano((int) Math.round(this.forca * 0.5));
                return "Ataque fraco. Dano: " + (int) Math.round(this.forca * 0.5) + " pontos de vida";
            } else if (dado == 2 || dado == 3) {
                alvo.receberDano(1);
                return "Passou de raspão! Dano: 1 ponto de vida";
            } else if (dado == 1) {
                return "Erro critico! Dano: 0 pontos de vida";
            } else {
                return "Erro, valor incorreto.";
            }
        } else if (ataque == "Pesado") {
            if (dado == 20) {
                alvo.receberDano((int) Math.round(this.forca * 2 * 1.5));
                return "Dano Critico! Dano: " + (int) Math.round((this.forca * 2 * 1.5)) + " pontos de vida";
            } else if (dado >= 17 && dado <= 19) {
                alvo.receberDano((int) Math.round(this.forca * 1.5 * 1.5));
                return "Ataque forte! Dano: " + (int) Math.round(this.forca * 1.5 * 1.5) + " pontos de vida";
            } else if (dado >= 13 && dado <= 16) {
                alvo.receberDano((int) Math.round(this.forca * 1.5 * 1.5));
                return "Ataque normal. Dano: " + (int) Math.round(this.forca * 1.5) + " pontos de vida";
            } else if (dado >= 4 && dado <= 12) {
                alvo.receberDano((int) Math.round(this.forca * 0.5 * 1.5));
                return "Ataque fraco. Dano: " + (int) Math.round(this.forca * 0.5 * 1.5) + " pontos de vida";
            } else if (dado == 2 || dado == 3) {
                alvo.receberDano(1);
                return "Passou de raspão! Dano: 1 ponto de vida";
            } else if (dado == 1) {
                return "Erro critico! Dano: 0 pontos de vida";
            } else {
                return "Erro, valor incorreto.";
            }
        } else {
            if (dado == 20) {
                alvo.receberDano((int) Math.round(this.forca * 2 * 1.5));
                return "Jogou uma pedra gigante! Dano: " + (int) Math.round((this.forca * 2 * 1.5)) + " pontos de vida";
            } else if (dado >= 15 && dado <= 19) {
                alvo.receberDano((int) Math.round(this.forca * 1.5 * 1.5));
                return "Jogou uma adaga! Dano: " + (int) Math.round(this.forca * 1.5 * 1.5) + " pontos de vida";
            } else if (dado >= 10 && dado <= 14) {
                alvo.receberDano((int) Math.round(this.forca * 1.5 * 1.5));
                return "Jogou uma pedra normal. Dano: " + (int) Math.round(this.forca * 1.5) + " pontos de vida";
            } else if (dado >= 4 && dado <= 9) {
                alvo.receberDano((int) Math.round(this.forca * 0.5 * 1.1));
                return "Ataque fraco. Dano: " + (int) Math.round(this.forca * 0.5 * 1.1) + " pontos de vida";
            } else if (dado == 2 || dado == 3) {
                return "Não encontrou nada para jogar";
            } else if (dado == 1) {
                alvo.setCura("atirar");
                return "Jogou uma poção de cura no adversário!";
            } else {
                return "Erro, valor incorreto.";
            }
        }
    }

    @Override
    public String receberDano(int valor) {
        this.vida -= valor;
        if (this.vida >= 1) {
            return this.getNome() + "recebeu um ataque e perdeu " + valor + " pontos de vida!";
        } else {
            return this.getNome() + "morreu!";
        }
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    public String getStatus() {
        return this.getNome() + " está com " + getVida() + " pontos de vida.";
    }

    @Override
    public String setCura(String metodo) {
        this.vida += 5;
        if (metodo == "atirar") {
            return "Aecebeu uma cura e aumentou a vida em 5";
        } else {
            return "Achou uma poção, ao tomar aumentou a vida em 5";
        }
    }

    @Override
    public String setCura(String metodo, int valor) {
        this.vida += valor;
        return "Se curou usando magia";
    }
     public String setMana(int valor){
        this.mana -= valor;
        return "Mana atual é: " + this.mana;
    }

}
