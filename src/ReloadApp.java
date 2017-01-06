import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Filip on 06.01.2017.
 */

/**
 * https://stackoverflow.com/questions/35858903/how-to-reload-an-application-in-javafx
 *
 * @author okr
 * @date 08.03.2016
 *
 */
public class ReloadApp extends Application {
    boolean state = true;

    @Override
    public void start(final Stage primaryStage) {
        System.out.println("state is " + state);
        playGame();
        System.out.println("state is " + state);

        final Button restartButton = new Button("Restart");
        restartButton.setOnAction(__ ->
        {
            System.out.println("Restarting app!");
            primaryStage.close();
            Platform.runLater(() -> new ReloadApp().start(new Stage()));
        });
        primaryStage.setScene(new Scene(new StackPane(restartButton)));
        primaryStage.show();
    }

    /**
     * Simulate a game play by changing the global state.
     */

    private void playGame() {
        state = false;
    }
}