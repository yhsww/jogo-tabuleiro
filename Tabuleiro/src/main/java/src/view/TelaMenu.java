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

import src.models.Jogo;
public class TelaMenu implements Tela {

    @Override
    public Parent getView() {

        // Fundo cinza claro
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

       
        Label titulo = new Label("Jogo de Tabuleiro");
        titulo.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 42));

        
        Label subtitulo = new Label("Menu");
        subtitulo.setFont(Font.font("System", FontWeight.BOLD, 24));

     
        Button btnJogar = criarBotao("Jogar");
        Button btnConfiguracoes = criarBotao("Configurações");
        Button btnRegras = criarBotao("Regras");

        btnJogar.setOnAction(e -> {
            Jogo jogo = new Jogo(false); // AJUSTAR: modoDebug fixo em false por enquanto
            Navegadordetelas.irPara(new Telacadastrojogadores(jogo));
        });
        Configuracoes.iniciarDebugNoTerminal(() -> {
        btnConfiguracoes.setDisable(false);
        btnJogar.setDisable(false);
});
        btnRegras.setOnAction(e -> {
            Navegadordetelas.irPara(new Telaregras());
});

        card.getChildren().addAll(titulo, subtitulo, btnJogar, btnConfiguracoes, btnRegras);
        fundo.getChildren().add(card);

        return fundo;
    }

    private Button criarBotao(String texto) {
        Button botao = new Button(texto);
        botao.setFont(Font.font("System", FontWeight.BOLD, 18));
        botao.setPrefWidth(370);
        botao.setPrefHeight(55);
        botao.setStyle(
            "-fx-background-color: #c3cad4; " +
            "-fx-background-radius: 30; " +
            "-fx-text-fill: black;"
        );

        //efeito hover simples
        botao.setOnMouseEntered(e -> botao.setStyle(
            "-fx-background-color: #b0b8c4; " +
            "-fx-background-radius: 30; " +
            "-fx-text-fill: black;"
        ));
        botao.setOnMouseExited(e -> botao.setStyle(
            "-fx-background-color: #c3cad4; " +
            "-fx-background-radius: 30; " +
            "-fx-text-fill: black;"
        ));

        return botao;
    }
}