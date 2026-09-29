package src.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navegadordetelas {

    private static Stage stagePrincipal;

    private Navegadordetelas() {
    }

    public static void inicializar(Stage stage) {
        stagePrincipal = stage;
    }

    public static void irPara(Tela tela) {
        if (stagePrincipal == null) {
            throw new IllegalStateException(
                "NavegadorDeTelas não foi inicializado. Chame inicializar(stage) primeiro.");
        }
        Scene scene = new Scene(tela.getView());
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }
}