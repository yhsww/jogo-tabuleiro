package src.models;
public abstract class Jogador {

    protected String nome;
    protected Cor cor;
    protected int posicao;
    protected boolean pulaRodada;
    protected int qtdRodadas;

    public Jogador(String nome, Cor cor){
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.pulaRodada = false;
        this.qtdRodadas = 0;
    }

    public void setQtdRodadas(int qtdRodadas){
        this.qtdRodadas = qtdRodadas;
    }

    public int getQtdRodadas(){
        return this.qtdRodadas;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPulaRodada(boolean pulaRodada){
        this.pulaRodada = pulaRodada;
    }

    public boolean getPulaRodada(){
        return this.pulaRodada;
    }

    public String getNome(){
        return this.nome;
    }

    public void setCor(Cor cor){
        this.cor = cor;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }

    public Cor getCor(){
        return this.cor;
    }

    public abstract boolean jogarDados();

    public String toString(){
        return "Nome: " + this.nome + " |Cor: " + this.cor.getTipoCor() + " |Posição: " + this.posicao;
    }

}
