package nsu.chebotareva.task_2_3_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SnakeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(SnakeApplication.class.getResource("/nsu/chebotareva/task_2_3_1/hello-view.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}