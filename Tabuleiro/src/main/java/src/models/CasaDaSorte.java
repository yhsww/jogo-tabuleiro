package src.models;
import java.util.List;

public class CasaDaSorte extends Casa{

    public CasaDaSorte(int posicao){
        super(posicao);
    }

    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        System.out.println("Casa da sorte: ande 3 casas se não for azarado!");
        if(!(jogador instanceof JogadorAzarado)){

            int novaPosicao = jogador.getPosicao() + 3;
            jogador.setPosicao(novaPosicao);
            System.out.println(jogador.getNome() + " está na casa " + jogador.getPosicao());
        }else{
            System.out.println("Jogador azarado! Permanece na mesma casa");
            System.out.println(jogador.getNome() + " está na casa " + jogador.getPosicao());
        }
        
    }



    
}
