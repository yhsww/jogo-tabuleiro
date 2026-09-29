package src.models;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    public static List<Casa> casasTabuleiro = new ArrayList<>();
    public static final int TOTAL_CASAS = 40;
    
    // 0 - 39
    // 1 - 40

    public void criarTabuleiro(){

        Tabuleiro.casasTabuleiro.clear();
        for(int i = 0; i < TOTAL_CASAS; i++){
            Tabuleiro.casasTabuleiro.add(new CasaNeutra(i+1));
        }

        Tabuleiro.casasTabuleiro.set(12, new CasaSurpresa(13));
        Tabuleiro.casasTabuleiro.set(9, new CasaPularRodada(10));
        Tabuleiro.casasTabuleiro.set(24, new CasaPularRodada(25));
        Tabuleiro.casasTabuleiro.set(37, new CasaPularRodada(38));
        Tabuleiro.casasTabuleiro.set(4, new CasaDaSorte(5));
        Tabuleiro.casasTabuleiro.set(14, new CasaDaSorte(15));
        Tabuleiro.casasTabuleiro.set(29, new CasaDaSorte(30));
        Tabuleiro.casasTabuleiro.set(16, new CasaDaDiscordia(17));
        Tabuleiro.casasTabuleiro.set(26, new CasaDaDiscordia(27));
        Tabuleiro.casasTabuleiro.set(19, new CasaMagica(20));
        Tabuleiro.casasTabuleiro.set(34, new CasaMagica(35));
        
    }


}
