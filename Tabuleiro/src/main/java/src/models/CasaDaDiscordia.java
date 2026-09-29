package src.models;
import java.util.ArrayList;
import java.util.List;

import src.util.Utilitarios;

public class CasaDaDiscordia extends Casa{

    public CasaDaDiscordia(int posicao){
        super(posicao);
    }
    
    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        System.out.println("Casa da discórdia: escolha um jogador para voltar ao início do jogo!");
        
        Jogador busca = null;

        List<Cor> coresDisponiveis = jogadores.stream().filter(j -> !j.equals(jogador)).map(j -> j.getCor()).toList();

        for(Cor cor: coresDisponiveis){
            System.out.println(cor.getNumCor() + " - " + cor.getTipoCor());
        }

        System.out.println("Insira o número correspondente à cor do oponente: ");
        int numCor  = Utilitarios.inteiroValido();

        for(Jogador oponente: jogadores){

            if(!oponente.equals(jogador) && oponente.getCor().getNumCor() == numCor){
                busca = oponente;
                break;
            }
        }

        if(busca == null){
            System.out.println("Jogador não encontrado!");
            return;
        }
       
        busca.setPosicao(1);
        System.out.println(busca.getNome() + " retorna para a casa 1");

    }


}
