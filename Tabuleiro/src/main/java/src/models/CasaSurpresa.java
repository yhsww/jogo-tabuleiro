package src.models;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CasaSurpresa extends Casa{

    public CasaSurpresa(int posicao){
        super(posicao);
    }

    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        //1 - azarado, 2 - sortudo, 3 - normal

        System.out.println("Casa Surpresa: altera o tipo de jogador!");

        
        int cartaSorteada;
        do{
            cartaSorteada = ThreadLocalRandom.current().nextInt(1, 4);
        }while(cartaSorteada == 1 && jogador instanceof JogadorAzarado ||
               cartaSorteada == 2 && jogador instanceof JogadorSortudo ||
               cartaSorteada == 3 && jogador instanceof JogadorNormal);

        Jogador novoJogador;
        if(cartaSorteada == 1){
            System.out.println("Carta Surpresa: jogador agora é azarado!");
            novoJogador = new JogadorAzarado(jogador.getNome(), jogador.getCor());
        }else if(cartaSorteada == 2){
            System.out.println("Carta Surpresa: jogador agora é sortudo!");
            novoJogador = new JogadorSortudo(jogador.getNome(), jogador.getCor());
        }else{
            System.out.println("Carta Surpresa: jogador agora é normal!");
            novoJogador = new JogadorNormal(jogador.getNome(), jogador.getCor());
        }

        
        novoJogador.setPosicao(jogador.getPosicao());
        novoJogador.setPulaRodada(jogador.getPulaRodada());
        novoJogador.setQtdRodadas(jogador.getQtdRodadas());

        int indice = jogadores.indexOf(jogador);
        if(indice >= 0){
            jogadores.set(indice, novoJogador);
        }

        System.out.println(novoJogador.getNome() + " está na casa " + novoJogador.getPosicao());
    }
}