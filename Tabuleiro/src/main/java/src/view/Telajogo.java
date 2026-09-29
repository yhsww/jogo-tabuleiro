package src.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import src.models.Casa;
import src.models.CasaDaDiscordia;
import src.models.CasaDaSorte;
import src.models.CasaMagica;
import src.models.CasaPularRodada;
import src.models.CasaSurpresa;
import src.models.Cor;
import src.models.Jogador;
import src.models.JogadorAzarado;
import src.models.JogadorSortudo;
import src.models.Jogo;

public class Telajogo implements Tela {

    private static final int TAMANHO_GRID = 11;
    private static final int TOTAL_CASAS = 40;

    private final Jogo jogo;
    private final Map<Integer, FlowPane> tokensPorCasa = new HashMap<>();

    
    private Label lblVez;          
    private Label lblTituloCentro; 
    private Label lblMensagem;    
    private FlowPane boxEscolha;  
    private Button btnRodar;      


    public Telajogo(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public Parent getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #d9d9d9;");

        root.setCenter(montarTabuleiro());
        root.setBottom(montarControles());

       //mostrar de quem é a vez
        lblVez = new Label();
        lblVez.setFont(Font.font("System", FontWeight.BOLD, 20));
        VBox topo = new VBox(lblVez);
        topo.setAlignment(Pos.CENTER);
        topo.setPadding(new Insets(0, 0, 10, 0));
        root.setTop(topo);

        atualizarPosicoes();
        atualizarIndicadorVez();

        return root;
    }

    private GridPane montarTabuleiro() {
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setAlignment(Pos.CENTER);

        List<int[]> posicoes = calcularPosicoesPerimetro();

        for (int i = 0; i < posicoes.size(); i++) {
            int numeroCasa = i + 1;
            int[] rc = posicoes.get(i);
            StackPane casaView = criarCasaView(numeroCasa);
            grid.add(casaView, rc[1], rc[0]);
        }

        lblTituloCentro = new Label("Jogo de\nTabuleiro");
        lblTituloCentro.setFont(Font.font("System", FontWeight.BOLD, 26));
        lblTituloCentro.setTextAlignment(TextAlignment.CENTER);
        lblTituloCentro.setWrapText(true);
        lblTituloCentro.setMaxWidth(400);

        lblMensagem = new Label("Clique em \"Rolar dados\" para começar!");
        lblMensagem.setFont(Font.font("System", 15));
        lblMensagem.setTextAlignment(TextAlignment.CENTER);
        lblMensagem.setWrapText(true);
        lblMensagem.setMaxWidth(400);

        boxEscolha = new FlowPane(8, 8);
        boxEscolha.setAlignment(Pos.CENTER);
        boxEscolha.setPrefWrapLength(400);
        boxEscolha.setMaxWidth(400);

        VBox conteudoCentro = new VBox(12, lblTituloCentro, lblMensagem, boxEscolha);
        conteudoCentro.setAlignment(Pos.CENTER);

        StackPane centro = new StackPane(conteudoCentro);
        centro.setAlignment(Pos.CENTER);

        GridPane.setColumnSpan(centro, TAMANHO_GRID - 2);
        GridPane.setRowSpan(centro, TAMANHO_GRID - 2);
        GridPane.setHalignment(centro, HPos.CENTER);
        GridPane.setValignment(centro, VPos.CENTER);
        grid.add(centro, 1, 1);

        return grid;
    }

    private List<int[]> calcularPosicoesPerimetro() {
        List<int[]> posicoes = new ArrayList<>();
        int n = TAMANHO_GRID;

        for (int col = n - 1; col >= 0; col--) {
            posicoes.add(new int[]{n - 1, col});
        }

        for (int row = n - 2; row >= 0; row--) {
            posicoes.add(new int[]{row, 0});
        }

        for (int col = 1; col <= n - 1; col++) {
            posicoes.add(new int[]{0, col});
        }

        for (int row = 1; row <= n - 2; row++) {
            posicoes.add(new int[]{row, n - 1});
        }

        return posicoes;
    }

    private StackPane criarCasaView(int numeroCasa) {
        StackPane casaView = new StackPane();
        casaView.setPrefSize(55, 55);
        casaView.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #999999; " +
            "-fx-border-width: 1;"
        );
        VBox conteudo = new VBox(2);
        conteudo.setAlignment(Pos.CENTER);

        Label numero = new Label(String.valueOf(numeroCasa));
        numero.setFont(Font.font("System", FontWeight.BOLD, 10));

        FlowPane tokens = new FlowPane(2, 2);
        tokens.setAlignment(Pos.CENTER);
        tokens.setPrefWrapLength(40);

        conteudo.getChildren().addAll(numero, tokens);
        casaView.getChildren().add(conteudo);

        tokensPorCasa.put(numeroCasa, tokens);

