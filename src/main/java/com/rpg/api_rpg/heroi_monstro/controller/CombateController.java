package com.rpg.api_rpg.heroi_monstro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rpg.api_rpg.heroi_monstro.model.Heroi;
import com.rpg.api_rpg.heroi_monstro.model.Monstro;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CombateController {
    private static Heroi heroi = new Heroi("Heroi");
    private static Monstro monstro = new Monstro();
    public Random aleatorio = new Random();

    @PostMapping("/atacar")
    public String atacar(String ataqueHeroi) {
        if (heroi.getVida() <= 0) {
            return "Personagem " + heroi.getNome() + "Já está morto!";
        } else if (monstro.getVida() <= 0) {
            return "Monstro " + monstro.getNome() + "Já está morto!";
        } else {
  
            List<String> ataqueMonstro = new ArrayList<String>(List.of("Normal", "Pesado", "JogarItem"));
            return heroi.atacar(monstro, ataqueHeroi) + "\n"
                    + monstro.atacar(heroi, ataqueMonstro.get(this.aleatorio.nextInt(2)));
        }
    }

    @PostMapping("/curar")
    public String curar() {
        if (heroi.getCura() == 0) {
            return "Sem poções de cura!";
        } else {
            List<String> ataqueMonstro = new ArrayList<String>(List.of("Normal", "Pesado", "JogarItem"));
            return heroi.curar() + "\n" + monstro.atacar(heroi, ataqueMonstro.get(this.aleatorio.nextInt(2)));
        }
    }

    @GetMapping("/statusHeroi")
    public String getStatusHeroi() {
        return heroi.getStatus();
    }

    @GetMapping("/statusMonstro")
    public String getStatusMonstro() {
        return monstro.getStatus();
    }
}
