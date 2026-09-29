/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 *
 * @author julia
 */
public class Telaregras implements Tela{

    
    @Override
    public Parent getView() {
    StackPane fundo = new StackPane();
    fundo.setStyle("-fx-background-color: #d9d9d9;");
    fundo.setPrefSize(1120, 610);
    VBox card = new VBox(20);
    card.setAlignment(Pos.CENTER);
    card.setPadding(new Insets(50, 90, 50, 90));
    card.setMaxSize(840, 560);
    card.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
    DropShadow sombra = new DropShadow();
    sombra.setRadius(15);
    sombra.setColor(Color.rgb(0, 0, 0, 0.15));
    card.setEffect(sombra);
    Label titulo = new Label("Regras do jogo");
    titulo.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 42));
    Label texto = new Label(
        "• O tabuleiro tem 40 casas. Vence quem chegar primeiro na casa 40.\n" +
        "• Cada jogador joga na sua vez, rolando dois dados.\n" +
        "• Dados iguais: o jogador joga de novo.\n" +
        "• Jogadores azarados tiram no máximo 6; sortudos, mais de 6.\n\n" +
        "Casas especiais:\n" +
        "• Sorte (5, 15, 30): anda 3 casas, exceto se for azarado.\n" +
        "• Pula rodada (10, 25, 38): fica sem jogar na próxima rodada.\n" +
        "• Surpresa (13): sorteia um novo tipo de jogador.\n" +
        "• Discórdia (17, 27): escolhe um jogador para voltar ao início.\n" +
        "• Mágica (20, 35): troca de lugar com o último jogador."
    );
    texto.setFont(Font.font("System", 18));
    texto.setWrapText(true);
    Button btnVoltar = new Button("Voltar");
    btnVoltar.setFont(Font.font("System", FontWeight.BOLD, 16));
    btnVoltar.setPrefWidth(200);
    btnVoltar.setOnAction(e -> Navegadordetelas.irPara(new TelaMenu()));

    
    card.getChildren().addAll(titulo, texto, btnVoltar);
    fundo.getChildren().add(card);

    return fundo;
}
        
    }
