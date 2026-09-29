package src.models;
import java.util.concurrent.ThreadLocalRandom;

public class JogadorNormal extends Jogador{

    public JogadorNormal(String nome, Cor cor){
        super(nome, cor);
    }

    public boolean jogarDados(){

        boolean jogaDeNovo = false;

        int dado1 = ThreadLocalRandom.current().nextInt(1, 7);
        int dado2 = ThreadLocalRandom.current().nextInt(1, 7);

        int resultado = dado1 + dado2;
        this.posicao += resultado;

        System.out.println("Dado 1: " + dado1);
        System.out.println("Dado 2: " + dado2);
        System.out.println(this.nome + " anda " + resultado + " casas");

        if(dado1 == dado2){
            jogaDeNovo = true;
        }

        return jogaDeNovo; 
    }
    
}
