package src.view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import src.models.Cor;
import src.models.Jogador;
import src.models.Jogo;

public class Telacadastrojogadores implements Tela {

    private static final int MAX_JOGADORES = 6; 

    private final Jogo jogo;
    private final ComboBox<Cor> comboCor = new ComboBox<>();
    private final TextField campoNome = new TextField();
    private final VBox listaJogadores = new VBox(8);
    private final Label contador = new Label();
    private final Button btnJogar = new Button("Jogar");

    public Telacadastrojogadores(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public Parent getView() {
        StackPane fundo = new StackPane();
        fundo.setStyle("-fx-background-color: #d9d9d9;");
        fundo.setPrefSize(1120, 700);

        VBox card = new VBox(18);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(45, 60, 45, 60));
        card.setMaxSize(700, 620);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 30;");

        DropShadow sombra = new DropShadow();
        sombra.setRadius(15);
        sombra.setColor(Color.rgb(0, 0, 0, 0.15));
        card.setEffect(sombra);

        Label titulo = new Label("Jogo de Tabuleiro");
        titulo.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 30));

        Label subtitulo = new Label("Cadastro de Jogadores");
        subtitulo.setFont(Font.font("System", FontWeight.BOLD, 18));

        HBox formulario = montarFormulario();

        contador.setFont(Font.font("System", FontWeight.BOLD, 13));
        contador.setStyle("-fx-text-fill: #5b6472;");

        listaJogadores.setPadding(new Insets(10, 0, 10, 0));

        btnJogar.setFont(Font.font("System", FontWeight.BOLD, 16));
        btnJogar.setPrefWidth(220);
        btnJogar.setPrefHeight(48);
        btnJogar.setStyle("-fx-background-color: #c3cad4; -fx-background-radius: 24;");
        btnJogar.setDisable(true);
        btnJogar.setOnAction(e -> Navegadordetelas.irPara(new Telajogo(jogo)));

        card.getChildren().addAll(titulo, subtitulo, formulario, contador, listaJogadores, btnJogar);
        fundo.getChildren().add(card);

        atualizarComboCor();
        atualizarLista();

        return fundo;
    }

    private HBox montarFormulario() {
        HBox linha = new HBox(10);
        linha.setAlignment(Pos.CENTER);

        campoNome.setPromptText("Nome do jogador");
        campoNome.setPrefWidth(220);

        comboCor.setPromptText("Cor");
        comboCor.setPrefWidth(140);
        comboCor.setCellFactory(lv -> celulaDeCor());
        comboCor.setButtonCell(celulaDeCor());

        Button btnAdicionar = new Button("Adicionar");
        btnAdicionar.setStyle("-fx-background-color: #c3cad4; -fx-background-radius: 18;");
        btnAdicionar.setOnAction(e -> adicionarJogador());

        linha.getChildren().addAll(campoNome, comboCor, btnAdicionar);
        return linha;
    }

    private ListCell<Cor> celulaDeCor() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Cor cor, boolean vazio) {
                super.updateItem(cor, vazio);
                if (vazio || cor == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Circle bolinha = new Circle(6, CoresUI.converter(cor));
                    setGraphic(bolinha);
                    setText(cor.getTipoCor());
                }
            }
        };
    }

    private void adicionarJogador() {
        String nome = campoNome.getText() == null ? "" : campoNome.getText().trim();
        Cor cor = comboCor.getValue();

        if (nome.isEmpty()) {
            mostrarAviso("Insira um nome para o jogador.");
            return;
        }
        if (cor == null) {
            mostrarAviso("Escolha uma cor para o jogador.");
            return;
        }
        if (Jogo.getJogadores().size() >= MAX_JOGADORES) {
            mostrarAviso("Limite máximo de " + MAX_JOGADORES + " jogadores atingido.");
            return;
        }

        jogo.adicionarJogador(nome, cor); 

        campoNome.clear();
        comboCor.setValue(null);

        atualizarComboCor();
        atualizarLista();
    }

    private void atualizarComboCor() {
        List<Cor> disponiveis = new ArrayList<>(List.of(Cor.values()));
        for (Jogador jogador : Jogo.getJogadores()) {
            disponiveis.remove(jogador.getCor());
        }
        comboCor.getItems().setAll(disponiveis);
    }

    private void atualizarLista() {
        listaJogadores.getChildren().clear();

        for (Jogador jogador : Jogo.getJogadores()) {
            HBox linha = new HBox(10);
            linha.setAlignment(Pos.CENTER_LEFT);

            Circle bolinha = new Circle(7, CoresUI.converter(jogador.getCor()));

            Label nome = new Label(jogador.getNome() + " — " + jogador.getCor().getTipoCor());
            nome.setFont(Font.font("System", FontWeight.NORMAL, 14));

            linha.getChildren().addAll(bolinha, nome);
            listaJogadores.getChildren().add(linha);
        }

        int qtd = Jogo.getJogadores().size();
        contador.setText(qtd + "/" + MAX_JOGADORES + " jogadores");
        btnJogar.setDisable(qtd < 2);
    }

    private void mostrarAviso(String mensagem) {
        Alert alerta = new Alert(AlertType.WARNING, mensagem);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}