package src.models;
import java.util.List;

public class CasaNeutra extends Casa{
    
    public CasaNeutra(int posicao){
        super(posicao);
    }

    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        System.out.println(jogador.getNome() + " está na casa " + jogador.getPosicao());
        
    }

}
