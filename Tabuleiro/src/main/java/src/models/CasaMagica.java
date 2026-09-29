package src.models;
import java.util.Comparator;
import java.util.List;

public class CasaMagica extends Casa{

    public CasaMagica(int posicao){
        super(posicao);
    }

    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){

        System.out.println("Casa Mágica: jogador troca de posição com o último jogador!");
        List<Jogador> jogadoresPorPosicao = jogadores.stream().sorted(Comparator.comparing(Jogador::getPosicao)).toList();

        Jogador ultimo = jogadoresPorPosicao.get(0);

        if(ultimo.equals(jogador)){
            System.out.println("O jogador deve permanecer na mesma casa");
            return;
        }

        int posicaoAtual = jogador.getPosicao();
        int posicaoUltimo = jogadoresPorPosicao.get(0).getPosicao();

        jogador.setPosicao(posicaoUltimo);
        ultimo.setPosicao(posicaoAtual);

        System.out.println(jogador.getNome() + " agora está na casa " + jogador.getPosicao());
        System.out.println(ultimo + " agora está na casa " + ultimo.getPosicao());

    }
    
}
