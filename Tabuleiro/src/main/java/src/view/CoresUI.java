package src.view;

import javafx.scene.paint.Color;
import src.models.Cor;

/**
 * AJUSTAR quando você mandar o Cor.java: os nomes abaixo são um chute
 * baseado no getTipoCor() que aparece no seu Jogo.java.
 */
public class CoresUI {

    private CoresUI() {
    }

    public static Color converter(Cor cor) {
        String tipo = cor.getTipoCor().toLowerCase();
        return switch (tipo) {
            case "vermelho" -> Color.RED;
            case "azul"     -> Color.ROYALBLUE;
            case "verde"    -> Color.FORESTGREEN;
            case "amarelo"  -> Color.GOLD;
            case "roxo"     -> Color.MEDIUMPURPLE;
            case "laranja"  -> Color.DARKORANGE;
            case "rosa"     -> Color.HOTPINK;
            default         -> Color.GRAY;
        };
    }
}