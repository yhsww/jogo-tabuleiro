package src.models;
import java.util.concurrent.ThreadLocalRandom;

public class JogadorAzarado extends Jogador{

    public JogadorAzarado(String nome, Cor cor){
        super(nome, cor);
    }

    public boolean jogarDados(){

        boolean jogaDeNovo = false;

        int dado1 = 0, dado2 = 0, resultado = 0;

        do{
            dado1 = ThreadLocalRandom.current().nextInt(1, 7);
            dado2 = ThreadLocalRandom.current().nextInt(1, 7);
            resultado = dado1 + dado2;

        }while(resultado > 6);

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
