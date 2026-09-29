package src.models;
import java.util.List;

public abstract class Casa {

    protected int posicao;

    public Casa(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public abstract void aplicarEfeito(Jogador jogador, List<Jogador> jogadores);

}