        return casaView;
    }

    private VBox montarControles() {
        VBox controles = new VBox(10);
        controles.setAlignment(Pos.CENTER);
        controles.setPadding(new Insets(15, 0, 0, 0));

        
        btnRodar = new Button("Rolar dados");
        btnRodar.setFont(Font.font("System", FontWeight.BOLD, 16));
        btnRodar.setPrefWidth(280);
        btnRodar.setPrefHeight(45);
        btnRodar.setStyle("-fx-background-color: #c3cad4; -fx-background-radius: 20;");

        btnRodar.setOnAction(e -> jogarTurno());
        controles.getChildren().add(btnRodar);
        return controles;
    }

   
    private void jogarTurno() {
        if (jogo.isFimDeJogo() || jogo.getJogadorAtual() == null) return;

        //confere as regras
        if (!jogo.iniciarPartidaSeAutorizada()) {
            mostrarMensagem("Partida não autorizada",
                "É preciso ter pelo menos 2 jogadores e 2 tipos de jogador diferentes " +
                "(azarado, sortudo, normal).");
            return;
        }

        Jogador jogador = jogo.getJogadorAtual();
        int de = jogador.getPosicao();
        boolean jogaDeNovo = jogo.rolarDadosAtual(); //só o jogador da vez rola os dados
        int para = jogador.getPosicao();
        Casa casa = jogo.getCasaAtual();

        String cabecalho = (de <= 0)
            ? jogador.getNome() + " foi para a casa " + para + "."
            : jogador.getNome() + " andou da casa " + de + " para a casa " + para + ".";
        String titulo = tituloDaCasa(casa);
        String corpo = cabecalho + "\n\n" + descricaoDaCasa(casa);

        
        if (casa instanceof CasaDaDiscordia) {
            atualizarPosicoes();
            mostrarEscolhaDiscordia(jogador, jogaDeNovo, titulo, corpo);
            return;
        }

       
        Map<Cor, Integer> antes = new HashMap<>();
        for (Jogador j : Jogo.getJogadores()) {
            antes.put(j.getCor(), j.getPosicao());
        }

        casa.aplicarEfeito(jogador, Jogo.getJogadores());

        StringBuilder sb = new StringBuilder(corpo);
        String mudancas = descreverMudancas(antes);
        if (!mudancas.isEmpty()) {
            sb.append("\n\n").append(mudancas);
        }
        if (casa instanceof CasaSurpresa) {
            
            sb.append("\n\nNovo tipo de ").append(jogador.getNome()).append(": ")
              .append(nomeDoTipo(jogo.getJogadorAtual())).append(".");
        }

        concluirTurno(jogaDeNovo, titulo, sb.toString());
    }

    
    private void concluirTurno(boolean jogaDeNovo, String titulo, String corpo) {
        jogo.encerrarJogada(); 
        atualizarPosicoes();

        StringBuilder sb = new StringBuilder(corpo);

        if (jogo.isFimDeJogo()) {
            Jogador vencedor = jogo.getVencedor();
            mostrarMensagem("Fim de jogo!",
                sb + "\n\nVencedor: " + vencedor.getNome() + "! Parabéns!");
            btnRodar.setDisable(true);
            atualizarIndicadorVez();
            return;
        }

        Jogador atual = jogo.getJogadorAtual();

        if (jogaDeNovo && !atual.getPulaRodada()) {
            sb.append("\n\nDados iguais! ").append(atual.getNome()).append(" joga de novo.");
        } else {
            if (jogaDeNovo) {
                sb.append("\n\nDados iguais, mas ").append(atual.getNome())
                  .append(" vai ficar sem jogar na próxima rodada.");
            }
            
            for (Jogador pulado : jogo.avancarVez()) {
                sb.append("\n").append(pulado.getNome())
                  .append(" perdeu a vez (Casa Pula Rodada).");
            }
        }

        mostrarMensagem(titulo, sb.toString());
        atualizarIndicadorVez();
        btnRodar.setDisable(false);
    }

   
    private void mostrarEscolhaDiscordia(Jogador jogador, boolean jogaDeNovo, String titulo, String corpo) {
        mostrarMensagem(titulo, corpo + "\n\nEscolha quem volta para o início:");
        btnRodar.setDisable(true); //impede rolar os dados enquanto a escolha não é feita

        for (Jogador alvo : Jogo.getJogadores()) {
            if (alvo == jogador) continue; //não pode escolher a si mesmo

            Color cor = converterCor(alvo.getCor());
            Button botao = new Button(alvo.getNome());
            botao.setFont(Font.font("System", FontWeight.BOLD, 14));
            botao.setStyle(
                "-fx-background-color: " + paraHex(cor) + "; " +
                "-fx-text-fill: " + corDoTexto(cor) + "; " +
                "-fx-border-color: black; -fx-border-radius: 15; -fx-background-radius: 15;"
            );

            botao.setOnAction(e -> {
                jogo.enviarParaInicio(alvo); // alvo volta para a casa 1
                boxEscolha.getChildren().clear();
                concluirTurno(jogaDeNovo, titulo,
                    corpo + "\n\n" + jogador.getNome() + " escolheu " + alvo.getNome() +
                    ", que volta para a casa 1.");
            });

            boxEscolha.getChildren().add(botao);
        }
    }

   
    private void mostrarMensagem(String titulo, String corpo) {
        lblTituloCentro.setFont(Font.font("System", FontWeight.BOLD, 22));
        lblTituloCentro.setText(titulo);
        lblMensagem.setText(corpo);
        boxEscolha.getChildren().clear();
    }

    //atualizar o que tem la em cima com o nome e cor
    private void atualizarIndicadorVez() {
        Jogador j = jogo.isFimDeJogo() ? jogo.getVencedor() : jogo.getJogadorAtual();
        if (j == null) {
            lblVez.setText("Nenhum jogador");
            return;
        }
        Color cor = converterCor(j.getCor());
        String prefixo = jogo.isFimDeJogo() ? "Vencedor: " : "Vez de: ";
        lblVez.setText(prefixo + j.getNome() + " (" + j.getCor().getTipoCor() + ")");
        // Fundo na cor do jogador; o texto fica preto ou branco conforme a cor, para dar para ler.
        lblVez.setStyle(
            "-fx-background-color: " + paraHex(cor) + "; " +
            "-fx-text-fill: " + corDoTexto(cor) + "; " +
            "-fx-padding: 6 20 6 20; " +
            "-fx-background-radius: 20; " +
            "-fx-border-color: black; -fx-border-radius: 20;"
        );
    }

    
    private String tituloDaCasa(Casa casa) {
        String nome;
        if (casa instanceof CasaDaSorte)          nome = "Casa da Sorte";
        else if (casa instanceof CasaPularRodada) nome = "Casa Pula Rodada";
        else if (casa instanceof CasaSurpresa)    nome = "Casa Surpresa";
        else if (casa instanceof CasaDaDiscordia) nome = "Casa da Discórdia";
        else if (casa instanceof CasaMagica)      nome = "Casa Mágica";
        else                                      nome = "Casa Neutra";
        return nome + " (casa " + casa.getPosicao() + ")";
    }

    private String descricaoDaCasa(Casa casa) {
        if (casa instanceof CasaDaSorte)
            return "Ande 3 casas para frente! (Jogadores azarados não ganham o bônus.)";
        if (casa instanceof CasaPularRodada)
            return "O jogador fica sem jogar na próxima rodada.";
        if (casa instanceof CasaSurpresa)
            return "Sorteia um novo tipo para o jogador: azarado, sortudo ou normal.";
        if (casa instanceof CasaDaDiscordia)
            return "Escolha um adversário para voltar para o início do jogo (casa 1).";
        if (casa instanceof CasaMagica)
            return "O jogador troca de posição com o jogador que está por último.";
        return "Casa neutra: nada acontece.";
    }

    //compara as posições antes/depois do efeito e lista quem mudou de casa.
    private String descreverMudancas(Map<Cor, Integer> antes) {
        StringBuilder sb = new StringBuilder();
        for (Jogador j : Jogo.getJogadores()) {
            Integer posAntes = antes.get(j.getCor());
            if (posAntes != null && posAntes != j.getPosicao()) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(j.getNome()).append(": casa ").append(posAntes)
                  .append(" \u2192 casa ").append(Math.min(j.getPosicao(), TOTAL_CASAS));
            }
        }
        return sb.toString();
    }

    
    private String nomeDoTipo(Jogador j) {
        if (j instanceof JogadorAzarado) return "azarado";
        if (j instanceof JogadorSortudo) return "sortudo";
        return "normal";
    }

    
    private String paraHex(Color c) {
        return String.format("#%02X%02X%02X",
            (int) Math.round(c.getRed() * 255),
            (int) Math.round(c.getGreen() * 255),
            (int) Math.round(c.getBlue() * 255));
    }

    //muda a cor conforme o brilho pro texto não sumir
    private String corDoTexto(Color fundo) {
        double brilho = 0.299 * fundo.getRed() + 0.587 * fundo.getGreen() + 0.114 * fundo.getBlue();
        return brilho > 0.6 ? "black" : "white";
    }

 
    public void atualizarPosicoes() {
        tokensPorCasa.values().forEach(fp -> fp.getChildren().clear());

      
        for (Jogador jogador : Jogo.getJogadores()) {
            int posicao = jogador.getPosicao();
            if (posicao < 1 || posicao > TOTAL_CASAS) continue;

            FlowPane tokens = tokensPorCasa.get(posicao);
            if (tokens != null) {
                Circle token = new Circle(8);
                token.setFill(converterCor(jogador.getCor()));
                token.setStroke(Color.BLACK);
                token.setStrokeWidth(1);
                tokens.getChildren().add(token);
            }
        }
    }

    private Color converterCor(Cor cor) {
        String tipo = cor.getTipoCor().toLowerCase();
        return switch (tipo) {
            case "vermelho" -> Color.RED;
            case "azul"     -> Color.ROYALBLUE;
            case "verde"    -> Color.FORESTGREEN;
            case "amarelo"  -> Color.GOLD;
                //arrumar a cor do branco e preto
            case "branco"   -> Color.WHITE;
            case "preto"    -> Color.BLACK;
            case "roxo"     -> Color.MEDIUMPURPLE;
            case "laranja"  -> Color.DARKORANGE;
            case "rosa"     -> Color.HOTPINK;
            default         -> Color.GRAY;
        };
    }
}