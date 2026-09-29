package src.models;
import java.util.List;

public class CasaPularRodada extends Casa{

    public CasaPularRodada(int posicao){
        super(posicao);
    }

    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        System.out.println("Casa Pula Rodada: jogador passará a próxima rodada sem jogar!");
        jogador.setPulaRodada(true);
        System.out.println(jogador.getNome() + " está na casa " + jogador.getPosicao());

    }
    
}
