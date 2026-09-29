/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.view;

/**
 *
 * @author julia
 */

import javafx.application.Platform;
import src.models.Jogo;
import src.util.Utilitarios;

public class Configuracoes {


    public static void iniciarDebugNoTerminal(Runnable aoTerminar) {
        Thread thread = new Thread(() -> {
            try {
                rodarDebug();
            } finally {
                if (aoTerminar != null) {
                    Platform.runLater(aoTerminar);
                }
            }
        });
        thread.setDaemon(true); 
        thread.start();
    }

    private static void rodarDebug() {
        Jogo jogo = new Jogo(true); 

        System.out.println("===== MODO DEBUG (terminal) =====");
        System.out.println("Quantos jogadores (2 a 6)? ");
        int qtd = Utilitarios.inteiroValido();
        while (qtd < 2 || qtd > 6) {
            System.out.println("Valor inválido. Insira de 2 a 6: ");
            qtd = Utilitarios.inteiroValido();
        }

        for (int i = 0; i < qtd; i++) {
            jogo.adicionarJogador(); 
        }

        
        while (!jogo.autorizarPartida()) {
            if (Jogo.getJogadores().size() >= 6) {
                System.out.println("Não foi possível formar tipos diferentes. Tente de novo.");
                return;
            }
            System.out.println("Adicione mais um jogador para ter tipos diferentes.");
            jogo.adicionarJogador();
        }

        while (true) {
            jogo.iniciarRodada();
            boolean alguemChegou = Jogo.getJogadores().stream()
                                       .anyMatch(j -> j.getPosicao() >= 40);
            if (alguemChegou) {
                jogo.iniciarRodada();
                break;
            }
        }

        System.out.println("===== FIM DO MODO DEBUG =====");
    }
}