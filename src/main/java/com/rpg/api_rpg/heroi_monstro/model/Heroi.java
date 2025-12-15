
package com.rpg.api_rpg.heroi_monstro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Heroi implements Combatente {

    private String nome;
    private int vida = 100;
    private int forca = 8;
    private int cura = 3;
    private int mana = 10;
    private int moedas = 0;
    private int experiencia = 0;
    private int nivel = 1;
    private List<String> magias = new ArrayList<String>(List.of("Bola de fogo", "Dardo de gelo", "Vento Cortante"));

    public Heroi(String nomeHeroi) {
        this.nome = nomeHeroi;
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
                return "Acerto Critico! Dano: " + (this.forca * 2) + " pontos de vida";
            } else if (dado >= 13 && dado <= 19) {
                alvo.receberDano(this.forca);
                this.verificaMorte(alvo);
                return "Acerto cheio! Dano: " + this.forca + " pontos de vida";
            } else if (dado >= 8 && dado <= 12) {
                alvo.receberDano((int) Math.round(this.forca * 0.5));
                this.verificaMorte(alvo);
                return "De raspão. Dano: " + (int) Math.round(this.forca * 0.5) + " pontos de vida";
            } else if (dado >= 2 && dado <= 7) {
                return "Errou ataque";
            } else if (dado == 1) {
                this.vida -= 1;
                this.verificaMorte(this);
                return "Erro critico! 1 de dano em si mesmo!";
            } else {
                return "Erro, valor incorreto.";
            }
        } else if (ataque == "Pesado") {
            if (dado >= 18 && dado <= 20) {
                alvo.receberDano((int) Math.round(this.forca * 2 * 1.5));
                this.verificaMorte(alvo);
                return "Dano Critico! Dano: " + (int) Math.round((this.forca * 2 * 1.5)) + " pontos de vida";
            } else if (dado >= 15 && dado <= 17) {
                alvo.receberDano((int) Math.round(this.forca * 1.5));
                this.verificaMorte(alvo);
                return "Ataque com precisão! Dano: " + (int) Math.round(this.forca * 1.5) + " pontos de vida";
            } else if (dado >= 6 && dado <= 14) {
                alvo.receberDano((int) Math.round(this.forca * 1.5 * 0.5));
                this.verificaMorte(alvo);
                return "Ataque pesado normal. Dano: " + (int) Math.round(this.forca * 1.5 * 0.5) + " pontos de vida";
            } else if (dado >= 2 || dado <= 5) {
                return "Errou o ataque!";
            } else if (dado == 1) {
                this.vida -= 1;
                this.verificaMorte(this);
                return "Erro critico! 1 de dano a si mesmo!";
            } else {
                return "Erro, valor incorreto.";
            }
        } else {
            if (dado >= 15 && dado <= 20) {
                alvo.receberDano((int) Math.round(this.forca * 0.8 * 1.5));
                return "Jogou uma pedra gigante! Dano: " + (int) Math.round((this.forca * 0.8 * 1.5))
                        + " pontos de vida";
            } else if (dado >= 3 && dado <= 14) {
                alvo.receberDano((int) Math.round(this.forca * 0.8));
                this.verificaMorte(alvo);
                return "Jogou uma adaga! Dano: " + (int) Math.round(this.forca * 0.8) + " pontos de vida";
            } else if (dado == 2) {
                return "Não encontrou nada para jogar";
            } else if (dado == 1) {
                alvo.setCura("atirar");
                return "Jogou uma poção de cura no adversário!";
            } else {
                return "Erro, valor incorreto.";
            }
        }
    }

    private void verificaMorte(Combatente alvo) {
        if (alvo.getVida() <= 0) {
            aumentarExperiencia(20);
        }
    }

    public void aumentarExperiencia(int experiencia) {
        this.experiencia += experiencia;
        if (this.experiencia >= 100) {
            this.experiencia -= 100;
            this.nivel += 1;
            this.forca += 1;
            this.mana += 1;
        }
    }

    public String magia(Combatente alvo, int magia) {
        if (alvo.getVida() <= 0) {
            return alvo.getNome() + "está morto!";
        }
        Random aleatorio = new Random();
        int dado = aleatorio.nextInt(21);
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

    }

    public String setCura(String metodo) {
        this.vida += 5;
        if (metodo == "atirar") {

            return "Recebeu uma cura e aumentou a vida em 5";
        } else if (metodo == "magia") {
            return "Se curou usando magia";
        } else {
            this.vida += 5;
            return "Achou uma poção, ao tomar aumentou a vida em 5";
        }
    }

    public String setCura(String metodo, int valor) {
        this.vida += valor;
        return "Se curou usando magia";
    }

    @Override
    public String receberDano(int valor) {
        if (this.vida <= 0) {
            return this.nome + "está morto!";
        }
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

    public int getCura() {
        return this.cura;
    }

    public String curar() {
        if (this.cura == 0) {
            return "Não tem mais nenhuma poção de cura";
        }
        Random aleatorio = new Random();
        int dado = aleatorio.nextInt(21);
        if (dado == 20) {
            this.cura -= 1;
            this.vida += 10;
            return "Tomou uma poção rara! Curou: 10 pontos de vida";
        } else if (dado >= 15 && dado <= 19) {
            this.cura -= 1;
            this.vida += 7;
            return "Tomou uma poção melhorada! Curou: 7 pontos de vida";
        } else if (dado >= 10 && dado <= 14) {
            this.cura -= 1;
            this.vida += 5;
            return "Tomou a poção de cura. Curou: 5 pontos de vida ";
        } else if (dado >= 4 && dado <= 9) {
            this.cura -= 1;
            this.vida += 3;
            return "Derramou um pouco da poção. Curou: 3 pontos de vida";

        } else if (dado == 2 || dado == 3) {
            this.cura -= 1;
            this.vida += 1;
            return "Derramou quase toda poção! Curou: 1 ponto de vida";

        } else if (dado == 1) {
            this.cura -= 1;
            return "Erro critico, derrubou toda poção! Curou: 0 pontos de vida";
        } else {
            return "Erro, valor incorreto.";
        }
    }

    public String getStatus() {
        return this.getNome() + " está com " + getVida() + " pontos de vida. Também tem " + this.cura
                + " poções de cura no inventário.";
    }

    public String setMana(int valor) {
        this.mana -= valor;
        return "Mana atual é: " + this.mana;
    }
}
