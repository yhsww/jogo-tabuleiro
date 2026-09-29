package src;
 
import javafx.application.Application;
import javafx.stage.Stage;
import src.view.Navegadordetelas;
import src.view.TelaMenu;
 
public class Main extends Application {
 
    @Override
    public void start(Stage stage) {
        Navegadordetelas.inicializar(stage);
        stage.setTitle("Jogo de Tabuleiro");
        Navegadordetelas.irPara(new TelaMenu());
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
 